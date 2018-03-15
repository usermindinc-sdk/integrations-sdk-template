package com.usermind.usermindsdk.common.stream;

import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.kinesis.model.*;
import com.google.common.util.concurrent.Uninterruptibles;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.common.support.dry.aws.AwsServiceClientBuilder;
import com.usermind.usermindsdk.common.support.exception.EmptyCatchTracer;
import org.slf4j.Logger;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * implements the commonlib stream as a kinesis stream.
 */
public class KinesisStream implements Stream {

  private final String streamName;
  private final String partitionKey;

  private final Logger logger;
  private final AmazonKinesisClient kinesisClient;

  /**
   * build a commonlib kinesis stream.
   *
   * @param configuration drives building the stream
   * @param logger        logger to use
   */
  public KinesisStream(Configuration configuration, Logger logger) {
    this.logger = logger;

    if (configuration.hasPath("prefix")) {
      String prefix = configuration.getString("prefix");
      kinesisClient = new AwsServiceClientBuilder(prefix, configuration).getKinesisClient();
      configuration.checkPathExists(Configuration.buildFullPath(prefix, "stream_name"));
      configuration.checkPathExists(Configuration.buildFullPath(prefix, "partition_key"));
      streamName = configuration.getString(Configuration.buildFullPath(prefix, "stream_name"));
      partitionKey = configuration.getString(Configuration.buildFullPath(prefix, "partition_key"));
    } else {
      kinesisClient = new AwsServiceClientBuilder(configuration).getKinesisClient();
      configuration.checkPathExists("stream_name");
      configuration.checkPathExists("partition_key");
      streamName = configuration.getString("stream_name");
      partitionKey = configuration.getString("partition_key");
    }
    createIfNeeded();
  }

  public KinesisStream(Configuration configuration) {
    this(configuration, null);
  }

