package com.usermind.usermindsdk.worker.normalization;

import com.usermind.usermindsdk.common.config.Configuration;

/**
 * Resolves entity type.
 * @deprecated renaming, use {@link EntityConfigKeyResolver}.
 */
@Deprecated
public interface EntityTypeResolver {
  String resolve(Configuration entity, NormalizationConfig config);
}
