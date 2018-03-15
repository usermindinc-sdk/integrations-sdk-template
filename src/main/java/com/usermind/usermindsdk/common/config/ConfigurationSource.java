package com.usermind.usermindsdk.common.config;

/**
 * Interface definition for configuration sources. All configuration sources must adhere to this
 * interface. Specifics on how the actual loading and persisting is done should be captured in the
 * specific implementations
 */
public interface ConfigurationSource {

  /**
   * Loads and returns the configuration from the source.
   *
   * @return configuration
   */
  Configuration load();

  /**
   * Persists the given configuration to the source.
   * Note: by default throws {@code UnsupportedOperationException}.
   *
   * @param configuration configuration we are persisting
   */
  default void save(Configuration configuration) {
    throw new UnsupportedOperationException("Save operation is not supported by "
        + this.getClass().getName());
  }
}
