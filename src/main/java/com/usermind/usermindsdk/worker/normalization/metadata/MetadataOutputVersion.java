package com.usermind.usermindsdk.worker.normalization.metadata;

public enum MetadataOutputVersion {
  /**
   * default metadata output format (all fields at the root level).
   */
  V1,
  /**
   * new metadata output format:
   * {
   *   "type": "account",
   *   "displayName": "Account",
   *   "fields": {
   *     ...
   *   }
   * }
   */
  V2
}
