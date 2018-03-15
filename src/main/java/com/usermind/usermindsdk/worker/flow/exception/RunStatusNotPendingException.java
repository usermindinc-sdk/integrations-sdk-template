package com.usermind.usermindsdk.worker.flow.exception;

import static com.usermind.usermindsdk.worker.flow.RunStatus.PENDING;

public class RunStatusNotPendingException extends NonRetriableIntegrationException {

  private static final long serialVersionUID = 1L;

  public RunStatusNotPendingException() {
    super("Run is not in expected status '" + PENDING + "'");
  }

}
