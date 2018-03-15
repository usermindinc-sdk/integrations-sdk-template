package com.usermind.usermindsdk.common.support.exception;

import org.slf4j.Logger;

/**
 * used for the purpose of tracing empty catch blocks where it makes sense.
 */
public class EmptyCatchTracer {

  /**
   * notes the empty catch block.
   * @param logger logger to use to make the note
   * @param trouble the actual throwable
   */
  public static void note(Logger logger, Throwable trouble) {
    if (logger != null) {
      logger.trace("Empty Catch Block", trouble);
    }
  }
}
