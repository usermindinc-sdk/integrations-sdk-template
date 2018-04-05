package com.usermind.usermindsdk.baselib.writers.s3;

import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressEventType;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SSEAwsKeyManagementParams;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferProgress;
import com.amazonaws.services.s3.transfer.Upload;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.collect.Sets;
import com.usermind.usermindsdk.baselib.dataReaders.RunPoller;
import com.usermind.usermindsdk.baselib.dataReaders.WorkerInfo;
import com.usermind.usermindsdk.baselib.writers.EntityWriter;
import com.usermind.usermindsdk.baselib.writers.exception.EntityWriterException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Collections.emptyMap;
import static java.util.stream.Collectors.toList;

/**
 * Entity writer S3 implementation. Under the hood it uses a {@code TransferManager} which uploads
 * data asynchronously making decision on whether to use a multipart API. In addition to the
 * {@code TransferManager} chunking when it uses a multipart API this implementation also makes
 * chunks of records limited by either records count or size in bytes. Also this implementation
 * allows to compress the output using the GZIP - by default this feature is switched on.
 * After all entities have been written, entity writer must be closed to flush all internal
 * buffers.
 */
public class EntityS3Writer extends EntityWriter {

  private static final Logger LOGGER = LoggerFactory.getLogger(EntityS3Writer.class);

  static final String S3_KEY_SEPARATOR = "/";

  private static final String CHUNK_CLOSE_ERROR_FORMAT = "[%s] %s";
  private static final String CHUNKS_CLOSE_ERROR_FORMAT = "Failed to close %d local chunks due "
          + "to errors: %s; cause initialized with the last thrown exception";
  private static final String FAILED_UPLOAD_ERROR_FORMAT =
          "Upload '%s' has failed with the status '%s'";

  private final TransferManager transferManager;
  // Active chunks - entity name to the map of chunk type - local chunk
  private final Map<String, Map<ChunkType, LocalChunk>> activeChunks;
  // For each entity chunk the sequence number to continue from, see more in {@code Builder} docs
  private final Map<String, Map<ChunkType, Integer>> checkpoints;
  private final Consumer<Map<String, Map<ChunkType, Integer>>> onCloseCheckpointsConsumer;
  private final Set<Upload> activeUploads;
  private final Set<Upload> failedUploads;
  private final S3Config s3Config;
  private final WorkerInfo workerInfo;
  private final RunPoller runPoller;

  private boolean closed;

  public EntityS3Writer(WorkerInfo workerInfo,
                        TransferManager transferManager, RunPoller runPoller,
                        S3Config s3Config,
                        Map<String, Map<ChunkType, Integer>> checkpoints,
                        Consumer<Map<String, Map<ChunkType, Integer>>> onCloseCheckpointsConsumer) {


    checkNotNull(workerInfo);
    checkNotNull(transferManager);
    checkNotNull(runPoller);
    checkNotNull(s3Config);
    checkNotNull(checkpoints);
    checkNotNull(onCloseCheckpointsConsumer);

    this.workerInfo = workerInfo;
    this.transferManager = transferManager;
    this.s3Config = s3Config;
    this.activeChunks = new HashMap<>();
    this.checkpoints = new HashMap<>(checkpoints); // We better copy it
    this.runPoller = runPoller;

    this.onCloseCheckpointsConsumer = checkNotNull(onCloseCheckpointsConsumer);

    // For this case we are ok with the default equals/hashCode
    this.activeUploads = Sets.newConcurrentHashSet();
    this.failedUploads = Sets.newConcurrentHashSet();

    this.closed = false;
  }

  public void writeFile(String fileName, String fileContents) {
    resolveLocalChunk(fileName, ChunkType.RAW).add(fileContents);
  }

  @Override
  public void writeEntity(String entity) {
    checkNotNull(entity);
    checkState(!closed, "Attempting to write entity after the writer is closed");
    checkFailedUploads();

    //TODO - entities? Strings?
//    if (!entity.isExtracted()) {
//      resolveLocalChunk(workerInfo.getWorkerType(), ChunkType.RAW).add(entity.getRaw());
//    }
//
//    resolveLocalChunk(workerInfo.getWorkerType(), NORMALIZED).add(entity.getEntity());
//
//    metadataMerger.merge(entity);
  }

  private void checkFailedUploads() {
    if (!failedUploads.isEmpty()) {
      String errorMessage = failedUploads.stream()
              .map(upload -> String.format(FAILED_UPLOAD_ERROR_FORMAT, upload.getDescription(),
                      upload.getState()))
              .collect(Collectors.joining("; "));
      throw new EntityWriterException("Some uploads have failed: " + errorMessage);
    }
  }

