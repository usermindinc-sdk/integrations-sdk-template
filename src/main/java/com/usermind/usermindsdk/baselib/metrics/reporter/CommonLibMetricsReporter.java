package com.usermind.usermindsdk.baselib.metrics.reporter;

import com.google.common.base.Strings;
import com.google.common.collect.ObjectArrays;
import com.usermind.usermindsdk.baselib.metrics.MetricsCollectorClient;
import com.usermind.usermindsdk.common.boot.CommonLib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Common Lib metrics reporter. Uses {@code CommonLib.get().getMetrics()} client.
 */
public class CommonLibMetricsReporter implements MetricsReporter<MetricsCollectorClient> {

  private final String pathPattern;
  private final String[] defaultTags;
  private final MetricsCollectorClient metricsCollectorClient;

  private CommonLibMetricsReporter(String pathPrefix, String[] defaultTags,
      MetricsCollectorClient metricsCollectorClient) {

    checkArgument(!Strings.isNullOrEmpty(pathPrefix));
    this.pathPattern = pathPrefix + ".%s";
    this.defaultTags = checkNotNull(defaultTags);
    this.metricsCollectorClient = checkNotNull(metricsCollectorClient);
  }

  /**
   * See {@code MetricsCollectorClient.count} for more info.
   * @param relativePath relative path
   * @param delta delta
   * @param tag1 tag1 name
   * @param value1 tag1 value
   */
  @Override
  public void count(String relativePath, long delta, String tag1, Object value1) {
    count(relativePath, delta, new Tag(tag1, value1));
  }

  /**
   * See {@code MetricsCollectorClient.count} for more info.
   * @param relativePath relative path
   * @param delta delta
   * @param tag1 tag1 name
   * @param value1 tag1 value
   * @param tag2 tag2 name
   * @param value2 tag2 value
   */
  @Override
  public void count(String relativePath, long delta, String tag1, Object value1, String tag2,
      Object value2) {

    count(relativePath, delta, new Tag(tag1, value1), new Tag(tag2, value2));
  }

  /**
   * See {@code MetricsCollectorClient.count} for more info.
   * @param relativePath relative path
   * @param delta delta
   * @param tag1 tag1 name
   * @param value1 tag1 value
   * @param tag2 tag2 name
   * @param value2 tag2 value
   * @param tag3 tag3 name
   * @param value3 tag3 value
   */
  @Override
  public void count(String relativePath, long delta, String tag1, Object value1, String tag2,
      Object value2, String tag3, Object value3) {

    count(relativePath, delta, new Tag(tag1, value1), new Tag(tag2, value2), new Tag(tag3, value3));
  }

  /**
   * See {@code MetricsCollectorClient.count} for more info.
   * @param relativePath relative path
   * @param delta delta
   * @param tags tags
   */
  @Override
  public void count(String relativePath, long delta, Tag... tags) {
    checkArgument(!Strings.isNullOrEmpty(relativePath));
    checkNotNull(tags);

    String path = resolvePath(relativePath);
    String[] tagsWithDefaults = appendDefaultTags(toStringArray(tags));
    metricsCollectorClient.count(path, delta, tagsWithDefaults);
  }

  /**
   * See {@code MetricsCollectorClient.histogram} for more info.
   * @param relativePath relative path
   * @param value delta
   * @param tag1 tag1 name
   * @param value1 tag1 value
   */
  @Override
  public void histogram(String relativePath, long value, String tag1, Object value1) {
    histogram(relativePath, value, new Tag(tag1, value1));
  }

  /**
   * See {@code MetricsCollectorClient.histogram} for more info.
   * @param relativePath relative path
   * @param value delta
   * @param tag1 tag1 name
   * @param value1 tag1 value
   * @param tag2 tag2 name
   * @param value2 tag2 value
   */
  @Override
  public void histogram(String relativePath, long value, String tag1, Object value1, String tag2,
      Object value2) {

    histogram(relativePath, value, new Tag(tag1, value1), new Tag(tag2, value2));
  }

  /**
   * See {@code MetricsCollectorClient.histogram} for more info.
   * @param relativePath relative path
   * @param value delta
   * @param tag1 tag1 name
   * @param value1 tag1 value
   * @param tag2 tag2 name
   * @param value2 tag2 value
   * @param tag3 tag2 name
   * @param value3 tag2 value
   */
  @Override
  public void histogram(String relativePath, long value, String tag1, Object value1, String tag2,
      Object value2, String tag3, Object value3) {

    histogram(relativePath, value, new Tag(tag1, value1), new Tag(tag2, value2),
        new Tag(tag3, value3));
  }

