package com.usermind.usermindsdk.worker.normalization;

import com.typesafe.config.ConfigValueType;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.worker.normalization.exception.NormalizationException;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.usermind.usermindsdk.worker.normalization.NormalizationConfig.ACTION_PATH;
import static org.apache.commons.lang3.StringUtils.isNotBlank;


/**
 * Holder for field path and description.
 */
public class FieldDescription {
  private static final String EXPECTED_PATH = "expected";
  private static final String NORMALIZED_NAME_PATH = "normalized_name";
  private static final String METADATA_PATH = "metadata";

  private final String path;
  private final Configuration config;
  private final ActionDescription actionDescription;
  private final boolean isNested;

  /**
   * Ctor.
   * @param path path
   * @param config config
   * @param isNested if the field is nested (under other field)
   */
  public FieldDescription(String path, Configuration config, boolean isNested) {
    checkArgument(StringUtils.isNotEmpty(path));
    this.path = path;
    this.config = checkNotNull(config);
    this.actionDescription = ActionDescription.fromConfiguration(config);
    this.isNested = isNested;
  }

  /**
   * Ctor.
   * @param path path
   * @param config config
   */
  public FieldDescription(String path, Configuration config) {
    this(path, config, false);
  }

  public boolean isNested() {
    return isNested;
  }

  public String getPath() {
    return path;
  }

  public Configuration getConfig() {
    return config;
  }

  public String getActionName() {
    return actionDescription.getActionName();
  }

  public Configuration getActionConfig() {
    return actionDescription.getActionConfig();
  }

  public boolean isExpected() {
    return getConfig().getBoolean(EXPECTED_PATH);
  }

  public Optional<String> getNormalizedName() {
    return getConfig().getStringAsOptional(NORMALIZED_NAME_PATH);
  }

  public Configuration getMetadataConfig() {
    return getConfig().atPath(METADATA_PATH);
  }

  static class ActionDescription {

    private static final String DEFAULT_ACTION = "COPY";
    private static final String ACTION_NAME_PATH = Configuration.buildFullPath(ACTION_PATH, "name");
    private static final String ACTION_CONFIG_PATH =
        Configuration.buildFullPath(ACTION_PATH, "config");
    private static final Configuration EMPTY_CONFIG = ConfigurationBuilder.getEmpty();

    private final String actionName;
    private final Configuration actionConfig;

    /**
     * Ctor.
     * @param actionName action name
     * @param actionConfig action config
     */
    private ActionDescription(String actionName, Configuration actionConfig) {
      checkArgument(isNotBlank(actionName));
      this.actionName = actionName;
      this.actionConfig = checkNotNull(actionConfig);
    }

    public String getActionName() {
      return actionName;
    }

    public Configuration getActionConfig() {
      return actionConfig;
    }

    /**
     * Creates {@link ActionDescription} for specified <code>fieldConfiguration</code>.
     * @param fieldConfiguration fields configuration.
     * @return FieldActionDescription
     */
    public static ActionDescription fromConfiguration(Configuration fieldConfiguration) {
      Configuration config = EMPTY_CONFIG;
      String actionName = DEFAULT_ACTION;

      if (fieldConfiguration.hasPath(ACTION_PATH)) {
        ConfigValueType actionType = fieldConfiguration.getFieldType(ACTION_PATH);

        if (actionType == ConfigValueType.OBJECT) {
          actionName = fieldConfiguration.getString(ACTION_NAME_PATH);
          config = fieldConfiguration.atPath(ACTION_CONFIG_PATH, config);
        } else if (actionType == ConfigValueType.STRING) {
          actionName = fieldConfiguration.getString(ACTION_PATH);
        } else {
          throw new NormalizationException("'action' type could be either STRING or OBJECT");
        }
      }

      return new ActionDescription(actionName, config);
    }
  }
}
