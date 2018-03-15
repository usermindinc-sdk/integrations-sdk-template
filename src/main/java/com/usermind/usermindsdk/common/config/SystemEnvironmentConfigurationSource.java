package com.usermind.usermindsdk.common.config;

public class SystemEnvironmentConfigurationSource implements ConfigurationSource {

  @Override
  public Configuration load() {
    return ConfigurationBuilder.fromEnvironment();
  }
}
