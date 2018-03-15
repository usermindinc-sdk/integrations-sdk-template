package com.usermind.usermindsdk.worker.util;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.base.IntegrationConfigurationBuilder;

public class IntegrationVersionDescriptionUtil {
  /**
   * Generates a workflow id for an integration version description
   * @param integrationType
   * @param version
   * @return workflow id
   */
  public static String generateWorkflowId(String integrationType, String version) {
    return integrationType + "-" + version;
  }

  /**
   * Generates a workflow id for an integration version description
   * @param integrationConfig
   * @return
   */
  public static String generateWorkflowId(Configuration integrationConfig) {
    String integrationType = IntegrationConfigurationBuilder.getIntegrationType(integrationConfig);
    String version = IntegrationConfigurationBuilder.getVersion(integrationConfig);
    return generateWorkflowId(integrationType, version);
  }

}
