package com.usermind.usermindsdk.common.support.exception;

import com.amazonaws.util.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * generic exception that can be used by http clients.
 */
public class HttpClientException extends IntegrationException {
  private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientException.class);

  private final int responseCode;

  public HttpClientException(String message) {
    super(message);
    this.responseCode = 0; // not actually a response code
  }

  public HttpClientException(String message, int responseCode) {
    super(message);
    this.responseCode = responseCode;
  }

  /**
   * {@inheritDoc}.
   */
  @Override
  public boolean isRetryable() {
    // bad requests are not retryable
    // you can even further refine this if you want
    return ((responseCode < 400) || (responseCode > 499));
  }

  /**
   * check the response and raises an exception if needed.
   * @param response to check
   */
  public static void checkResponse(CloseableHttpResponse response) {
    int responseCode = getResponseCode(response);
    if ((responseCode < 200) || (responseCode > 299)) {
      String message = buildMessage(response);
      LOGGER.error(message);
      throw new HttpClientException(message, responseCode);
    }
  }

  private static String buildMessage(CloseableHttpResponse response) {
    try {
      return Integer.toString(response.getStatusLine().getStatusCode()) + " " + IOUtils
          .toString(response.getEntity().getContent());
    } catch (IOException e) {
      return "failed inspecting the response";
    }
  }

  private static int getResponseCode(CloseableHttpResponse response) {
    return response.getStatusLine().getStatusCode();
  }
}

