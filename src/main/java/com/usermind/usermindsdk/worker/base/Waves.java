package com.usermind.usermindsdk.worker.base;

import com.usermind.usermindsdk.common.config.Configuration;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Waves {

  private Waves() {}

  /**
   * Retrieve the wave timestamp from the run, using either the explicit
   * timestamp setting (if available) or the run created timestamp.
   *
   * @param connector The API connector for the current run.
   * @return the wave timestamp
   */
  public static long getWaveTimestamp(IntegrationApiConnector connector) {
    Configuration run = connector.getCachedRun();
    if (run.hasPath("output.waveTimestamp")) {
      return run.getLong("output.waveTimestamp");
    }
    return LocalDateTime.parse(run.getString("created_at"))
        .toInstant(ZoneOffset.UTC).toEpochMilli();
  }

}