  /**
   * See {@code MetricsCollectorClient.histogram} for more info.
   * @param relativePath relative path
   * @param value delta
   * @param tags tags
   */
  @Override
  public void histogram(String relativePath, long value, Tag... tags) {
    checkArgument(!Strings.isNullOrEmpty(relativePath));
    checkNotNull(tags);

    String path = resolvePath(relativePath);
    String[] tagsWithDefaults = appendDefaultTags(toStringArray(tags));
    metricsCollectorClient.histogram(path, value, tagsWithDefaults);
  }

  /**
   * See {@code MetricsCollectorClient.gauge} for more info.
   * @param relativePath relative path
   * @param value delta
   * @param tag1 tag1 name
   * @param value1 tag1 value
   */
  @Override
  public void gauge(String relativePath, long value, String tag1, Object value1) {
    gauge(relativePath, value, new Tag(tag1, value1));
  }

  /**
   * See {@code MetricsCollectorClient.gauge} for more info.
   * @param relativePath relative path
   * @param value delta
   * @param tag1 tag1 name
   * @param value1 tag1 value
   * @param tag2 tag2 name
   * @param value2 tag2 value
   */
  @Override
  public void gauge(String relativePath, long value, String tag1, Object value1, String tag2,
      Object value2) {

    gauge(relativePath, value, new Tag(tag1, value1), new Tag(tag2, value2));
  }

  /**
   * See {@code MetricsCollectorClient.gauge} for more info.
   * @param relativePath relative path
   * @param value delta
   * @param tag1 tag1 name
   * @param value1 tag1 value
   * @param tag2 tag2 name
   * @param value2 tag2 value
   * @param tag3 tag2 name
   * @param value3 tag2 value
   */
  @Override
  public void gauge(String relativePath, long value, String tag1, Object value1, String tag2,
      Object value2, String tag3, Object value3) {

    gauge(relativePath, value, new Tag(tag1, value1), new Tag(tag2, value2),
        new Tag(tag3, value3));
  }

  /**
   * See {@code MetricsCollectorClient.gauge} for more info.
   * @param relativePath relative path
   * @param value delta
   * @param tags tags
   */
  @Override
  public void gauge(String relativePath, long value, Tag... tags) {
    checkArgument(!Strings.isNullOrEmpty(relativePath));
    checkNotNull(tags);

    String path = resolvePath(relativePath);
    String[] tagsWithDefaults = appendDefaultTags(toStringArray(tags));
    metricsCollectorClient.gauge(path, value, tagsWithDefaults);
  }

  /**
   * See {@code MetricsCollectorClient.increment} for more info.
   * @param relativePath relative path
   * @param tag1 tag1 name
   * @param value1 tag1 value
   */
  @Override
  public void increment(String relativePath, String tag1, Object value1) {
    increment(relativePath, new Tag(tag1, value1));
  }

  /**
   * See {@code MetricsCollectorClient.increment} for more info.
   * @param relativePath relative path
   * @param tag1 tag1 name
   * @param value1 tag1 value
   * @param tag2 tag1 name
   * @param value2 tag1 value
   */
  @Override
  public void increment(String relativePath, String tag1, Object value1, String tag2,
      Object value2) {
    increment(relativePath, new Tag(tag1, value1), new Tag(tag2, value2));
  }

  /**
   * See {@code MetricsCollectorClient.increment} for more info.
   * @param relativePath relative path
   * @param tag1 tag1 name
   * @param value1 tag1 value
   * @param tag2 tag1 name
   * @param value2 tag1 value
   * @param tag3 tag1 name
   * @param value3 tag1 value
   */
  @Override
  public void increment(String relativePath, String tag1, Object value1, String tag2, Object value2,
      String tag3, Object value3) {

    increment(relativePath, new Tag(tag1, value1), new Tag(tag2, value2), new Tag(tag3, value3));
  }

  /**
   * See {@code MetricsCollectorClient.increment} for more info.
   * @param relativePath relative path
   * @param tags tags
   */
  @Override
  public void increment(String relativePath, Tag... tags) {
    checkArgument(!Strings.isNullOrEmpty(relativePath));
    checkNotNull(tags);

    String path = resolvePath(relativePath);
    String[] tagsWithDefaults = appendDefaultTags(toStringArray(tags));
    metricsCollectorClient.increment(path, tagsWithDefaults);
  }

