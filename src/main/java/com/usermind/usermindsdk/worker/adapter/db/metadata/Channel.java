package com.usermind.usermindsdk.worker.adapter.db.metadata;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ChannelsRecord;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.usermind.usermindsdk.worker.autogen.lc39.tables.Channels.CHANNELS;

/**
 * Channel.
 */
public class Channel {
  private static final Logger LOGGER = LoggerFactory.getLogger(Channel.class);

  private final long channelId;

  public Channel(long channelId) {
    this.channelId = channelId;
  }

  /**
   * update the hasEntities flag.
   *
   * @param canHaz value to set
   * @param create DSLContext to use
   */
  public void updateHasEntities(boolean canHaz, DSLContext create) {
    ChannelsRecord channelsRecord = create.fetchOne(CHANNELS, CHANNELS.ID.equal(channelId));
    if (channelsRecord == null) {
      LOGGER.error("No such connection: {}", channelId);
      return;
    }
    channelsRecord.setHasEntities(canHaz);
    channelsRecord.store();
  }

  /**
   * update the hasActions flag.
   *
   * @param canHaz value to set
   * @param create DSLContext to use
   */
  public void updateHasActions(boolean canHaz, DSLContext create) {
    ChannelsRecord channelsRecord = create.fetchOne(CHANNELS, CHANNELS.ID.equal(channelId));
    if (channelsRecord == null) {
      LOGGER.error("No such connection: {}", channelId);
      return;
    }
    channelsRecord.setHasActions(canHaz);
    channelsRecord.store();
  }
}
