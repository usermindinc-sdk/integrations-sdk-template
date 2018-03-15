package com.usermind.usermindsdk.worker.flow.exception;

public class SerializedDataSizeExceededException extends RuntimeException {

  public SerializedDataSizeExceededException(){
  }

  public SerializedDataSizeExceededException(String message) {
    super(message);
  }
}
