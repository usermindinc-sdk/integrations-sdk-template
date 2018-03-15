package com.usermind.usermindsdk.worker.writers.exception;

/**
 * *Note* that writer still can throw exceptions like {@code IllegalArgumentException}
 * or {@code IllegalStateException}.
 */
public class EntityWriterException extends RuntimeException {

  public EntityWriterException(String message) {
    super(message);
  }

  public EntityWriterException(String message, Throwable cause) {
    super(message, cause);
  }
}
