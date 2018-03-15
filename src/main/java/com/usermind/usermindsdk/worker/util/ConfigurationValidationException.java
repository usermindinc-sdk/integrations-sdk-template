package com.usermind.usermindsdk.worker.util;

/**
 * Marker exception for errors thrown during configuration validation.
 */
public class ConfigurationValidationException extends RuntimeException {

  public ConfigurationValidationException(String message) {
    super(message);
  }
}
