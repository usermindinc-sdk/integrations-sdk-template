package com.usermind.usermindsdk.common.config;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationLoadingException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  private final List<Throwable> causes;

  public ConfigurationLoadingException(String message, List<Throwable> causes) {
    super(message);
    this.causes = new ArrayList<Throwable>(causes);
  }

  @Override
  public String getMessage() {
    StringBuilder messages = new StringBuilder(super.getMessage()).append(":");
    for (Throwable cause : causes) {
      messages.append("\n\tCause: ").append(cause.getMessage());
    }

    return messages.toString();
  }
}
