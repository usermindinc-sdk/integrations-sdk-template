package com.usermind.usermindsdk.worker.normalization.exception;

import com.usermind.usermindsdk.common.config.Configuration;

/**
 * Runtime exception to determine situation when expected field is not present inside entity object.
 */
public class MissingExpectedFieldException extends CorruptedEntityException {
  /**
   * Ctor.
   * @param fieldName field name.
   * @param entity entity
   */
  public MissingExpectedFieldException(String fieldName, Configuration entity) {
    super(fieldName, entity);
  }

  /**
   * Ctor.
   * @param fieldName field name.
   * @param entity entity
   * @param fatal is fatal exception
   */
  public MissingExpectedFieldException(String fieldName, Configuration entity, boolean fatal) {
    super(fieldName, entity, fatal);
  }

  @Override
  public String getMessage() {
    return String
        .format("Missing expected field '%s' for entity '%s'", getFieldPath(), getEntity());
  }
}
