package com.usermind.usermindsdk.worker.adapter.db.actions;

import com.usermind.usermindsdk.common.boot.CommonLib;
import com.usermind.usermindsdk.fetch.FullFetch;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ActionjournalRecord;
import org.apache.commons.lang3.Validate;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import static com.usermind.usermindsdk.worker.autogen.lc39.tables.Actionjournal.ACTIONJOURNAL;

/**
 * ActionJournal - Resource for updating action record data.
 */
public class ActionJournal {
  private static final Logger LOGGER = LoggerFactory.getLogger(FullFetch.class);

  private long actionId;

  public enum Status {
    FAILED(0),
    SUCCESS(1),
    PENDING(2),
    IN_PROGRESS(3),
    INVALID(6);

    private static final Map<Integer,Status> lookup = new HashMap<>();
    static {
      for (Status status : EnumSet.allOf(Status.class)) {
        lookup.put(status.getStatusState(), status);
      }
    }

    private int value;

    Status(int value) {
      this.value = value;
    }

    public int getStatusState() {
      return value;
    }

    public static Status get(int value) {
      return lookup.get(value);
    }
  }

  public ActionJournal(long actionId) {
    this.actionId = actionId;
  }

  /**
   * Update the action status column in ActionJournal.
   *
   * @param create transaction for this update
   * @param status update record with this value
   */
  public void updateStatus(DSLContext create, Status status) {
    Validate.notNull(status, "Invalid Action status, action status cannot be null");
    ActionjournalRecord record = create.fetchOne(ACTIONJOURNAL, ACTIONJOURNAL.ID.eq(actionId));
    Validate.notNull(record, "Unable to retrieve action from action journal {}", actionId);
    Status previousStatus = Status.get(record.getStatus());
    if (previousStatus == null) {
      LOGGER.error("Current integer Status {} in the ActionJournal is invalid for actionId {}. Continuing with"
          + " the update to Status {}", record.getStatus(), actionId, status);
    }
    else {
      LOGGER.debug("Updated action state of actionId {} from {} to {}",
          actionId,
          previousStatus.toString(),
          status.toString());
    }
    record.setStatus(status.getStatusState());
    record.store();
  }

}
