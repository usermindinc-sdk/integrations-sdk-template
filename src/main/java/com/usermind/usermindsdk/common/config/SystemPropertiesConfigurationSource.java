package com.usermind.usermindsdk.common.config;

public class SystemPropertiesConfigurationSource implements ConfigurationSource {

  @Override
  public Configuration load() {
    return ConfigurationBuilder.fromSystemProperties();
  }
}
