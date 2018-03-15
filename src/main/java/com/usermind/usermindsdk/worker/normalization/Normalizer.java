package com.usermind.usermindsdk.worker.normalization;

import com.usermind.usermindsdk.common.config.Configuration;

import java.util.List;

/**
 * Normalizer base interface.
 */
public interface Normalizer {
  NormalizationResult normalize(List<Configuration> entities);
}
