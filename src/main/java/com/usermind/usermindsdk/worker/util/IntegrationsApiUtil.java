package com.usermind.usermindsdk.worker.util;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.worker.restapi.ResourceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class IntegrationsApiUtil {
  private static final Logger LOGGER = LoggerFactory.getLogger(IntegrationsApiUtil.class);

  private IntegrationsApiUtil() {}

  public static void upsertIntegrationConfiguration(ResourceClient integrationClient,
                                                     Configuration integrationConfiguration,
                                                     String integrationType,
                                                     String integrationVersion,
                                                     String integrationTypeId) {


    HashMap<String, String> params = new HashMap<>();
    params.put("integration_type_id", integrationTypeId);
    params.put("version", integrationVersion);

    List<Configuration> restApiResponse = integrationClient.query(params);
    if (restApiResponse.isEmpty()) {
      LOGGER.info("Registering new Integration");
      Configuration integrationConf = new ConfigurationBuilder()
        .put("integration_type_id", integrationTypeId)
        .put("version",integrationVersion)
        .putConf("configuration", ConfigurationBuilder.getEmpty())
        .toConfiguration();
      integrationConf = integrationConf.mergeAtPath("configuration", integrationConfiguration);
      integrationClient.create("register_" + UUID.randomUUID().toString(), integrationConf);

      //Reissuing the query to see if the restAPIResponse got a value post creation when its empty.
      //Hack for the issue where it was not getting the ID
      restApiResponse = integrationClient.query(params);
    }

    if (restApiResponse.size() == 1) {
      Configuration integrationConf = restApiResponse.get(0);
      if (!integrationConf.atPath("configuration").equals(integrationConfiguration)) {
        LOGGER.info("Updating Integration Configuration");
        integrationConf = new ConfigurationBuilder(integrationConf.withoutPath("configuration"))
          .putConf("configuration", integrationConfiguration)
          .toConfiguration();
        integrationClient.update("update_" + UUID.randomUUID().toString(), integrationConf);
      }
    } else {
      String errMsg = "Found more than one integration_type = " + integrationType
        + " with version = " + integrationVersion;
      LOGGER.error(errMsg);
      throw new IllegalStateException(errMsg);
    }
  }

  public static String upsertIntegrationTypeConfiguration(ResourceClient integrationTypeClient,
                                                    Configuration integrationTypeConfiguration,
                                                    String integrationType) {
    Configuration integrationTypeConf;

    List<Configuration> restApiResponse = integrationTypeClient
      .query("integration_type", integrationType);
    if (restApiResponse.isEmpty()) {
      LOGGER.info("Registering new Integration Type");
      Configuration confIntegType = new ConfigurationBuilder()
        .put("integration_type", integrationType)
        .putConf("configuration", integrationTypeConfiguration)
        .toConfiguration();
      integrationTypeConf = integrationTypeClient
        .create("register_" + UUID.randomUUID().toString(), confIntegType);
      //Reissuing the query to see if the restAPIResponse got a value post creation when its empty.
      //Hack for the issue where it was not getting the ID
      restApiResponse = integrationTypeClient
        .query("integration_type", integrationType);
    }

    if (restApiResponse.size() == 1) {
      integrationTypeConf = restApiResponse.get(0);
      if (!integrationTypeConf.atPath("configuration").equals(integrationTypeConfiguration)) {
        LOGGER.info("Updating Integration Type Configuration");
        integrationTypeConf = new ConfigurationBuilder(integrationTypeConf
          .withoutPath("configuration"))
          .putConf("configuration", integrationTypeConfiguration)
          .toConfiguration();
        integrationTypeClient
          .update("update_" + UUID.randomUUID().toString(), integrationTypeConf);
      }
    } else {
      String errorMsg = "Found more than one integration_type = " + integrationType;
      LOGGER.error(errorMsg);
      throw new IllegalStateException(errorMsg);
    }
    return integrationTypeConf.getString("id");
  }


}
