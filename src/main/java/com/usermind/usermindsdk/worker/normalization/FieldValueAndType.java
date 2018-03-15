package com.usermind.usermindsdk.worker.normalization;

import com.typesafe.config.ConfigValueType;
import com.usermind.usermindsdk.common.config.Configuration;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Holder for configs value and type.
 */
public class FieldValueAndType {
  private final Object value;
  private final String type;

  /**
   * Ctor.
   * @param value value
   * @param type type
   */
  public FieldValueAndType(Object value, String type) {
    checkArgument(isNotBlank(type));
    this.value = value;
    this.type = type;
  }

  public Object getValue() {
    return value;
  }

  public String getType() {
    return type;
  }

  /**
   * Returns configuration field value and type.
   * @param config configuration object.
   * @param fieldPath field path.
   * @return created {@link FieldValueAndType}
   */
  public static FieldValueAndType fromConfig(Configuration config,
      String fieldPath) {
    checkArgument(isNotBlank(fieldPath));
    ConfigValueType type = config.getFieldType(fieldPath);
    FieldValueAndType valueAndType;
    switch (type) {
      case BOOLEAN:
        valueAndType = new FieldValueAndType(config.getBoolean(fieldPath), type.toString());
        break;
      case NULL:
        valueAndType = new FieldValueAndType(null, type.toString());
        break;
      case NUMBER:
        valueAndType = new FieldValueAndType(detectAndExtractNumber(config,fieldPath),
            type.toString());
        break;
      case STRING:
        valueAndType = new FieldValueAndType(config.getString(fieldPath), type.toString());
        break;
      case OBJECT:
        valueAndType = new FieldValueAndType(config.atPath(fieldPath), type.toString());
        break;
      case LIST:
        valueAndType = new FieldValueAndType(config.getAnyValueList(fieldPath), type.toString());
        break;
      default:
        throw new IllegalArgumentException("Unsupported field type: " + type.toString());
    }
    return valueAndType;
  }

  private static Object detectAndExtractNumber(Configuration config, String fieldPath) {
    try {
      return Long.valueOf(config.getString(fieldPath));
    } catch (NumberFormatException e) {
      return Double.valueOf(config.getString(fieldPath));
    }
  }
}
