package com.usermind.usermindsdk.common.stream;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.InvalidConfigurationException;

/**
 * the commonlib stream factory.
 */
public class StreamFactory {

  private static final String STREAM_TYPE = "streamType";
  private static final String KINESIS_STREAM_TYPE = "kinesis";

  /**
   * the stream builden.
   * @param configuration to use
   * @return the stream
   */
  public Stream getStream(Configuration configuration) {
    if (!configuration.hasPath(STREAM_TYPE)) {
      throw new InvalidConfigurationException("missing " + STREAM_TYPE + " property");
    }
    if (!configuration.getString(STREAM_TYPE).equals(KINESIS_STREAM_TYPE)) {
      throw new InvalidConfigurationException(
          "unsupported stream type " + configuration.getString(STREAM_TYPE));
    }
    return new KinesisStream(configuration);
  }
}
