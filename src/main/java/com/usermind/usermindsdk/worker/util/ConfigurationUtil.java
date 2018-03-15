package com.usermind.usermindsdk.worker.util;

import com.typesafe.config.ConfigUtil;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Util methods for Configuration class, like in {@link ConfigUtil}.
 */
public class ConfigurationUtil {

  /**
   * Quotes the specified path if not quoted.
   * @param path path to be quoted.
   * @return quoted path.
   */
  public static String quotePath(String path) {
    checkArgument(isNotBlank(path));
    return path.startsWith("\"") && path.endsWith("\"") ? path : ConfigUtil.quoteString(path);
  }
}
