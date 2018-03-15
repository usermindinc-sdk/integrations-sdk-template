package com.usermind.usermindsdk.worker.flow.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Base integration exception.
 */
public abstract class IntegrationException extends RuntimeException {

  public IntegrationException(String message) {
    super(message);
  }

  public IntegrationException(Throwable cause) {
    super(cause);
  }

  public IntegrationException(String message, Throwable cause) {
    super(message, cause);
  }

  @JsonIgnore
  protected abstract boolean isRetriable();
}
