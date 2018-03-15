package com.usermind.usermindsdk.worker.flow.exception;

/**
 * Retriable integration exception.
 */
public class RetriableIntegrationException extends IntegrationException {

  public RetriableIntegrationException(String message) {
    super(message);
  }

  public RetriableIntegrationException(Throwable cause) {
    super(cause);
  }

  public RetriableIntegrationException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  protected boolean isRetriable() {
    return true;
  }
}
