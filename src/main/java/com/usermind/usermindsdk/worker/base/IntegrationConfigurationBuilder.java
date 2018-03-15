package com.usermind.usermindsdk.worker.base;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * used to build the configuration associated with an integration version.
 */
public class IntegrationConfigurationBuilder {

  private final ConfigurationBuilder configurationBuilder;

  public IntegrationConfigurationBuilder() {
    configurationBuilder = new ConfigurationBuilder();
  }

  /**
   * sets the integration type.
   *
   * @param integrationType integration type
   * @return the config builder with the new property set
   */
  public IntegrationConfigurationBuilder setIntegrationType(String integrationType) {
    checkNotNull(integrationType);
    configurationBuilder.put("integration.type.name", integrationType);
    return this;
  }

  /**
   * returns the integration type from a provided configuration.
   *
   * @param integConfiguration provided config
   * @return integration type
   */
  public static String getIntegrationType(Configuration integConfiguration) {
    return integConfiguration.getString("integration.type.name");
  }

  /**
   * set the integration type level config.
   *
   * @param config desired config
   * @return the config builder with the new property set
   */
  public IntegrationConfigurationBuilder setIntegrationTypeConfig(Configuration config) {
    checkNotNull(config);
    configurationBuilder.putConf("integration.type.config",config);
    return this;
  }

  /**
   * returns the integration type config from a provided configuration.
   *
   * @param integConfiguration provided config
   * @return integration type config
   */
  public static Configuration getIntegrationTypeConfig(Configuration integConfiguration) {
    return integConfiguration.atPath("integration.type.config", ConfigurationBuilder.getEmpty());
  }

  /**
   * set the version.
   *
   * @param version desired version
   * @return the config builder with the new property set
   */
  public IntegrationConfigurationBuilder setVersion(String version) {
    checkNotNull(version);
    configurationBuilder.put("integration.version", version);
    return this;
  }

  /**
   * returns integration version from a provided config.
   *
   * @param integConfiguration provided config
   * @return integ version
   */
  public static String getVersion(Configuration integConfiguration) {
    return integConfiguration.getString("integration.version");
  }

  /**
   * set the action and the builder key associated with the action.
   *
   * @param actionName          action name
   * @param actionConfiguration action configuration
   * @return the config builder with the new property set
   */
  public IntegrationConfigurationBuilder setAction(String actionName,
                                                   Configuration actionConfiguration) {
    checkNotNull(actionName);
    configurationBuilder.putConf("integration.config.actions." + actionName, actionConfiguration);
    return this;
  }

  /**
   * return the actions defined for this integration from a provided config.
   *
   * @param integConfiguration provided config
   * @return Configuration holding the actions
   */
  public static Configuration getActions(Configuration integConfiguration) {
    return integConfiguration.atPath("integration.config.actions", ConfigurationBuilder.getEmpty());
  }

  /**
   * sets the integration level config.
   *
   * @param config desired config
   * @return the config builder with the new property set
   */
  public IntegrationConfigurationBuilder setIntegrationConfig(Configuration config) {
    checkNotNull(config);
    configurationBuilder.putConf("integration.config", config);
    return this;
  }

  /**
   * retrieves the integration config from a provided config.
   *
   * @param integConfiguration provided config
   * @return integration config
   */
  public static Configuration getIntegrationConfig(Configuration integConfiguration) {
    return integConfiguration.atPath("integration.config", ConfigurationBuilder.getEmpty());
  }

  /**
   * sets the default connection state. this will be used in case there isn't any connection state
   * on the first run
   *
   * @param config desired default connection state
   * @return the config builder with the new property set
   */
  public IntegrationConfigurationBuilder setDefaultConnectionState(Configuration config) {
    Configuration defaultConnectionState = new ConfigurationBuilder().toConfiguration();
    if (config != null) {
      defaultConnectionState = config;
    }
    configurationBuilder.putConf("integration.connectionstate.default", defaultConnectionState);
    return this;
  }

  /**
   * get default Connection state for given integConfig.
   *
   * @param integConfiguration provided config
   * @return default connection state
   */
  public static Configuration getDefaultConnectionState(Configuration integConfiguration) {
    return integConfiguration.atPath("integration.connectionstate.default",
        ConfigurationBuilder.getEmpty());
  }

  /**
   * tells us if this specific version of the integration is live. across all versions of an
   * integration type only one version can be live at a time the live version should be used when
   * launching new actions of that integration type without specifying a version (basically live
   * tags the default version)
   *
   * @param isLive flag to indicate if integ is live or not
   * @return the config builder with the new property set
   */
  public IntegrationConfigurationBuilder setLive(boolean isLive) {
    configurationBuilder.put("integration.live", isLive);
    return this;
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
