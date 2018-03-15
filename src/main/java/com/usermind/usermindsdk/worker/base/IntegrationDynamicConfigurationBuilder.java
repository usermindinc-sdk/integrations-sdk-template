package com.usermind.usermindsdk.worker.base;


import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.usermind.usermindsdk.common.config.ConfigurationBuilder.getEmpty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Used to build a dynamic configuration associated with a connection.
 */
public class IntegrationDynamicConfigurationBuilder {

  private final ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();

  /**
   * set the action and the builder key associated with the action.
   *
   * @param actionName          action name
   * @param actionConfiguration action configuration
   * @return the config builder with the new property set
   */
  public IntegrationDynamicConfigurationBuilder setAction(String actionName,
                                                   Configuration actionConfiguration) {
    checkArgument(isNotBlank(actionName));
    checkNotNull(actionConfiguration);
    configurationBuilder.putConf("dynamic.config.actions." + actionName, actionConfiguration);
    return this;
  }

  /**
   * return the actions defined for this connection from a provided config.
   *
   * @param integrationDynamicConfig provided config
   * @return Configuration holding the actions
   */
  public static Configuration getActions(Configuration integrationDynamicConfig) {
    checkNotNull(integrationDynamicConfig);
    return integrationDynamicConfig.atPath("dynamic.config.actions", getEmpty());
  }

  /**
   * build the final immutable Configuration object via the builder.
   *
   * @return the configuration that we've just built with this builder
   */
  public Configuration toConfiguration() {
    return configurationBuilder.toConfiguration();
  }
}
