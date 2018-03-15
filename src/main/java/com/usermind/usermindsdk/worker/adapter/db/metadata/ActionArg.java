package com.usermind.usermindsdk.worker.adapter.db.metadata;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ActionargsRecord;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.sql.Timestamp;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.usermind.usermindsdk.worker.autogen.lc39.tables.Actionargs.ACTIONARGS;

/**
 * ActionArg.
 */
public class ActionArg {

  private final long organizationId;
  private final long channelId;
  private final String actionName;
  private final String name;

  /**
   * ActionArg ctor.
   *
   * @param organizationId organizationId
   * @param channelId channelId
   * @param actionName actionName the arg is bound to
   * @param name action arg name
   */
  public ActionArg(long organizationId, long channelId, String actionName, String name) {
    this.organizationId = organizationId;
    this.channelId = channelId;
    this.actionName = actionName;
    this.name = checkNotNull(name);
  }

  /**
   * create the DB with this actionarg.
   * @param displayName displayName
   * @param assignable assignable
   * @param type type
   * @param path path
   * @param create DSLContext to use
   */
  public void create(String displayName, Boolean assignable, String type, String path,
                     DSLContext create) {
    checkNotNull(displayName);
    checkNotNull(type);

    checkNotNull(displayName);
    Result<?> result =
        create.select().from(ACTIONARGS).where(ACTIONARGS.ORGANIZATION_ID.eq(organizationId))
            .and(ACTIONARGS.ACTION_ID
                     .eq(new Action(organizationId, channelId, actionName).getId(create)))
            .and(ACTIONARGS.NAME.eq(name)).fetch();
    checkState(result.size() < 1,
               "got multiple fields for same (organizationId, actionId, name). aborting");

    long timestamp = DateTime.now(DateTimeZone.UTC).getMillis();
    ActionargsRecord aarec = create.newRecord(ACTIONARGS);
    aarec.setOrganizationId(organizationId);
    aarec.setActionId(new Action(organizationId, channelId, actionName).getId(create));
    aarec.setName(name);
    aarec.setDisplayName(displayName);
    aarec.setAssignable(assignable);
    aarec.setType(type);
    aarec.setPath(path);
    aarec.setCreatedAt(new Timestamp(timestamp));
    aarec.setUpdatedAt(new Timestamp(timestamp));
    aarec.store();

  }
}
