package com.usermind.usermindsdk.worker.writers.s3;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Records chunk type - raw, normalized or metadata.
 */
public enum ChunkType {

  RAW("raw"), NORMALIZED("normalized"), METADATA("meta"), ACTIONMETADATA("action-meta");

  private final String value;

  ChunkType(String value) {
    this.value = value;
  }

  @JsonValue
  @Override
  public String toString() {
    return value;
  }
}
