package com.usermind.usermindsdk.common.stream.reader;

import com.amazonaws.retry.RetryPolicy;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.model.*;
import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.metrics.MetricsCollectorClient;
import com.usermind.usermindsdk.common.metrics.reporter.MetricsReporter;
import com.usermind.usermindsdk.common.support.dry.aws.AwsServiceClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiConsumer;

import static com.amazonaws.ClientConfiguration.DEFAULT_RETRY_POLICY;
import static com.google.common.base.Preconditions.*;
import static java.util.Collections.unmodifiableMap;
import static java.util.stream.Collectors.*;

/**
 * Kinesis stream reader implementation working with raw {@code Record}s.
 * This reader is stateful i.e. it updates its internal state with each {@code getRecords} call.
 * Note: still in case of continuous and massive load this reader may not be available to consume
 * all coming data from all shards (which leads to the data expiration) - in such scenario
 * we have to consume data concurrently from each shard. The metric "kinesis.millisBehindLatest"
 * tells us how far we are from this moment. Potentially this reader could be extended with
 * concurrent read support - the question is how this plays with
 * AWS SWF - the environment where we often use this reader.
 */
public class KinesisStreamRawRecordsReader implements KinesisStreamReader<Record> {

  private static final Logger LOGGER = LoggerFactory.getLogger(KinesisStreamRawRecordsReader.class);

  private static final RetryPolicy.RetryCondition RETRY_CONDITION =
      new KinesisStreamRetryCondition();

  private static final int SLEEP_MILLIS_ON_EMPTY_RESULT = 1000;
  private static final int SLEEP_MILLIS_ON_NONEMPTY_RESULT = 200; // Kinesis allows <= 5 reads/sec.
  private static final int DEFAULT_MAX_RETRIES = 10;
  // Kinesis limit per a single shard, for simplicity it's applied even if we have multiple shards
  public static final int MAX_LIMIT = 10_000;

  private final String streamName;
  private final Configuration kinesisConfig;
  private final AmazonKinesis kinesisClient;
  private final Map<String, String> iterators;
  private final Map<String, String> checkpoints;
  private final MetricsReporter<MetricsCollectorClient> metricsReporter;

  /**
   * {@code KinesisStreamReader} ctor.
   * @param kinesisConfig kinesis config
   * @param streamName stream name
   * @param checkpoints checkpoints - sequence number per each shard
   * @param metricsReporter metrics reporter
   * @param kinesisClient optional, used in *unit tests* to provide a {@code AmazonKinesis} mock
   */
  private KinesisStreamRawRecordsReader(Configuration kinesisConfig, String streamName,
      Map<String, String> checkpoints, MetricsReporter<MetricsCollectorClient> metricsReporter,
      Optional<AmazonKinesis> kinesisClient) {

    checkArgument(!Strings.isNullOrEmpty(streamName));
    this.streamName = streamName;
    this.kinesisConfig = checkNotNull(kinesisConfig);
    this.checkpoints = new HashMap<>(checkNotNull(checkpoints));
    this.metricsReporter = checkNotNull(metricsReporter);

    this.kinesisClient = kinesisClient.orElseGet(() -> createKinesisClient(kinesisConfig));

    this.iterators = getIterators(this.kinesisClient, this.streamName, this.checkpoints, false);
  }

  /**
   * {@code KinesisStreamReader} ctor.
   * @param kinesisConfig kinesis config
   * @param streamName stream name
   * @param checkpoints checkpoints - sequence number per each shard
   * @param metricsReporter metrics reporter
   * @param kinesisClient optional, used in *unit tests* to provide a {@code AmazonKinesis} mock
   */
  private KinesisStreamRawRecordsReader(Configuration kinesisConfig, String streamName,
                                        Map<String, String> checkpoints, MetricsReporter<MetricsCollectorClient> metricsReporter,
                                        Optional<AmazonKinesis> kinesisClient, boolean isLatest) {

    checkArgument(!Strings.isNullOrEmpty(streamName));
    this.streamName = streamName;
    this.kinesisConfig = checkNotNull(kinesisConfig);
    this.checkpoints = new HashMap<>(checkNotNull(checkpoints));
    this.metricsReporter = checkNotNull(metricsReporter);

    this.kinesisClient = kinesisClient.orElseGet(() -> createKinesisClient(kinesisConfig));
    this.iterators = getIterators(this.kinesisClient, this.streamName, this.checkpoints, isLatest);
  }

