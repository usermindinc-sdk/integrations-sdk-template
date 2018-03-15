package com.usermind.usermindsdk.worker.normalization.action;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.worker.normalization.*;

/**
 * COPY action implementation.
 * <p>Field config example:</p>
 * <pre>
 *   "firstName": {
 *      "expected":true,
 *      "metadata": {
 *        "display_name": "First Name",
 *        "read_only": false,
 *        "is_required": false
 *      }
 *    }
 * </pre>
 * <p>
 *   Entity field:
 * </p>
 * <pre>
 *   {"firstName": "Alex"}
 * </pre>
 * will be normalized as:
 * <pre>
 *   {"firstName": "Alex"}
 * </pre>
 */
public class CopyFieldAction extends AbstractFieldAction {
  public CopyFieldAction() {
    this("COPY");
  }

  protected CopyFieldAction(String actionName) {
    super(actionName);
  }

  @Override
  protected void doProcess(NormalizationConfig config, FieldDescription fieldDescription,
      Configuration entity, NormalizationOutputBuilder output) {
    FieldValueAndType valueAndType = getValueAndType(entity, fieldDescription);

    Configuration metadataConfig = fieldDescription.getMetadataConfig();

    String type = metadataConfig.getString(FieldMetadata.TYPE_PATH, valueAndType.getType());

    Configuration updatedMetadataConfig = metadataConfig
        .merge(new ConfigurationBuilder().put(FieldMetadata.TYPE_PATH, type).toConfiguration());

    FieldMetadata metadata = FieldMetadata.fromConfig(updatedMetadataConfig);

    String normalizedName = fieldDescription.getNormalizedName()
        .orElseGet(() -> formatNormalizedFieldName(fieldDescription));

    output.put(normalizedName, valueAndType.getValue(), metadata);
  }

  protected FieldValueAndType getValueAndType(Configuration entity,
      FieldDescription fieldDescription) {
    return FieldValueAndType.fromConfig(entity, fieldDescription.getPath());
  }

  /**
   * Formats normalized field name. For complex fields the '__um__' prefix will be added
   * to the field path and all '.' symbols will be replaced with '_'. For other fields
   * normalized field name will be the same as field path.
   * @param description field description
   * @return normalized field name
   */
  private String formatNormalizedFieldName(FieldDescription description) {
    return description.isNested()
        ? addUmPrefix(description.getPath().replace('.', '_'))
        : description.getPath();
  }
}
