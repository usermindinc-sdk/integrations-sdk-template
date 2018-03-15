package com.usermind.usermindsdk.common.boot;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.metrics.MetricsCollectorClient;
import com.usermind.usermindsdk.common.stream.StreamFactory;

/**
 * interface definition for all objects held in a worker lib registry.
 */
public interface CommonLibRegistry {

  /**
   * retrieves the configuration stored in the registry.
   *
   * @return the associated configuration
   */
  Configuration getConfiguration();

  /**
   * retrieves the metrics client stored in the registry.
   *
   * @return the metrics client
   */
  MetricsCollectorClient getMetrics();

  /**
   * retrieves the stream factory stored in the registry.
   *
   * @return the stream factory
   */
  StreamFactory getStreamFactory();
}
