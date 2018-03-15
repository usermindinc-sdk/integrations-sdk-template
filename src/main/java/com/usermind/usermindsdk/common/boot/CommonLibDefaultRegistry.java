package com.usermind.usermindsdk.common.boot;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.common.config.ConfigurationSource;
import com.usermind.usermindsdk.common.config.DefaultConfigurationSource;
import com.usermind.usermindsdk.common.metrics.MetricsCollectorClient;
import com.usermind.usermindsdk.common.metrics.StatsDMetricsCollector;
import com.usermind.usermindsdk.common.stream.StreamFactory;
import com.usermind.usermindsdk.common.support.exception.EmptyCatchTracer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkState;

/**
 * Default repository implementation for the Common Lib.
 */
public class CommonLibDefaultRegistry implements CommonLibRegistry {

  private final Configuration configuration;
  private final MetricsCollectorClient metricsCollectorClient;
  private final StreamFactory streamFactory;

  /**
   * build a repository using a path and a configuration source.
   * @param configurationPath given path
   * @param configurationSource given source
   */
  public CommonLibDefaultRegistry(Optional<String> configurationPath,
                                  Optional<ConfigurationSource> configurationSource) {
    Configuration baseConfig;
    if (configurationSource.isPresent()) {
      baseConfig = configurationSource.get().load();
    } else {
      baseConfig = new DefaultConfigurationSource().load();
    }
    if (configurationPath.isPresent()) {
      checkState(baseConfig.hasPath(configurationPath.get()) == true,
                 "configurationPath " + configurationPath.get() + " cannot be located");
      configuration = baseConfig.atPath(configurationPath.get());
    } else {
      configuration = baseConfig;
    }
    Configuration metricsConfiguration = configuration;
    if (configuration.hasPath("metrics")) {
      metricsConfiguration = configuration.atPath("metrics");
    }
    metricsCollectorClient = new StatsDMetricsCollector(metricsConfiguration);
    streamFactory = new StreamFactory();
  }

  /**
   * convenience method {@link #CommonLibDefaultRegistry(Optional, Optional)}.
   * @param configurationPath given path
   */
  public CommonLibDefaultRegistry(Optional<String> configurationPath) {
    this(configurationPath, Optional.empty());
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public Configuration getConfiguration() {
    return configuration;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public MetricsCollectorClient getMetrics() {
    return metricsCollectorClient;
  }

  @Override
  public StreamFactory getStreamFactory() {
    return streamFactory;
  }

  private Configuration setCloudWatchStreamIfNotSpecified(
      Configuration loggerFactoryConfiguration) {
    if (loggerFactoryConfiguration.hasPath("loggers.appenders.awsCloudWatchLogs")) {
      if (!loggerFactoryConfiguration
          .hasPath("loggers.appenders.awsCloudWatchLogs.awsLogsStream")) {
        try {
          loggerFactoryConfiguration = loggerFactoryConfiguration.merge(
              new ConfigurationBuilder()
                  .put("loggers.appenders.awsCloudWatchLogs.awsLogsStream", InetAddress
                      .getLocalHost().getHostAddress()).toConfiguration());
        } catch (UnknownHostException e) {
          EmptyCatchTracer.note(null, e);
        }
      }
    }
    return loggerFactoryConfiguration;
  }
}
