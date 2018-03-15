package com.usermind.usermindsdk.common.config;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import com.amazonaws.util.StringUtils;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.ConfigValueType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;

/**
 * An immutable object mapping configuration paths to configuration values. Used for handling
 * configurations
 *
 * <p>
 * Current implementation internally is built on top of {@link Config} class from
 * https://github.com/typesafehub/config library.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
// Switches off type info if the one is set
@JsonDeserialize(using = ConfigurationJsonDeserializer.class)
public class Configuration {

  private final Config config;

  /**
   * This constructor is package-private, so that it can only be called from classes within this
   * library, and should not be visible for external code.
   *
   * @param config the config.
   */
  Configuration(Config config) {
    this.config = checkNotNull(config);
  }



  /**
   * helper method that builds a path from a prefix and a key.
   *
   * @param parts array of String type path's parts
   * @return full path
   */
  public static String buildFullPath(String... parts) {
    checkNotNull(parts);
    return Joiner.on(".").join(parts);
  }

  /**
   * Returns a new value computed by merging this value with another, with keys in the other value
   * "winning" over the this' one.
   *
   * <p>
   * Let's assume we have the following property sets:
   *
   * <pre>
   *  c1:
   *    (foo: defaultFooValue)
   *    (bar: defaultBarValue)
   *  c2:
   *    (bar: overrideingBarValue)
   *    (baz: bazValue)
   * </pre>
   *
   * <p>
   * The results are:
   *
   * <pre>
   * Configuration result = c1.merge(c2);
   *
   * result.getString("foo"); // returns "defaultFooValue"
   * result.getString("bar"), // returns "overridingBarValue"
   * result.getString("baz"), // returns "bazValue"
   * </pre>
   *
   * @param overridingConfiguration an object whose keys take precedence over the keys in this
   *        object.
   * @return a new configuration object containing all the keys from the current one and the
   *         provided one.
   * @see Config#withFallback(com.typesafe.config.ConfigMergeable) for more details about how
   *      merging works under the hood.
   */
  public Configuration merge(Configuration overridingConfiguration) {
    return new Configuration(overridingConfiguration.config.withFallback(this.config));
  }

  /**
   * mergeAtPath. Same as merge but the merge is done at a given path instead of root.
   * @param path path to merge at
   * @param partialConfiguration the configuration to merge in
   * @return configuration containing the merged give partial configuration
   */
  public Configuration mergeAtPath(String path, Configuration partialConfiguration) {
    if (StringUtils.isNullOrEmpty(path)) {
      return merge(partialConfiguration);
    }

    return new Configuration(config.withValue(path, partialConfiguration.config.root()));
  }

  /**
   * Returns the boolean value at the requested path.
   *
   * @param path path expression
   * @return the boolean value at the requested path
   */
  public boolean getBoolean(String path) {
    return config.getBoolean(path);
  }

  /**
   * Returns the boolean value at the requested path or default value if original doesn't exist.
   *
   * @param path path expression
   * @param defaultValue default value
   * @return the boolean value at the requested path
   */
  public boolean getBoolean(String path, boolean defaultValue) {
    return getBooleanAsOptional(path).orElse(defaultValue);
  }

  /**
   * Returns the int value at the requested path.
   *
   * @param path path expression
   * @return the int value at the requested path
   */
  public int getInt(String path) {
    return config.getInt(path);
  }

  /**
   * Returns the integer value at the requested path or default value if original doesn't exist.
   *
   * @param path path expression
   * @param defaultValue default value
   * @return the int value at the requested path
   */
  public int getInt(String path, int defaultValue) {
    return getIntegerAsOptional(path).orElse(defaultValue);
  }

  /**
   * Returns the long value at the requested path.
   *
   * @param path path expression
   * @return the long value at the requested path
   */
  public long getLong(String path) {
    return config.getLong(path);
  }

  /**
   * Returns the long value at the requested path or default value if original doesn't exist.
   *
   * @param path path expression
   * @param defaultValue default value
   * @return the long value at the requested path
   */
  public long getLong(String path, long defaultValue) {
    return getLongAsOptional(path).orElse(defaultValue);
  }

  /**
   * Returns the double value at the requested path.
   *
   * @param path path expression
   * @return the double value at the requested path
   */
  public double getDouble(String path) {
    return config.getDouble(path);
  }

  /**
   * Returns the double value at the requested path or default value if original doesn't exist.
   *
   * @param path path expression
   * @param defaultValue default value
   * @return the double value at the requested path
   */
  public double getDouble(String path, double defaultValue) {
    return getDoubleAsOptional(path).orElse(defaultValue);
  }

  /**
   * Returns the string value at the requested path.
   *
   * @param path path expression
   * @return the string value at the requested path
   */
  public String getString(String path) {
    return config.getString(path);
  }

  /**
   * Returns the string value at the requested path or default value if original doesn't exist.
   *
   * @param path path expression
   * @param defaultValue default value
   * @return the string value at the requested path
   */
  public String getString(String path, String defaultValue) {
    return getStringAsOptional(path).orElse(defaultValue);
  }

  /**
   * Returns any type value at the requested path.
   *
   * @param path path expression
   * @return the Object value at the requested path
   */
  public Object getValue(String path) {
    return config.getAnyRef(path);
  }

  /**
   * Returns any type value at the requested path or default value if original doesn't exist.
   *
   * @param path path expression
   * @param defaultValue default value
   * @return the Object value at the requested path
   */
  public Object getValue(String path, Object defaultValue) {
    return getValueAsOptional(path).orElse(defaultValue);
  }

  /**
   * Returns an Optional object of Boolean value at the requested path.
   *
   * @param path path expression
   * @return the boolean value at the requested path
   */
  public Optional<Boolean> getBooleanAsOptional(String path) {
    return hasPath(path) ? Optional.of(getBoolean(path)) : Optional.empty();
  }

  /**
   * Returns an Optional object of Integer value at the requested path.
   *
   * @param path path expression
   * @return the int value at the requested path
   */
  public OptionalInt getIntegerAsOptional(String path) {
    return hasPath(path) ? OptionalInt.of(getInt(path)) : OptionalInt.empty();
  }

  /**
   * Returns an Optional object Long value at the requested path.
   *
   * @param path path expression
   * @return the long value at the requested path
   */
  public OptionalLong getLongAsOptional(String path) {
    return hasPath(path) ? OptionalLong.of(getLong(path)) : OptionalLong.empty();
  }

  /**
   * Returns an Optional object Double value at the requested path.
   *
   * @param path path expression
   * @return the double value at the requested path
   */
  public OptionalDouble getDoubleAsOptional(String path) {
    return hasPath(path) ? OptionalDouble.of(getDouble(path)) : OptionalDouble.empty();
  }

  /**
   * Returns an Optional object of String value at the requested path.
   *
   * @param path path expression
   * @return the string value at the requested path
   */
  public Optional<String> getStringAsOptional(String path) {
    return hasPath(path) ? Optional.of(getString(path)) : Optional.empty();
  }

  /**
   * Returns an Optional object of any type value at the requested path.
   *
   * @param path path expression
   * @return the Object value at the requested path
   */
  public Optional<Object> getValueAsOptional(String path) {
    return hasPath(path) ? Optional.of(getValue(path)) : Optional.empty();
  }

  /**
   * Returns a list of configurations build by looking at the requested path.
   *
   * @param path path expression
   * @return list of Configurations
   */
  public List<Configuration> getConfigurationsList(String path) {
    List<Configuration> configurations = new ArrayList<>();
    for (Config subconfiguration : config.getConfigList(path)) {
      configurations.add(new Configuration(subconfiguration));
    }
    return configurations;
  }

  /**
   * Returns a list of configurations build by looking at the requested path or default value
   * if original is missed.
   *
   * @param path path expression
   * @param defaultValue default if not present
   * @return list of Configurations
   */
  public List<Configuration> getConfigurationsList(String path,
                                                   List<Configuration> defaultValue) {
    return getConfigurationListAsOptional(path).orElse(defaultValue);
  }

  /**
   * Returns an Optional object of configurations list build by looking at the requested path.
   *
   * @param path path expression
   * @return list of Configurations
   */
  public Optional<List<Configuration>> getConfigurationListAsOptional(String path) {
    return hasPath(path) ? Optional.of(getConfigurationsList(path)) : Optional.empty();
  }

  /**
   * Returns a list of strings by looking at the requested path.
   * @param path path expression
   * @return list of String
   */
  public List<String> getStringList(String path) {
    return config.getStringList(path);
  }

  /**
   * Returns a list of strings by looking at the requested path or default value if original
   * is missed.
   * @param path path expression
   * @param defaultValue default if not present
   * @return list of String
   */
  public List<String> getStringList(String path, List<String> defaultValue) {
    return getStringListAsOptional(path).orElse(defaultValue);
  }

  /**
   * Returns an Optional object of string values list by looking at the requested path.
   * @param path path expression
   * @return list of String
   */
  public Optional<List<String>> getStringListAsOptional(String path) {
    return hasPath(path) ? Optional.of(config.getStringList(path)) : Optional.empty();
  }

  /**
   * Returns a list of any Objects inside.
   * @param path path expression
   * @return list of any Objects
   */
  public List<?> getAnyValueList(String path) {
    return config.getAnyRefList(path);
  }

  /**
   * Returns all keys traversing configuration recursively exclude paths where value is NULL.
   * This method is deprecated as not work properly, it retrieves not all keys, the keys that has
   * NULL value won't be retrieved. Use getAllPaths instead of this method.
   *
   * @param path path which defines a level of configuration tree a traversal should start from, if
   *        it's empty then recursive traversal starts from the root
   * @return a set containing all traversed keys
   */
  @Deprecated
  public Set<String> getAllKeys(Optional<String> path) {
    Set<Entry<String, ConfigValue>> configurationEntries;
    if (path.isPresent()) {
      configurationEntries = config.getConfig(path.get()).entrySet();
    } else {
      configurationEntries = config.entrySet();
    }

    Set<String> keys = new HashSet<>();
    for (Entry<String, ConfigValue> entry : configurationEntries) {
      keys.add(entry.getKey());
    }
    return keys;
  }

  /**
   * Returns keys at a given level of configuration tree.
   * This method is deprecated to follow new naming convention using "path" instead "key" as we
   * improve "getAllKeys" method and now it called getAllPaths. Use getPaths instead of this method.
   *
   * @param path path which defines a level of configuration tree, if it's empty then keys of
   *        current level (root) are returned
   * @return keys at a given level of configuration tree
   */
  @Deprecated
  public Set<String> getKeys(Optional<String> path) {
    ConfigObject configurationObject;

    if (path.isPresent()) {
      configurationObject = config.getConfig(path.get()).root();
    } else {
      configurationObject = config.root();
    }

    return configurationObject.keySet();
  }

  /**
   * Returns keys at given level of configuration tree including paths where value is NULL.
   *
   * @param path path which defines a level of configuration tree, if it's empty then keys of
   *        current level (root) are returned
   * @return keys at a given level of configuration tree
   */
  public Set<String> getPaths(Optional<String> path) {
    ConfigObject configurationObject;

    if (path.isPresent()) {
      configurationObject = config.getConfig(path.get()).root();
    } else {
      configurationObject = config.root();
    }

    return configurationObject.keySet();
  }

  /**
   * Returns all keys at given an deeper levels of configuration tree including paths where value
   * is NULL .
   *
   * @param path path which defines a level of configuration tree, if it's empty then keys of
   *        current level (root) are returned
   * @return keys at a given level of configuration tree
   */
  public Set<String> getAllPaths(Optional<String> path) {
    Set<String> result = new HashSet<>();
    ConfigObject configurationObject;

    if (path.isPresent()) {
      configurationObject = config.getConfig(path.get()).root();
    } else {
      configurationObject = config.root();
    }

    for (String nodeName : configurationObject.keySet()) {
      String fullPath = path.isPresent() ? buildFullPath(path.get(), nodeName) : nodeName;
      ConfigValueType type = getFieldType(fullPath);
      if (ConfigValueType.OBJECT.equals(type)) {
        result.addAll(getAllPaths(Optional.of(fullPath)));
      } else {
        result.add(fullPath);
      }
    }

    return result;
  }

  /**
   * Returns a configuration sub tree defined by a given path.
   *
   * @param path path which defines a configuration sub tree
   * @return configuration sub tree
   */
  public Configuration atPath(String path) {
    checkArgument(!Strings.isNullOrEmpty(path));
    return new Configuration(config.getConfig(path));
  }

  /**
   * Returns a configuration sub tree defined by a given path or default value if original
   * is missed.
   *
   * @param path path which defines a configuration sub tree
   * @param defaultValue default if not present
   * @return configuration sub tree
   */
  public Configuration atPath(String path, Configuration defaultValue) {
    return atPathAsOptional(path).orElse(defaultValue);
  }

  /**
   * Returns an Optional object of configuration as sub tree defined by a given path.
   *
   * @param path path which defines a configuration sub tree
   * @return configuration sub tree
   */
  public Optional<Configuration> atPathAsOptional(String path) {
    checkArgument(!Strings.isNullOrEmpty(path));
    return hasPath(path) ? Optional.of(atPath(path)) : Optional.empty();
  }

  /**
   * Returns a configuration copy without the given path.
   *
   * @param path path which defines a configuration sub tree we want to remove
   * @return configuration without the given path
   */
  public Configuration withoutPath(String path) {
    checkArgument(!Strings.isNullOrEmpty(path));
    return new Configuration(config.withoutPath(path));
  }

  /**
   * Checks whether a value is present and non-null at the given path.
   *
   * @param path path to check
   *
   * @return true if a non-null value is present at the path
   */
  public boolean hasPath(String path) {
    checkArgument(!Strings.isNullOrEmpty(path));
    return config.hasPath(path);
  }

  /**
   * Returns true if the {@code Configuration} is empty.
   *
   * @return true if the configuration is empty
   */
  public boolean isEmpty() {
    return config.isEmpty();
  }

  /**
   * Checks if path is present even when the value is null.
   *
   * @param path path to check
   *
   * @return true if path present even value is null.
   */
  public boolean hasPathOrNull(String path) {
    checkArgument(!Strings.isNullOrEmpty(path));
    return config.hasPathOrNull(path);
  }

  /**
   * Same as hasPath, but the config is invalid if the path is missing.
   *
   * @param path to check
   * @throws InvalidConfigurationException if given path is missing.
   */
  public void checkPathExists(String path) throws InvalidConfigurationException {
    if (!hasPath(path)) {
      throw new InvalidConfigurationException(
              "Configuration is invalid. Path " + path + " is missing");
    }
  }

  /**
   * Same as hasPathOrNull, but the config is invalid if the path is missing.
   *
   * @param path to check
   * @throws InvalidConfigurationException if given path is missing.
   */
  public void checkPathExistsOrNull(String path) throws InvalidConfigurationException {
    if (!hasPathOrNull(path)) {
      throw new InvalidConfigurationException(
              "Configuration is invalid. Path " + path + " is missing");
    }
  }

  /**
   * get the type associated with the object at the given path.
   * @param path to inspect
   * @return object type
   */
  public ConfigValueType getFieldType(String path) {
    try {
      return config.getValue(path).valueType();
    } catch (ConfigException.Null configNullEx) {
      return ConfigValueType.NULL;
    } catch (ConfigException.Missing missingEx) {
      throw new InvalidConfigurationException(
              "Configuration is invalid. Path " + path + " is missing", missingEx);
    }
  }

  @Override
  public int hashCode() {
    // we do the "42*" just so our hash code won't match that of the
    // underlying object. there's no real reason it can't match, but
    // making it not match might catch some kinds of bug.
    return 42 * config.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Configuration) {
      return config.equals(((Configuration) other).config);
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return toJson();
  }

  /**
   * This method is used by Jackson JSON mapper.
   *
   * @return {@code this} {@code Configuration} object serialized into a JSON string
   */
  @JsonValue
  @JsonRawValue
  private String toJson() {
    return config.root().render(ConfigRenderOptions.concise());
  }

  Config getConfig() {
    return config;
  }
}
