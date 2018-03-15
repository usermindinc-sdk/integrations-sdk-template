package com.usermind.usermindsdk.worker.normalization;

import com.usermind.usermindsdk.common.config.Configuration;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Configuration of dynamic section.
 */
public class EntityDynamicConfig {
  private final Set<String> ignoreFields;

  public EntityDynamicConfig(Set<String> ignoreFields) {
    this.ignoreFields = checkNotNull(ignoreFields);
  }

  public Set<String> getIgnoreFields() {
    return ignoreFields;
  }

  /**
   * Deserialize {@link EntityDynamicConfig} from configuration.
   * @param config configuration.
   * @return {@link EntityDynamicConfig}
   */
  public static EntityDynamicConfig fromConfig(Configuration config) {
    return new EntityDynamicConfig(new HashSet<>(
        config.getStringListAsOptional(NormalizationConfig.DYNAMIC_IGNORE_FIELDS)
            .orElse(Collections.emptyList())));
  }
}
