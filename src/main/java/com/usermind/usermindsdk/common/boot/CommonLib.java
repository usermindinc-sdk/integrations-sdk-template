package com.usermind.usermindsdk.common.boot;

import com.google.common.cache.*;
import com.usermind.usermindsdk.common.config.ConfigurationSource;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * Glue class that wraps all objects that the worker library works with.
 * Internally it build a configuration path to configuration registry map
 */
public class CommonLib {
  /* the default profile that will be used if no profile is specified */
  private static String DEFAULT_CONFIGURATION_PROFILE = "default";

  /* the maximum number of path=>registry mappings to keep around */
  private static int MAXIMUM_CONFIGURATIONS_IN_CACHE = 50;

  /* the configuration source that will be used when building configurations
  for the attached repositories */
  private static ConfigurationSource commonLibConfigurationSource = null;

  /* cache that holds the path=>registry mappings */
  private static LoadingCache<String, CommonLibRegistry> commonLibRegistryLoadingCache;

  /* holds currently loading registries */
  private static final Set<String> NOW_LOADING_REGISTRIES = Collections.newSetFromMap(
      new ConcurrentHashMap<>());

  static {
    commonLibRegistryLoadingCache = CacheBuilder.newBuilder()
        .maximumSize(MAXIMUM_CONFIGURATIONS_IN_CACHE)
        .removalListener(new RemovalListener<String, CommonLibRegistry>() {
          @Override
          public void onRemoval(RemovalNotification<String, CommonLibRegistry> notification) {
            CommonLibRegistry commonLibRegistry = notification.getValue();
            try {
              commonLibRegistry.getMetrics().stop();
            } catch (Exception exception) {
              exception.printStackTrace();
            }
          }
        }).build(new CacheLoader<String, CommonLibRegistry>() {
          @Override
          public CommonLibRegistry load(String configurationPath) throws RuntimeException {
            try {
              NOW_LOADING_REGISTRIES.add(configurationPath);
              return createCommonLibRegistry(configurationPath);
            } finally {
              NOW_LOADING_REGISTRIES.remove(configurationPath);
            }
          }
        });
  }

  /**
   * Initializes the worker library with the specified configuration source.
   * if no configuration source is given or init is not called, the default configuration source
   * will be used
   * @param configurationSourceToUse the source to use
   */
  public static void init(ConfigurationSource configurationSourceToUse) {
    commonLibConfigurationSource = configurationSourceToUse;
    commonLibRegistryLoadingCache.invalidateAll();
  }

  /**
   * Retrieves the worker lib registry associated with the given path.
   * @param configurationPath configuration path to the registry
   * @return the worker lib registry
   */
  public static CommonLibRegistry get(String configurationPath) {
    try {
      return commonLibRegistryLoadingCache.get(configurationPath);
    } catch (ExecutionException ee) {
      throw new RuntimeException(ee.getCause());
    }
  }

  /**
   * Convenience method for {@link #get(String)}*
   * @return the worker lib registry
   */
  public static CommonLibRegistry get() {
    return get(DEFAULT_CONFIGURATION_PROFILE);
  }

  /**
   * Allows to check if the {@code CommonLibRegistry} associated with a given configurationPath is
   * now loading into the cache.
   * @param configurationPath configuration path associated with the registry
   * @return true if the {@code CommonLibRegistry} associated with a given configurationPath is
   *         now loading into the cache
   */
  public static boolean isNowLoading(String configurationPath) {
    return NOW_LOADING_REGISTRIES.contains(configurationPath);
  }

  /**
   * Convenience method for {@link #isNowLoading(String)}*
   * @return true if the {@code CommonLibRegistry} associated with the "default" configuration path
   *         is now loading into the cache
   */
  public static boolean isNowLoading() {
    return isNowLoading(DEFAULT_CONFIGURATION_PROFILE);
  }

  private static CommonLibRegistry createCommonLibRegistry(String configurationPath) {
    if (commonLibConfigurationSource != null) {
      return new CommonLibDefaultRegistry(Optional.of(configurationPath),
                                            Optional.of(commonLibConfigurationSource));
    } else {
      return new CommonLibDefaultRegistry(Optional.of(configurationPath));
    }
  }
}
