package com.usermind.usermindsdk.common.metrics;

import com.timgroup.statsd.NoOpStatsDClient;
import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.InvalidConfigurationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * StatsDMetricsCollector.
 */
public class StatsDMetricsCollector implements MetricsCollectorClient {

  private static final String CLIENT_TYPE = "clientType";
  private static final String NO_OP_CLIENT_TYPE = "noOp";
  private static final String NON_BLOCKING_CLIENT_TYPE = "dataDogNonBlocking";
  private static final String PREFIX = "prefix";
  private static final String HOST = "host";
  private static final String PORT = "port";
  private static final String TAGS = "tags";

  private final Configuration configuration;
  private StatsDClient statsDClient;

  public StatsDMetricsCollector(Configuration metricsConfiguration) {
    configuration = checkNotNull(metricsConfiguration);
    statsDClient = buildMetricsProxy();
  }

  /**
   * verifies if the given property exists in the config.
   * @param property to verify
   */
  private void checkConfigPropertyExists(String property) {
    if (!configuration.hasPath(property)) {
      throw new InvalidConfigurationException(property + " is not specified in the config");
    }
  }

  /**
   * build the actual statsd client we will use.
   * the proxy is meant to hide the underneath implentation from the client
   * @return the clients that this class will use internally
   */
  private StatsDClient buildMetricsProxy() {
    checkConfigPropertyExists(CLIENT_TYPE);
    String clientType = configuration.getString(CLIENT_TYPE);
    if (clientType.equals(NO_OP_CLIENT_TYPE)) {

      return new NoOpStatsDClient();

    } else if (clientType.equals(NON_BLOCKING_CLIENT_TYPE)) {

      checkConfigPropertyExists(TAGS);
      List<String> tags = new ArrayList<>();
      for (String key : configuration.getKeys(Optional.of(TAGS))) {
        tags.add(key + ":" + configuration.getString(TAGS + "." + key));
      }

      checkConfigPropertyExists(PREFIX);
      checkConfigPropertyExists(HOST);
      checkConfigPropertyExists(PORT);

      return new NonBlockingStatsDClient(configuration.getString(PREFIX),
                                         configuration.getString(HOST),
                                         configuration.getInt(PORT),
                                         tags.toArray(new String[tags.size()]));
    } else {
      throw new InvalidConfigurationException("unknown client type specified: " + clientType);
    }
  }

  /**
   * Setter to be used only for purpose of unit tests.
   * @param statsDClient statsd client
   */
  void setStatsDClient(StatsDClient statsDClient) {
    this.statsDClient = checkNotNull(statsDClient);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void stop() {
    statsDClient.stop();
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void count(String aspect, long delta, String... tags) {
    statsDClient.count(aspect, delta, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void incrementCounter(String aspect, String... tags) {
    statsDClient.incrementCounter(aspect, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void increment(String aspect, String... tags) {
    statsDClient.increment(aspect, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void decrementCounter(String aspect, String... tags) {
    statsDClient.decrementCounter(aspect, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void decrement(String aspect, String... tags) {
    statsDClient.decrement(aspect, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void recordGaugeValue(String aspect, double value, String... tags) {
    statsDClient.recordGaugeValue(aspect, value, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void recordGaugeValue(String aspect, long value, String... tags) {
    statsDClient.recordGaugeValue(aspect, value, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void gauge(String aspect, double value, String... tags) {
    statsDClient.gauge(aspect, value, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void gauge(String aspect, long value, String... tags) {
    statsDClient.gauge(aspect, value, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void recordExecutionTime(String aspect, long timeInMs, String... tags) {
    statsDClient.recordExecutionTime(aspect, timeInMs, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void time(String aspect, long value, String... tags) {
    statsDClient.time(aspect, value, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void recordHistogramValue(String aspect, double value, String... tags) {
    statsDClient.recordHistogramValue(aspect, value, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void recordHistogramValue(String aspect, long value, String... tags) {
    statsDClient.recordHistogramValue(aspect, value, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void histogram(String aspect, double value, String... tags) {
    statsDClient.histogram(aspect, value, tags);
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public void histogram(String aspect, long value, String... tags) {
    statsDClient.histogram(aspect, value, tags);
  }
}
