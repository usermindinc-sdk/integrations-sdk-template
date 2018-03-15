package com.usermind.usermindsdk.worker.normalization;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.common.config.ConfigurationSource;
import com.usermind.usermindsdk.common.metrics.MetricsCollectorClient;
import com.usermind.usermindsdk.common.metrics.reporter.CommonLibMetricsReporter;
import com.usermind.usermindsdk.common.metrics.reporter.MetricsReporter;
import com.usermind.usermindsdk.fetch.FullFetch;
import com.usermind.usermindsdk.worker.normalization.action.*;
import com.usermind.usermindsdk.worker.normalization.exception.CorruptedEntityException;
import com.usermind.usermindsdk.worker.normalization.metadata.MetadataOutputVersion;
import com.usermind.usermindsdk.worker.util.IntegrationMetricsPathBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;

/**
 * Normalizer implementation. Use its builder to create an instance.
 * To provide custom field actions register via builder action instances.
 * <p>Example:</p>
 * <pre>
 *   <code>
 * ConfigurableNormalizer normalizer = ConfigurableNormalizer.newBuilder()
 *       .setConfigurationSource(configurationSource)
 *       .setIntegrationName("jive")
 *       .setIntegrationVersion("normalization")
 *       .registerAction(new CopyAction())
 *       .registerAction(new IdCopyAction())
 *       .build();
 *   </code>
 * </pre>
 */
