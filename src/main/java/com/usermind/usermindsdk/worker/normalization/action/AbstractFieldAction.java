package com.usermind.usermindsdk.worker.normalization.action;

import com.google.common.base.Preconditions;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.normalization.FieldDescription;
import com.usermind.usermindsdk.worker.normalization.NormalizationConfig;
import com.usermind.usermindsdk.worker.normalization.NormalizationOutputBuilder;
import com.usermind.usermindsdk.worker.normalization.exception.MissingExpectedFieldException;

/**
 * Action that contains common logic. Extend it for your needs.
 */
public abstract class AbstractFieldAction implements FieldAction {
  protected final String name;

  protected AbstractFieldAction(final String name) {
    this.name = Preconditions.checkNotNull(name);
  }

  @Override
  public void process(NormalizationConfig config, FieldDescription fieldDescription,
      Configuration entity, NormalizationOutputBuilder output) {
    boolean fieldExistsAndNotNull = entity.hasPath(fieldDescription.getPath());
    if (fieldDescription.isExpected() && !fieldExistsAndNotNull) {
      throw new MissingExpectedFieldException(fieldDescription.getPath(), entity);
    }
    boolean fieldExists = entity.hasPathOrNull(fieldDescription.getPath());
    if (fieldExists) {
      doProcess(config, fieldDescription, entity, output);
    }
  }

  protected String addUmPrefix(String value) {
    return NormalizationConfig.UM_PREFIX + value;
  }

  protected abstract void doProcess(NormalizationConfig config, FieldDescription fieldDescription,
      Configuration entity, NormalizationOutputBuilder output);

  @Override
  public String getActionName() {
    return name;
  }
}
