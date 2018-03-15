package com.usermind.usermindsdk.worker.restapi;

import com.amazonaws.util.IOUtils;
import com.usermind.usermindsdk.common.boot.CommonLib;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * exception used by the rest api client.
 */
public class ClientException extends RuntimeException {
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientException.class);

  private final int responseCode;

  public ClientException(String message) {
    super(message);
    this.responseCode = 0; // not actually a response code
  }

  public ClientException(String message, int responseCode) {
    super(message);
    this.responseCode = responseCode;
  }

  /**
   * checks if the request that generated this is retryable.
   * @return true if request is retryable
   */
  public boolean isRetryable() {
    // bad requests are not retryable - 409:conflict is also retriable
    return (responseCode == 409) || (responseCode < 400) || (responseCode > 499);
  }

  /**
   * gets the response code that was encapsulated in.
   * @return int response code
   */
  public int getResponseCode() {
    return responseCode;
  }

  /**
   * check the response and raises an exception if needed.
   * @param response to check
   */
  public static void checkResponse(CloseableHttpResponse response) {
    int responseCode = extractResponseCode(response);
    if ((responseCode < 200) || (responseCode > 299)) {
      String message = buildMessage(response);
      LOGGER.error(message);
      throw new ClientException(message, responseCode);
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

  private static int extractResponseCode(CloseableHttpResponse response) {
    return response.getStatusLine().getStatusCode();
  }
}
