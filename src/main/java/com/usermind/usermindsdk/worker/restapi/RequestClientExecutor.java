package com.usermind.usermindsdk.worker.restapi;

import com.amazonaws.util.IOUtils;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.common.config.InvalidConfigurationException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Helper that wraps the httpclient, httpcontext and the endpoint configuration.
 */
public class RequestClientExecutor {
  private static final Logger LOGGER = LoggerFactory.getLogger(RequestClientExecutor.class);

  private CloseableHttpClient httpClient;
  private HttpClientContext httpcontext;
  private Configuration restEndpointConfiguration;

  /**
   * build the request executor based on the provided endpoint configuration.
   *
   * @param restEndpointConfiguration to use
   */
  public RequestClientExecutor(Configuration restEndpointConfiguration) {
    restEndpointConfiguration.checkPathExists("scheme");
    restEndpointConfiguration.checkPathExists("host");
    restEndpointConfiguration.checkPathExists("port");
    restEndpointConfiguration.checkPathExists("apiKey");
    this.restEndpointConfiguration = restEndpointConfiguration;

    this.httpClient = HttpClients.createDefault();
    Credentials credentials =
        new UsernamePasswordCredentials(restEndpointConfiguration.getString("apiKey"), "");

    HttpHost targetHost = new HttpHost(restEndpointConfiguration.getString("host"),
        restEndpointConfiguration.getInt("port"), restEndpointConfiguration.getString("scheme"));

    AuthScope authScope = new AuthScope(targetHost);

    CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    credentialsProvider.setCredentials(authScope, credentials);

    AuthCache authCache = new BasicAuthCache();
    authCache.put(targetHost, new BasicScheme());

    httpcontext = HttpClientContext.create();
    httpcontext.setCredentialsProvider(credentialsProvider);
    httpcontext.setAuthCache(authCache);
  }

  private Configuration buildConfigurationFromResponse(CloseableHttpResponse response) {
    String responseString = null;
    try {
      responseString = IOUtils.toString(response.getEntity().getContent());
    } catch (IOException e) {
      LOGGER.error("Failed on I/O Operation", e);
      throw new ClientException("Failed on I/O Operation: " + e.getMessage());
    }

    Configuration responseConfiguration = ConfigurationBuilder.createConfiguration(responseString);

    if (response.containsHeader("Location")) {
      String location = response.getFirstHeader("Location").getValue();
      if (location != null && !location.isEmpty()) {
        responseConfiguration = new ConfigurationBuilder(responseConfiguration)
            .put("location", location)
            .toConfiguration();
      }
    }

    return responseConfiguration;
  }

  /**
   * perform the given request against the configured endpoint without any retries.
   *
   * @param request to execute
   * @return Configuration object with the response
   */
  public Configuration performRequestNoRetry(HttpUriRequest request) {
    CloseableHttpResponse response = null;
    try {
      response = httpClient.execute(request, httpcontext);
      ClientException.checkResponse(response);
      return buildConfigurationFromResponse(response);
    } catch (IOException e) {
      LOGGER.warn("Failed on I/O Operation", e);
      throw new ClientException("Failed on I/O Operation: " + e.getMessage());
    } finally {
      if (response != null) {
        try {
          response.close();
        } catch (IOException e) {
          LOGGER.warn("Failed on I/O Operation", e);
          throw new ClientException("Failed on I/O Operation (close): " + e.getMessage());
        }
      }
    }
  }

  /**
   * perform the given request against the configured endpoint.
   *
   * @param request to execute
   * @return Configuration object with the response
   */
  public Configuration performRequest(HttpUriRequest request) {
    int retries = 0;
    // TODO: extract this in configuration
    int maxRetries = 5;
    while (true) {
      try {
        if (retries > 0) {
          LOGGER.warn("retry " + retries + " for " + request.toString());
          Thread.sleep((long) (Math.pow(2, retries) * 500));
        }
        return performRequestNoRetry(request);
      } catch (ClientException clientException) {
        if ((!clientException.isRetryable()) || (retries >= maxRetries)) {
          throw clientException;
        }
        retries += 1;
      } catch (InterruptedException e) {
        LOGGER.error("thread sleep interrupted while in exponential backoff", e);
        throw new ClientException("thread sleep interrupted" + e.getMessage());
      }
    }
  }

  /**
   * Build an URI based on the provided path, the config and the params.
   *
   * @param path to use when building
   * @param params to use when building
   * @return the build URI that can be used
   */
  public URI buildUri(String path, Map<String, String> params) {
    try {
      URIBuilder uriBuilder =
          new URIBuilder().setScheme(restEndpointConfiguration.getString("scheme"))
              .setHost(restEndpointConfiguration.getString("host"))
              .setPort(restEndpointConfiguration.getInt("port")).setPath(path);
      if (params != null) {
        for (Map.Entry<String, String> entry : params.entrySet()) {
          uriBuilder = uriBuilder.setParameter(entry.getKey(), entry.getValue());
        }
      }
      return uriBuilder.build();
    } catch (URISyntaxException e) {
      LOGGER.error("URI Syntax Error", e);
      throw new InvalidConfigurationException(e.getMessage());
    }
  }

  /**
   * Build an URI based on the provided fullPath.ß
   *
   * @param fullPath to use when building
   * @return the build URI that can be used
   */
  public URI buildUriForNextPage(String fullPath) {
    try {
      return new URIBuilder(fullPath).build();
    } catch (URISyntaxException e) {
      LOGGER.error("URI Syntax Error", e);
      throw new InvalidConfigurationException(e.getMessage());
    }
  }
}
