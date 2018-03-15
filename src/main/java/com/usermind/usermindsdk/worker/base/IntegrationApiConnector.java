package com.usermind.usermindsdk.worker.base;

import com.google.common.util.concurrent.Uninterruptibles;
import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.SecuredConfigurationSource;
import com.usermind.usermindsdk.worker.flow.exception.NonRetriableIntegrationException;
import com.usermind.usermindsdk.worker.restapi.ClientException;
import com.usermind.usermindsdk.worker.restapi.ResourceClient;
import com.usermind.usermindsdk.worker.restapi.ResourceClientBuilder;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static com.google.common.base.Preconditions.checkState;

/**
 * Api Connector class.
 *
 * <p>
 * Must be used by the integration to read and update data against the rest api
 * The only thing that are provided to the integration workflows are: the endpoint, credentials
 * and the url to the run associated with the workflow. Everything else can be read through the api
 *
 * <p>
 * The only things that a normal integration workflow should update are the connection state and
 * the run itself.
 *
 * <p>
 * Example building using the IntegrationApiConnector
 * <pre>
 * {@code
 * public static void main(String... args) throws IOException {
 *    Logger logger = LoggerFactory.getLogger(App.class);*
 *    Configuration connector=new ConfigurationBuilder()
 *       .put("runId","2ef63f13-7a02-400e-9a1f-4a70a5ec6fd6")
 *       .toConfiguration();
 *
 *   IntegrationApiConnector runDmc=new IntegrationApiConnector(connector);
 *   logger.info(runDmc.getConnectionState().toString());
 *
 *   Configuration updateRunDelta=new ConfigurationBuilder()
 *       .put("status","b3b3")
 *       .put("action","disconnect")
 *       .put("input.zork","snork")
 *       .toConfiguration();
 *
 *   Configuration updateStateDelta=new ConfigurationBuilder()
 *       .put("state.zoo","moo"+System.currentTimeMillis())
 *       .toConfiguration();
 *
 *   runDmc.updateRun(updateRunDelta);
 *   runDmc.updateConnectionState(updateStateDelta);
 * }
 * }
 * </pre>
 */
public class IntegrationApiConnector {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(IntegrationApiConnector.class);


  private static final int DEFAULT_MAX_RETRIES = 8;

  private final Configuration connectorConfiguration;
  private final Configuration kmsClient;
  private final AtomicReference<Integer> failedPreconditionMaxRetries;
  private HashMap<ResourceClientBuilder.Type, ResourceClient> connectorLinks;
  private HashMap<ResourceClientBuilder.Type, Configuration> localConfigurationCopy;
  private List<Configuration> localStreams;

  /**
   * ctor that build the connector based on the given configuration.
   * @param connectorConfiguration to use
   */
  public IntegrationApiConnector(Configuration connectorConfiguration) {
    this.connectorConfiguration = connectorConfiguration;
    this.kmsClient = CommonLib.get().getConfiguration().atPath("integrationsWorker.kmsClient");

    this.connectorLinks = new HashMap<>();
    for (ResourceClientBuilder.Type type : ResourceClientBuilder.Type.values()) {
      this.connectorLinks
          .put(type, ResourceClientBuilder.build(connectorConfiguration, type, kmsClient));
    }

    this.localConfigurationCopy = new HashMap<>();
    this.failedPreconditionMaxRetries = new AtomicReference<>();
  }

  /**
   * Connector configuration getter.
   * @return this connector {@code Configuration}.
   */
  public Configuration getConfiguration() {
    return connectorConfiguration;
  }

  /**
   * update the connection state.
   * the param is a delta that the client wants to apply and contains only the changes to be made
   * @param updatedConnectionStateDelta delta to apply
   */
  public void updateConnectionState(Configuration updatedConnectionStateDelta) {
    connectorLinks.get(ResourceClientBuilder.Type.CONNECTION_STATE)
        .update(UUID.randomUUID().toString(),
                getConnectionState().merge(updatedConnectionStateDelta));
  }