  private LocalChunk resolveLocalChunk(String entityName, ChunkType chunkType) {
    String runId = runPoller.getRunId();

    Map<ChunkType, LocalChunk> entityChunks = activeChunks.computeIfAbsent(entityName,
            entityNameKey -> new EnumMap<>(ChunkType.class));

    LocalChunk entityChunk = entityChunks.computeIfAbsent(chunkType, chunkTypeKey ->
            new LocalChunk(entityName, runId, getStartSequenceNumber(entityName, chunkType),
                    chunkTypeKey, s3Config.getUseCompression(), s3Config.getEncryptLocalChunks()));

    if (entityChunk.getRecordsCount() < s3Config.getMaxRecordsInChunk()
            && entityChunk.getBytesCount() < s3Config.getMaxChunkSizeBytes()) {

      // The existing chunk can be used
      return entityChunk;
    }

    entityChunk.close();
    uploadLocalChunk(entityChunk);

    // Creating the new local chunk with sequence number +1
    return entityChunks.compute(chunkType, (chunkTypeKey, closedChunk) ->
            new LocalChunk(entityName, runId, closedChunk.getSequenceNumber() + 1, chunkTypeKey,
                    s3Config.getUseCompression(), s3Config.getEncryptLocalChunks()));
  }

  /**
   * Resolves a start sequence number from checkpoints falling-back to 0.
   * @param entityName entity name
   * @param chunkType chunk type
   * @return start sequence number
   */
  private int getStartSequenceNumber(String entityName, ChunkType chunkType) {
    Optional<Integer> checkpointSequenceNumber =
            Optional.ofNullable(checkpoints.getOrDefault(entityName, emptyMap()).get(chunkType));

    return checkpointSequenceNumber.map(sequenceNumber -> sequenceNumber + 1).orElse(0);
  }

  private void uploadLocalChunk(LocalChunk localChunk) {
    // Wait for active chunks completion if {@code maxUploadsInParallel} is exceeded
    if (activeUploads.size() >= s3Config.getMaxUploadsInParallel()) {
      LOGGER.debug("Exceeded the limit ({}) for max uploads in parallel, waiting for the \"free "
              + "parking lot\"", s3Config.getMaxUploadsInParallel());

      // We better do it in the loop as the {@code activeUploads} can empty at some point
      waitForUploadsCompletion(1);
    }

    Stopwatch uploadTimeWatch = Stopwatch.createStarted();
    LOGGER.info("Uploading the local chunk: {}", localChunk);

    String entityName = localChunk.getEntityName();
    ChunkType chunkType = localChunk.getChunkType();

    String objectKey = buildObjectKey(localChunk.getSequenceNumber(), chunkType, entityName,
            s3Config.getUseCompression());

    LOGGER.debug("Computed the object key: {}", objectKey);

    PutObjectRequest putObjectRequest;
    if (s3Config.getEncryptLocalChunks()) {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(localChunk.getUnencryptedFileSize());
      putObjectRequest = new PutObjectRequest(s3Config.getBucket(), objectKey, localChunk.getInputStream(),
              metadata);
    } else {
      File localChunkFile = localChunk.getFile().toFile();
      putObjectRequest = new PutObjectRequest(s3Config.getBucket(), objectKey, localChunkFile);
    }

    if (!StringUtils.isEmpty(s3Config.getKmsKeyId())) {
      // Will result in using the server-side KMS-based encryption
      SSEAwsKeyManagementParams keyManagementParams = new SSEAwsKeyManagementParams(s3Config.getKmsKeyId());
      putObjectRequest.setSSEAwsKeyManagementParams(keyManagementParams);
    }

    Upload upload = transferManager.upload(putObjectRequest);
    activeUploads.add(upload);
    upload.addProgressListener(new ProgressListener() {

      private long lastProgressLoggedAtMillis = 0;

      @Override
      public void progressChanged(ProgressEvent progressEvent) {
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
          case TRANSFER_COMPLETED_EVENT:
            completeEvent(eventType);
            break;

          case TRANSFER_CANCELED_EVENT:
          case TRANSFER_FAILED_EVENT:
            failEvent(eventType);
            break;

          case TRANSFER_STARTED_EVENT:
            trackEvent(eventType);
            break;

          default:
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis >= lastProgressLoggedAtMillis + s3Config.getProgressLoggingPeriodMillis()) {
              logProgress();
            }
        }
      }

      private void logProgress() {
        TransferProgress progress = upload.getProgress();
        LOGGER.info("Transfer: {}; State: {}; Progress: bytes {} from total {}, pct. {}%",
                upload.getDescription(), upload.getState(), progress.getBytesTransferred(),
                progress.getTotalBytesToTransfer(), progress.getPercentTransferred());
        lastProgressLoggedAtMillis = System.currentTimeMillis();
      }

      private void completeEvent(ProgressEventType eventType) {
//        metricsReporter.histogram("writer.s3.uploadedChunkSizeBytes",
//                localChunk.getBytesCount(), "entity", entityName, "type", chunkType,
//                "bucket", s3Config.getBucket());
//        metricsReporter.histogram("writer.s3.uploadedChunkRecordsCount",
//                localChunk.getRecordsCount(), "entity", entityName, "type", chunkType, "bucket",
//                s3Config.getBucket());

        long uploadTimeMillis = uploadTimeWatch.elapsed(TimeUnit.MILLISECONDS);
//        metricsReporter.histogram("writer.s3.uploadTimeMillis", uploadTimeMillis, "entity",
//                entityName, "type", chunkType, "bucket", s3Config.getBucket());
        LOGGER.debug("'{}' upload took {} millis", upload.getDescription(), uploadTimeMillis);
        cleanupEvent(eventType);
      }

      private void failEvent(ProgressEventType eventType) {
        failedUploads.add(upload);
        cleanupEvent(eventType);
      }

      private void cleanupEvent(ProgressEventType eventType) {
        activeUploads.remove(upload); // Removing by ref
        localChunk.delete();
        trackEvent(eventType);
      }

      private void trackEvent(ProgressEventType eventType) {
        logProgress();

        LOGGER.info("Got transfer event '{}' for upload: {}", eventType,
                upload.getDescription());
//        metricsReporter.increment("writer.s3.transferEvent", "event", eventType);
      }
    });

    // Logging active uploads count
    int activeUploadsCount = activeUploads.size();
    LOGGER.debug("{} active uploads", activeUploadsCount);
