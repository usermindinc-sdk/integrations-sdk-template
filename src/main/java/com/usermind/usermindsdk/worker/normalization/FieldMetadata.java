package com.usermind.usermindsdk.worker.normalization;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;

import static com.google.common.base.Preconditions.checkState;

/**
 * Field metadata.
 */
public class FieldMetadata {
  public static final String TYPE_PATH = "type";
  public static final String IS_REQUIRED_PATH = "is_required";
  public static final String READ_ONLY_PATH = "read_only";
  public static final String DISPLAY_NAME_PATH = "display_name";
  public static final String IGNORE_PATH = "ignore";

  private static final FieldMetadata IGNORED_FIELD_METADATA =
      new FieldMetadata(null, false, true, null, true);

  // TODO: 11/13/15 introduce enum
  private final String type;
  private final boolean isRequired;
  private final boolean readOnly;
  private final String displayName;
  private final boolean ignore;

  /**
   * Ctor for metadata.
   *
   * @param type type
   * @param isRequired should be required on create/update
   * @param readOnly is readonly
   * @param displayName user friendly name.
   */
  public FieldMetadata(String type, boolean isRequired, boolean readOnly,
      String displayName) {
    this(type, isRequired, readOnly, displayName, false);
  }

  /**
   * Ctor for metadata.
   *
   * @param type type
   * @param isRequired should be required on create/update
   * @param readOnly is readonly
   * @param displayName user friendly name.
   * @param ignore if true than it will not appear in the metadata output.
   */
  public FieldMetadata(String type, boolean isRequired, boolean readOnly,
      String displayName, boolean ignore) {
    this.type = type;
    this.isRequired = isRequired;
    this.readOnly = readOnly;
    this.displayName = displayName;
    this.ignore = ignore;
  }

  /**
   * Reads FieldMetadata from {@link Configuration}.
   * @param config config to read.
   * @return FieldMetadata.
   */
  public static FieldMetadata fromConfig(Configuration config) {
    boolean ignore = config.getBoolean(IGNORE_PATH, false);

    if (ignore) {
      return IGNORED_FIELD_METADATA;
    }

    return new FieldMetadata(
        config.getString(TYPE_PATH),
        config.getBoolean(IS_REQUIRED_PATH),
        config.getBoolean(READ_ONLY_PATH),
        config.getString(DISPLAY_NAME_PATH)
    );
  }

  /**
   * Converts this to {@link Configuration}.
   * @return created configuration.
   */
  public Configuration toConfiguration() {
    checkState(!ignore, "Ignored metadata cannot be converted to Configuration");

    return new ConfigurationBuilder()
        .put(TYPE_PATH, type)
        .put(IS_REQUIRED_PATH, isRequired)
        .put(READ_ONLY_PATH, readOnly)
        .put(DISPLAY_NAME_PATH, displayName)
        .toConfiguration();
  }

  public String getType() {
    return type;
  }

  public boolean isReadOnly() {
    return readOnly;
  }

  public boolean isRequired() {
    return isRequired;
  }

  public String getDisplayName() {
    return displayName;
  }

  public boolean isIgnored() {
    return ignore;
  }
}