  private AmazonKinesis createKinesisClient(Configuration kinesisConfig) {
    int kinesisClientMaxRetries = kinesisConfig.getInt("maxErrorRetry", DEFAULT_MAX_RETRIES);

    RetryPolicy retryPolicy = new RetryPolicy(RETRY_CONDITION,
        DEFAULT_RETRY_POLICY.getBackoffStrategy(), kinesisClientMaxRetries,
        DEFAULT_RETRY_POLICY.isMaxErrorRetryInClientConfigHonored());

    return new AwsServiceClientBuilder(kinesisConfig, retryPolicy).getKinesisClient();
  }


  /**
   * Gets iterators for the given checkpoints.
   * @param kinesisClient Amazon Kinesis client
   * @param streamName stream name
   * @param checkpoints checkpoints
   * @return iterators map - shards id to iterator
   */
  private Map<String, String> getIterators(AmazonKinesis kinesisClient, String streamName,
                                           Map<String, String> checkpoints, boolean isLatest) {

    List<Shard> shards = getShards(kinesisClient, streamName);
    Set<String> shardIds = shards.stream().map(Shard::getShardId).collect(toSet());

    // Orphan checkpoint is the checkpoint pointing to the expired shard id - e.g. when fetches were
    // not made during the retention period. In this case it's better to re-iterate the stream.
    Set<String> orphanCheckpoints = Sets.difference(checkpoints.keySet(), shardIds);
    if (!orphanCheckpoints.isEmpty()) {
      LOGGER.warn("Re-iterating stream '{}' due to the detected orphan checkpoints: {}", streamName,
          orphanCheckpoints);
      metricsReporter.count("kinesis.orphanCheckpoints", orphanCheckpoints.size(),
          "stream", streamName);
      checkpoints.clear();
    }

    Map<String, String> iterators = new HashMap<>();
    for (Shard shard : shards) {
      String shardId = shard.getShardId();

      // That means we iterate shard if either {@code checkpoints} is not empty and
      // this shard has a corresponding mapping *or* if this shard is "top-level" shard -
      // with {@code parentShardId} and {@code adjacentParentShardId} equal null or
      // {@code parentShardId} and {@code adjacentParentShardId} referring to expired shard
      boolean iterateShard = checkpoints.isEmpty()
          ? !shardIds.contains(shard.getParentShardId())
              && !shardIds.contains(shard.getAdjacentParentShardId())
          : checkpoints.containsKey(shardId);

      if (iterateShard) {
        Optional<String> sequenceNumber = Optional.ofNullable(checkpoints.get(shardId));
        String iterator = getShardIterator(kinesisClient, streamName, shardId, sequenceNumber, isLatest);
        iterators.put(shardId, iterator);
      }
    }

    return iterators;
  }

  /**
   * Returns shard iterator value for the given shard id and optional sequence number.
   * @param kinesisClient Amazon Kinesis client
   * @param streamName stream name
   * @param shardId shard id
   * @param sequenceNumber sequence number
   * @return iterator value - is alive for 5 minutes
   */
  private String getShardIterator(AmazonKinesis kinesisClient, String streamName,
                                  String shardId, Optional<String> sequenceNumber, boolean findLatest) {

    GetShardIteratorRequest getShardIteratorRequest = new GetShardIteratorRequest();
    getShardIteratorRequest.setStreamName(streamName);
    getShardIteratorRequest.setShardId(shardId);

    // See more in {@code GetShardIteratorRequest.setShardIteratorType} docs
      if(findLatest) {
          getShardIteratorRequest.setShardIteratorType(ShardIteratorType.LATEST);
      } else if (sequenceNumber.isPresent()) {
      getShardIteratorRequest.setShardIteratorType(ShardIteratorType.AFTER_SEQUENCE_NUMBER);
      getShardIteratorRequest.setStartingSequenceNumber(sequenceNumber.get());
    } else {
      getShardIteratorRequest.setShardIteratorType(ShardIteratorType.TRIM_HORIZON);
    }

    GetShardIteratorResult shardIteratorResult =
        kinesisClient.getShardIterator(getShardIteratorRequest);

    return shardIteratorResult.getShardIterator();
  }