//    metricsReporter.histogram("writer.s3.activeUploads", activeUploadsCount, "bucket", s3Config.getBucket(),
//            "integration", workerInfo.getWorkerType());
  }

  //TODO - use string instead of chunktype? Or stick with chunktype and the writer knowing about the different data types?
  private String buildObjectKey(int sequenceNumber, ChunkType chunkType, String entityName,
                                boolean useCompression) {

    // The result object key is going to have the following format:
    // integration/connection/{legacy_conn_id}/normalized/{run_created_at_millis}
    // /{run_created_at_millis}-{org_id}-{integration_name}-{entity_name}.0.{gz or json}
    List<String> objectKeyParts = new ArrayList<>();
    objectKeyParts.add(s3Config.getObjectKeyPrefix().endsWith(S3_KEY_SEPARATOR)
            ? StringUtils.chop(s3Config.getObjectKeyPrefix()) : s3Config.getObjectKeyPrefix());

    objectKeyParts.add(runPoller.getLegacyId());
//    objectKeyParts.add(chunkType == NORMALIZED || chunkType == METADATA ||
//            chunkType == ACTIONMETADATA
//            ? NORMALIZED.toString() : chunkType.toString());

    objectKeyParts.add(String.valueOf(runPoller.getWaveTimestamp()));

//    if (chunkType == METADATA || chunkType == ACTIONMETADATA) {
//      objectKeyParts.add(chunkType.toString());
//    }

    String objectExtension = sequenceNumber + "." + (useCompression ? "gz" : "json");
    String objectName = String.join("-", String.valueOf(runPoller.getWaveTimestamp()),
            runPoller.getOrgId(), workerInfo.getWorkerType(), entityName + "." + objectExtension);

    objectKeyParts.add(objectName);

    return String.join(S3_KEY_SEPARATOR, objectKeyParts);
  }

  /**
   * Closes the writer, uploads remaining chunks and waits for their completion. Shuts down
   * the underlying {@code Transfer manager}.
   */
  @Override
  public void close() {
    if (closed) {
      LOGGER.warn("The writer is already closed");
      return;
    }

    LOGGER.debug("Closing the S3 writer");
    Stopwatch closeTimeWatch = Stopwatch.createStarted();
    try {
      // Flushing metadata records
//      flushMetadata();

      // Collecting all local chunks into list
      List<LocalChunk> localChunks = activeChunks.values().stream()
              .flatMap(entityChunks -> entityChunks.values().stream()).collect(toList());

      LOGGER.debug("{} local chunks remain active", localChunks.size());

      // Closing local chunks
      closeLocalChunks(localChunks);

      // Calling checkpoints consumer
      onCloseCheckpointsConsumer.accept(getCheckpoints(activeChunks));

      // Uploading local chunks
      localChunks.forEach(this::uploadLocalChunk);
      LOGGER.debug("Waiting for completion of {} active uploads", activeUploads.size());
      activeUploads.forEach(this::waitForUploadCompletion);

      checkFailedUploads(); // We need to check them exactly at this point
    } catch (Exception ex) {
      LOGGER.error("Failed to close the writer, aborting active uploads", ex);
      activeUploads.forEach(this::abortUploadQuietly);
      throw ex;
    } finally {
      // Shutting down the transfer manager and removing chunks
      try {
        LOGGER.debug("Shutting down the transfer manager");
        transferManager.shutdownNow();
      } finally {
        // We better re-iterate them here
        activeChunks.values().stream().flatMap(entityChunks -> entityChunks.values().stream())
                .forEach(LocalChunk::delete);
        activeChunks.clear();
      }
    }

    long closeTimeMillis = closeTimeWatch.elapsed(TimeUnit.MILLISECONDS);
//    getMetricsReporter().histogram("writer.s3.closeTimeMillis", closeTimeMillis,
//            "bucket", s3Config.getBucket());
    LOGGER.debug("It took {} millis to close the writer", closeTimeMillis);

    closed = true;
  }

  private Map<String, Map<ChunkType, Integer>> getCheckpoints(
          Map<String, Map<ChunkType, LocalChunk>> entityChunks) {

    // Re-mapping Map<String, Map<ChunkType, LocalChunk>> to Map<String, Map<ChunkType, Integer>>
    Map<String, Map<ChunkType, Integer>> checkpoints = new HashMap<>();

    entityChunks.forEach((entity, chunks) ->
            chunks.forEach((type, chunk) ->
                    checkpoints.computeIfAbsent(entity, entityNameKey -> new HashMap<>())
                            .put(type, chunk.getSequenceNumber())));

    return checkpoints;
  }

