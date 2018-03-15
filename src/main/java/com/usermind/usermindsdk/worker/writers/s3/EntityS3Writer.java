package com.usermind.usermindsdk.worker.writers.s3;

import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressEventType;
import com.amazonaws.event.ProgressListener;
import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SSEAwsKeyManagementParams;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerConfiguration;
import com.amazonaws.services.s3.transfer.TransferProgress;
import com.amazonaws.services.s3.transfer.Upload;
import com.google.common.base.Stopwatch;
import com.google.common.base.Throwables;
import com.google.common.collect.Sets;
import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.metrics.MetricsCollectorClient;
import com.usermind.usermindsdk.common.metrics.reporter.MetricsReporter;
import com.usermind.usermindsdk.common.support.dry.aws.AwsServiceClientBuilder;
import com.usermind.usermindsdk.worker.writers.EntityWriter;
import com.usermind.usermindsdk.worker.base.IntegrationApiConnector;
import com.usermind.usermindsdk.worker.base.Waves;
import com.usermind.usermindsdk.worker.normalization.NormalizedEntity;
import com.usermind.usermindsdk.worker.normalization.metadata.EntityMetadataMerger;
import com.usermind.usermindsdk.worker.util.ConfigurationValidator;
import com.usermind.usermindsdk.worker.writers.EntityWriter;
import com.usermind.usermindsdk.worker.writers.WriterType;
import com.usermind.usermindsdk.worker.writers.exception.EntityWriterException;
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
import static com.usermind.usermindsdk.common.config.ConfigurationBuilder.getEmpty;
import static com.usermind.usermindsdk.worker.writers.s3.ChunkType.ACTIONMETADATA;
import static com.usermind.usermindsdk.worker.writers.s3.ChunkType.METADATA;
import static com.usermind.usermindsdk.worker.writers.s3.ChunkType.NORMALIZED;
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

  private final String bucketName;
  private final String objectKeyPrefix;
  private final Optional<String> kmsKeyId;
  private final int maxRecordsInChunk;
  private final long maxChunkSizeBytes;
  private final int maxUploadsInParallel;
  private final long progressLoggingPeriodMillis;
  private final boolean useCompression;
  private final Context context;
  private final ActionMetadataHelper actionMetadataHelper;
  private final TransferManager transferManager;
  // Active chunks - entity name to the map of chunk type - local chunk
  private final Map<String, Map<ChunkType, LocalChunk>> activeChunks;
  // For each entity chunk the sequence number to continue from, see more in {@code Builder} docs
  private final Map<String, Map<ChunkType, Integer>> checkpoints;
  private final Consumer<Map<String, Map<ChunkType, Integer>>> onCloseCheckpointsConsumer;
  private final Set<Upload> activeUploads;
  private final Set<Upload> failedUploads;
  private final boolean encryptLocalChunks;

  private boolean closed;

  private EntityS3Writer(TransferManager transferManager, IntegrationApiConnector apiConnector,
                         MetricsReporter<MetricsCollectorClient> metricsReporter, Configuration config,
                         Map<String, Map<ChunkType, Integer>> checkpoints,
                         Consumer<Map<String, Map<ChunkType, Integer>>> onCloseCheckpointsConsumer,
                         boolean encryptLocalChunks) {

    super(metricsReporter, new EntityMetadataMerger(metricsReporter));

    this.bucketName = config.getString("bucket");
    this.objectKeyPrefix = config.getString("objectKeyPrefix");
    this.kmsKeyId = config.getStringAsOptional("kmsKeyId");
    this.maxRecordsInChunk = config.getInt("maxRecordsInChunk", 20_000);
    this.maxChunkSizeBytes = config.getLong("maxChunkSizeBytes", 5_000_000_000L);
    this.useCompression = config.getBoolean("useCompression", true);
    // 10 is used because it's a default thread pool size used by {@code TransferManager}
    this.maxUploadsInParallel = config.getInt("maxUploadsInParallel", 10);
    this.progressLoggingPeriodMillis = config.getLong("progressLoggingPeriodMillis", 1000);

    this.transferManager = checkNotNull(transferManager);
    this.context = new Context(apiConnector);

    this.actionMetadataHelper = new ActionMetadataHelper(apiConnector);

    this.activeChunks = new HashMap<>();
    this.checkpoints = new HashMap<>(checkpoints); // We better copy it

    this.onCloseCheckpointsConsumer = checkNotNull(onCloseCheckpointsConsumer);

    this.encryptLocalChunks = encryptLocalChunks;

    // For this case we are ok with the default equals/hashCode
    this.activeUploads = Sets.newConcurrentHashSet();
    this.failedUploads = Sets.newConcurrentHashSet();

    this.closed = false;
  }

  @Override
  public void writeEntity(NormalizedEntity entity) {
    checkNotNull(entity);
    checkState(!closed, "Attempting to write entity after the writer is closed");
    checkFailedUploads();

    String entityName = entity.getType();

    if (!entity.isExtracted()) {
      resolveLocalChunk(entityName, ChunkType.RAW).add(entity.getRaw());
    }

    resolveLocalChunk(entityName, NORMALIZED).add(entity.getEntity());

    metadataMerger.merge(entity);
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
    String runId = context.getRunId();

    Map<ChunkType, LocalChunk> entityChunks = activeChunks.computeIfAbsent(entityName,
        entityNameKey -> new EnumMap<>(ChunkType.class));

    LocalChunk entityChunk = entityChunks.computeIfAbsent(chunkType, chunkTypeKey ->
        new LocalChunk(entityName, runId, getStartSequenceNumber(entityName, chunkType),
            chunkTypeKey, useCompression, encryptLocalChunks));

    if (entityChunk.getRecordsCount() < maxRecordsInChunk
        && entityChunk.getBytesCount() < maxChunkSizeBytes) {

      // The existing chunk can be used
      return entityChunk;
    }

    entityChunk.close();
    uploadLocalChunk(entityChunk);

    // Creating the new local chunk with sequence number +1
    return entityChunks.compute(chunkType, (chunkTypeKey, closedChunk) ->
        new LocalChunk(entityName, runId, closedChunk.getSequenceNumber() + 1, chunkTypeKey,
            useCompression, encryptLocalChunks));
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
    if (activeUploads.size() >= maxUploadsInParallel) {
      LOGGER.debug("Exceeded the limit ({}) for max uploads in parallel, waiting for the \"free "
          + "parking lot\"", maxUploadsInParallel);

      // We better do it in the loop as the {@code activeUploads} can empty at some point
      waitForUploadsCompletion(1);
    }

    Stopwatch uploadTimeWatch = Stopwatch.createStarted();
    LOGGER.info("Uploading the local chunk: {}", localChunk);
    MetricsReporter<MetricsCollectorClient> metricsReporter = getMetricsReporter();
    metricsReporter.increment("writer.s3.uploadStarted", "bucket", bucketName);

    String entityName = localChunk.getEntityName();
    ChunkType chunkType = localChunk.getChunkType();

    String objectKey = buildObjectKey(localChunk.getSequenceNumber(), chunkType, entityName,
        useCompression);

    LOGGER.debug("Computed the object key: {}", objectKey);

    PutObjectRequest putObjectRequest;
    if (encryptLocalChunks) {
      ObjectMetadata metadata = new ObjectMetadata();
      metadata.setContentLength(localChunk.getUnencryptedFileSize());
      putObjectRequest = new PutObjectRequest(bucketName, objectKey, localChunk.getInputStream(),
          metadata);
    } else {
      File localChunkFile = localChunk.getFile().toFile();
      putObjectRequest = new PutObjectRequest(bucketName, objectKey, localChunkFile);
    }

    if (kmsKeyId.isPresent()) {
      // Will result in using the server-side KMS-based encryption
      SSEAwsKeyManagementParams keyManagementParams = new SSEAwsKeyManagementParams(kmsKeyId.get());
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
            if (currentTimeMillis >= lastProgressLoggedAtMillis + progressLoggingPeriodMillis) {
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
        metricsReporter.histogram("writer.s3.uploadedChunkSizeBytes",
            localChunk.getBytesCount(), "entity", entityName, "type", chunkType,
            "bucket", bucketName);
        metricsReporter.histogram("writer.s3.uploadedChunkRecordsCount",
            localChunk.getRecordsCount(), "entity", entityName, "type", chunkType, "bucket",
            bucketName);

        long uploadTimeMillis = uploadTimeWatch.elapsed(TimeUnit.MILLISECONDS);
        metricsReporter.histogram("writer.s3.uploadTimeMillis", uploadTimeMillis, "entity",
            entityName, "type", chunkType, "bucket", bucketName);
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
        metricsReporter.increment("writer.s3.transferEvent", "event", eventType);
      }
    });

    // Logging active uploads count
    int activeUploadsCount = activeUploads.size();
    LOGGER.debug("{} active uploads", activeUploadsCount);
    metricsReporter.histogram("writer.s3.activeUploads", activeUploadsCount, "bucket", bucketName,
        "integration", context.getIntegrationName());
  }

  private String buildObjectKey(int sequenceNumber, ChunkType chunkType, String entityName,
      boolean useCompression) {

    // The result object key is going to have the following format:
    // integration/connection/{legacy_conn_id}/normalized/{run_created_at_millis}
    // /{run_created_at_millis}-{org_id}-{integration_name}-{entity_name}.0.{gz or json}
    List<String> objectKeyParts = new ArrayList<>();
    objectKeyParts.add(objectKeyPrefix.endsWith(S3_KEY_SEPARATOR)
        ? StringUtils.chop(objectKeyPrefix) : objectKeyPrefix);

    objectKeyParts.add(context.getLegacyConnectionId());
    objectKeyParts.add(chunkType == NORMALIZED || chunkType == METADATA ||
          chunkType == ACTIONMETADATA
        ? NORMALIZED.toString() : chunkType.toString());

    objectKeyParts.add(String.valueOf(context.getWaveTimestamp()));

    if (chunkType == METADATA || chunkType == ACTIONMETADATA) {
      objectKeyParts.add(chunkType.toString());
    }

    String objectExtension = sequenceNumber + "." + (useCompression ? "gz" : "json");
    String objectName = String.join("-", String.valueOf(context.getWaveTimestamp()),
        context.getOrgId(), context.getIntegrationName(), entityName + "." + objectExtension);

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
      flushMetadata();

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
    getMetricsReporter().histogram("writer.s3.closeTimeMillis", closeTimeMillis,
        "bucket", bucketName);
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

  private void flushMetadata() {
    LOGGER.debug("Flushing merged metadata");
    // Metadata merger holds a map with the entry for each entity type
    metadataMerger.getMergedMetadata().forEach((entityName, metadata) ->
          resolveLocalChunk(entityName, METADATA).add(metadata));

    if (actionMetadataHelper.hasActionMetadata()) {
      resolveLocalChunk("Actions", ACTIONMETADATA)
            .add(actionMetadataHelper.getActionMetadata());
    }
  }

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
   * Writer context  - provides all needed info about run, connection and so on.
   */
  private static class Context {

    private final String integrationName;
    private final String runId;
    private final long waveTimestamp;
    private final String legacyConnectionId;
    private final String orgId;

    /**
     * {@code Context} ctor. Validates the "writer.type" integration config property.
     * @param apiConnector Integration-API connector
     */
    Context(IntegrationApiConnector apiConnector) {
      checkNotNull(apiConnector);

      LOGGER.debug("Building the S3 writer context");
      checkWriterType(apiConnector);

      this.integrationName = apiConnector.getCachedIntegrationType().getString("integration_type");

      Configuration run = apiConnector.getCachedRun();
      this.runId = run.getString("id");
      this.waveTimestamp = Waves.getWaveTimestamp(apiConnector);

      Configuration connection = apiConnector.getCachedConnection();
      this.legacyConnectionId = connection.getString("legacy_connection_id");
      this.orgId = connection.getString("org_id");
    }

    private void checkWriterType(IntegrationApiConnector apiConnector) {
      Optional<WriterType> writerType =
          apiConnector.getCachedIntegrationType().atPath("configuration")
          .merge(apiConnector.getCachedIntegration().atPath("configuration"))
          .getStringAsOptional("writer.type")
          .flatMap(WriterType::fromValue);

      checkState(writerType.isPresent() && writerType.get() == WriterType.S3,
          "Integration or integration type must be configured to use S3 writer");
    }

    public String getIntegrationName() {
      return integrationName;
    }

    public String getRunId() {
      return runId;
    }

    public long getWaveTimestamp() {
      return waveTimestamp;
    }

    public String getLegacyConnectionId() {
      return legacyConnectionId;
    }

    public String getOrgId() {
      return orgId;
    }
  }

  /**
   * Action metadata extractor - provides action metadata configuration.
   */
  private static class ActionMetadataHelper {

    private final Configuration actionMetadata;

    ActionMetadataHelper(IntegrationApiConnector apiConnector) {

      LOGGER.debug("Building the S3 writer context");
      Configuration dynamicActions = apiConnector.getConnectionState()
            .atPath(Configuration.buildFullPath("state", "dynamic.config.actions"), getEmpty());
      Configuration staticActions = apiConnector.getCachedIntegration()
            .atPath(Configuration.buildFullPath("configuration", "actions"), getEmpty());

      this.actionMetadata = dynamicActions.merge(staticActions);
    }

    public Configuration getActionMetadata() { return actionMetadata;}

    public  Boolean hasActionMetadata() { return !(this.actionMetadata.isEmpty()); }

  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * {@code EntityS3Writer} builder.
   */
  public static class Builder {

    private static final ConfigurationValidator CONFIG_VALIDATOR = createConfigValidator();
    private static final int DEFAULT_MAX_RETRIES = 10;

    private IntegrationApiConnector apiConnector;
    private MetricsReporter<MetricsCollectorClient> metricsReporter;
    private Optional<TransferManager> transferManager = Optional.empty();
    private Optional<String> writerConfigPath = Optional.empty();
    private Optional<Configuration> writerConfig = Optional.empty();
    private Map<String, Map<ChunkType, Integer>> checkpoints = emptyMap();
    private Consumer<Map<String, Map<ChunkType, Integer>>> onCloseCheckpointsConsumer;
    private boolean encryptLocalChunks = true;

    /**
     * Integration-API connector.
     * @param apiConnector API connector
     * @return this
     */
    public Builder setApiConnector(IntegrationApiConnector apiConnector) {
      this.apiConnector = checkNotNull(apiConnector);
      return this;
    }

    /**
     * Metrics reporter.
     * @param metricsReporter metrics reporter
     * @return this
     */
    public Builder setMetricsReporter(MetricsReporter<MetricsCollectorClient> metricsReporter) {
      this.metricsReporter = checkNotNull(metricsReporter);
      return this;
    }

    /**
     * Override the default transfer manager.
     * @param transferManager transfer manager
     * @return this
     */
    public Builder setTransferManager(TransferManager transferManager) {
      this.transferManager = Optional.ofNullable(transferManager);
      return this;
    }

    /**
     * Path to the writer config inside of a CommonLib config.
     * @param writerConfigPath path to the writer config, e.g. "integrationsWorker.s3Writer"
     * @return this
     */
    public Builder setWriterConfigPath(String writerConfigPath) {
      this.writerConfigPath = Optional.ofNullable(writerConfigPath);
      return this;
    }

    /**
     * An explicit writer config.
     * @param writerConfig writer config
     * @return this
     */
    public Builder setWriterConfig(Configuration writerConfig) {
      this.writerConfig = Optional.ofNullable(writerConfig);
      return this;
    }

    /**
     * Each uploaded chunk has a sequence number which gets incremented per an entity type
     * in context of the same run. Checkpoints allow to persist these sequence numbers between
     * S3 writer usages per a single run. The idea behind this is that you can collect S3 writer
     * checkpoints passing the on-close checkpoints consumer and use them next time
     * passing into S3 writer builder. *Note* that you already have two implementations you can use
     * depending on your case - {@code OnCloseDefaultConsumer} which allows to access consumed
     * checkpoints and {@code OnCloseNopConsumer} which can be used in case when you are using
     * a single S3 writer instance per run.
     * @param checkpoints checkpoints - chunk sequence number per each entity chunk
     * @return this
     */
    public Builder setCheckpoints(Map<String, Map<ChunkType, Integer>> checkpoints) {
      this.checkpoints = checkNotNull(checkpoints);
      return this;
    }

    /**
     * Each uploaded chunk has a sequence number which gets incremented per an entity type
     * in context of the same run. Checkpoints allow to persist these sequence numbers between
     * S3 writer usages per a single run. The idea behind this is that you can collect S3 writer
     * checkpoints passing the on-close checkpoints consumer and use them next time
     * passing into S3 writer builder. *Note* that you already have two implementations you can use
     * depending on your case - {@code OnCloseDefaultConsumer} which allows to access consumed
     * checkpoints and {@code OnCloseNopConsumer} which can be used in case when you are using
     * a single S3 writer instance per run.
     * @param onCloseCheckpointsConsumer checkpoints consumer
     * @return this
     */
    public Builder setOnCloseCheckpointsConsumer(
        Consumer<Map<String, Map<ChunkType, Integer>>> onCloseCheckpointsConsumer) {
      this.onCloseCheckpointsConsumer = checkNotNull(onCloseCheckpointsConsumer);
      return this;
    }

    /**
     * Specifies whether local chunks must be encrypted as they are stored in temp files on disk
     * before being uploaded to S3. By default this value is set to true.
     * Turning encryption off may improve upload throughput in case if chunk file is larger than
     * 16 MB {@code TransferManagerConfiguration#DEFAULT_MULTIPART_UPLOAD_THRESHOLD} because chunk
     * will be split into parts and uploaded in parallel.
     *
     * @param encryptLocalChunks encryptLocalChunks flag
     * @return this
     */
    public Builder setEncryptLocalChunks(boolean encryptLocalChunks) {
      this.encryptLocalChunks = encryptLocalChunks;
      return this;
    }

    /**
     * Builds a new {@code Builder}.
     * @return {@code Builder}
     * @throws IllegalStateException on illegal builder state
     */
    public EntityS3Writer build() {
      LOGGER.debug("Building the S3 writer");

      checkState(writerConfigPath.isPresent() ^ writerConfig.isPresent(),
          "You must set either an explicit writer config or a writer config path");
      checkState(onCloseCheckpointsConsumer != null, "Checkpoints on-close consumer must be set");

      Configuration writerResolvedConfig = writerConfig.orElseGet(() ->
          CommonLib.get().getConfiguration().atPath(writerConfigPath.get()));

      CONFIG_VALIDATOR.validate(writerResolvedConfig);

      TransferManager resolvedTransferManager = transferManager.orElseGet(() ->
          createTransferManager(writerResolvedConfig));

      return new EntityS3Writer(resolvedTransferManager, apiConnector, metricsReporter,
          writerResolvedConfig, checkpoints, onCloseCheckpointsConsumer, encryptLocalChunks);
    }

    private TransferManager createTransferManager(Configuration writerConfig) {
      int maxRetriesOnError = writerConfig.getInt("maxRetriesOnError", DEFAULT_MAX_RETRIES);
      RetryPolicy retryPolicy =  new RetryPolicy(null, null, maxRetriesOnError, true);
      AmazonS3 s3Client = new AwsServiceClientBuilder(writerConfig, retryPolicy).getS3Client();

      // TODO for the future: configurable thread pool size, by default it's 10
      TransferManager transferManager = new TransferManager(s3Client);

      TransferManagerConfiguration transferConfiguration = new TransferManagerConfiguration();
      writerConfig.getLongAsOptional("minimumUploadPartSize")
          .ifPresent(transferConfiguration::setMinimumUploadPartSize);
      writerConfig.getLongAsOptional("multipartUploadThreshold")
          .ifPresent(transferConfiguration::setMultipartUploadThreshold);

      transferManager.setConfiguration(transferConfiguration);

      return transferManager;
    }

    private static ConfigurationValidator createConfigValidator() {
      int kb = 1024;
      int mb = 1024 * kb;
      int second = 1000;

      return ConfigurationValidator.builder()
          .required("bucket").notEmpty("bucket")
          .required("objectKeyPrefix").notEmpty("objectKeyPrefix")
          .notEmpty("kmsKeyId")
          .isNumber("maxRetriesOnError").inRange("maxRetriesOnError", 1, 10)
          .isNumber("maxRecordsInChunk").inRange("maxRecordsInChunk", 1, 1000_000)
          .isNumber("maxChunkSizeBytes").min("maxChunkSizeBytes", 100 * kb)
          .isNumber("maxUploadsInParallel").inRange("maxUploadsInParallel", 1, 50)
          .isNumber("progressLoggingPeriodMillis").inRange("progressLoggingPeriodMillis", 100,
              60 * second)
          .isBoolean("useCompression")
          .isNumber("minimumUploadPartSize").min("minimumUploadPartSize", 5 * mb)
          .isNumber("multipartUploadThreshold").min("multipartUploadThreshold", 16 * mb)
          .build();
    }
  }
}
