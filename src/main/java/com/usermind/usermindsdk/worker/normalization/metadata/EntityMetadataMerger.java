package com.usermind.usermindsdk.worker.normalization.metadata;

import com.typesafe.config.ConfigValueType;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.metrics.MetricsCollectorClient;
import com.usermind.usermindsdk.common.metrics.reporter.MetricsReporter;
import com.usermind.usermindsdk.worker.normalization.NormalizedEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.usermind.usermindsdk.common.config.Configuration.buildFullPath;
import static com.usermind.usermindsdk.worker.normalization.FieldMetadata.TYPE_PATH;
import static com.usermind.usermindsdk.worker.util.ConfigurationUtil.quotePath;
import static java.util.Optional.empty;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Entity metadata merger.
 */
public class EntityMetadataMerger {

  private static final Logger LOGGER = LoggerFactory.getLogger(EntityMetadataMerger.class);

  private final MetricsReporter<MetricsCollectorClient> metricsReporter;
  private final Map<String, Configuration> entityTypeMergedMetadata;

  /**
   * {@code EntityMetadataMerger} ctor.
   * @param metricsReporter a metrics reporter to use
   */
  public EntityMetadataMerger(MetricsReporter<MetricsCollectorClient> metricsReporter) {
    this.metricsReporter = checkNotNull(metricsReporter);
    this.entityTypeMergedMetadata = new HashMap<>();
  }

  /**
   * Merges a given entity metadata.
   * @param entity entity
   */
  public void merge(NormalizedEntity entity) {
    checkNotNull(entity);
    merge(entity.getType(), entity.getMetadata());
  }

  /**
   * Merge metadata for a particular entity type.
   * @param entityType entity type.
   * @param entityMetadata entity metadata.
   */
  public void merge(String entityType, Configuration entityMetadata) {
    checkArgument(isNotBlank(entityType));
    checkNotNull(entityMetadata);
    entityTypeMergedMetadata.merge(entityType, entityMetadata,
        (mergedMetadata, metadata) -> checkAndRemap(entityType, mergedMetadata, metadata));
  }

  private Configuration checkAndRemap(String entityType, Configuration mergedMetadata,
      Configuration metadata) {
    MetadataOutputVersion metadataVersion = getMetadataVersion(metadata);

    Configuration metadataFields = getFieldsMetadata(metadata, metadataVersion);
    Configuration mergedMetadataFields = getFieldsMetadata(mergedMetadata, metadataVersion);

    for (String path : mergedMetadataFields.getPaths(empty())) {
      String quotedPath = quotePath(path);
      String typePath = buildFullPath(quotedPath, TYPE_PATH);

      if (metadataFields.hasPath(quotedPath)
          && !mergedMetadataFields.getString(typePath).equals(metadataFields.getString(typePath))) {

        metricsReporter.increment("normalization.metadata.propertyTypeOverride", "property", path,
            "entity", entityType);
        LOGGER.warn("Got metadata type override for property '{}' of entity type '{}'", path,
            entityType);
      }
    }

    // Merging current merged metadata with the given metadata
    return mergedMetadata.merge(metadata);
  }

  /**
   * Returns a merged metadata for each entity type.
   * @return a {@code Map} of {@code String} entity type to merged metadata {@code Configuration}
   */
  public Map<String, Configuration> getMergedMetadata() {
    return Collections.unmodifiableMap(entityTypeMergedMetadata);
  }

  private static Configuration getFieldsMetadata(Configuration metadata,
      MetadataOutputVersion version) {
    return version == MetadataOutputVersion.V2
        ? metadata.atPath("fields")
        : metadata;
  }

  private static MetadataOutputVersion getMetadataVersion(Configuration metadata) {
    MetadataOutputVersion version = MetadataOutputVersion.V1;
    if (metadata.hasPath("version") && metadata.getFieldType("version") == ConfigValueType.STRING) {
      version = MetadataOutputVersion.valueOf(metadata.getString("version"));
    }
    return version;
  }
}
