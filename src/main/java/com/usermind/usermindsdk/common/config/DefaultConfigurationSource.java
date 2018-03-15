package com.usermind.usermindsdk.common.config;

import java.util.ArrayList;
import java.util.List;

/**
 * Default configuration source. To be used to dynamically attempt to probe all available
 * configuration sources
 */
public class DefaultConfigurationSource implements ConfigurationSource {

  public static final String COMMONLIB_CONFIG = "COMMONLIB_CONFIG";

  private List<ConfigurationSource> configurationSources;

  /**
   * Builds the default configuration source.
   *
   * <p>
   * The default source is one of the following:
   *
   * <ul>
   * <li>json file specified by the {@link #COMMONLIB_CONFIG} environment variable</li>
   * <li>a combination of the following sources picked up and merged in the order specified here:
   * {@link Ec2InstanceConfigurationSource}, {@link JsonFileConfigurationSource}</li>
   * </ul>
   */
  public DefaultConfigurationSource() {
    configurationSources = new ArrayList<>();

    Configuration environment = new SystemEnvironmentConfigurationSource().load();
    if (environment.hasPath(COMMONLIB_CONFIG)) {
      configurationSources
          .add(new JsonFileConfigurationSource(environment.getString(COMMONLIB_CONFIG)));
    } else {
      configurationSources.add(new Ec2InstanceConfigurationSource());
      configurationSources.add(new JsonFileConfigurationSource());
    }
  }

  @Override
  public Configuration load() {
    Configuration defaultConfiguration = null;
    List<Throwable> exceptions = new ArrayList<>();

    for (ConfigurationSource cs : configurationSources) {
      try {
        if (defaultConfiguration != null) {
          defaultConfiguration = defaultConfiguration.merge(cs.load());
        } else {
          defaultConfiguration = cs.load();
        }
      } catch (RuntimeException re) {
        exceptions.add(re);
      }
    }
    if (defaultConfiguration == null) {
      throw new ConfigurationLoadingException(
          "Failed to initialize from any of the specified sources", exceptions);
    }
    return defaultConfiguration;
  }
}
