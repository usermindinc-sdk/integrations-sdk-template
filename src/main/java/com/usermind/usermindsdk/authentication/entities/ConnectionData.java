package com.usermind.usermindsdk.authentication.entities;

public class ConnectionData {

  private Encrypted encrypted;

  public ConnectionData(Encrypted encrypted){
    this.encrypted = encrypted;
  }

  public ConnectionData() {
  }

  public Encrypted getEncrypted() {
    return encrypted;
  }

  public void setEncrypted(Encrypted encrypted) {
    this.encrypted = encrypted;
  }
}
