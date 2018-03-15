package com.usermind.usermindsdk.worker.normalization.action;

import com.typesafe.config.ConfigValueType;
import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.normalization.*;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNoneBlank;

/**
 * Pulls up all array items that are key-value.
 * <p>Field config example:</p>
 * <pre>
 *   "attributes": {
 *     "expected": true,
 *     "action": {
 *       "name": "PULL_UP_KEY_VALUE_ARRAY",
 *       "config": {
 *         "keyField":"name",
 *         "valueField":"value"
 *       }
 *     }
 *   }
 * </pre>
 * <p>
 *   Entity field:
 * </p>
 * <pre>
 *   {
 *    "attributes": [
 *         {
 *           "name": "Source Type",
 *           "value": "Web page visit"
 *         },
 *         {
 *           "name": "Source Info",
 *           "value": "http://search.yahoo.com/search?p=train+cappuccino+army"
 *         }
 *      ]
 *     }
 * </pre>
 * will be normalized as:
 * <pre>
 *   {
 *     "attributes": {
 *       "metadata": {
 *         "attributes_Source_Info": {
 *           "display_name": "Attributes Source Info",
 *           "is_required": false,
 *           "read_only": true,
 *           "type": "STRING"
 *         },
 *         "attributes_Source_Type": {
 *           "display_name": "Attributes Source Type",
 *           "is_required": false,
 *           "read_only": true,
 *           "type": "STRING"
 *         }
 *       }
 *     },
 *     "attributes_Source_Info": "http://search.yahoo.com/search?p=train+cappuccino+army",
 *     "attributes_Source_Type": "Web page visit"
 *   }
 * </pre>
 */
public class PullUpKeyValueArrayFieldAction extends AbstractFieldAction {
  private static final String KEY_FIELD = "keyField";
  private static final String VALUE_FIELD = "valueField";

  private final Logger logger = LoggerFactory.getLogger(PullUpKeyValueArrayFieldAction.class);

  /**
   * Ctor.
   */
  public PullUpKeyValueArrayFieldAction() {
    super("PULL_UP_KEY_VALUE_ARRAY");
  }

  @Override
  protected void doProcess(NormalizationConfig config, FieldDescription fieldDescription,
      Configuration entity, NormalizationOutputBuilder output) {

    if (!entity.hasPath(fieldDescription.getPath())) {
      return;
    }

    Configuration actionConfig = fieldDescription.getActionConfig();
    validateActionConfig(actionConfig);

    List<Configuration> arrayItems =
        entity.getConfigurationsList(fieldDescription.getPath());

    String keyPath = actionConfig.getString(KEY_FIELD);
    String valuePath = actionConfig.getString(VALUE_FIELD);

    for (Configuration item : arrayItems) {
      String keyValue = item.getString(keyPath);
      String correctedFieldName = keyValue.replace(' ', '_');
      try {
        FieldValueAndType valueAndType = FieldValueAndType.fromConfig(item, valuePath);

        if (valueAndType.getType().equals(ConfigValueType.OBJECT.name())) {
          Configuration objectInside = (Configuration) valueAndType.getValue();
          for (String key : objectInside.getAllPaths(Optional.empty())) {

            FieldValueAndType subFieldValueAndType = FieldValueAndType.fromConfig(objectInside,
                key);

            String normalizedName = escapeFieldName(addUmPrefix(
                String.format("%s_%s_%s", fieldDescription.getPath(), correctedFieldName, key)));

            String displayName = WordUtils.capitalize(fieldDescription.getPath() + " " + keyValue
                + " " + key);

            FieldMetadata metadata = new FieldMetadata(subFieldValueAndType.getType(), false,
                true, displayName);

            output.put(normalizedName, subFieldValueAndType.getValue(), metadata);
          }
        } else {
          String normalizedName = escapeFieldName(
              addUmPrefix(String.format("%s_%s", fieldDescription.getPath(), correctedFieldName)));

          String displayName = WordUtils.capitalize(fieldDescription.getPath()) + " " + keyValue;

          FieldMetadata metadata = new FieldMetadata(valueAndType.getType(), false, true,
              displayName);

          if (valueAndType.getType().equals(ConfigValueType.LIST.name())) {
            output.putList(normalizedName, item.getAnyValueList(valuePath), metadata);
          } else {
            output.put(normalizedName, valueAndType.getValue(), metadata);
          }
        }
      } catch (IllegalArgumentException e) {
        logger.warn("Not supported field type : {}",item);
      }
    }
  }

  protected void validateActionConfig(Configuration actionConfig) {
    actionConfig.checkPathExists(KEY_FIELD);
    actionConfig.checkPathExists(VALUE_FIELD);

    checkArgument(
        isNoneBlank(actionConfig.getString(KEY_FIELD), actionConfig.getString(VALUE_FIELD)));
  }

  private static String escapeFieldName(String fieldName) {
    return '"' + fieldName + '"';
  }
}
