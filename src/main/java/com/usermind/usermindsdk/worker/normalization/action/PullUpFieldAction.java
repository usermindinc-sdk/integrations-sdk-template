package com.usermind.usermindsdk.worker.normalization.action;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.normalization.FieldDescription;
import com.usermind.usermindsdk.worker.normalization.NormalizationConfig;
import com.usermind.usermindsdk.worker.normalization.NormalizationOutputBuilder;

import java.util.Optional;

import static com.usermind.usermindsdk.worker.normalization.NormalizationConfig.FIELDS_PATH;

/**
 * PULL_UP action implementation.
 * <p>Field config example:</p>
 * <pre>
 *   "primaryAttribute": {
 *      "action": "PULL_UP",
 *      "expected":true,
 *      "fields": {
 *        "name": {
 *           "expected": true,
 *           "metadata": {
 *              "display_name": "Primary Attribute Name",
 *              "read_only": false,
 *              "is_required": false
 *           }
 *         },
 *        "dataType": {
 *           "expected": true,
 *           "metadata": {
 *             "display_name": "Primary Attribute Data Type",
 *             "read_only": false,
 *             "is_required": true
 *           }
 *        }
 *      }
 *   }
 * </pre>
 * <p>
 *   Entity field:
 * </p>
 * <pre>
 *   {
 *     "name": {
 *       "firstName": "Alex"
 *     }
 *   }
 * </pre>
 * will be normalized as:
 * <pre>
 *   {"name_firstName": "Alex"}
 * </pre>
 */
public class PullUpFieldAction extends AbstractFieldAction {
  public PullUpFieldAction() {
    super("PULL_UP");
  }

  @Override
  protected void doProcess(NormalizationConfig config, FieldDescription fieldDescription,
      Configuration entity, NormalizationOutputBuilder output) {
    Configuration fields = fieldDescription.getConfig().atPath(FIELDS_PATH);

    for (String nestedFieldPath : fields.getPaths(Optional.empty())) {
      Configuration nestedFieldConfig = fields.atPath(nestedFieldPath);

      String nestedFieldFullPath =
          Configuration.buildFullPath(fieldDescription.getPath(), nestedFieldPath);

      FieldDescription nestedFieldDescription =
          new FieldDescription(nestedFieldFullPath, nestedFieldConfig, true);

      FieldAction action = config.resolveFieldAction(nestedFieldDescription);

      action.process(config, nestedFieldDescription, entity, output);
    }
  }
}
