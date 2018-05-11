package com.usermind.usermindsdk.authentication.entities;

public class Endpoints {
  private String api;
  private String identity;

  public Endpoints(String api, String identity) {
    this.api = api;
    this.identity = identity;
  }

  public Endpoints() {
  }

  public String getApi() {
    return api;
  }

  public String getIdentity() {
    return identity;
  }
}