  /**
   * update the connection state by merging the configuration at given path.
   * the param is a delta that the client wants to apply and contains only the changes to be made
   * @param path path to merge configuration at
   * @param updatedConnectionStateDelta delta to apply
   */
  public void updateConnectionState(String path, Configuration updatedConnectionStateDelta) {
    connectorLinks.get(ResourceClientBuilder.Type.CONNECTION_STATE)
          .update(UUID.randomUUID().toString(),
                getConnectionState().mergeAtPath(path, updatedConnectionStateDelta));
  }
  /**
   * update the connection state, retries {@link org.apache.http.HttpStatus#SC_PRECONDITION_FAILED}.
   * the param is a delta that the client wants to apply and contains only the changes to be made
   * @param updatedConnectionStateDelta delta to apply
   */
  public void updateConnectionStateConcurrently(Configuration updatedConnectionStateDelta) {
    int retries = 0;
    int maxRetries = getCachedFailedPreconditionMaxRetries();
    do {
      try {
        updateConnectionState(updatedConnectionStateDelta);
        return;
      } catch (ClientException ex) {
        if (ex.getResponseCode() != HttpStatus.SC_PRECONDITION_FAILED) {
          throw ex;
        }
        Uninterruptibles
            .sleepUninterruptibly((long) (Math.pow(2, retries) * 500), TimeUnit.MILLISECONDS);
        LOGGER.debug("Re-attempting connection state update on status code '{}'",
            HttpStatus.SC_PRECONDITION_FAILED);
      }
    } while (retries++ < maxRetries);

    throw new NonRetriableIntegrationException(String
        .format("Failed to update connection state using %s attempts",
            failedPreconditionMaxRetries));
  }

  /**
   * update the run.
   * the param is a delta that the client wants to apply and contains only the changes to be made
   * @param updatedRunDelta delta to apply
   */
  public void updateRun(Configuration updatedRunDelta) {
    connectorLinks.get(ResourceClientBuilder.Type.RUN)
        .update(UUID.randomUUID().toString(),
                getRun().merge(updatedRunDelta));
    localConfigurationCopy.remove(ResourceClientBuilder.Type.RUN);
  }

  /**
   * private helper used to dry up the code.
   * @param type we are loading
   * @param id for the entity we are loading
   * @return the entity
   */
  private Configuration loadToLocal(ResourceClientBuilder.Type type, String id) {
    return loadToLocal(type, id, Collections.emptyMap());
  }

  /**
   * private helper used to dry up the code.
   * @param type we are loading
   * @param id for the entity we are loading
   * @param params query params
   * @return the entity
   */
  private Configuration loadToLocal(ResourceClientBuilder.Type type, String id,
    Map<String, String> params) {
    HashMap<String, String> queryParams = new HashMap<>(params);
    queryParams.put("id", id);
    List<Configuration> local = connectorLinks.get(type).query(queryParams);
    checkState(local.size() == 1,
               "found more or less than exactly one " + type + " associated with id " + id + " : "
               + local.toString());
    localConfigurationCopy.put(type, local.get(0));
    Configuration retrievedConfig = local.get(0);
    // this applies to all entities that have configuration or a state
    for (String encryptedZone : Arrays.asList("configuration.encrypted", "state.encrypted")) {
      if (retrievedConfig.hasPath(encryptedZone)) {
        final Configuration finalRetrievedConfig = retrievedConfig;
        Configuration
            decryptedConfig =
            new SecuredConfigurationSource(kmsClient,
                                           () -> finalRetrievedConfig
                                               .atPath(encryptedZone)).load();
        retrievedConfig =
            retrievedConfig.withoutPath(encryptedZone)
                .mergeAtPath(encryptedZone, decryptedConfig);
      }
    }
    // this applies only to RUN entities
    if (type == ResourceClientBuilder.Type.RUN) {
      if (retrievedConfig.hasPath("input.encrypted")) {
        final Configuration finalRetrievedConfig = retrievedConfig;
        Configuration
            decryptedConfig =
            new SecuredConfigurationSource(kmsClient,
                                           () -> finalRetrievedConfig.atPath("input.encrypted"))
                .load();
        retrievedConfig =
            retrievedConfig.withoutPath("input.encrypted")
                .mergeAtPath("input", decryptedConfig);
      }
    }
    localConfigurationCopy.put(type, retrievedConfig);
    return retrievedConfig;
  }

