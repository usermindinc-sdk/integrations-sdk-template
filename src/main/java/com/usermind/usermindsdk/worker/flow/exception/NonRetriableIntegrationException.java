package com.usermind.usermindsdk.worker.flow.exception;

/**
 * Non-retriable integration exception.
 */
public class NonRetriableIntegrationException extends IntegrationException {

  public NonRetriableIntegrationException(String message) {
    super(message);
  }

  public NonRetriableIntegrationException(Throwable cause) {
    super(cause);
  }

  public NonRetriableIntegrationException(String message, Throwable cause) {
    super(message, cause);
  }

  @Override
  protected boolean isRetriable() {
    return false;
  }
}
