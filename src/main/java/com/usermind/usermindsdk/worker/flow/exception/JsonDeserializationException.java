package com.usermind.usermindsdk.worker.flow.exception;

/**
 * Deserialization data exception.
 */
public class JsonDeserializationException extends NonRetriableIntegrationException {

  public JsonDeserializationException(Throwable cause) {
    super(cause);
  }

  public JsonDeserializationException(String message, Throwable cause) {
    super(message, cause);
  }

  public JsonDeserializationException(String message) {
    super(message);
  }
}
