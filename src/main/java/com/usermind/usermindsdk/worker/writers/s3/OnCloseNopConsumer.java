package com.usermind.usermindsdk.worker.writers.s3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.function.Consumer;

/**
 * NOP consumer implementation.
 */
public class OnCloseNopConsumer implements Consumer<Map<String, Map<ChunkType, Integer>>> {

  private static final Logger LOGGER = LoggerFactory.getLogger(OnCloseNopConsumer.class);

  @Override
  public void accept(Map<String, Map<ChunkType, Integer>> checkpoints) {
    LOGGER.info("Using NOP consumer, checkpoints: {}", checkpoints);
  }
}
