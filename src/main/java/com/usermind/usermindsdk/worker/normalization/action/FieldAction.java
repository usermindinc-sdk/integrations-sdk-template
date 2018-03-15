package com.usermind.usermindsdk.worker.normalization.action;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.normalization.FieldDescription;
import com.usermind.usermindsdk.worker.normalization.NormalizationConfig;
import com.usermind.usermindsdk.worker.normalization.NormalizationOutputBuilder;

/**
 * Field Action processor for individual entity's field.
 */
public interface FieldAction {
  void process(NormalizationConfig config, FieldDescription fieldDescription, Configuration entity,
               NormalizationOutputBuilder output);

  String getActionName();
}
