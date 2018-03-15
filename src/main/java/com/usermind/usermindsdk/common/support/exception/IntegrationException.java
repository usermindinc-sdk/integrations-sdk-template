package com.usermind.usermindsdk.common.support.exception;

/**
 * IntegrationsBaseException.
 */
public abstract class IntegrationException extends RuntimeException {

  public IntegrationException() {
    super();
  }

  public IntegrationException(String message) {
    super(message);
  }

  public IntegrationException(String message, Throwable throwable) {
    super(message, throwable);
  }

  public IntegrationException(Throwable throwable) {
    super(throwable);
  }

  /**
   * checks if the request that generated this is retryable.
   * @return true if request is retryable
   */
  public abstract boolean isRetryable();
}