  /**
   * See {@code MetricsCollectorClient.decrement} for more info.
   * @param relativePath relative path
   * @param tag1 tag1 name
   * @param value1 tag1 value
   */
  @Override
  public void decrement(String relativePath, String tag1, Object value1) {
    decrement(relativePath, new Tag(tag1, value1));
  }

  /**
   * See {@code MetricsCollectorClient.decrement} for more info.
   * @param relativePath relative path
   * @param tag1 tag1 name
   * @param value1 tag1 value
   * @param tag2 tag1 name
   * @param value2 tag1 value
   */
  @Override
  public void decrement(String relativePath, String tag1, Object value1, String tag2,
      Object value2) {
    decrement(relativePath, new Tag(tag1, value1), new Tag(tag2, value2));
  }

  /**
   * See {@code MetricsCollectorClient.decrement} for more info.
   * @param relativePath relative path
   * @param tag1 tag1 name
   * @param value1 tag1 value
   * @param tag2 tag1 name
   * @param value2 tag1 value
   * @param tag3 tag1 name
   * @param value3 tag1 value
   */
  @Override
  public void decrement(String relativePath, String tag1, Object value1, String tag2, Object value2,
      String tag3, Object value3) {

    decrement(relativePath, new Tag(tag1, value1), new Tag(tag2, value2), new Tag(tag3, value3));
  }

  /**
   * See {@code MetricsCollectorClient.decrement} for more info.
   * @param relativePath relative path
   * @param tags tags
   */
  @Override
  public void decrement(String relativePath, Tag... tags) {
    checkArgument(!Strings.isNullOrEmpty(relativePath));
    checkNotNull(tags);

    String path = resolvePath(relativePath);
    String[] tagsWithDefaults = appendDefaultTags(toStringArray(tags));
    metricsCollectorClient.decrement(path, tagsWithDefaults);
  }

  private String resolvePath(String relativePath) {
    return String.format(pathPattern, relativePath);
  }

  private String[] appendDefaultTags(String[] tags) {
    return ObjectArrays.concat(tags, defaultTags, String.class);
  }

  private String[] toStringArray(Tag[] tags) {
    return Arrays.stream(tags).map(Tag::toString).toArray(String[]::new);
  }

  @Override
  public MetricsCollectorClient getClient() {
    return metricsCollectorClient;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * Tag name/value wrapper. Note: allows null value.
   */
  public static class Tag {

    private static final String NAME_VALUE_SEPARATOR = ":";

    private final String name;
    private final Object value;

    /**
     * {@code Tag} ctor.
     * @param name tag name
     * @param value tag value
     */
    public Tag(String name, Object value) {
      checkArgument(!Strings.isNullOrEmpty(name));
      this.name = name;
      this.value = checkNotNull(value);
    }

    public String getName() {
      return name;
    }

    public Object getValue() {
      return value;
    }

    @Override
    public String toString() {
      return name + NAME_VALUE_SEPARATOR + value;
    }
  }

  public static class Builder {

    private String pathPrefix;
    private List<Tag> defaultTags = new ArrayList<>();
    private Optional<MetricsCollectorClient> metricsCollectorClient = Optional.empty();

    public Builder setPathPrefix(String pathPrefix) {
      this.pathPrefix = checkNotNull(pathPrefix);
      return this;
    }

    /**
     * Default tags are passed with each method call.
     * @param name tag name
     * @param value tag value
     * @return this
     */
    public Builder addDefaultTag(String name, Object value) {
      defaultTags.add(new Tag(name, value));
      return this;
    }

    /**
     * Used in *unit tests*.
     * @param metricsCollectorClient client
     * @return this
     */
    Builder setMetricsCollectorClient(MetricsCollectorClient metricsCollectorClient) {
      this.metricsCollectorClient = Optional.ofNullable(metricsCollectorClient);
      return this;
    }

    /**
     * Builds a {@code CommonLibMetricsReporter}.
     * @return new {@code CommonLibMetricsReporter}
     */
    public CommonLibMetricsReporter build(MetricsCollectorClient metricsCollectorClient) {
      String[] defaultTagsArray = defaultTags.stream().map(Tag::toString).toArray(String[]::new);
      //MetricsCollectorClient commonLibClient = CommonLib.get().getMetrics();
      return new CommonLibMetricsReporter(pathPrefix, defaultTagsArray,
          metricsCollectorClient);
    }
  }
}
