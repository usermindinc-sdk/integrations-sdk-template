package com.usermind.usermindsdk.worker.normalization;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class NormalizationResult {
  private final List<NormalizedEntity> normalizedEntities;
  private final int corruptedEntitiesCount;

  public NormalizationResult(List<NormalizedEntity> normalizedEntities,
      int corruptedEntitiesCount) {
    this.normalizedEntities = checkNotNull(normalizedEntities);
    this.corruptedEntitiesCount = corruptedEntitiesCount;
  }

  public List<NormalizedEntity> getNormalizedEntities() {
    return normalizedEntities;
  }

  public int getCorruptedEntitiesCount() {
    return corruptedEntitiesCount;
  }
}
