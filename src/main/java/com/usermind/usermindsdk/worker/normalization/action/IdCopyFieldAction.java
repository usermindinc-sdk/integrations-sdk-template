package com.usermind.usermindsdk.worker.normalization.action;

import com.typesafe.config.ConfigValueType;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.normalization.FieldDescription;
import com.usermind.usermindsdk.worker.normalization.FieldMetadata;
import com.usermind.usermindsdk.worker.normalization.NormalizationConfig;
import com.usermind.usermindsdk.worker.normalization.NormalizationOutputBuilder;
import com.usermind.usermindsdk.worker.normalization.exception.MissingExpectedFieldException;

/**
 * ID_COPY action implementation.
 * <p> id Config example: </p>
 * <pre>
 * {
 *   "action": {
 *     "name": "ID_COPY",
 *     "config": {
 *       "path":"id"
 *     }
 *   }
 * }
 * </pre>
 * Entity's id field:
 * <pre>
 *   {"id":1}
 * </pre>
 * will be normalized as:
 * <pre>
 *   {"Id":1}
 * </pre>
 *
 */
public class IdCopyFieldAction implements FieldAction {
  private static final String ID_NORMALIZED_NAME = "Id";

  public IdCopyFieldAction() {
  }

  @Override
  public void process(NormalizationConfig config, FieldDescription fieldDescription,
      Configuration entity, NormalizationOutputBuilder output) {

    Configuration actionConfig = fieldDescription.getActionConfig();

    String idPath = actionConfig.getString("path", "id");

    if (entity.hasPath(idPath)) {
      String idValue = entity.getString(idPath);
      FieldMetadata metadata =
          new FieldMetadata(ConfigValueType.STRING.name(), false, true, ID_NORMALIZED_NAME);
      output.put(ID_NORMALIZED_NAME, idValue, metadata);
    } else {
      throw new MissingExpectedFieldException(idPath, entity, true);
    }
  }

  @Override
  public String getActionName() {
    return "ID_COPY";
  }
}
