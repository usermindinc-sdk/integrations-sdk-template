package com.usermind.usermindsdk.worker.restapi;

import com.google.common.collect.ImmutableSet;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.common.config.SecuredConfigurationBuilderSource;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * client for integration rest api - types.
 */
public class ResourceClient {

  private final Configuration resourceConfiguration;
  private final RequestClientExecutor requestClientExecutor;
  private final Configuration kmsClient;
  private static final int MAX_PER_PAGE = 499;
  private static final Set<String> NOT_FILTER_QUERY_PARAMS =
    ImmutableSet.of("sortOrder", "perPage", "embed");

  private static final Logger LOGGER = LoggerFactory.getLogger(ResourceClient.class);

  /**
   * ctor that works with a provided executor and resource configuration.
   *
   * @param requestClientExecutor to use
   * @param resourceConfiguration to use
   * @param kmsClient config to use
   */
  public ResourceClient(RequestClientExecutor requestClientExecutor,
                        Configuration resourceConfiguration,
                        Configuration kmsClient) {
    this.requestClientExecutor = requestClientExecutor;
    this.kmsClient = kmsClient;

    resourceConfiguration.checkPathExists("path");
    resourceConfiguration.checkPathExists("query");
    resourceConfiguration.checkPathExists("filters");
    this.resourceConfiguration = resourceConfiguration;
  }

  /**
   * get the object that has the provided id.
   * @param objectId id of the object to get
   * @return the object that was requested
   */
  public Configuration get(String objectId) {
    return get(objectId, Collections.emptyMap());
  }

  /**
   * get the object that has the provided id.
   * @param objectId id of the object to get
   * @param params query params
   * @return the object that was requested
   */
  public Configuration get(String objectId, Map<String, String> params) {
    HttpGet httpget = new HttpGet(requestClientExecutor
      .buildUri(resourceConfiguration.getString("path") + "/" + objectId, params));
    httpget.addHeader("Content-Type", "application/json");
    LOGGER.info(httpget.toString());
    return requestClientExecutor.performRequest(httpget);
  }

  /**
   * issues a query with the give properties and return a list of results.
   *
   * @param properties to filter on
   * @return list of results as Configuration objects
   */
  public List<Configuration> query(Map<String, String> properties) {
    return query(properties, Integer.MAX_VALUE);
  }

  /**
   * issues a query with the give properties and return a list of results.
   *
   * @param properties to filter on
   * @param limit max number of items to retrieve
   * @return list of results as Configuration objects
   */
  public List<Configuration> query(Map<String, String> properties, int limit) {
    // special case when we are passing in the id directly
    // this is not really a query, it's more like a get
    List<Configuration> queryResponse = new ArrayList<>();
    if (properties.get("id") != null) {
      queryResponse.add(get(properties.get("id"), properties));
      return queryResponse;
    }
    // normal path
    HashMap<String, String> params = new HashMap<>();
    for (Map.Entry<String, String> property : properties.entrySet()) {
      String propertyName = property.getKey();
      if (NOT_FILTER_QUERY_PARAMS.contains(propertyName)) {
        params.put(propertyName, property.getValue());
      } else {
        params.put("filter[" + propertyName + "]", properties.get(propertyName));
      }
    }

    // the max page size if not specified, choose a default
    // if this query qualifies for internal pagination by the REST API and limit can fit on a
    // single page, use limit as the perPage
    if (properties.get("perPage") == null) {
      String perPage = "50";
      if (properties.containsKey("start.created_at")
          && properties.containsKey("end.created_at")
          && limit <= MAX_PER_PAGE) {
        perPage = String.valueOf(limit);
      }
      params.put("perPage", perPage);
    }

    HttpGet
        httpget =
        new HttpGet(
            requestClientExecutor.buildUri(resourceConfiguration.getString("path"), params));
    httpget.addHeader("Content-Type", "application/json");
    LOGGER.info(httpget.toString());

    Configuration baseResponse = requestClientExecutor.performRequest(httpget);
    queryResponse.addAll(baseResponse.getConfigurationsList("results"));

    // pagination FTW
    while (baseResponse.hasPath("next_page_url") && queryResponse.size() < limit) {
      httpget.setURI(
          requestClientExecutor.buildUriForNextPage(baseResponse.getString("next_page_url")));
      LOGGER.info(httpget.toString());
      baseResponse = requestClientExecutor.performRequest(httpget);
      queryResponse.addAll(baseResponse.getConfigurationsList("results"));
    }
    return queryResponse;
  }

