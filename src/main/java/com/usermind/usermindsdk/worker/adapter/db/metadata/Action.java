package com.usermind.usermindsdk.worker.adapter.db.metadata;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ActionsRecord;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.sql.Timestamp;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.usermind.usermindsdk.worker.autogen.lc39.tables.Actions.ACTIONS;

/**
 * Action.
 */
public class Action {
  private final long organizationId;
  private final long channelId;
  private final String name;


  /**
   * Action ctor.
   *
   * @param organizationId organizationId
   * @param channelId channelId
   * @param name action name
   */
  public Action(long organizationId, long channelId, String name) {
    this.organizationId = organizationId;
    this.channelId = channelId;
    this.name = checkNotNull(name);
  }

  /**
   * create the DB with this action.
   * @param displayName displayName
   * @param hasVariables hasVariables
   * @param hasActions hasActions
   * @param forEntity forEntity
   * @param parentId parentId
   * @param create DSLContext to use
   * @return record id
   */
  public long create(String displayName, Boolean hasVariables, Optional<Boolean> hasActions,
      Optional<String> forEntity, Optional<Long> parentId, DSLContext create) {
    checkNotNull(displayName);
    Result<?> result =
        create.select().from(ACTIONS).where(ACTIONS.ORGANIZATION_ID.eq(organizationId))
            .and(ACTIONS.CHANNEL_ID.eq(channelId))
            .and(ACTIONS.NAME.eq(name)).fetch();
    checkState(result.size() < 1,
               "got multiple fields for same (organizationId, channelId, name). aborting");
    long timestamp = DateTime.now(DateTimeZone.UTC).getMillis();
    ActionsRecord act = create.newRecord(ACTIONS);
    act.setName(name);
    act.setDisplayName(displayName);
    act.setHasVariables(hasVariables);
    act.setHasActions(hasActions.orElse(false));
    act.setForEntity(forEntity.orElse(null));
    act.setCreatedAt(new Timestamp(timestamp));
    act.setUpdatedAt(new Timestamp(timestamp));
    act.setOrganizationId(organizationId);
    act.setChannelId(channelId);
    parentId.ifPresent(act::setParentId);
    act.store();
    return getId(create);

  }

  /**
   * retrieve the id associatd with an action.
   * @param create dslcontext to use
   * @return id associated
   */
  public long getId(DSLContext create) {
    Result<?> result =
        create.select().from(ACTIONS).where(ACTIONS.ORGANIZATION_ID.eq(organizationId))
            .and(ACTIONS.CHANNEL_ID.eq(channelId)).and(ACTIONS.NAME.eq(name)).fetch();
    checkState(result.size() == 1,
               "did not get an id for (organizationId, channelId). aborting");
    return result.get(0).getValue(ACTIONS.ID);
  }
}
