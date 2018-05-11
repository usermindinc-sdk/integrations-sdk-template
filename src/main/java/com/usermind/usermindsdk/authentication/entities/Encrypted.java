package com.usermind.usermindsdk.authentication.entities;


public class Encrypted {

  private Credentials credentials;
  private Endpoints endpoints;

  public Encrypted(Credentials credentials, Endpoints endpoints) {
    this.credentials = credentials;
    this.endpoints = endpoints;
  }

  public Encrypted() {
  }

  public Credentials getCredentials() {
    return credentials;
  }

  public Endpoints getEndpoints() {
    return endpoints;
  }
}
