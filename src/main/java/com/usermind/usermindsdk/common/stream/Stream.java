package com.usermind.usermindsdk.common.stream;

import com.usermind.usermindsdk.common.config.Configuration;

import java.util.List;

/**
 * commonlib stream interface definition.
 */
public interface Stream {

  /**
   * writes the given content to the stream.
   * @param item the content
   * @return config indicating where the value was written
   */
  Configuration writeRecord(String item);

  /**
   * writes the items to the stream.
   * @param items list of items to write
   */
  void writeRecords(List<String> items);

  /**
   * retrieves a snapshot of the stream at this point in time.
   * @return configuration containing the snapshot
   */
  Configuration snapshotCurrentState();
}
