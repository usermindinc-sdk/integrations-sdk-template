package com.usermind.usermindsdk.baselib.writers.s3;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class S3Config {
    private String endpoint = "";
    private String bucket = "";
    private String objectKeyPrefix = "";
    private String kmsKeyId = "";
    private Integer maxRecordsInChunk = 20_000;
    private Integer maxUploadsInParallel = 10;
    private Integer socketTimeout = 70000;
    private Long maxChunkSizeBytes = 5_000_000_000L;
    private Long progressLoggingPeriodMillis = 1000L;
    private Long minimumUploadPartSize = -1L;
    private Long multipartUploadThreshold = -1L;
    private Boolean useCompression = true;
    private Boolean encryptLocalChunks = true;
    private Boolean pathStyle = false;
    private Boolean createBucket = false;
    private Integer maxRetries = 10;


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = checkNotNull(endpoint);
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = checkNotNull(bucket);
    }

    public String getObjectKeyPrefix() {
        return objectKeyPrefix;
    }

    public void setObjectKeyPrefix(String objectKeyPrefix) {
        this.objectKeyPrefix = checkNotNull(objectKeyPrefix);
    }

    public String getKmsKeyId() {
        return kmsKeyId;
    }

    public void setKmsKeyId(String kmsKeyId) {
        this.kmsKeyId = checkNotNull(kmsKeyId);
    }

    public Integer getMaxRecordsInChunk() {
        return maxRecordsInChunk;
    }

    public void setMaxRecordsInChunk(Integer maxRecordsInChunk) {
        checkNotNull(maxRecordsInChunk);
        checkArgument(1 <= maxRecordsInChunk && maxRecordsInChunk <= 1000_000, "maxRecordsInChunk must be in range [1, 1,000,000]");
        this.maxRecordsInChunk = maxRecordsInChunk;
    }

    public Integer getMaxUploadsInParallel() {
        return maxUploadsInParallel;
    }

    public void setMaxUploadsInParallel(Integer maxUploadsInParallel) {
        checkNotNull(maxUploadsInParallel);
        checkArgument(1 <= maxUploadsInParallel && maxUploadsInParallel <= 50, "maxUploadsInParallel must be in range [1, 50]");
        this.maxUploadsInParallel = checkNotNull(maxUploadsInParallel);
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = checkNotNull(socketTimeout);
    }

    public Long getMaxChunkSizeBytes() {
        return maxChunkSizeBytes;
    }

    public void setMaxChunkSizeBytes(Long maxChunkSizeBytes) {
        checkNotNull(maxChunkSizeBytes);
        checkArgument(1 <= maxChunkSizeBytes && maxChunkSizeBytes <= 102_400, "maxChunkSizeBytes must be in range [1, 102,400]");
        this.maxChunkSizeBytes = checkNotNull(maxChunkSizeBytes);
    }

    public Long getProgressLoggingPeriodMillis() {
        return progressLoggingPeriodMillis;
    }

    public void setProgressLoggingPeriodMillis(Long progressLoggingPeriodMillis) {
        checkNotNull(progressLoggingPeriodMillis);
        checkArgument(100 <= progressLoggingPeriodMillis && progressLoggingPeriodMillis <= 60000, "progressLoggingPeriodMillis must be in range [1, 60,000]");
        this.progressLoggingPeriodMillis = checkNotNull(progressLoggingPeriodMillis);
    }

    public Boolean getUseCompression() {
        return useCompression;
    }

    public void setUseCompression(Boolean useCompression) {
        this.useCompression = checkNotNull(useCompression);
    }

    public Boolean getEncryptLocalChunks() {
        return encryptLocalChunks;
    }

    public void setEncryptLocalChunks(Boolean encryptLocalChunks) {
        this.encryptLocalChunks = checkNotNull(encryptLocalChunks);
    }

    public Integer getMaxRetries() {
        return maxRetries;
    }

    public void setMaxRetries(Integer maxRetries) {
        checkNotNull(maxRetries);
        checkArgument(1 <= maxRetries && maxRetries <= 10, "maxRetries must be in range [1, 10]");
        this.maxRetries = maxRetries;
    }

    public Long getMinimumUploadPartSize() {
        return minimumUploadPartSize;
    }

    public void setMinimumUploadPartSize(Long minimumUploadPartSize) {
        checkNotNull(maxRetries);
        checkArgument(1 <= minimumUploadPartSize && minimumUploadPartSize <= 5242880, "minimumUploadPartSize must be in range [1, 5,242,880]");
        this.minimumUploadPartSize = minimumUploadPartSize;
    }

    public Long getMultipartUploadThreshold() {
        return multipartUploadThreshold;
    }

    public void setMultipartUploadThreshold(Long multipartUploadThreshold) {
        checkNotNull(maxRetries);
        checkArgument(1 <= multipartUploadThreshold && multipartUploadThreshold <= 16777216, "multipartUploadThreshold must be in range [1, 16,777,216]");
        this.multipartUploadThreshold = multipartUploadThreshold;
    }

    public Boolean getPathStyle() {
        return pathStyle;
    }

    public void setPathStyle(Boolean pathStyle) {
        this.pathStyle = checkNotNull(pathStyle);
    }

    public Boolean getCreateBucket() {
        return createBucket;
    }

    public void setCreateBucket(Boolean createBucket) {
        this.createBucket = checkNotNull(createBucket);
    }
}
