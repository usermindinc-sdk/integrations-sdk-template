package com.usermind.usermindsdk.worker.normalization;

import com.typesafe.config.ConfigValueType;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.worker.normalization.metadata.MetadataOutputVersion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Represents Normalization output.
 */
public class NormalizationOutputBuilder {
  private final String entityType;
  private final String entityDisplayName;
  private final Configuration rawEntity;
  private final boolean extracted;

  /**
   * Entity output builder.
   */
  private final ConfigurationBuilder builder;
  /**
   * Entity output metadata builder.
   */
  private final ConfigurationBuilder metadataBuilder;
  /**
   * Extracted entities.
   * Should be populated when we have EXTRACT fieldActions.
   */
  private final List<NormalizedEntity> extractedEntities;

  private MetadataOutputVersion metadataOutputVersion = MetadataOutputVersion.V1;

  /**
   * Ctor.
   * @param entityType entity type
   * @param rawEntity raw entity
   */
  public NormalizationOutputBuilder(String entityType, Configuration rawEntity) {
    this(entityType, rawEntity, false);
  }

  /**
   * Ctor.
   * @param entityType entity type
   * @param rawEntity raw entity
   * @param extracted whether this entity was extracted from other entity
   */
  public NormalizationOutputBuilder(String entityType, Configuration rawEntity, boolean extracted) {
    this(entityType, entityType, rawEntity, extracted);
  }

  /**
   * Ctor.
   * @param entityType entity type
   * @param entityDisplayName entity display name
   * @param rawEntity raw entity
   * @param extracted whether this entity was extracted from other entity
   */
  public NormalizationOutputBuilder(String entityType, String entityDisplayName,
      Configuration rawEntity, boolean extracted) {
    checkArgument(isNotBlank(entityType));
    checkArgument(isNotBlank(entityDisplayName));
    this.entityType = entityType;
    this.entityDisplayName = entityDisplayName;
    this.rawEntity = checkNotNull(rawEntity);
    this.builder = new ConfigurationBuilder();
    this.metadataBuilder = new ConfigurationBuilder();
    this.extractedEntities = new LinkedList<>();
    this.extracted = extracted;
  }

  public NormalizationOutputBuilder setMetadataOutputVersion(
      MetadataOutputVersion metadataOutputVersion) {
    this.metadataOutputVersion = checkNotNull(metadataOutputVersion);
    return this;
  }

  /**
   * Returns new list that contains builder configuration and extracted entities.
   * @return list of configuration.
   */
  public List<NormalizedEntity> buildOutput() {
    List<NormalizedEntity> out = new ArrayList<>(extractedEntities.size() + 1);
    out.addAll(extractedEntities);
    NormalizedEntity normalizedEntity =
        new NormalizedEntity(entityType, rawEntity, builder.toConfiguration(),
            buildMetadataOutput(), extracted);
    out.add(normalizedEntity);
    return out;
  }

  /**
   * Add field and value into Normalization Output including Metadata if field type is not NULL.
   *
   * @param fieldName field name
   * @param fieldValue field value
   * @param metadata metadata
   */
  public void put(String fieldName, Object fieldValue, FieldMetadata metadata) {
    builder.put(fieldName, fieldValue);
    // TODO: we skip NULL value type metadata as we don't know correct type of this field. We should
    // investigate this in future and decide to keep this approach or use any other default type.
    if (!metadata.isIgnored() && !metadata.getType().equals(ConfigValueType.NULL.toString())) {
      metadataBuilder.putConf(fieldName, metadata.toConfiguration());
    }
  }

  /**
   * Add array field and value into Normalization Output.
   *
   * @param fieldName field name
   * @param fieldArray field value
   * @param metadata metadata
   */
  public void putList(String fieldName, List<?> fieldArray, FieldMetadata metadata) {
    builder.putAnyValueList(fieldName, fieldArray);
    if (!metadata.isIgnored()) {
      metadataBuilder.putConf(fieldName, metadata.toConfiguration());
    }
  }

  private Configuration buildMetadataOutput() {
    switch (metadataOutputVersion) {
      case V1:
        return metadataBuilder.toConfiguration();
      case V2:
        ConfigurationBuilder metaBuilder = new ConfigurationBuilder();
        metaBuilder.put("type", entityType);
        metaBuilder.put("display_name", entityDisplayName);
        metaBuilder.putConf("fields", metadataBuilder.toConfiguration());
        metaBuilder.put("version", metadataOutputVersion.name());
        return metaBuilder.toConfiguration();
      default:
        throw new IllegalArgumentException("Illegal meta out type " + metadataOutputVersion);
    }
  }
}
