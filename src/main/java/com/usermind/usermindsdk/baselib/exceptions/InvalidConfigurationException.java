package com.usermind.usermindsdk.baselib.exceptions;

/**
 * Exception used to signal when the provided configuration is invalid.
 */
public class InvalidConfigurationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public InvalidConfigurationException(String message) {
    super(message);
  }

  public InvalidConfigurationException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