  private String getLatestShardIterator(String shardId) {
      GetShardIteratorRequest getShardIteratorRequest = new GetShardIteratorRequest();
      getShardIteratorRequest.setStreamName(streamName);
      getShardIteratorRequest.setShardId(shardId);

      // See more in {@code GetShardIteratorRequest.setShardIteratorType} docs
      getShardIteratorRequest.setShardIteratorType(ShardIteratorType.LATEST);

      GetShardIteratorResult shardIteratorResult =
              kinesisClient.getShardIterator(getShardIteratorRequest);

      return shardIteratorResult.getShardIterator();
  }


  /**
   * Lists all shards for the given stream.
   * @param kinesisClient Amazon Kinesis client
   * @param streamName stream name
   * @return list of {@code Shard}
   */
  private List<Shard> getShards(AmazonKinesis kinesisClient, String streamName) {
    List<Shard> shards = new ArrayList<>();

    DescribeStreamRequest describeStreamRequest = new DescribeStreamRequest();
    describeStreamRequest.setStreamName(streamName);

    String exclusiveStartShardId = null;
    do {
      describeStreamRequest.setExclusiveStartShardId(exclusiveStartShardId);

      DescribeStreamResult describeStreamResult =
          kinesisClient.describeStream(describeStreamRequest);

      StreamDescription streamDescription = describeStreamResult.getStreamDescription();
      shards.addAll(streamDescription.getShards());

      if (streamDescription.getHasMoreShards()) {
        exclusiveStartShardId = shards.get(shards.size() - 1).getShardId();
      } else {
        exclusiveStartShardId = null;
      }
    } while (exclusiveStartShardId != null);

    return shards;
  }

  /**
   * Reads records from all stream shards iterating them one-by-one. Handles re-sharding
   * in the following manner:
   *  - when shard gets split this code switches to its descendant shards
   *  - when two shards are merged this code switches to descendant shard only if a second merged
   *  shard has been consumed. *Note that* more than {@code limit} records can be returned,
   *  e.g. when we have limit 1000 and 3 shards it will return 334 * 3 records. *Note* on empty
   *  result this method sleeps 1 sec. and on nonempty result 200 millis.
   * @param limit read limit, max 10 000
   * @return list of {@code Record}
   */
  @Override
  public List<Record> getRecords(int limit) {
    checkArgument(limit > 0);
    checkIterators();

    int limitPerShard = (int) Math.ceil((double) Math.min(limit, MAX_LIMIT) / iterators.size());

    Queue<ShardAwareGetRecordsRequest> requestsQueue = iterators.entrySet().stream()
        .map(iteratorEntry -> new ShardAwareGetRecordsRequest()
            .withShardId(iteratorEntry.getKey())
            .withShardIterator(iteratorEntry.getValue())
            .withLimit(limitPerShard))
        .collect(toCollection(LinkedList::new));

    List<Record> allRecords = new ArrayList<>();
    int shardsReadCount = 0;

    // New requests will be added to the queue in case of split or merge
    while (!requestsQueue.isEmpty()) {
      ShardAwareGetRecordsRequest getRecordsRequest = requestsQueue.remove();
      shardsReadCount++;

      String shardId = getRecordsRequest.getShardId();

      GetRecordsResult getRecordsResult;
      try {
        getRecordsResult = kinesisClient.getRecords(getRecordsRequest);
      } catch (ExpiredIteratorException ex) {
        LOGGER.info("Shard iterator '" + getRecordsRequest.getShardIterator()
            + "' has expired, getting a new one", ex);
        metricsReporter.increment("kinesis.expiredShardIterator", "shard", shardId,
            "stream", streamName);
        String newIterator =  getShardIterator(kinesisClient, streamName, shardId,
            Optional.ofNullable(checkpoints.get(shardId)), false);
        iterators.put(shardId, newIterator);

        getRecordsRequest.setShardIterator(newIterator);
        getRecordsResult = kinesisClient.getRecords(getRecordsRequest);
      }

      List<Record> shardRecords = getRecordsResult.getRecords();
      allRecords.addAll(shardRecords);

      int getRecordsRequestLimit = getRecordsRequest.getLimit();
      int shardRecordsCount = shardRecords.size();
      LOGGER.debug("Read {} records from shard '{}' with limit {}", shardRecordsCount, shardId,
          getRecordsRequestLimit);

      Long millisBehindLatest = getRecordsResult.getMillisBehindLatest();
      metricsReporter.histogram("kinesis.millisBehindLatest", millisBehindLatest, "shard", shardId,
          "stream", streamName);

      String nextShardIterator = getRecordsResult.getNextShardIterator();
      if (nextShardIterator != null) {
        iterators.put(shardId, nextShardIterator);

        if (shardRecordsCount > 0) {
          Record checkpointRecord = shardRecords.get(shardRecordsCount - 1);
          checkpoints.put(shardId, checkpointRecord.getSequenceNumber());
        }
      } else {
        // Split or merge has happened to this shard
        iterators.remove(shardId);
        checkpoints.remove(shardId);

        List<Shard> shardsCreatedByResharding = getShardsCreatedByResharding(shardId);

        // Read remaining records from the descendant shard if shard read limit > shard read count
        BiConsumer<String, String> descendantShardIteratorConsumer = null;
        if (getRecordsRequestLimit > shardRecordsCount) {
          int limitPerDescendantShard = (int) Math.ceil(
              (double) (getRecordsRequestLimit - shardRecordsCount) / shardsCreatedByResharding
                  .size());

          descendantShardIteratorConsumer = (descendantShardId, iterator) ->
              requestsQueue.add(new ShardAwareGetRecordsRequest()
                  .withShardId(descendantShardId)
                  .withShardIterator(iterator)
                  .withLimit(limitPerDescendantShard));
        }

        handleResharding(shardId, shardsCreatedByResharding,
            Optional.ofNullable(descendantShardIteratorConsumer));
      }
    }

    LOGGER.debug("Read {} records from {} shards", allRecords.size(), shardsReadCount);

    // Sleeping to avoid exceeding the limit on getRecords frequency
    int sleepMillis = allRecords.isEmpty()
        ? SLEEP_MILLIS_ON_EMPTY_RESULT
        : SLEEP_MILLIS_ON_NONEMPTY_RESULT;
    // Let's put this here - otherwise we would have to manage some state/flag
    sleepInterruptibly(sleepMillis);

    return allRecords;
  }