public final class ConfigurableNormalizer implements Normalizer {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurableNormalizer.class);

  private static final String DEFAULT_INTEGRATION_VERSION = "normalization";

  private final NormalizationConfig normalizationConfig;
  private final MetricsReporter<MetricsCollectorClient> metricsReporter;
  private final boolean entityExtractMode; // This flag is set/used internally
  private final MetadataOutputVersion metadataOutputVersion;

  private EntityConfigKeysResolver entityConfigKeysResolver;

  private final Map<String, ConfigurableNormalizer> extractNormalizers = new HashMap<>();

  /**
   * Ctor for normalizer.
   * @param entityConfigKeysResolver resolvers for type, display_name
   *        {@link EntityConfigKeysResolver}
   * @param metadataOutVersion metadata output version
   * @param metricsReporter {@link MetricsReporter}.
   * @param configuration configuration source @see {@link ConfigurationSource}
   * @param fieldActions actions map.
   * @param extractNormalizersEntityTypeResolvers entity type resolvers for
   *        extract normalizers.
   * @param entityExtractMode whether this normalization is applied to the extracted entity
   */
  private ConfigurableNormalizer(EntityConfigKeysResolver entityConfigKeysResolver,
      MetadataOutputVersion metadataOutVersion,
      MetricsReporter<MetricsCollectorClient> metricsReporter,
      Configuration configuration,
      Map<String, FieldAction> fieldActions,
      Map<String, EntityConfigKeysResolver> extractNormalizersEntityTypeResolvers,
      boolean entityExtractMode) {

    checkNotNull(entityConfigKeysResolver);
    checkNotNull(metricsReporter);

    this.entityConfigKeysResolver = entityConfigKeysResolver;
    this.metricsReporter = metricsReporter;
    this.normalizationConfig = new NormalizationConfig(fieldActions, configuration);
    this.entityExtractMode = entityExtractMode;
    this.metadataOutputVersion = checkNotNull(metadataOutVersion);

    if (!normalizationConfig.getExtractConfigSources().isEmpty()) {
      for (Map.Entry<String, String> extractConfigEntry :
          normalizationConfig.getExtractConfigSources().entrySet()) {
        Builder extractNormalizerBuilder = ConfigurableNormalizer.newBuilder()
              .setConfigurationSource(
                  new ResourceConfigurationSource(extractConfigEntry.getValue()))
              .setMetricsReporter(metricsReporter);
        fieldActions.values().forEach(extractNormalizerBuilder::registerAction);
        if (extractNormalizersEntityTypeResolvers.containsKey(extractConfigEntry.getKey())) {
          Map<String, EntityConfigKeyResolver> resolvers =
              extractNormalizersEntityTypeResolvers.get(extractConfigEntry.getKey()).getResolvers();
          resolvers.forEach(extractNormalizerBuilder::setEntityConfigKeyResolver);
        }
        ConfigurableNormalizer extractNormalizer = extractNormalizerBuilder
            .useEntityExtractMode()
            .build();
        extractNormalizers.put(extractConfigEntry.getKey(), extractNormalizer);
      }
    }
  }

  @Override
  public NormalizationResult normalize(List<Configuration> entities) {
    List<NormalizedEntity> responses = new ArrayList<>(entities.size());
    int corruptedCount = 0;

    for (Configuration entity : entities) {
      NormalizationResult result = normalize(entity);
      responses.addAll(result.getNormalizedEntities());
      corruptedCount += result.getCorruptedEntitiesCount();
    }

    for (Normalizer extractNormalizer : extractNormalizers.values()) {
      NormalizationResult extractResult = extractNormalizer.normalize(entities);
      responses.addAll(extractResult.getNormalizedEntities());
      corruptedCount += extractResult.getCorruptedEntitiesCount();
    }

    return new NormalizationResult(responses, corruptedCount);
  }

  private NormalizationResult normalize(Configuration entity) {
    final String entityType =
        entityConfigKeysResolver.getForType().resolve(entity, normalizationConfig);

    String entityDisplayName =
        entityConfigKeysResolver.getForDisplayName().resolve(entity, normalizationConfig);

    final NormalizationOutputBuilder output =
        new NormalizationOutputBuilder(entityType, entityDisplayName, entity, entityExtractMode)
          .setMetadataOutputVersion(metadataOutputVersion);
    try {
      normalizationConfig.getEntityDynamicConfig()
          .ifPresent(dc -> normalizeRootFieldsDynamically(entity, output, dc));

      for (FieldDescription fieldDescription : normalizationConfig.getFieldDescriptions()) {
        FieldAction action = normalizationConfig.resolveFieldAction(fieldDescription);
        action.process(normalizationConfig, fieldDescription, entity, output);
      }

      return new NormalizationResult(output.buildOutput(), 0);
    } catch (CorruptedEntityException ne) {
      LOGGER.error(ne.getMessage(), ne);
      if (ne.isFatal()) {
        throw ne;
      } else {
        metricsReporter
            .count("normalization.entityCorrupted", 1, "entityType", entityType, "fieldPath",
                ne.getFieldPath());
        // TODO think about how to handle corrupted extracted entities
        return new NormalizationResult(Collections.emptyList(), 1);
      }
    } catch (Exception e) {
      LOGGER.error("Normalization exception for entity: " + entity.toString(), e);
      throw e;
    }
  }

  /**
   * All fields that are not declared in 'fields'and 'dynamic.ignore' sections
   * are normalized dynamically.
   * @param entity entity to normalize.
   * @param output output builder.
   * @param dynamicConfig {@link EntityDynamicConfig}.
   */
  private void normalizeRootFieldsDynamically(Configuration entity,
      NormalizationOutputBuilder output, EntityDynamicConfig dynamicConfig) {

    Set<String> ignoreFields =
        normalizationConfig
            .getFieldDescriptions()
            .stream()
            .map(FieldDescription::getPath)
            .collect(Collectors.toSet());

    ignoreFields.addAll(dynamicConfig.getIgnoreFields());

    // ignoreFields that are not present in config file 'ignoreFields' section
    Set<String> fields = Sets.difference(entity.getPaths(Optional.empty()), ignoreFields);

    for (String fieldName : fields) {
      FieldValueAndType field = FieldValueAndType.fromConfig(entity, fieldName);

      switch (field.getType()) {
        case "LIST":
          // we ignore lists for now
          break;
        case "OBJECT":
          FieldDescription fieldDescription =
              new FieldDescription(fieldName, createPullUpAllFieldsConfig(fieldName));
          FieldAction action = normalizationConfig.resolveFieldAction(fieldDescription);
          action.process(normalizationConfig, fieldDescription, entity, output);
          break;
        default:
          FieldMetadata metadata = new FieldMetadata(field.getType(), false, true, fieldName);
          output.put(fieldName, field.getValue(), metadata);
      }
    }
  }

  private static Configuration createPullUpAllFieldsConfig(String field) {
    return new ConfigurationBuilder()
        .put("action", "PULL_UP_ALL")
        .put("expected", false)
        .put("metadata.is_required", false)
        .put("metadata.read_only", true)
        .put("metadata.display_name", field)
        .toConfiguration();
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private static final List<FieldAction> DEFAULT_ACTIONS = ImmutableList.of(
        new CopyFieldAction(),
        new IdCopyFieldAction(),
        new PullUpFieldAction(),
        new PullUpAllFieldsAction(),
        new PullUpKeyValueArrayFieldAction(),
        new CopyIsoDateFieldAction(),
        new CopyCustomDateFieldAction()
    );

    private final Map<String, FieldAction> actions = new HashMap<>();
    private final Map<String, EntityConfigKeysResolver>
        extractNormalizersEntityConfigKeysResolvers = new HashMap<>();
    private ConfigurationSource configurationSource;
    private String integrationVersion = DEFAULT_INTEGRATION_VERSION;
    private String integrationName;
    private boolean entityExtractMode;
    private MetricsReporter<MetricsCollectorClient> metricsReporter;
    private MetadataOutputVersion metadataOutVersion = MetadataOutputVersion.V1;
    private final EntityConfigKeysResolver configKeysResolver = new EntityConfigKeysResolver();

    private Builder() {
    }

    /**
     * Sets integration version.
     * @param integrationVersion integration version.
     * @return {@link Builder}.
     * @deprecated use {@link #setMetricsReporter(MetricsReporter)}.
     */
    @Deprecated
    public Builder setIntegrationVersion(String integrationVersion) {
      this.integrationVersion = checkNotNull(integrationVersion);
      return this;
    }

    /**
     * Sets integration name.
     * @param integrationName integration name.
     * @return {@link Builder}.
     * @deprecated use {@link #setMetricsReporter(MetricsReporter)}.
     */
    @Deprecated
    public Builder setIntegrationName(String integrationName) {
      this.integrationName = checkNotNull(integrationName);
      return this;
    }

    /**
     * Sets metrics reporter.
     * @param reporter {@link MetricsReporter}
     * @return {@link Builder}.
     */
    public Builder setMetricsReporter(MetricsReporter<MetricsCollectorClient> reporter) {
      this.metricsReporter = checkNotNull(reporter);
      return this;
    }

    /**
     * Sets entity type resolver.
     *
     * @param resolver {@link EntityTypeResolver}
     * @return {@link Builder}.
     * @deprecated use {@link #setEntityTypeConfigKeyResolver(EntityConfigKeyResolver)}
     */
    @Deprecated
    public Builder setEntityTypeResolver(EntityTypeResolver resolver) {
      checkNotNull(resolver);
      EntityConfigKeyResolver configResolver = resolver::resolve;
      return setEntityTypeConfigKeyResolver(configResolver);
    }

    /**
     * Sets entity type resolver.
     *
     * @param resolver {@link EntityConfigKeyResolver}
     * @return {@link Builder}.
     */
    public Builder setEntityTypeConfigKeyResolver(EntityConfigKeyResolver resolver) {
      return setEntityConfigKeyResolver(NormalizationConfig.TYPE_PATH, resolver);
    }

    /**
     * Sets entity display name resolver.
     * @param resolver {@link EntityConfigKeyResolver}
     * @return {@link Builder}.
     */
    public Builder setEntityDisplayNameConfigKeyResolver(EntityConfigKeyResolver resolver) {
      return setEntityConfigKeyResolver(NormalizationConfig.DISPLAY_NAME_PATH, resolver);
    }

    /**
     * Sets entity config key resolver.
     * @param key config key.
     * @param resolver {@link EntityConfigKeyResolver}
     * @return {@link Builder}.
     */
    public Builder setEntityConfigKeyResolver(String key, EntityConfigKeyResolver resolver) {
      checkArgument(StringUtils.isNotBlank(key));
      checkNotNull(resolver);
      configKeysResolver.register(key, resolver);
      return this;
    }

    /**
     * Sets entity type resolver for extract normalizer.
     * @param extractNormalizerId normalizer id
     * @param resolvers {@link EntityConfigKeysResolver}.
     * @return {@link Builder}.
     */
    public Builder setEntityConfigKeysResolverForExtractNormalizer(String extractNormalizerId,
        EntityConfigKeysResolver resolvers) {
      checkArgument(StringUtils.isNotBlank(extractNormalizerId));
      checkNotNull(resolvers);
      this.extractNormalizersEntityConfigKeysResolvers.put(extractNormalizerId, resolvers);
      return this;
    }

    /**
     * Sets configuration source to read the configuration.
     * @param configurationSource configuration source.
     * @return this.
     */
    public Builder setConfigurationSource(ConfigurationSource configurationSource) {
      checkNotNull(configurationSource,
          "Could not build normalizer, provide ConfigurationSource please!");
      this.configurationSource = configurationSource;
      return this;
    }

    /**
     * Sets entity metadata output version.
     * @param version {@link MetadataOutputVersion}
     * @return {@link Builder}.
     */
    public Builder setMetadataOutputVersion(MetadataOutputVersion version) {
      this.metadataOutVersion = checkNotNull(version);
      return this;
    }

    private Builder useEntityExtractMode() {
      this.entityExtractMode = true;
      return this;
    }

    /**
     * Registers action into normalizer.
     * @param fieldAction action instance
     * @return this.
     */
    public Builder registerAction(FieldAction fieldAction) {
      checkNotNull(fieldAction);
      actions.put(fieldAction.getActionName(), fieldAction);
      return this;
    }

    /**
     * Builds new {@link ConfigurableNormalizer}.
     *
     * @return built instance.
     */
    public ConfigurableNormalizer build() {
      // Either integration name and version OR metrics reporter (not both) must be set
      checkState((integrationName != null && integrationVersion != null)
          ^ metricsReporter != null, "Either integrationName&integrationVersion or "
          + "metricsReporter must be set (not both)");

      Configuration config = configurationSource.load();

      Map<String, FieldAction> defaultActions = DEFAULT_ACTIONS.stream()
          .collect(Collectors.toMap(FieldAction::getActionName, Function.identity()));

      actions.putAll(defaultActions);

      MetricsReporter<MetricsCollectorClient> resolvedMetricsReporter = metricsReporter == null
          ? createMetricsReporter(integrationName, integrationVersion)
          : metricsReporter;

      return new ConfigurableNormalizer(configKeysResolver, metadataOutVersion,
          resolvedMetricsReporter, config, actions, extractNormalizersEntityConfigKeysResolvers,
          entityExtractMode);
    }

    private static MetricsReporter<MetricsCollectorClient> createMetricsReporter(
        String integrationName, String integrationVersion) {
      return CommonLibMetricsReporter.newBuilder()
          .setPathPrefix(new IntegrationMetricsPathBuilder()
              .setIntegrationName(integrationName)
              .setIntegrationVersion(integrationVersion)
              .setFlowName("fetch")
              .build())
          .build();
    }
  }
}
