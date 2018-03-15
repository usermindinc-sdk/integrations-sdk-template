package com.usermind.usermindsdk.worker.base;

/**
 * enum for record type associated with the stream record.
 */
public enum RecordType {
  START_FETCH_MARKER,
  END_FETCH_MARKER,
  RAW_ENTITY_METADATA,
  ENTITY_METADATA,
  RAW_ENTITY_DATA,
  ENTITY_DATA
}