  /**
   * load the run.
   * @return run properties wrapped in a config object
   */
  public Configuration getRun() {
    return loadToLocal(ResourceClientBuilder.Type.RUN, connectorConfiguration.getString("runId"));
  }

  /**
   * load the run.
   * @param params query params.
   * @return run properties wrapped in a config object
   */
  public Configuration getRun(Map<String, String> params) {
    return loadToLocal(ResourceClientBuilder.Type.RUN, connectorConfiguration.getString("runId"),
      params);
  }

  /**
   * return the cached copy of the run, if any.
   * @return run properties wrapped in a config object
   */
  public Configuration getCachedRun() {
    return getCachedRun(Collections.emptyMap());
  }

  /**
   * return the cached copy of the run, if any.
   * @param params query params.
   * @return run properties wrapped in a config object
   */
  public Configuration getCachedRun(Map<String, String> params) {
    if (localConfigurationCopy.containsKey(ResourceClientBuilder.Type.RUN)) {
      return localConfigurationCopy.get(ResourceClientBuilder.Type.RUN);
    }
    return getRun(params);
  }

  /**
   * load the connection.
   * @return connection properties wrapped in a config object
   */
  public Configuration getConnection() {
    return getConnection(Collections.emptyMap());
  }

  /**
   * load the connection.
   * @param params query params.
   * @return connection properties wrapped in a config object
   */
  public Configuration getConnection(Map<String, String> params) {
    if (connectorConfiguration.hasPath("connectionId")) {
      if (connectorConfiguration.hasPath("runId")) {
        throw new RuntimeException("you cannot specify both a connectionId and a runId");
      }
      return loadToLocal(ResourceClientBuilder.Type.CONNECTION,
        connectorConfiguration.getString("connectionId"), params);
    } else {
      return loadToLocal(ResourceClientBuilder.Type.CONNECTION,
        getRun(params).getString("connection_id"));
    }
  }

  /**
   * return the cached copy of the connection, if any.
   * @return connection properties wrapped in a config object
   */
  public Configuration getCachedConnection() {
    return getCachedConnection(Collections.emptyMap());
  }

  /**
   * return the cached copy of the connection, if any.
   * @param params query params.
   * @return connection properties wrapped in a config object
   */
  public Configuration getCachedConnection(Map<String, String> params) {
    if (localConfigurationCopy.containsKey(ResourceClientBuilder.Type.CONNECTION)) {
      return localConfigurationCopy.get(ResourceClientBuilder.Type.CONNECTION);
    }
    return getConnection(params);
  }

  /**
   * load the connection state.
   * @return connection state properties wrapped in a config object
   */
  public Configuration getConnectionState() {
    return getConnectionState(Collections.emptyMap());
  }

  /**
   * load the connection state.
   * @param params query params.
   * @return connection state properties wrapped in a config object
   */
  public Configuration getConnectionState(Map<String, String> params) {
    return loadToLocal(ResourceClientBuilder.Type.CONNECTION_STATE, getConnection().getString(
      "state_id"), params);
  }

  /**
   * return the cached copy of the connection state, if any.
   * @return connection state properties wrapped in a config object
   */
  public Configuration getCachedConnectionState() {
    return getCachedConnectionState(Collections.emptyMap());
  }

  /**
   * return the cached copy of the connection state, if any.
   * @param params query params.
   * @return connection state properties wrapped in a config object
   */
  public Configuration getCachedConnectionState(Map<String, String> params) {
    if (localConfigurationCopy.containsKey(ResourceClientBuilder.Type.CONNECTION_STATE)) {
      return localConfigurationCopy.get(ResourceClientBuilder.Type.CONNECTION_STATE);
    }
    return getConnectionState(params);
  }

