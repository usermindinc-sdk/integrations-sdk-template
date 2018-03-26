package com.usermind.usermindsdk.baselib.metrics.reporter;

import static com.usermind.usermindsdk.baselib.metrics.reporter.CommonLibMetricsReporter.Tag;

/**
 * Metrics reporter interface. Extend its API with new methods as you need it.
 */
public interface MetricsReporter<T> {

  void count(String relativePath, long delta, String tag1, Object value1);

  void count(String relativePath, long delta, String tag1, Object value1, String tag2,
             Object value2);

  void count(String relativePath, long delta, String tag1, Object value1, String tag2,
             Object value2, String tag3, Object value3);

  void count(String relativePath, long delta, Tag... tags);

  void histogram(String relativePath, long value, String tag1, Object value1);

  void histogram(String relativePath, long value, String tag1, Object value1, String tag2,
                 Object value2);

  void histogram(String relativePath, long value, String tag1, Object value1, String tag2,
                 Object value2, String tag3, Object value3);

  void histogram(String relativePath, long value, Tag... tags);

  void gauge(String relativePath, long value, String tag1, Object value1);

  void gauge(String relativePath, long value, String tag1, Object value1, String tag2,
             Object value2);

  void gauge(String relativePath, long value, String tag1, Object value1, String tag2,
             Object value2, String tag3, Object value3);

  void gauge(String relativePath, long value, Tag... tags);

  void increment(String relativePath, String tag1, Object value1);

  void increment(String relativePath, String tag1, Object value1, String tag2, Object value2);

  void increment(String relativePath, String tag1, Object value1, String tag2, Object value2,
                 String tag3, Object value3);

  void increment(String relativePath, Tag... tags);

  void decrement(String relativePath, String tag1, Object value1);

  void decrement(String relativePath, String tag1, Object value1, String tag2, Object value2);

  void decrement(String relativePath, String tag1, Object value1, String tag2, Object value2,
                 String tag3, Object value3);

  void decrement(String relativePath, Tag... tags);

  /**
   * The underlying client.
   * @return client
   */
  T getClient();
}
