package com.usermind.usermindsdk.common.config;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * Configuration source loading configuration from the file passed in json format.
 */
public class JsonFileConfigurationSource implements ConfigurationSource {

  /* location where the file configuration will live in by default */
  public static final String DEFAULT_FILE_CONFIGURATION_LOCATION = "/etc/usermind/config.json";

  /* location of the file used for configuration */
  private final String jsonConfigurationFile;

  public JsonFileConfigurationSource(String jsonConfigurationFile) {
    this.jsonConfigurationFile = jsonConfigurationFile;
  }

  public JsonFileConfigurationSource() {
    this(DEFAULT_FILE_CONFIGURATION_LOCATION);
  }

  @Override
  public Configuration load() {
    try {
      File file = new File(jsonConfigurationFile);
      String fileContents = Files.toString(file, Charsets.UTF_8);
      JsonStringConfigurationSource jsonStringConfigurationSource
          = new JsonStringConfigurationSource(fileContents);
      return jsonStringConfigurationSource.load();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