  private void createIfNeeded() {
    try {
      kinesisClient.createStream(streamName, 1);
    } catch (ResourceInUseException e) {
      EmptyCatchTracer.note(logger, e);
    }
    // Block for up to 2 minutes in 10 second increments until the stream is created
    // Stream creation typically takes around 30 seconds
    int tries = 0;
    final int interval = 10;
    final int max_tries = 2 * 60 / interval;
    while (tries < max_tries) {
      try {
        // describeStream will throw if the named stream is not in status ACTIVE
        kinesisClient.describeStream(streamName);
        if (logger != null) {
          logger.info("Kinesis stream {} created after {} seconds", streamName, tries * interval);
        }
        break;
      } catch (ResourceNotFoundException e) {
        // This exception is thrown if the stream does not exist OR is not in status ACTIVE
        // When we request the creation of a stream, it is in status CREATING for about 30 seconds
        Uninterruptibles.sleepUninterruptibly(interval, TimeUnit.SECONDS);
      } finally {
        tries++;
      }
    }
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Configuration writeRecord(String item) {
    try {
      PutRecordRequest putRecordRequest = new PutRecordRequest();
      putRecordRequest.setStreamName(streamName);
      putRecordRequest.setPartitionKey(partitionKey);
      putRecordRequest.setData(ByteBuffer.wrap(item.getBytes(UTF_8)));
      PutRecordResult result = kinesisClient.putRecord(putRecordRequest);
      return new ConfigurationBuilder()
          .put("streamName", streamName)
          .put("shardId", result.getShardId())
          .put("sequenceNumber", result.getSequenceNumber())
          .toConfiguration();
    } catch (Exception e) {
      if (logger != null) {
        logger.error("failed publishing to stream", e);
      }
      throw new RuntimeException(
          "exhausted all retries pushing to the Kinesis stream! " + streamName);
    }
  }

  /**
   * {@inheritDoc}.
   *
   * <p> Each PutRecords request can support up to 500 records. Each record in the request can be as
   * large as up to 50 KB per record, up to a limit of 4.5 MB for the entire request.
   *
   * @see <a href="http://docs.aws.amazon.com/kinesis/latest/dev/kinesis-using-sdk-java-add-data-to-stream.html">kinesis
   * doc</a>
   */
  @Override
  public void writeRecords(List<String> items) {
    for (List<PutRecordsRequestEntry> putRecordsRequestEntryList : prepareItems(items)) {
      if (putRecordsRequestEntryList.size() == 0) {
        continue;
      }
      PutRecordsResult
          putRecordsResult =
          putRecordsRequestEntryListHelper(putRecordsRequestEntryList);

      int retries = 0;
      while (putRecordsResult.getFailedRecordCount() > 0) {
        try {
          // Failures are typically due to a ProvisionedThroughputExceededException
          // Wait a second before retrying so we don't immediately encounter this issue again
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        retries += 1;
        if (retries > 10) {
          if (logger != null) {
            logger.error("exhausted all retries pushing to the Kinesis stream! " + streamName);
          }
          throw new RuntimeException(
              "exhausted all retries pushing to the Kinesis stream! " + streamName);
        }
        putRecordsRequestEntryList = getFailedRecords(putRecordsRequestEntryList,
                putRecordsResult);
        putRecordsResult =
            putRecordsRequestEntryListHelper(putRecordsRequestEntryList);
      }
    }
  }

  /**
   * {@inheritDoc}.
   *
   * <p>
   * In case of kinesis it's a describe stream. This can and will be used to find sections of the
   * stream that we are interested in.
   */
  @Override
  public Configuration snapshotCurrentState() {
    Configuration snapshot = new ConfigurationBuilder().toConfiguration();
    DescribeStreamRequest describeStreamRequest = new DescribeStreamRequest();
    describeStreamRequest.setStreamName(streamName);
    List<Shard> shards = new ArrayList<>();
    String exclusiveStartShardId = null;
    do {
      describeStreamRequest.setExclusiveStartShardId(exclusiveStartShardId);
      DescribeStreamResult
          describeStreamResult =
          kinesisClient.describeStream(describeStreamRequest);
      shards.addAll(describeStreamResult.getStreamDescription().getShards());
      if (describeStreamResult.getStreamDescription().getHasMoreShards() && shards.size() > 0) {
        exclusiveStartShardId = shards.get(shards.size() - 1).getShardId();
      } else {
        exclusiveStartShardId = null;
      }
    } while (exclusiveStartShardId != null);

    for (Shard shard : shards) {
      snapshot = snapshot.mergeAtPath("streams." + streamName + ".shards." + shard.getShardId(),
                                      ConfigurationBuilder.createConfiguration(shard.toString()));
    }
    return snapshot;
  }

  private List<PutRecordsRequestEntry> getFailedRecords(
          final List<PutRecordsRequestEntry> putRecordsRequestEntryList, PutRecordsResult
          putRecordsResult) {
    final List<PutRecordsRequestEntry> failedRecordsList = new ArrayList<>();
    final List<PutRecordsResultEntry> putRecordsResultEntryList = putRecordsResult.getRecords();
    for (int i = 0; i < putRecordsResultEntryList.size(); i++) {
      PutRecordsRequestEntry putRecordRequestEntry = putRecordsRequestEntryList.get(i);
      PutRecordsResultEntry putRecordsResultEntry = putRecordsResultEntryList.get(i);
      if (putRecordsResultEntry.getErrorCode() != null) {
        failedRecordsList.add(putRecordRequestEntry);
      }
    }
    return failedRecordsList;
  }

  private List<List<PutRecordsRequestEntry>> prepareItems(List<String> items) {
    List<List<PutRecordsRequestEntry>> itemsLists = new ArrayList<>();
    List<PutRecordsRequestEntry> currentItemsList = new ArrayList<>();
    itemsLists.add(currentItemsList);

    for (String item : items) {
      PutRecordsRequestEntry putRecordsRequestEntry = new PutRecordsRequestEntry();
      putRecordsRequestEntry.setData(ByteBuffer.wrap(item.getBytes(UTF_8)));
      putRecordsRequestEntry.setPartitionKey(partitionKey);
      currentItemsList.add(putRecordsRequestEntry);

      if (currentItemsList.size() >= 500) {
        currentItemsList = new ArrayList<>();
        itemsLists.add(currentItemsList);
      }
    }
    return itemsLists;
  }

  private PutRecordsResult putRecordsRequestEntryListHelper(List<PutRecordsRequestEntry> entry) {
    PutRecordsRequest putRecordsRequest = new PutRecordsRequest();
    putRecordsRequest.setStreamName(streamName);
    putRecordsRequest.setRecords(entry);
    return kinesisClient.putRecords(putRecordsRequest);
  }
}
