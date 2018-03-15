package com.usermind.usermindsdk.worker.normalization;

import java.util.HashMap;
import java.util.Map;

import static com.usermind.usermindsdk.worker.normalization.NormalizationConfig.DISPLAY_NAME_PATH;
import static com.usermind.usermindsdk.worker.normalization.NormalizationConfig.TYPE_PATH;

/**
 * Holds config key resolvers (type, display_name).
 */
public class EntityConfigKeysResolver {
  private final Map<String, EntityConfigKeyResolver> resolvers;

  /**
   * C'tor.
   */
  public EntityConfigKeysResolver() {
    this.resolvers = new HashMap<>();
    this.resolvers.put(TYPE_PATH, new ConfigEntityTypeResolver());
    this.resolvers.put(DISPLAY_NAME_PATH, new ConfigEntityDisplayNameResolver());
  }

  public EntityConfigKeysResolver(Map<String, EntityConfigKeyResolver> resolvers) {
    this.resolvers = new HashMap<>(resolvers);
  }

  public EntityConfigKeysResolver register(String configKey, EntityConfigKeyResolver resolver) {
    resolvers.put(configKey, resolver);
    return this;
  }

  public EntityConfigKeyResolver get(String configKey) {
    return resolvers.get(configKey);
  }

  public EntityConfigKeyResolver getForType() {
    return get(TYPE_PATH);
  }

  public EntityConfigKeyResolver getForDisplayName() {
    return get(DISPLAY_NAME_PATH);
  }

  public Map<String, EntityConfigKeyResolver> getResolvers() {
    return resolvers;
  }
}