  /**
   * load the integration.
   * @return integration properties wrapped in a config object
   */
  public Configuration getIntegration() {
    return getIntegration(Collections.emptyMap());
  }

  /**
   * load the integration.
   * @param params query params.
   * @return integration properties wrapped in a config object
   */
  public Configuration getIntegration(Map<String, String> params) {
    return loadToLocal(ResourceClientBuilder.Type.INTEGRATION,
      getConnection().getString("integration_id"), params);
  }

  /**
   * return the cached copy of the integration, if any.
   * @return integration properties wrapped in a config object
   */
  public Configuration getCachedIntegration() {
    return getCachedIntegration(Collections.emptyMap());
  }

  /**
   * return the cached copy of the integration, if any.
   * @param params query params.
   * @return integration properties wrapped in a config object
   */
  public Configuration getCachedIntegration(Map<String, String> params) {
    if (localConfigurationCopy.containsKey(ResourceClientBuilder.Type.INTEGRATION)) {
      return localConfigurationCopy.get(ResourceClientBuilder.Type.INTEGRATION);
    }
    return getIntegration(params);
  }

  /**
   * load the integration type.
   * @return integration type properties wrapped in a config object
   */
  public Configuration getIntegrationType() {
    return getIntegrationType(Collections.emptyMap());
  }

  /**
   * load the integration type.
   * @param params query params.
   * @return integration type properties wrapped in a config object
   */
  public Configuration getIntegrationType(Map<String, String> params) {
    return loadToLocal(ResourceClientBuilder.Type.INTEGRATION_TYPE,
      getIntegration().getString("integration_type_id"), params);
  }

  /**
   * return the cached copy of the integration type, if any.
   * @return integration type properties wrapped in a config object
   */
  public Configuration getCachedIntegrationType() {
    return getCachedIntegrationType(Collections.emptyMap());
  }

  /**
   * return the cached copy of the integration type, if any.
   * @param params query params.
   * @return integration type properties wrapped in a config object
   */
  public Configuration getCachedIntegrationType(Map<String, String> params) {
    if (localConfigurationCopy.containsKey(ResourceClientBuilder.Type.INTEGRATION_TYPE)) {
      return localConfigurationCopy.get(ResourceClientBuilder.Type.INTEGRATION_TYPE);
    }
    return getIntegrationType(params);
  }

  /**
   * load the streams associated with the run.
   * @return list of streams wrapped in config objects
   */
  public List<Configuration> getStreams() {
    List<Configuration> streams = new ArrayList<>();
    for (String streamId : getConnection().getStringList("streams")) {
      streams.add(loadToLocal(ResourceClientBuilder.Type.STREAM, streamId));
    }
    localStreams = streams;
    return localStreams;
  }

  /**
   * load the cached streams associated with the run, if any.
   * @return list of streams wrapped in config objects
   */
  public List<Configuration> getCachedStreams() {
    if (localStreams != null) {
      return localStreams;
    }
    return getStreams();
  }

  public Configuration encrypt(Configuration plain) {
    return connectorLinks.get(ResourceClientBuilder.Type.ENCRYPTION)
        .create(UUID.randomUUID().toString(), plain);
  }

  /**
   * build a merge version of all configurations.
   * @return merged connector configuration
   */
  public Configuration getMergedConfiguration() {
    return getCachedIntegrationType().atPath("configuration")
        .merge(getCachedIntegration().atPath("configuration"))
        .merge(getCachedConnection().atPath("configuration"))
        .merge(getCachedConnectionState().atPath("state"));
  }

  /**
   * return cached max retries for update collision.
   * @return max retries
   */
  public int getCachedFailedPreconditionMaxRetries() {
    return this.failedPreconditionMaxRetries.updateAndGet(value -> {
        if (value != null) {
          return value;
        }
        Configuration connectionConfiguration = getCachedConnection().atPath("configuration");
        int preconditionMaxRetries =
            connectionConfiguration.getInt("failedPreconditionMaxRetries", DEFAULT_MAX_RETRIES);
        return Math.max(1, preconditionMaxRetries);
      });
  }
}
