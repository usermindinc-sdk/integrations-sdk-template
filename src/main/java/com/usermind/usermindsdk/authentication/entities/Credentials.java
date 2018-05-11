package com.usermind.usermindsdk.authentication.entities;

public class Credentials {
  private String clientId;
  private String clientSecret;

  public Credentials(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  public Credentials() {
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }
}
