package com.usermind.usermindsdk.worker.restapi;

import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Helper Builder Class for {@link ResourceClient}.
 */
public class ResourceClientBuilder {

  /**
   * all the types of resources that are available to us.
   */
  public enum Type {
    INTEGRATION_TYPE,
    INTEGRATION,
    CONNECTION,
    CONNECTION_STATE,
    RUN,
    STREAM,
    STREAM_CLIENT,
    SYSTEM_LOG,
    ENCRYPTION
  }

  /**
   * maps the routes used by various resources in a nice config we can reuse.
   */
  private static Configuration RESOURCE_TYPE_MAPS = new ConfigurationBuilder()

      .put("path.INTEGRATION_TYPE", "/v1/integration_types")
      .put("path.INTEGRATION", "/v1/integrations")
      .put("path.CONNECTION", "/v1/connections")
      .put("path.CONNECTION_STATE", "/v1/connection_states")
      .put("path.RUN", "/v1/runs")
      .put("path.STREAM", "/v1/streams")
      .put("path.STREAM_CLIENT", "/v1/stream_clients")
      .put("path.SYSTEM_LOG", "/v1/system_logs")
      .put("path.ENCRYPTION", "/v1/encryption")

      .put("query.ALL.id", true)
      .put("filters.ALL.id", true)
      .put("filters.ALL.created_at", true)
      .put("filters.ALL.updated_at", true)

      .toConfiguration();

  /**
   * resource client builder.
   * @param restEndpointConfiguration configuration of the endpoint we want to use
   * @param type the type of resource we want to build
   * @param kmsClient kms client config
   * @return the client
   */
  public static ResourceClient build(Configuration restEndpointConfiguration, Type type,
                                     Configuration kmsClient) {
    ConfigurationBuilder resourceConfig = new ConfigurationBuilder();
    resourceConfig.put("path", RESOURCE_TYPE_MAPS.getString("path." + type.toString()));

    List<Pair<String, String>> mappers = new ArrayList<>();
    mappers.add(Pair.of("filters", "ALL"));
    mappers.add(Pair.of("filters", type.toString()));
    mappers.add(Pair.of("query", "ALL"));
    mappers.add(Pair.of("query", type.toString()));

    for (Pair<String, String> mapper : mappers) {
      String lookupKeyPrefix = mapper.getKey() + "." + mapper.getValue();
      String targetKeyPrefix = mapper.getKey();
      if (RESOURCE_TYPE_MAPS.hasPath(lookupKeyPrefix)) {
        for (String s : RESOURCE_TYPE_MAPS.getKeys(Optional.of(lookupKeyPrefix))) {
          resourceConfig.put(targetKeyPrefix + "." + s,
                             RESOURCE_TYPE_MAPS.getBoolean(lookupKeyPrefix + "." + s));
        }
      }
    }

    checkNotNull(restEndpointConfiguration);
    RequestClientExecutor requestClientExecutor =
        new RequestClientExecutor(getDefaultConfig().merge(restEndpointConfiguration));

    return new ResourceClient(requestClientExecutor, resourceConfig.toConfiguration(), kmsClient);
  }

  /**
   * convenience method for{@link #build(Configuration, ResourceClientBuilder.Type, Configuration)}.
   * @param type the type of resource we want to build
   * @param kmsClient kms config
   * @return the client
   */
  public static ResourceClient build(Type type, Configuration kmsClient) {
    return build(getDefaultConfig(), type, kmsClient);
  }

  /**
   * Helper that picks up the default config from commonlib.
   *
   * @return the rest api default config
   */
  private static Configuration getDefaultConfig() {
    Configuration config = CommonLib.get().getConfiguration();
    if (config.hasPath("integrationsWorker.integrationsApiConnector")) {
      return config.atPath("integrationsWorker.integrationsApiConnector");
    }
    return new ConfigurationBuilder().toConfiguration();
  }
}
