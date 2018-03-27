package com.usermind.usermindsdk.baselib.metrics;

import com.google.common.collect.ImmutableList;
import com.timgroup.statsd.NoOpStatsDClient;
import com.timgroup.statsd.NonBlockingStatsDClient;
import com.timgroup.statsd.StatsDClient;
import com.usermind.usermindsdk.baselib.exceptions.InvalidConfigurationException;
import org.apache.commons.lang3.StringUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * StatsDMetricsCollector.
 */
public class StatsDMetricsCollector implements MetricsCollectorClient {

    private static final String NO_OP_CLIENT_TYPE = "noOp";
    private static final String NON_BLOCKING_CLIENT_TYPE = "dataDogNonBlocking";

    private final MetricsConfiguration metricsConfiguration;
    private StatsDClient statsDClient;

    public StatsDMetricsCollector(MetricsConfiguration metricsConfiguration) {
        this.metricsConfiguration = checkNotNull(metricsConfiguration);
        statsDClient = buildMetricsProxy();
    }

    /**
     * verifies if the given property exists in the config.
     * @param property to verify
     */
//    private void checkConfigPropertyExists(String property) {
//        if (!configuration.hasPath(property)) {
//            throw new InvalidConfigurationException(property + " is not specified in the config");
//        }
//    }

  /*
  CLIENT_TYPE - "dataDogNonBlocking"
  tags - "stack"
  prefix - ""
  host - "docker-prod.west.usermind.com"
  port - 8125



   */
    /**
     * build the actual statsd client we will use.
     * the proxy is meant to hide the underneath implementation from the client
     * @return the clients that this class will use internally
     */
    private StatsDClient buildMetricsProxy() {
        String clientType = metricsConfiguration.getClientType();
        if (clientType.equals(NO_OP_CLIENT_TYPE)) {
            return new NoOpStatsDClient();
        } else if (clientType.equals(NON_BLOCKING_CLIENT_TYPE)) {

            ImmutableList<String> tags = ImmutableList.copyOf(metricsConfiguration.getTags());

            if (StringUtils.isEmpty(metricsConfiguration.getHost())) {
                throw new InvalidConfigurationException("Metrics:Host is not specified in the config");
            }
            return new NonBlockingStatsDClient(metricsConfiguration.getPrefix(),
                    metricsConfiguration.getHost(),
                    metricsConfiguration.getPort(),
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
