package com.usermind.usermindsdk.worker.normalization.exception;

/**
 * Base class for Normalization Exceptions.
 */
public class NormalizationException extends RuntimeException {
  public NormalizationException() {
  }

  public NormalizationException(String message) {
    super(message);
  }

  public NormalizationException(String message, Throwable cause) {
    super(message, cause);
  }
}
