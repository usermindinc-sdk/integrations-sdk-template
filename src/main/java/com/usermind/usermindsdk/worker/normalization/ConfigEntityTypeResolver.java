package com.usermind.usermindsdk.worker.normalization;

import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.worker.normalization.exception.NormalizationException;

/**
 * Reads entity type from configuration.
 */
public class ConfigEntityTypeResolver implements EntityConfigKeyResolver {
  public ConfigEntityTypeResolver() {
  }

  @Override
  public String resolve(Configuration entity, NormalizationConfig entityConfig) {
    return entityConfig.getEntityType()
        .orElseThrow(() -> new NormalizationException("Entity type is not found"));
  }
}