  private void handleResharding(String closedShardId, List<Shard> shardsCreatedByResharding,
      Optional<BiConsumer<String, String>> descendantShardIteratorConsumer) {

    switch (shardsCreatedByResharding.size()) {
      case 1:
        LOGGER.info("Shard '{}' has been merged", closedShardId);
        Shard descendantShard = shardsCreatedByResharding.get(0);
        String otherMergedShardId = descendantShard.getAdjacentParentShardId().equals(closedShardId)
            ? descendantShard.getParentShardId()
            : descendantShard.getAdjacentParentShardId();

        if (iterators.containsKey(otherMergedShardId)) {
          // We have to consume other merged shard before switching to this one
          return;
        }

        LOGGER.info("Both merged shards '{}' and '{}' were consumed",
            descendantShard.getParentShardId(), descendantShard.getAdjacentParentShardId());
        break;

      case 2:
        LOGGER.info("Shard '{}' has been split", closedShardId);
        break;

      default:
        // Then most probably it's a bug
        List<String> shardIdsCreatedByResharding = shardsCreatedByResharding.stream()
            .map(Shard::getShardId)
            .collect(toList());
        throw new IllegalStateException("Failed to handle Kinesis re-sharding: closed shard id '"
            + closedShardId + "', shards created by re-sharding " + shardIdsCreatedByResharding);
    }

    for (Shard descendantShard : shardsCreatedByResharding) {
      String descendantShardId = descendantShard.getShardId();
      LOGGER.info("Switching to descendant shard '{}'", descendantShardId);

      String iterator = getShardIterator(kinesisClient, streamName, descendantShardId,
          Optional.empty(), false);

      descendantShardIteratorConsumer
          .ifPresent(consumer -> consumer.accept(descendantShardId, iterator));

      iterators.put(descendantShardId, iterator);

      // We shouldn't use a starting sequence number as we use "AFTER_SEQUENCE_NUMBER" iterator
      checkpoints.put(descendantShardId, null);
    }
  }

