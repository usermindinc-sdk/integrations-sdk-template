package com.usermind.usermindsdk.worker.writers;

import java.util.Arrays;
import java.util.Optional;

/**
 * Supported writer types, for now S3 and Kinesis. *Note* that it's used in most of the places where
 * we are checking the writer type *except* the Integrations-API as it's not using the worker-base.
 */
public enum WriterType {

  S3, KINESIS;

  /**
   * Attempts to resolve a {@code WriterTypes} constant by a given {@code String} value in
   * a *case-ignore* manner.
   * @param value value
   * @return {@code Optional} of {@code WriterTypes} constant
   */
  public static Optional<WriterType> fromValue(String value) {
    return Arrays.stream(values())
        .filter(writerType -> writerType.toString().equalsIgnoreCase(value))
        .findFirst();
  }
}
