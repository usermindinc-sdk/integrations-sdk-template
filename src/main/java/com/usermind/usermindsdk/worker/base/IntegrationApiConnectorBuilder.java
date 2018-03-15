package com.usermind.usermindsdk.worker.base;


import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.worker.restapi.ResourceClient;
import com.usermind.usermindsdk.worker.restapi.ResourceClientBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Builder used by the legacy adapted and the driver.
 * Most integrations should use the IntegrationApiConnector and only
 * look at data associated with their own run
 */
public class IntegrationApiConnectorBuilder {
  private final Configuration connectorConfiguration;
  private final ResourceClient runResourceClient;
  private final Configuration kmsClient;

  private static Logger
      logger = LoggerFactory.getLogger(IntegrationApiConnectorBuilder.class);

  /**
   * IntegrationApiConnectorBuilder ctor.
   * @param connectorConfiguration configuration to use
   */
  public IntegrationApiConnectorBuilder(Configuration connectorConfiguration) {
    this.connectorConfiguration = connectorConfiguration;
    kmsClient = CommonLib.get().getConfiguration().atPath("integrationsWorker.kmsClient");
    this.runResourceClient = ResourceClientBuilder.build(this.connectorConfiguration,
                                                         ResourceClientBuilder.Type.RUN, kmsClient);
  }

  /**
   * get a list of runs in the specified range.
   * @param start of range
   * @param end of range
   * @param additionalParams additional params to filter on
   * @return list of apiconnectors, one for each run
   */
  public List<IntegrationApiConnector> getRuns(DateTime start, DateTime end,
                                               HashMap<String, String> additionalParams) {
    return getRuns(start, end, additionalParams, Integer.MAX_VALUE);
  }

  /**
   * get a list of runs in the specified range.
   * @param start of range
   * @param end of range
   * @param additionalParams additional params to filter on
   * @param limit max number of runs to get (ie limit)
   * @return list of apiconnectors, one for each run
   */
  public List<IntegrationApiConnector> getRuns(DateTime start, DateTime end,
                                               HashMap<String, String> additionalParams,
                                               int limit) {
    logger.info("getRuns between " + start.toString() + " and " + end.toString());
    ArrayList<IntegrationApiConnector> connectors = new ArrayList<>();
    HashMap<String, String> params = new HashMap<>();
    params.put("start.created_at", start.toString());
    params.put("end.created_at", end.toString());
    params.put("sortOrder","asc");
    if (additionalParams != null) {
      params.putAll(additionalParams);
    }
    for (Configuration run: runResourceClient.query(params, limit)) {
      Configuration runIdOverride = new ConfigurationBuilder()
          .put("runId",run.getString("id"))
          .put("runConnectionId",run.getString("connection_id"))
          .toConfiguration();
      Configuration runConnectorConfig = this.connectorConfiguration.merge(runIdOverride);
      connectors.add(new IntegrationApiConnector(runConnectorConfig));
    }
    return connectors;
  }

  /**
   * conveninence method.
   * for {@link #getRuns(org.joda.time.DateTime, org.joda.time.DateTime, HashMap)}
   * @param start of range
   * @param end of range
   * @return list of apiconnectors, one for each run
   */
  public List<IntegrationApiConnector> getRuns(DateTime start, DateTime end) {
    return getRuns(start, end, null);
  }
}
