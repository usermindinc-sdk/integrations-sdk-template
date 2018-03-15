package com.usermind.usermindsdk.worker.adapter.db.metadata;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FieldsRecord;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static com.usermind.usermindsdk.worker.autogen.lc39.tables.Fields.FIELDS;

/**
 * Field.
 */
public class Field {

  private final String name;
  private final String value;
  private final String displayName;
  private final Boolean isRequired;
  private final Optional<Boolean> readOnly;

  /**
   * build the Field db structure.
   * @param name of the field.
   * @param value type of the field.
   * @param displayName display name.
   * @param isRequired if field is mandatory.
   * @param readOnly if field is readonly.
   */
  public Field(String name, String value, String displayName, Boolean isRequired,
      Optional<Boolean> readOnly) {
    this.name = checkNotNull(name);
    this.value = checkNotNull(value);
    this.displayName = checkNotNull(displayName);
    this.isRequired = checkNotNull(isRequired);
    this.readOnly = checkNotNull(readOnly);
  }

  public String getName() {
    return name;
  }

  public String getValue() {
    return value;
  }

  /**
   * update the DB with this field.
   * @param entityId entityId
   * @param organizationId orgId
   * @param channelId channelId
   * @param create DSLContext to use
   */
  public void update(long entityId, long organizationId, long channelId, DSLContext create) {
    Result<?>
        result =
        create.select().from(FIELDS).where(FIELDS.ENTITY_ID.eq(entityId))
            .and(FIELDS.ORGANIZATION_ID.eq(organizationId)).and(FIELDS.NAME.eq(name)).fetch();
    checkState(result.size() <= 1,
               "got multiple fields for same (entityId, org, fieldName). aborting");
    if (result.isEmpty()) {
      long timestamp = DateTime.now(DateTimeZone.UTC).getMillis();
      FieldsRecord fr = create.newRecord(FIELDS);
      fr.setOrganizationId(organizationId);
      fr.setName(name);
      fr.setType(value);
      fr.setCreatedAt(new Timestamp(timestamp));
      fr.setUpdatedAt(new Timestamp(timestamp));
      fr.setDisplayName(displayName);
      fr.setIsRequired(isRequired);
      readOnly.ifPresent(fr::setReadOnly);
      fr.setEntityId(entityId);
      fr.store();
    } else {
      Map<org.jooq.Field<?>, Object> updates = new HashMap<>();
      if (!value.equals(result.getValue(0, FIELDS.TYPE))) {
        updates.put(FIELDS.TYPE, value);
      }
      if (!displayName.equals(result.getValue(0, FIELDS.DISPLAY_NAME))) {
        updates.put(FIELDS.DISPLAY_NAME, displayName);
      }
      if (!isRequired.equals(result.getValue(0, FIELDS.IS_REQUIRED))) {
        updates.put(FIELDS.IS_REQUIRED, isRequired);
      }
      readOnly.filter(isReadOnly -> !isReadOnly.equals(result.getValue(0, FIELDS.READ_ONLY)))
          .ifPresent(isReadOnly -> updates.put(FIELDS.READ_ONLY, isReadOnly));
      if (!updates.isEmpty()) {
        create.update(FIELDS).set(updates).where(FIELDS.ENTITY_ID.eq(entityId)).and(
            FIELDS.ORGANIZATION_ID.eq(organizationId)).and(FIELDS.NAME.eq(name)).execute();
      }
    }
  }

}
