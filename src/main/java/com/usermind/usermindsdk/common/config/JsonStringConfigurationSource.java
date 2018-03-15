package com.usermind.usermindsdk.common.config;

import com.google.common.base.Preconditions;
import com.typesafe.config.ConfigFactory;

/**
 * Configuration source loading configuration from json passed as a string.
 *
 * <p>
 * This implementation does not provide any json validation until the {@link #load()} method is
 * called.
 */
public class JsonStringConfigurationSource implements ConfigurationSource {

  private String jsonConfig;

  /**
   * Creates a new JsonStringConfigurationSource which reads configuration from given string.
   *
   * <p>
   * Note that the json is not validated until the {@link #load()} method is called.
   *
   * @param jsonConfig configuration in json format.
   */
  public JsonStringConfigurationSource(String jsonConfig) {
    this.jsonConfig = Preconditions.checkNotNull(jsonConfig);
  }

  @Override
  public Configuration load() {
    return new Configuration(ConfigFactory.parseString(jsonConfig));
  }
}