  /**
   * convenience method for querying with just 1 property/name pair.
   *
   * @param propertyName  the name of the property to query on
   * @param propertyValue the value we are looking for the given property
   * @return list of results as Configuration objects
   */
  public List<Configuration> query(String propertyName, String propertyValue) {
    HashMap<String, String> params = new HashMap<>();
    params.put(propertyName, propertyValue);
    return query(params);
  }

  /**
   * create an entity using the given properties.
   *
   * @param requestId     unique if associated with the request
   * @param plainConfiguration holding the properties of the entity we want to create
   * @return response in configuration form
   */
  public Configuration create(String requestId, Configuration plainConfiguration) {
    Configuration configuration = protectEncryptedFields(plainConfiguration);
    HttpPost
        httpPost =
        new HttpPost(requestClientExecutor.buildUri(resourceConfiguration.getString("path"), null));
    httpPost.addHeader("Content-Type", "application/json");
    httpPost.addHeader("API-RequestId", requestId);
    httpPost.setEntity(buildBodyFromConfiguration(configuration));
    LOGGER.info(httpPost.toString() + " " + configuration.toString() + " " + requestId);
    return requestClientExecutor.performRequest(httpPost);
  }

  /**
   * update an entity using the given properties.
   *
   * @param requestId     unique if associated with the request
   * @param plainConfiguration holding the properties of the entity we want to update
   */
  public void update(String requestId, Configuration plainConfiguration) {
    Configuration configuration = protectEncryptedFields(plainConfiguration);
    String objectId = configuration.getString("id");
    HttpPut httpPut =
        new HttpPut(requestClientExecutor
                        .buildUri(resourceConfiguration.getString("path") + "/" + objectId, null));
    httpPut.addHeader("Content-Type", "application/json");
    httpPut.addHeader("API-RequestId", requestId);
    if (configuration.hasPath("updated_at")) {
      httpPut.addHeader("API-UpdatedAt", configuration.getString("updated_at"));
    }

    // strip out fields that are readonly
    Configuration strippedConfiguration = filterReadOnlyFields(configuration);
    httpPut.setEntity(buildBodyFromConfiguration(strippedConfiguration));
    LOGGER.info(httpPut.toString() + " " + strippedConfiguration.toString() + " " + requestId);

    requestClientExecutor.performRequest(httpPut);
  }

  private Configuration protectEncryptedFields(Configuration plain) {
    Configuration rewrittenConfiguration = plain;
    for (String encryptedZone : Arrays.asList("configuration.encrypted", "state.encrypted")) {
      if (rewrittenConfiguration.hasPath(encryptedZone)) {
        Configuration
            encryptedConfig =
            new SecuredConfigurationBuilderSource(kmsClient)
                .build(rewrittenConfiguration.atPath(encryptedZone));
        rewrittenConfiguration = rewrittenConfiguration.withoutPath(encryptedZone)
            .mergeAtPath(encryptedZone, encryptedConfig);
      }
    }
    return rewrittenConfiguration;
  }

  private StringEntity buildBodyFromConfiguration(Configuration input) {
    try {
      Configuration
          baseMetaData =
          ConfigurationBuilder.createConfiguration("{\"metadata_ids\":[]}");
      return new StringEntity(baseMetaData.merge(input).toString());
    } catch (UnsupportedEncodingException e) {
      throw new ClientException("Unsupported Encoding: " + e.getMessage());
    }
  }

  private Configuration filterReadOnlyFields(Configuration configuration) {
    Configuration filteredConfiguration = configuration;
    for (String filterKey : resourceConfiguration.getKeys(Optional.of("filters"))) {
      if (resourceConfiguration.getBoolean("filters." + filterKey)) {
        filteredConfiguration = filteredConfiguration.withoutPath(filterKey);
      }
    }
    return filteredConfiguration;
  }

}