  /**
   * Retrieves shards which are descendant for the given closed shard. In case of split 2,
   * in case of merge - 1.
   * @param closedShardId closed shard id
   * @return list of {@code Shard}
   */
  private List<Shard> getShardsCreatedByResharding(String closedShardId) {
    // In case of split this code returns two descendant shards, in case of merge -
    // a single descendant shard
    List<Shard> allShards = getShards(kinesisClient, streamName);

    return allShards.stream()
        .filter(shard -> closedShardId.equals(shard.getParentShardId())
            || closedShardId.equals(shard.getAdjacentParentShardId())).collect(toList());
  }

  private void sleepInterruptibly(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Interrupted while sleeping", ex);
    }
  }

  private void checkIterators() {
    checkState(!iterators.isEmpty(), "Failed to iterate stream '%s' shards", streamName);
  }

  @Override
  public Map<String, String> getCheckpoints() {
    // With each {@code KinesisStreamRawRecordsReader.getRecords} call this map gets updated
    return unmodifiableMap(checkpoints);
  }

  /**
   * Used in *unit tests*.
   * @return kinesis config
   */
  Configuration getKinesisConfig() {
    return kinesisConfig;
  }

  /**
   * Used in *unit tests*.
   * @return shard iterators map
   */
  Map<String, String> iterators() {
    return iterators;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * {@code KinesisStreamReader} builder.
   */
  public static class Builder {

    private String streamName;
    private MetricsReporter<MetricsCollectorClient> metricsReporter;
    private Map<String, String> checkpoints;
    private Optional<String> kinesisConfigPath = Optional.empty();
    private Optional<Configuration> kinesisConfig = Optional.empty();
    private Optional<AmazonKinesis> kinesisClient = Optional.empty();
    private boolean isLatestIterator = false;

    /**
     * Stream name.
     * @param streamName stream name
     * @return this
     */
    public Builder setStreamName(String streamName) {
      this.streamName = checkNotNull(streamName);
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
     * Checkpoints - sequence number for each shard.
     * @param checkpoints checkpoints
     * @return this
     */
    public Builder setCheckpoints(Map<String, String> checkpoints) {
      this.checkpoints = checkNotNull(checkpoints);
      return this;
    }

    /**
     * Path to the worker lib config with Kinesis settings.
     * Either path or explicit config must be set.
     * @param kinesisConfigPath kinesis config path
     * @return this
     */
    public Builder setKinesisConfigPath(String kinesisConfigPath) {
      this.kinesisConfigPath = Optional.ofNullable(kinesisConfigPath);
      return this;
    }

    /**
     * An explicit kinesis config.
     * Either path or explicit config must be set.
     * @param kinesisConfig kinesis config
     * @return this
     */
    public Builder setKinesisConfig(Configuration kinesisConfig) {
      this.kinesisConfig = Optional.ofNullable(kinesisConfig);
      return this;
    }

    /**
     * Used in *unit tests* - to set a {@code AmazonKinesis} mock.
     * @param kinesisClient kinesis client
     * @return this
     */
    Builder setKinesisClient(AmazonKinesis kinesisClient) {
      this.kinesisClient = Optional.ofNullable(kinesisClient);
      return this;
    }

    public Builder setLatestIterator(boolean latestIterator) {
      this.isLatestIterator = latestIterator;
      return this;
    }

    /**
     * Builds a {@code KinesisStreamReader}.
     * @return a new {@code KinesisStreamRawRecordsReader}
     */
    public KinesisStreamRawRecordsReader build() {
      checkState(kinesisConfigPath.isPresent() ^ kinesisConfig.isPresent());

      Configuration commonLibConfig = CommonLib.get().getConfiguration();
      Configuration resolvedKinesisConfig =
          kinesisConfigPath.map(commonLibConfig::atPath).orElseGet(kinesisConfig::get);

        if(isLatestIterator) {
            return new KinesisStreamRawRecordsReader(resolvedKinesisConfig, streamName, checkpoints,
                    metricsReporter, kinesisClient, isLatestIterator);
        }
      return new KinesisStreamRawRecordsReader(resolvedKinesisConfig, streamName, checkpoints,
          metricsReporter, kinesisClient);
    }
  }
}
