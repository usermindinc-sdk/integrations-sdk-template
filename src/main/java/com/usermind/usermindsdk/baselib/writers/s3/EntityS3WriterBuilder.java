package com.usermind.usermindsdk.baselib.writers.s3;

import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerConfiguration;
import com.usermind.usermindsdk.baselib.dataReaders.RunPoller;
import com.usermind.usermindsdk.baselib.dataReaders.WorkerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Collections.emptyMap;

public class EntityS3WriterBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityS3WriterBuilder.class);

    private RunPoller runPoller;
    private WorkerInfo workerInfo;
    private Optional<TransferManager> transferManager = Optional.empty();
    private S3Config s3Config;
    private Map<String, Map<ChunkType, Integer>> checkpoints = emptyMap();
    private Consumer<Map<String, Map<ChunkType, Integer>>> onCloseCheckpointsConsumer;

    public static EntityS3WriterBuilder newBuilder() {
        return new EntityS3WriterBuilder();
    }

    /**
     * Override the default transfer manager.
     * @param transferManager transfer manager
     * @return this
     */
    public EntityS3WriterBuilder setTransferManager(TransferManager transferManager) {
        this.transferManager = Optional.ofNullable(transferManager);
        return this;
    }

    public EntityS3WriterBuilder setRunPoller(RunPoller runPoller) {
        this.runPoller = checkNotNull(runPoller);
        return this;
    }

    public EntityS3WriterBuilder setWorkerInfo(WorkerInfo workerInfo) {
        this.workerInfo = checkNotNull(workerInfo);
        return this;
    }

    public EntityS3WriterBuilder setS3Config(S3Config s3Config) {
        this.s3Config = checkNotNull(s3Config);
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
    public EntityS3WriterBuilder setCheckpoints(Map<String, Map<ChunkType, Integer>> checkpoints) {
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
    public EntityS3WriterBuilder setOnCloseCheckpointsConsumer(
            Consumer<Map<String, Map<ChunkType, Integer>>> onCloseCheckpointsConsumer) {
        this.onCloseCheckpointsConsumer = checkNotNull(onCloseCheckpointsConsumer);
        return this;
    }

    /**
     * Builds a new {@code Builder}.
     * @return {@code Builder}ß
     * @throws IllegalStateException on illegal builder state
     */
    public EntityS3Writer build() {
        LOGGER.debug("Building the S3 writer");

        checkState(onCloseCheckpointsConsumer != null, "Checkpoints on-close consumer must be set");

        TransferManager resolvedTransferManager = transferManager.orElseGet(() ->
                createTransferManager(s3Config));

        return new EntityS3Writer(workerInfo, resolvedTransferManager, runPoller,
                s3Config, checkpoints, onCloseCheckpointsConsumer);
    }

    private TransferManager createTransferManager(S3Config s3Config) {
        int maxRetriesOnError = s3Config.getMaxRetries();
        RetryPolicy retryPolicy =  new RetryPolicy(null, null, maxRetriesOnError, true);
        AmazonS3 s3Client = S3ClientCreator.getS3Client(s3Config, retryPolicy);

        // TODO for the future: configurable thread pool size, by default it's 10
        TransferManager transferManager = new TransferManager(s3Client);

        TransferManagerConfiguration transferConfiguration = new TransferManagerConfiguration();
        if (s3Config.getMinimumUploadPartSize() > 0) {
            transferConfiguration.setMinimumUploadPartSize(s3Config.getMinimumUploadPartSize());
        }
        if (s3Config.getMultipartUploadThreshold() > 0) {
            transferConfiguration.setMultipartUploadThreshold(s3Config.getMultipartUploadThreshold());
        }

        transferManager.setConfiguration(transferConfiguration);

        return transferManager;
    }

}
