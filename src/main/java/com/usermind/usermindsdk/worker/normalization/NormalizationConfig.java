package com.usermind.usermindsdk.worker.normalization;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.normalization.action.FieldAction;
import com.usermind.usermindsdk.worker.normalization.exception.NormalizationException;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Java object representation of entity config file.
 */
public class NormalizationConfig {
  public static final String INCLUDED_FIELDS_PATH = "%um_include_fields%";
  public static final String FIELDS_PATH = "fields";
  public static final String ID_PATH = "id";
  public static final String TYPE_PATH = "type";
  public static final String DISPLAY_NAME_PATH = "display_name";
  public static final String ACTION_PATH = "action";
  public static final String UM_PREFIX = "__um__";
  public static final String EXTRACT_CONFIGURATION = "extract_configurations";
  public static final String EXTRACT_CONF_PATH = "path";
  public static final String DYNAMIC_MODE = "dynamic";
  public static final String DYNAMIC_IGNORE_FIELDS = "ignore";

  // <action_name, FieldAction>
  private final Map<String, FieldAction> fieldActions;
  private final List<FieldDescription> fieldDescriptions;
  private final Configuration entityConfig;
  private final Map<String, String> extractConfigSources;
  private final Optional<EntityDynamicConfig> entityDynamicConfig;


  /**
   * Ctor.
   * @param actions action instances.
   * @param configuration entity configuration.
   */
  public NormalizationConfig(Map<String, FieldAction> actions, Configuration configuration) {
    this.fieldActions = checkNotNull(actions);
    this.entityConfig = validateEntityConfiguration(configuration);
    this.fieldDescriptions = buildFieldDescriptions(entityConfig);
    this.extractConfigSources = buildEntityConfigurationsToExtract(configuration);
    this.entityDynamicConfig = buildEntityDynamicConfig(configuration);
  }

  private Optional<EntityDynamicConfig> buildEntityDynamicConfig(Configuration configuration) {
    return configuration.atPathAsOptional(DYNAMIC_MODE).map(EntityDynamicConfig::fromConfig);
  }

  private Map<String, String> buildEntityConfigurationsToExtract(
      Configuration configuration) {

    List<Configuration> configurationList =
        configuration.getConfigurationsList(EXTRACT_CONFIGURATION, Collections.emptyList());

    return configurationList.stream().collect(Collectors.toMap(
        configItem -> configItem.getString(ID_PATH),
        configItem -> configItem.getString(EXTRACT_CONF_PATH)));
  }

  public List<FieldDescription> getFieldDescriptions() {
    return fieldDescriptions;
  }

  public Map<String, String> getExtractConfigSources() {
    return extractConfigSources;
  }

  /**
   * Returns action instance for specified field description.
   * @param fieldDescription field description
   * @return {@link FieldAction}
   */
  public FieldAction resolveFieldAction(FieldDescription fieldDescription) {
    String actionName = fieldDescription.getActionName();

    return Optional.ofNullable(fieldActions.get(actionName)).orElseThrow(
        () -> new NormalizationException("There was no action registered with name " + actionName));
  }

  public Configuration getEntityConfig() {
    return entityConfig;
  }

  public Optional<String> getEntityType() {
    return entityConfig.getStringAsOptional(TYPE_PATH);
  }

  public Optional<String> getEntityDisplayName() {
    return entityConfig.getStringAsOptional(DISPLAY_NAME_PATH);
  }

  public Optional<EntityDynamicConfig> getEntityDynamicConfig() {
    return entityDynamicConfig;
  }

  private Configuration validateEntityConfiguration(Configuration config) {
    String idActionPath = Configuration.buildFullPath(ID_PATH, ACTION_PATH);

    config.checkPathExists(idActionPath);
    config.checkPathExists(FIELDS_PATH);

    return config;
  }

  private static List<FieldDescription> buildFieldDescriptions(Configuration entityConfiguration) {
    List<FieldDescription> fieldDescriptions = new LinkedList<>();

    fieldDescriptions.add(new FieldDescription(ID_PATH, entityConfiguration.atPath(ID_PATH)));

    Configuration fieldsConfiguration = entityConfiguration.atPath(FIELDS_PATH);

    if (fieldsConfiguration.hasPath(INCLUDED_FIELDS_PATH)) {
      Configuration extraFieldsConfiguration = new ResourceConfigurationSource(fieldsConfiguration
          .getString(INCLUDED_FIELDS_PATH)).load();

      fieldsConfiguration = extraFieldsConfiguration
          .merge(fieldsConfiguration.withoutPath(INCLUDED_FIELDS_PATH));
    }

    fieldDescriptions.addAll(createFieldDescriptions(fieldsConfiguration));

    return fieldDescriptions;
  }

  private static List<FieldDescription> createFieldDescriptions(Configuration fieldsConfiguration) {
    List<FieldDescription> fieldDescriptions = new LinkedList<>();

    for (String fieldPath : fieldsConfiguration.getPaths(Optional.empty())) {
      Configuration fieldConfig = fieldsConfiguration.atPath(fieldPath);
      FieldDescription fieldDescription = new FieldDescription(fieldPath, fieldConfig);
      fieldDescriptions.add(fieldDescription);
    }
    return fieldDescriptions;
  }
}
