package com.usermind.usermindsdk.worker.normalization;

import com.usermind.usermindsdk.common.config.Configuration;

/**
 * Resolves entity config keys (ie: type, display_name).
 */
public interface EntityConfigKeyResolver {
  String resolve(Configuration entity, NormalizationConfig config);
}
