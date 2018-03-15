package com.usermind.usermindsdk.worker.adapter.db.metadata;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.EntitiesRecord;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;
import static com.usermind.usermindsdk.worker.autogen.lc39.Tables.ENTITIES;


/**
 * Entity.
 */
public class Entity {

  private String type;
  private String displayName;
  private HashMap<String, Field> fields;

  /**
   * build the Entity db structure.
   * @param type entity type
   * @param displayName entity display name
   */
  public Entity(String type, String displayName) {
    checkArgument(StringUtils.isNotBlank(type), "Missing type name for entity");
    checkArgument(StringUtils.isNotBlank(displayName), String.format("Missing displayName for type %s", type));
    this.type = type;
    this.displayName = displayName;
    fields = new HashMap<>();
  }

  /**
   * add field to the entity.
   * @param name field name
   * @param value field value
   * @param displayName display name
   * @param isRequired if mandatory
   * @param readOnly if readOnly
   */
  public void addField(String name, String value, String displayName, boolean isRequired,
                       Optional<Boolean> readOnly) {
    fields.put(name, new Field(name, value, displayName, isRequired, readOnly));
  }

  /**
   * update the DB with this entity.
   * @param organizationId orgId
   * @param channelId channelId
   * @param create DSLContext to use
   */
  public void update(long organizationId, long channelId, DSLContext create) {
    Result<?> result = create.select().from(ENTITIES).where(ENTITIES.CHANNEL_ID.eq(channelId)).and(
        ENTITIES.ORGANIZATION_ID.eq(organizationId)).and(ENTITIES.NAME.eq(type)).fetch();
    checkState(result.size() <= 1,
        "got multiple entities for same (org, channel, entity). aborting");
    if (result.isEmpty()) {
      long timestamp = DateTime.now(DateTimeZone.UTC).getMillis();
      EntitiesRecord er = create.newRecord(ENTITIES);
      er.setOrganizationId(organizationId);
      er.setChannelId(channelId);
      er.setName(type);
      er.setDisplayName(displayName);
      er.setCreatedAt(new Timestamp(timestamp));
      er.setUpdatedAt(new Timestamp(timestamp));
      er.store();
    }
    long entityId = create.select().from(ENTITIES).where(ENTITIES.CHANNEL_ID.eq(channelId)).and(
        ENTITIES.ORGANIZATION_ID.eq(organizationId)).and(ENTITIES.NAME.eq(type)).fetchOne()
        .getValue(ENTITIES.ID);
    for (Map.Entry<String, Field> fieldSelector : fields.entrySet()) {
      fieldSelector.getValue().update(entityId, organizationId, channelId, create);
    }
  }
}
