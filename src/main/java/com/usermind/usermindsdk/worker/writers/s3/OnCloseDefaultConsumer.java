package com.usermind.usermindsdk.worker.writers.s3;

import java.util.Map;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Checkpoints on-close consumer default implementation.
 */
public class OnCloseDefaultConsumer implements Consumer<Map<String, Map<ChunkType, Integer>>> {

  private Map<String, Map<ChunkType, Integer>> checkpoints;

  @Override
  public void accept(Map<String, Map<ChunkType, Integer>> checkpoints) {
    this.checkpoints = checkNotNull(checkpoints);
  }

  /**
   * Returns consumed checkpoints or throws {@code IllegalStateException} if they were not
   * consumed (yet?).
   * @return checkpoints
   * @throws IllegalStateException if checkpoints were not consumed
   */
  public Map<String, Map<ChunkType, Integer>> getCheckpoints() {
    checkState(checkpoints != null, "Checkpoints were not consumed");
    return checkpoints;
  }
}
