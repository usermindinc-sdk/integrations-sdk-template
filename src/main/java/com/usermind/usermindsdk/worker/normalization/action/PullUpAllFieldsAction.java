package com.usermind.usermindsdk.worker.normalization.action;

import com.typesafe.config.ConfigValueType;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.normalization.*;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.usermind.usermindsdk.worker.normalization.NormalizationConfig.FIELDS_PATH;
import static com.usermind.usermindsdk.worker.util.ConfigurationUtil.quotePath;


/**
 * PULL_UP_ALL action implementation.
 * <p>Field config example:</p>
 * <pre>
 *  {
 *     "id": {
 *      "action": {
 *        "name": "ID_COPY",
 *        "config": {
 *          "path": "id"
 *        }
 *      }
 *    },
 *    "fields": {
 *      "object": {
 *        "action": "PULL_UP_ALL",
 *        "expected": true,
 *        "fields": {
 *          "key_1": {
 *            "expected": true,
 *            "metadata": {
 *              "display_name": "Key1",
 *              "read_only": false,
 *              "is_required": false
 *            }
 *          }
 *        }
 *      }
 *    }
 *  }
 * </pre>
 * <p>
 *   Entity field:
 * </p>
 * <pre>
 *  {
 *    "object": {
 *      "key_1": "val1",
 *      "key_2": 1,
 *      "key_3": true
 *    }
 *  }
 * </pre>
 * will be normalized as:
 * <pre>
 *  {
 *    "__um__object_key_1": "val1",
 *    "__um__object_key_2": 1,
 *    "__um__object_key_3": true
 *  }
 * </pre>
 */
public class PullUpAllFieldsAction extends AbstractFieldAction {

  public PullUpAllFieldsAction() {
    super("PULL_UP_ALL");
  }

  @Override
  protected void doProcess(NormalizationConfig config, FieldDescription fieldDescription,
      Configuration entity, NormalizationOutputBuilder output) {

    Configuration fieldConfig = fieldDescription.getConfig();
    Map<String, Configuration> fieldConfigMap;
    String quotedFieldDescriptionPath = quotePath(fieldDescription.getPath());
    if (fieldConfig.hasPath(FIELDS_PATH)) {
      fieldConfigMap = fieldConfig.atPath(FIELDS_PATH).getPaths(Optional.empty()).stream()
        .collect(Collectors.toMap(key -> quotedFieldDescriptionPath + "." + quotePath(key),
            key -> fieldConfig.atPath(FIELDS_PATH + "." + quotePath(key))));
    } else {
      fieldConfigMap = Collections.emptyMap();
    }
    boolean isRequired = fieldDescription.getMetadataConfig().getBoolean("is_required");
    boolean isReadonly = fieldDescription.getMetadataConfig().getBoolean("read_only");
    String displayName = fieldDescription.getMetadataConfig().getString("display_name");

    if (entity.hasPath(quotedFieldDescriptionPath)) {
      for (String shortPath : entity.getPaths(Optional.of(quotedFieldDescriptionPath))) {
        String quotedShortPath = quotePath(shortPath);
        String fullPath = quotedFieldDescriptionPath + "." + quotedShortPath;

        if (fieldConfigMap.containsKey(fullPath)) {
          FieldDescription nestedFieldDescription =
              new FieldDescription(fullPath, fieldConfigMap.get(fullPath), true);

          FieldAction action = config.resolveFieldAction(nestedFieldDescription);

          action.process(config, nestedFieldDescription, entity, output);
        } else {
          writeFieldValue(entity, output,
              isRequired, isReadonly, displayName,
              fieldConfigMap, shortPath, fullPath);
        }
      }
    }
  }

  private void writeFieldValue(Configuration entity, NormalizationOutputBuilder output,
      boolean isRequired, boolean isReadonly, String displayName,
      Map<String, Configuration> fieldConfigMap, String shortPath,
      String fullPath) {
    Optional<Configuration> metadata = Optional.ofNullable(fieldConfigMap.get(fullPath));
    isReadonly = metadata.flatMap(config -> config.getBooleanAsOptional("metadata.read_only"))
        .orElse(isReadonly);
    isRequired = metadata.flatMap(config -> config.getBooleanAsOptional("metadata.is_required"))
        .orElse(isRequired);
    displayName += " " + metadata.flatMap(
        config -> config.getStringAsOptional("metadata.display_name"))
        .orElse(WordUtils.capitalize(shortPath.replace('.', ' ').replace('_', ' ')));

    if (entity.getFieldType(fullPath) == ConfigValueType.OBJECT) {
      for (String subPath : entity.getPaths(Optional.of(fullPath))) {
        writeFieldValue(entity, output,
            isRequired, isReadonly, displayName,
            fieldConfigMap, subPath, fullPath + "." + quotePath(subPath));
      }
    } else {
      writeDynamicField(entity, fullPath,
          isReadonly, isRequired, displayName, output);
    }
  }

  private void writeDynamicField(Configuration entity, String fullPath,
      boolean isReadonly, boolean isRequired, String displayName,
      NormalizationOutputBuilder output) {
    FieldValueAndType fieldValueAndType = FieldValueAndType.fromConfig(entity, fullPath);
    Object value = fieldValueAndType.getValue();
    String type = fieldValueAndType.getType();
    if (!type.equals(ConfigValueType.LIST.toString())) {
      String fieldName = addUmPrefix(fullPath.replace('.', '_'));
      output.put(fieldName, value, new FieldMetadata(type, isRequired,
          isReadonly, displayName));
    }
  }
}
