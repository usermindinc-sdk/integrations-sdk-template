package com.usermind.usermindsdk.common.config;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class ConfigurationSourceChain implements ConfigurationSource {
  private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationSourceChain.class);

  public List<ConfigurationSource> configurationSources = new LinkedList<>();

  /**
   * Create a configuration source combining the data from all provided sources,
   * if available.
   *
   * @param configurationSources the configuration sources in priority order
   */
  public ConfigurationSourceChain(List<ConfigurationSource> configurationSources) {
    checkNotNull(configurationSources, "No configuration sources provided");
    checkArgument(!configurationSources.isEmpty(), "No configuration sources provided");
    this.configurationSources.addAll(configurationSources);
  }

  /**
   * Create a configuration source combining the data from all provided sources,
   * if available.
   *
   * @param configurationSources the configuration sources in priority order
   */
  public ConfigurationSourceChain(ConfigurationSource... configurationSources) {
    checkNotNull(configurationSources, "No configuration sources provided");
    checkArgument(configurationSources.length > 0, "No configuration sources provided");
    Collections.addAll(this.configurationSources, configurationSources);
  }

  @Override
  public Configuration load() {
    return Lists.reverse(configurationSources).stream()
      .map(ConfigurationSourceChain::safeLoad)
      .reduce(
        ConfigurationBuilder.getEmpty(),
        Configuration::merge
      );
  }

  private static Configuration safeLoad(ConfigurationSource source) {
    try {
      return source.load();
    } catch (Exception e) {
      LOGGER.info("Unable to load configuration source", e);
      return ConfigurationBuilder.getEmpty();
    }
  }
}
