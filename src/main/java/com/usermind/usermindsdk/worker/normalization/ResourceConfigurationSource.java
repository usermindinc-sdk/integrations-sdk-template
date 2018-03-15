package com.usermind.usermindsdk.worker.normalization;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.usermind.usermindsdk.common.config.Configuration;
import com.usermind.usermindsdk.common.config.ConfigurationBuilder;
import com.usermind.usermindsdk.common.config.ConfigurationSource;
import com.usermind.usermindsdk.worker.normalization.exception.NormalizationException;

import java.io.IOException;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * ConfigurationSource that loads {@link Configuration} from jar classpath.
 */
public class ResourceConfigurationSource implements ConfigurationSource {
  private final String filePath;

  public ResourceConfigurationSource(String filePath) {
    checkArgument(isNotBlank(filePath));
    this.filePath = filePath;
  }

  /**
   * Loads configuration from classpath.
   * @return loaded configuration.
   */
  public Configuration load() {
    try {
      URL url = Resources.getResource(filePath);
      return ConfigurationBuilder.createConfiguration(Resources.toString(url, Charsets.UTF_8));
    } catch (IOException | IllegalArgumentException e) {
      throw new NormalizationException("Could not read configuration file: " + filePath, e);
    }
  }
}
