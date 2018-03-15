package com.usermind.usermindsdk.worker.adapter.db.metadata;

import org.jooq.DSLContext;

import static com.usermind.usermindsdk.worker.autogen.lc39.tables.Actionargs.ACTIONARGS;
import static com.usermind.usermindsdk.worker.autogen.lc39.tables.Actions.ACTIONS;

/**
 * Action.
 */
public class MetaActions {
  private final long organizationId;
  private final long channelId;

  /**
   * Action ctor.
   *
   * @param organizationId organizationId
   * @param channelId channelId
   */
  public MetaActions(long organizationId, long channelId) {
    this.organizationId = organizationId;
    this.channelId = channelId;
  }

  /**
   * delete the DB with this action.
   * @param create DSLContext to use
   */
  public void delete(DSLContext create) {

    //delete action-args
    create.deleteFrom(ACTIONARGS)
          .where(ACTIONARGS.ACTION_ID.in(create
                .select(ACTIONS.ID)
                .from(ACTIONS)
                .where(ACTIONS.ORGANIZATION_ID.eq(organizationId))
                .and(ACTIONS.CHANNEL_ID.eq(channelId))
          ))
          .execute();

    //delete actions
    create.deleteFrom(ACTIONS)
          .where(ACTIONS.ORGANIZATION_ID.eq(organizationId))
          .and(ACTIONS.CHANNEL_ID.eq(channelId))
          .execute();

  }

}
