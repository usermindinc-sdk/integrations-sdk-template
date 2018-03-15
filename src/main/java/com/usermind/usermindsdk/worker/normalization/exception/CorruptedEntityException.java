package com.usermind.usermindsdk.worker.normalization.exception;

import com.usermind.usermindsdk.common.config.Configuration;

public class CorruptedEntityException extends NormalizationException {
  private final boolean fatal;
  private final String fieldPath;
  private final Configuration entity;

  /**
   * Ctor for exception.
   *
   * @param fieldPath fieldPath path
   * @param entity entity
   * @param fatal fatal (the idea is to propagate further fatal exceptions)
   */
  public CorruptedEntityException(String fieldPath, Configuration entity, boolean fatal) {
    this.fieldPath = fieldPath;
    this.entity = entity;
    this.fatal = fatal;
  }

  /**
   * Ctor for exception. fatal param defaults to false.
   *
   * @param fieldPath fieldPath path
   * @param entity entity
   */
  public CorruptedEntityException(String fieldPath, Configuration entity) {
    this(fieldPath, entity, false);
  }

  public boolean isFatal() {
    return fatal;
  }

  @Override
  public String getMessage() {
    return String
        .format("Corrupted entity, field path: '%s', entity: '%s'", getFieldPath(), getEntity());
  }

  public String getFieldPath() {
    return fieldPath;
  }

  public Configuration getEntity() {
    return entity;
  }
}
