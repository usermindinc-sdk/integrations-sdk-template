package com.usermind.usermindsdk.authentication.entities;

public class Input {

  private ConnectionData connectionData;

  public Input(
      ConnectionData connectionData) {
    this.connectionData = connectionData;
  }

  /**
   * For JSON Binding
   */
  public Input() {
  }

  public ConnectionData getConnectionData() {
    return connectionData;
  }

  public void setConfigurationData(
      ConnectionData connectionData) {
    this.connectionData = connectionData;
  }
}