//  private void flushMetadata() {
//    LOGGER.debug("Flushing merged metadata");
//    // Metadata merger holds a map with the entry for each entity type
//    metadataMerger.getMergedMetadata().forEach((entityName, metadata) ->
//            resolveLocalChunk(entityName, METADATA).add(metadata));
//
//    if (actionMetadataHelper.hasActionMetadata()) {
//      resolveLocalChunk("Actions", ACTIONMETADATA)
//              .add(actionMetadataHelper.getActionMetadata());
//    }
//  }

  /**
   * This method added to give more info in case of failures - it attempts to close all chunks
   * saving all thrown error messages and using the last thrown exception as a cause.
   * @param localChunks local chunks to close
   */
  private void closeLocalChunks(List<LocalChunk> localChunks) {
    LOGGER.debug("Closing {} local chunks", localChunks.size());

    List<String> errorMessages = new ArrayList<>();
    Exception lastThrownException = null;

    for (LocalChunk localChunk : localChunks) {
      try {
        localChunk.close();
      } catch (Exception ex) {
        LOGGER.error("Failed to close the local chunk", ex);
        errorMessages.add(String.format(CHUNK_CLOSE_ERROR_FORMAT, localChunk,
                Throwables.getRootCause(ex).getMessage()));
        lastThrownException = ex;
      }
    }

    if (!errorMessages.isEmpty() && lastThrownException != null) {
      LOGGER.debug("Failed to close {} local chunks", errorMessages.size());
      String errorMessage = String.format(CHUNKS_CLOSE_ERROR_FORMAT, errorMessages.size(),
              String.join(";", errorMessages));
      throw new EntityWriterException(errorMessage, lastThrownException);
    }
  }

  private void waitForUploadsCompletion(int uploadsCountToWaitFor) {
    int completionCounter = 0;
    for (Upload upload : activeUploads) {
      if (completionCounter >= uploadsCountToWaitFor) {
        break;
      }
      waitForUploadCompletion(upload);
      completionCounter++;
    }
  }

  private void waitForUploadCompletion(Upload upload) {
    try {
      LOGGER.debug("Waiting for upload '{}' completion", upload.getDescription());
      upload.waitForCompletion();
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt(); // Restoring the interrupted status
      throw new RuntimeException("Interrupted while waiting for upload completion", ex);
    }
  }

  private void abortUploadQuietly(Upload upload) {
    try {
      LOGGER.debug("Aborting the upload '{}'", upload.getDescription());
      upload.abort();
    } catch (Exception ex) {
      LOGGER.error("Failed to abort the upload", ex);
    }
  }



  /**
   * Action metadata extractor - provides action metadata configuration.
   */
//  private static class ActionMetadataHelper {
//
//    private final Configuration actionMetadata;
//
//    ActionMetadataHelper(IntegrationApiConnector apiConnector) {
//
//      LOGGER.debug("Building the S3 writer context");
//      Configuration dynamicActions = apiConnector.getConnectionState()
//              .atPath(Configuration.buildFullPath("state", "dynamic.config.actions"), getEmpty());
//      Configuration staticActions = apiConnector.getCachedIntegration()
//              .atPath(Configuration.buildFullPath("configuration", "actions"), getEmpty());
//
//      this.actionMetadata = dynamicActions.merge(staticActions);
//    }
//
//    public Configuration getActionMetadata() { return actionMetadata;}
//
//    public  Boolean hasActionMetadata() { return !(this.actionMetadata.isEmpty()); }
//
//  }


}
