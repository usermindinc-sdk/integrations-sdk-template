package com.usermind.usermindsdk.common.stream.reader;

import java.util.List;
import java.util.Map;

/**
 * Kinesis stream reader interface. Implementations may return raw {@code Record}s or
 * any other applicable type, e.g. we can implement some micro-batching like KPL does - and return
 * some {@code List<MicroBatchedRecord>} for each raw {@code Record} or implement some filtering
 * by partition key.
 */
public interface KinesisStreamReader<T> {

  /**
   * Reads portion of records with a given limit leaving them raw, i.e. {@code Records} or
   * converting them.
   * @param limit read limit
   * @return records list - raw or converted, depending on implementation
   */
  List<T> getRecords(int limit);

  /**
   * Checkpoints - sequence number for each shard id.
   * @return checkpoints
   */
  Map<String, String> getCheckpoints();
}
