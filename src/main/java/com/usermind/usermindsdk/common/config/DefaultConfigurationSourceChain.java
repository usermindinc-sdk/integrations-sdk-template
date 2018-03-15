package com.usermind.usermindsdk.common.config;


import static com.usermind.usermindsdk.common.config.DefaultConfigurationSource.COMMONLIB_CONFIG;
import static com.usermind.usermindsdk.common.config.JsonFileConfigurationSource.DEFAULT_FILE_CONFIGURATION_LOCATION;

public class DefaultConfigurationSourceChain extends ConfigurationSourceChain {
  private static volatile Configuration environment = null;

  private static Configuration getEnvironment() {
    if (environment == null) {
      synchronized (DefaultConfigurationSourceChain.class) {
        if (environment == null) {
          environment = new SystemEnvironmentConfigurationSource().load();
        }
      }
    }
    return environment;
  }
  /**
   * Create a configuration source combining data from the environment, system
   * properties, a json configuration file (default or environment specified)
   * and ec2 role, in priority order.
   */
  public DefaultConfigurationSourceChain() {
      super(DefaultConfigurationSourceChain::getEnvironment,
          new SystemPropertiesConfigurationSource(),
          new JsonFileConfigurationSource(
              getEnvironment().getString(COMMONLIB_CONFIG, DEFAULT_FILE_CONFIGURATION_LOCATION)
          ),
          new Ec2InstanceConfigurationSource());
  }
}
