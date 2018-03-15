package com.usermind.usermindsdk.common.config;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Resources;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigList;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueFactory;


import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Convenience class to build an immutable {@link Configuration}. This class is to
 * {@link Configuration} as {@link StringBuilder} is to {@link String}.
 */
public final class ConfigurationBuilder {
  private static final Configuration EMPTY = new Configuration(ConfigFactory.empty());
  private Config properties;

  /**
   * Create new {@link ConfigurationBuilder} instance with no properties.
   */
  public ConfigurationBuilder() {
    properties = ConfigFactory.empty();
  }

  /**
   * Create new {@link ConfigurationBuilder} instance and initialize it with the properties provided
   * by given configuration.
   *
   * @param configuration configuration providing set of properties to start with.
   */
  public ConfigurationBuilder(Configuration configuration) {
    properties = configuration.getConfig();
  }

  private ConfigurationBuilder update(String path, ConfigValue value) {
    properties = ConfigFactory.empty().withValue(path, value).withFallback(properties);
    return this;
  }

  /**
   * Add new key-value pair to the builder. If a property with specified key already exists, it's
   * will be replaced with the provided value, unless both old and new values are map or config
   * values, in which case the values will be (effectively) merged, with any conflicts resolved
   * in favor of the new value.
   *
   * @param path property key (path).
   * @param value property value.
   * @return current instance of builder.
   */
  public ConfigurationBuilder put(String path, Object value) {
    if (value instanceof Configuration) {
      return putConf(path, (Configuration) value);
    }
    return update(path, ConfigValueFactory.fromAnyRef(value));
  }

  /**
   * Add new key-configuration pair to builder. If a property with specified key already exists, it
   * follows the rules as in {@link #put(String, Object)}.
   *
   * @param path property key (path).
   * @param configuration mmapped to the key
   * @return current instance of builder.
   */
  public ConfigurationBuilder putConf(String path, Configuration configuration) {
    return update(path, configuration.getConfig().root());
  }

  /**
   * Add new List of Configuration objects into given path.
   *
   * @param path property key (path).
   * @param configurations list of configurations
   * @return current instance of builder.
   */
  public ConfigurationBuilder putConfList(String path, List<Configuration> configurations) {
    ConfigList configList = ConfigValueFactory.fromIterable(configurations.stream()
        .map(c -> c.getConfig().root()).collect(Collectors.toList()));
    return update(path, configList);
  }

  /**
   * Add new List of any objects into given path.
   *
   * @param path property key (path).
   * @param configurations list of any objects
   * @return current instance of builder.
   */
  public ConfigurationBuilder putAnyValueList(String path, List<?> configurations) {
    ConfigList configList = ConfigValueFactory.fromIterable(configurations.stream()
        .map(c -> (c instanceof Configuration) ? ((Configuration) c).getConfig().root() : c)
        .collect(Collectors.toList()));
    return update(path, configList);
  }

  /**
   * @return a {@link Configuration} containing the properties added to this builder.
   */
  public Configuration toConfiguration() {
    return new Configuration(properties);
  }

  /**
   * Create configuration from map.
   *
   * @param properties the properties
   * @return configuration object containing all the properties from given map
   */
  public static Configuration createConfiguration(Map<String, ? extends Object> properties) {
    Preconditions.checkNotNull(properties);
    return new Configuration(ConfigFactory.parseMap(properties));
  }

  /**
   * Create configuration from json string.
   *
   * @param jsonConfig string in json format.
   * @return configuration object containing properties parsed from given json string.
   */
  public static Configuration createConfiguration(String jsonConfig) {
    return new Configuration(ConfigFactory.parseString(jsonConfig));
  }

  /**
   * Returns a {@code Configuration} containing a single entry.
   * @param key key.
   * @param value value.
   * @return {@code Configuration} with 1 key-value mapping.
   */
  public static Configuration createConfiguration(String key, Object value) {
    checkArgument(isNotBlank(key));
    Config config = ConfigFactory.empty()
        .withValue(key, ConfigValueFactory.fromAnyRef(value));
    return new Configuration(config);
  }

  /**
   * Returns a {@code Configuration} containing a single entry.
   * @param key1 first key.
   * @param value1 first value.
   * @param key2 second key.
   * @param value2 second value.
   * @return {@code Configuration} with 2 key-value mappings.
   */
  public static Configuration createConfiguration(String key1, Object value1, String key2,
      Object value2) {
    checkArgument(isNotBlank(key1));
    checkArgument(isNotBlank(key2));
    Config config = ConfigFactory.empty()
        .withValue(key1, ConfigValueFactory.fromAnyRef(value1))
        .withValue(key2, ConfigValueFactory.fromAnyRef(value2));
    return new Configuration(config);
  }

  /**
   * Returns a {@code Configuration} containing a single entry.
   * @param key1 first key.
   * @param value1 first value.
   * @param key2 second key.
   * @param value2 second value.
   * @param key3 third key.
   * @param value3 third value.
   * @return {@code Configuration} with 3 key-value mappings.
   */
  public static Configuration createConfiguration(String key1, Object value1, String key2,
      Object value2, String key3, Object value3) {
    checkArgument(isNotBlank(key1));
    checkArgument(isNotBlank(key2));
    checkArgument(isNotBlank(key3));
    Config config = ConfigFactory.empty()
        .withValue(key1, ConfigValueFactory.fromAnyRef(value1))
        .withValue(key2, ConfigValueFactory.fromAnyRef(value2))
        .withValue(key3, ConfigValueFactory.fromAnyRef(value3));
    return new Configuration(config);
  }

  /**
   * Reads a {@code Configuration} from a resource.
   * @param resourceName resource path.
   * @return {@code Configuration}
   * @throws IllegalArgumentException if the resource is not found
   * @throws RuntimeException having an {@code IOException} as cause
   */
  public static Configuration fromResource(String resourceName) {
    checkArgument(isNotBlank(resourceName));
    try {
      URL url = Resources.getResource(resourceName);
      String resourceAsString = Resources.toString(url, Charsets.UTF_8);
      return ConfigurationBuilder.createConfiguration(resourceAsString);
    } catch (IOException ex) {
      String message = String.format("Failed to build Configuration from resource: '%s'",
          resourceName);
      throw new RuntimeException(message, ex);
    }
  }

  /**
   * Create configuration from system environment.
   *
   * @return configuration object containing properties parsed from system environment variables.
   */
  public static Configuration fromEnvironment() {
    return new Configuration(ConfigFactory.parseMap(
        System.getenv().entrySet().stream().collect(Collectors.toMap(
            e -> safePath(e.getKey()),
            Map.Entry::getValue
        ))
    ));
  }

  private static String safePath(String key) {
    try {
      ConfigFactory.parseMap(Collections.singletonMap(key, 1));
      return key;
    } catch (Exception ex) {
      return '"' + key + '"';
    }
  }

  /**
   * Create configuration from system properties.
   *
   * @return configuration object containing properties parsed from system properties.
   */
  public static Configuration fromSystemProperties() {
    return new Configuration(ConfigFactory.systemProperties());
  }

  /**
   * Helper to build an empty config to prevent needing new ConfigurationBuilder().toConfiguration.
   * @return empty config
   */
  public static Configuration getEmpty() {
    return EMPTY;
  }
}
