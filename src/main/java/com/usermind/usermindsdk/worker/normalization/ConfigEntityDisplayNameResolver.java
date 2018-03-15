package com.usermind.usermindsdk.worker.normalization;

import com.usermind.usermindsdk.common.config.Configuration;

/**
 * Reads entity display name from configuration. Fallbacks to type if display_name is not found.
 */
public class ConfigEntityDisplayNameResolver implements EntityConfigKeyResolver {
  public ConfigEntityDisplayNameResolver() {
  }

  @Override
  public String resolve(Configuration entity, NormalizationConfig entityConfig) {
    return entityConfig
        .getEntityDisplayName()
        .orElseGet(() -> new ConfigEntityTypeResolver().resolve(entity, entityConfig));
  }
}
