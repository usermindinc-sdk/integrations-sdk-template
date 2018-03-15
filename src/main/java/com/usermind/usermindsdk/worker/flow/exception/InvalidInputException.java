package com.usermind.usermindsdk.worker.flow.exception;

/**
 * Invalid Input data exception.
 */

public class InvalidInputException extends NonRetriableIntegrationException {

  public InvalidInputException(String message) {
    super(message);
  }

  public InvalidInputException(Throwable cause) {
    super(cause);
  }

  public InvalidInputException(String message, Throwable cause) {
    super(message, cause);
  }
}
