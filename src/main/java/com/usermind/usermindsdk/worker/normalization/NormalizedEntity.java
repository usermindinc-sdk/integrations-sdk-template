package com.usermind.usermindsdk.worker.normalization;

import com.usermind.usermindsdk.common.config.Configuration;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Normalized entity POJO.
 */
public class NormalizedEntity {
  private final String type;
  private final Configuration raw;
  private final Configuration entity;
  private final Configuration metadata;
  private final boolean extracted;

  /**
   * Ctor.
   * @param type entity type
   * @param raw raw entity
   * @param entity normalized entity
   * @param metadata entity metadata
   */
  public NormalizedEntity(String type, Configuration raw, Configuration entity,
      Configuration metadata) {

    this(type, raw, entity, metadata, false);
  }

  /**
   * Ctor.
   * @param type entity type
   * @param raw raw entity
   * @param entity normalized entity
   * @param metadata entity metadata
   * @param extracted whether the entity was extracted from other entity
   */
  public NormalizedEntity(String type, Configuration raw, Configuration entity,
      Configuration metadata, boolean extracted) {
    checkArgument(isNotBlank(type));
    this.type = type;
    this.raw = checkNotNull(raw);
    this.entity = checkNotNull(entity);
    this.metadata = checkNotNull(metadata);
    this.extracted = extracted;
  }

  public String getType() {
    return type;
  }

  public Configuration getRaw() {
    return raw;
  }

  public Configuration getEntity() {
    return entity;
  }

  public Configuration getMetadata() {
    return metadata;
  }

  public boolean isExtracted() {
    return extracted;
  }
}
