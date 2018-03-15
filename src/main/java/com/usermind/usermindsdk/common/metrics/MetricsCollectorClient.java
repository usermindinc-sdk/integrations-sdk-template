package com.usermind.usermindsdk.common.metrics;

/**
 * Describes the metrics client connection interface, which may be used to post metrics in the form
 * of counters, timers, and gauges.
 *
 * <p> This is inspired by the StatsDClient interfaces found here: <ul>
 * <li>https://github.com/tim-group/java-statsd-client/blob/master/src/main/java/com/timgroup/statsd/StatsDClient.java</li>
 * <li>https://github.com/indeedeng/java-dogstatsd-client/blob/master/src/main/java/com/timgroup/statsd/StatsDClient.java</li>
 * </ul>
 *
 * <p> The reason why we are doing this is to isolate the client from the implementation. In the
 * original iteration it's going to be StatsD behind it (ie we will effectively proxy the calls) but
 * the glue code might get more complicated if for example we want to support cloudwatch (or other
 * type of metrics) via the commonlib
 *
 * <p> 3 key methods are provided for the submission of data-points for the application under
 * scrutiny: <ul> <li>{@link #incrementCounter} - adds one to the value of the specified named
 * counter</li> <li>{@link #recordGaugeValue} - records the latest fixed value for the specified
 * named gauge</li> <li>{@link #recordExecutionTime} - records an execution time in milliseconds for
 * the specified named operation</li> </ul>
 */
public interface MetricsCollectorClient {

  /**
   * Cleanly shut down. This method may throw an exception if the socket cannot be closed.
   */
  void stop();

  /**
   * Adjusts the specified counter by a given delta.
   *
   * <p>This addition of tags to the method is a DataDog extension, and may not work with other
   * servers.</p>
   *
   * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
   *
   * @param aspect the name of the counter to adjust
   * @param delta  the amount to adjust the counter by
   * @param tags   array of tags to be added to the data
   */
  void count(String aspect, long delta, String... tags);

  /**
   * Increments the specified counter by one.
   *
   * <p>This addition of tags to the method is a DataDog extension, and may not work with other
   * servers.</p>
   *
   * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
   *
   * @param aspect the name of the counter to increment
   * @param tags   array of tags to be added to the data
   */
  void incrementCounter(String aspect, String... tags);

  /**
   * Convenience method equivalent to {@link #incrementCounter(String, String[])}.
   *
   * @param aspect the name of the counter to increment
   * @param tags   array of tags to be added to the data
   */
  void increment(String aspect, String... tags);

  /**
   * Decrements the specified counter by one.
   *
   * <p>This addition of tags to the method is a DataDog extension, and may not work with other
   * servers.</p>
   *
   * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
   *
   * @param aspect the name of the counter to decrement
   * @param tags   array of tags to be added to the data
   */
  void decrementCounter(String aspect, String... tags);

  /**
   * Convenience method equivalent to {@link #decrementCounter(String, String[])}.
   *
   * @param aspect the name of the counter to decrement
   * @param tags   array of tags to be added to the data
   */
  void decrement(String aspect, String... tags);

  /**
   * Records the latest fixed value for the specified named gauge.
   *
   * <p>This addition of tags to the method is a DataDog extension, and may not work with other
   * servers.</p>
   *
   * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
   *
   * @param aspect the name of the gauge
   * @param value  the new reading of the gauge
   * @param tags   array of tags to be added to the data
   */
  void recordGaugeValue(String aspect, double value, String... tags);

  /**
   * Records the latest fixed value for the specified named gauge.
   *
   * <p>This addition of tags to the method is a DataDog extension, and may not work with other
   * servers.</p>
   *
   * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
   *
   * @param aspect the name of the gauge
   * @param value  the new reading of the gauge
   * @param tags   array of tags to be added to the data
   */
  void recordGaugeValue(String aspect, long value, String... tags);

  /**
   * Convenience method equivalent to {@link #recordGaugeValue(String, double, String[])}.
   *
   * @param aspect the name of the gauge
   * @param value  the new reading of the gauge
   * @param tags   array of tags to be added to the data
   */
  void gauge(String aspect, double value, String... tags);

  /**
   * Convenience method equivalent to {@link #recordGaugeValue(String, long, String[])}.
   *
   * @param aspect the name of the gauge
   * @param value  the new reading of the gauge
   * @param tags   array of tags to be added to the data
   */
  void gauge(String aspect, long value, String... tags);

  /**
   * Records an execution time in milliseconds for the specified named operation.
   *
   * <p>This addition of tags to the method is a DataDog extension, and may not work with other
   * servers.</p>
   *
   * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
   *
   * @param aspect   the name of the timed operation
   * @param timeInMs the time in milliseconds
   * @param tags     array of tags to be added to the data
   */
  void recordExecutionTime(String aspect, long timeInMs, String... tags);

  /**
   * Convenience method equivalent to {@link #recordExecutionTime(String, long, String[])}.
   *
   * @param aspect   the name of the timed operation
   * @param value the time in milliseconds
   * @param tags     array of tags to be added to the data
   */
  void time(String aspect, long value, String... tags);

  /**
   * Records a value for the specified named histogram.
   *
   * <p>This addition of tags to the method is a DataDog extension, and may not work with other
   * servers.</p>
   *
   * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
   *
   * @param aspect the name of the histogram
   * @param value  the value to be incorporated in the histogram
   * @param tags   array of tags to be added to the data
   */
  void recordHistogramValue(String aspect, double value, String... tags);

  /**
   * Records a value for the specified named histogram.
   *
   * <p>This addition of tags to the method is a DataDog extension, and may not work with other
   * servers.</p>
   *
   * <p>This method is non-blocking and is guaranteed not to throw an exception.</p>
   *
   * @param aspect the name of the histogram
   * @param value  the value to be incorporated in the histogram
   * @param tags   array of tags to be added to the data
   */
  void recordHistogramValue(String aspect, long value, String... tags);

  /**
   * Convenience method equivalent to {@link #recordHistogramValue(String, double, String[])}.
   *
   * @param aspect the name of the histogram
   * @param value  the value to be incorporated in the histogram
   * @param tags   array of tags to be added to the data
   */
  void histogram(String aspect, double value, String... tags);

  /**
   * Convenience method equivalent to {@link #recordHistogramValue(String, long, String[])}.
   *
   * @param aspect the name of the histogram
   * @param value  the value to be incorporated in the histogram
   * @param tags   array of tags to be added to the data
   */
  void histogram(String aspect, long value, String... tags);
}
