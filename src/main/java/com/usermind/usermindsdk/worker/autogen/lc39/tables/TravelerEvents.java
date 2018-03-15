/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.TravelerEventStatesRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.TravelerEventTypesRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.TravelerEventsRecord;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class TravelerEvents
extends TableImpl<TravelerEventsRecord> {
    private static final long serialVersionUID = -1818960306L;
    public static final TravelerEvents TRAVELER_EVENTS = new TravelerEvents();
    public final TableField<TravelerEventsRecord, Long> TRAVELER_EVENT_ID;
    public final TableField<TravelerEventsRecord, Long> TRAVELER_ID;
    public final TableField<TravelerEventsRecord, Long> TRAVELER_EVENT_TIMESTAMP;
    public final TableField<TravelerEventsRecord, Long> TRAVELER_EVENT_OFFSET;
    public final TableField<TravelerEventsRecord, Long> TRAVELER_EVENT_TYPE_ID;
    public final TableField<TravelerEventsRecord, Long> TRAVELER_EVENT_STATE_ID;
    public final TableField<TravelerEventsRecord, Object> TRAVELER_EVENT_DATA;
    public final TableField<TravelerEventsRecord, Long> PROC_TRIGGER_ID;
    public final TableField<TravelerEventsRecord, Long> JOURNEY_ID;
    public final TableField<TravelerEventsRecord, Long> JOURNEY_VERSION;
    public final TableField<TravelerEventsRecord, Long> ORGANIZATION_ID;
    public final TableField<TravelerEventsRecord, Long> PRIORITY;
    public final TableField<TravelerEventsRecord, Long> RETRY_COUNT;
    public final TableField<TravelerEventsRecord, Timestamp> NEXT_RETRY_TIME_UTC;
    public final TableField<TravelerEventsRecord, Long> CHECKOUT_COUNT;
    public final TableField<TravelerEventsRecord, Timestamp> CHECKOUT_UNTIL_UTC;
    public final TableField<TravelerEventsRecord, String> LAST_CHECKED_OUT_BY;
    public final TableField<TravelerEventsRecord, String> LAST_WORKED_BY;
    public final TableField<TravelerEventsRecord, Timestamp> LAST_WORK_START_TIME_UTC;
    public final TableField<TravelerEventsRecord, Timestamp> FIRST_WORK_START_TIME_UTC;
    public final TableField<TravelerEventsRecord, Timestamp> LAST_WORK_UPDATE_TIME_UTC;
    public final TableField<TravelerEventsRecord, Timestamp> MARKED_DONE_TIME_UTC;
    public final TableField<TravelerEventsRecord, Timestamp> CREATED_AT;
    public final TableField<TravelerEventsRecord, Timestamp> UPDATED_AT;
    public final TableField<TravelerEventsRecord, Long> RVN;

    @Override
    public Class<TravelerEventsRecord> getRecordType() {
        return TravelerEventsRecord.class;
    }

    public TravelerEvents() {
        this("traveler_events", null);
    }

    public TravelerEvents(String alias) {
        this(alias, TRAVELER_EVENTS);
    }

    private TravelerEvents(String alias, Table<TravelerEventsRecord> aliased) {
        this(alias, aliased, null);
    }

    private TravelerEvents(String alias, Table<TravelerEventsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.TRAVELER_EVENT_ID = TravelerEvents.createField("traveler_event_id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('traveler_events_traveler_event_id_seq'::regclass)", SQLDataType.BIGINT)), this, "Artificial primary key to make referring to a specific traveler event easier.");
        this.TRAVELER_ID = TravelerEvents.createField("traveler_id", SQLDataType.BIGINT.nullable(false), this, "The traveler_id, traveler_event_timestamp, and traveler_event_offset combination must be unique, and events can only be added in ascending timestamp then offset order. These values must be provided on creation, and there is no reasonable reason for them to ever be mutated, except for rows to be deleted to re-run a wave.");
        this.TRAVELER_EVENT_TIMESTAMP = TravelerEvents.createField("traveler_event_timestamp", SQLDataType.BIGINT.nullable(false), this, "Timestamp to associate with the event, for events from a wave the value is the timestamp of the wave. For before-time-tick events the timestamp is 1 ms before the wave timestamp. The order events are inserted in must only be ascending by timestamp and offset.");
        this.TRAVELER_EVENT_OFFSET = TravelerEvents.createField("traveler_event_offset", SQLDataType.BIGINT.nullable(false), this, "There are cases where we need to have multiple traveler_events for the same traveler in the same wave. When that happens the traveler_events will have the same traveler_id, and traveler_event_timestamp value, to make them be unique we need some other value that is also strictly ordered. The offset values should reset for each traveler_event_timestamp value. I debated naming the field sequence, index, or number but all of those names seemed to be overloaded in one way or another. Offsets when combined with the traveler_event_timestamp field form an always increasing value, and all new traveler_events for a traveler_id must occur after the last existing value.");
        this.TRAVELER_EVENT_TYPE_ID = TravelerEvents.createField("traveler_event_type_id", SQLDataType.BIGINT.nullable(false), this, "Reference to the exact type of the traveler event.");
        this.TRAVELER_EVENT_STATE_ID = TravelerEvents.createField("traveler_event_state_id", SQLDataType.BIGINT.nullable(false), this, "Because the traveler_event_states is so small, and should almost never be updated, the database will cache the entire table in RAM, and will be able to join against values in it trivally. This is the sort of thing where it makes sense to have the DB enforce foreign key constraints. Normally I don't like using constraints because they incur overhead inside the DB to enforce. But lookup tables like this are so trivial that ensuring correct values inside the database is super cheap and it turns out to be worth it.");
        this.TRAVELER_EVENT_DATA = TravelerEvents.createField("traveler_event_data", DefaultDataType.getDefaultDataType("jsonb"), this, "A place to put any information that is needed for the processing of the the traveler event. What is put in here will depend on the type of the event. And I'm not really sure exactly what it is going to have when.");
        this.PROC_TRIGGER_ID = TravelerEvents.createField("proc_trigger_id", SQLDataType.BIGINT.nullable(false), this, "The ProcTrigger that caused the traveler event to be created.");
        this.JOURNEY_ID = TravelerEvents.createField("journey_id", SQLDataType.BIGINT.nullable(false), this, "The Journey ID of the traveler that this event is for.");
        this.JOURNEY_VERSION = TravelerEvents.createField("journey_version", SQLDataType.BIGINT.nullable(false), this, "The version of the journey the traveler is using.");
        this.ORGANIZATION_ID = TravelerEvents.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "Keep track of the org because it is easier to do this than join back later");
        this.PRIORITY = TravelerEvents.createField("priority", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("100", SQLDataType.BIGINT)), this, "Priority for processing the traveler_event. The code that picks up traveler_events will pick up ones with lower priority (closer to 1) first. Using a BIGINT here just to be consistent with all the other fields.");
        this.RETRY_COUNT = TravelerEvents.createField("retry_count", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "Retry count and the next retry time are for governing how often actual processing is retried. The retry_count is incremented when a process actually starts working, at that time is when the next_retry_time is set as well.");
        this.NEXT_RETRY_TIME_UTC = TravelerEvents.createField("next_retry_time_utc", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("'1970-01-01'::date", SQLDataType.TIMESTAMP)), this, "The next time that the traveler event should start being worked on again.");
        this.CHECKOUT_COUNT = TravelerEvents.createField("checkout_count", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "Checkout keeps track of how many times the process that hunts for traveler events has tried to get one processed. To be checked out BOTH the next_retry_time_utc and the checkout_until_utc have to be in the past. Checkout keeps us from resending the same traveler event when the worker processes that are doing work are not moving things to active (and causing next_retry_time_utc to get bumped out) quickly.");
        this.CHECKOUT_UNTIL_UTC = TravelerEvents.createField("checkout_until_utc", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("'1970-01-01'::date", SQLDataType.TIMESTAMP)), this, "Time to keep from handing out a traveler event until. This keeps the system pulling events from spamming or beating the database to death re-reading the same data over and over.");
        this.LAST_CHECKED_OUT_BY = TravelerEvents.createField("last_checked_out_by", SQLDataType.CLOB.nullable(false).defaultValue(DSL.field("'none'::text", SQLDataType.CLOB)), this, "Set using the scheme above when the event is checked out.");
        this.LAST_WORKED_BY = TravelerEvents.createField("last_worked_by", SQLDataType.CLOB.nullable(false).defaultValue(DSL.field("'none'::text", SQLDataType.CLOB)), this, "Set using the scheme explained for last_checked_out_by when the event state is moved to Active.");
        this.LAST_WORK_START_TIME_UTC = TravelerEvents.createField("last_work_start_time_utc", SQLDataType.TIMESTAMP, this, "The time the traveler event last started work. If the traveler event has never been started the value is null. We try to not use nulls as much as possible, but we can't set the values properly at creation, and often need to set them only once. That is easy to manage with nulls so whoever has to try to do interval match with nulls, forgive me.");
        this.FIRST_WORK_START_TIME_UTC = TravelerEvents.createField("first_work_start_time_utc", SQLDataType.TIMESTAMP, this, "First time we start processing the event.");
        this.LAST_WORK_UPDATE_TIME_UTC = TravelerEvents.createField("last_work_update_time_utc", SQLDataType.TIMESTAMP, this, "Used to keep track of long running traveler events");
        this.MARKED_DONE_TIME_UTC = TravelerEvents.createField("marked_done_time_utc", SQLDataType.TIMESTAMP, this, "Used to track when we are done with a traveler event");
        this.CREATED_AT = TravelerEvents.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "The time the value was first created in the database. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.UPDATED_AT = TravelerEvents.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "The last time the value was updated at. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.RVN = TravelerEvents.createField("rvn", SQLDataType.BIGINT.nullable(false), this, "Record version number used for optimistic locking, value is managed by triggers. All queries to update a record must include the rvn with the value the changes they are requesting was based on.");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<TravelerEventsRecord, Long> getIdentity() {
        return Keys.IDENTITY_TRAVELER_EVENTS;
    }

    @Override
    public UniqueKey<TravelerEventsRecord> getPrimaryKey() {
        return Keys.PK_TRAVELER_EVENTS;
    }

    @Override
    public List<UniqueKey<TravelerEventsRecord>> getKeys() {
        return Arrays.asList(Keys.PK_TRAVELER_EVENTS, Keys.TE_UNIQUE_TRAVELER_TIMESTAMP_OFFSET_CONSTRAINT);
    }

    @Override
    public List<ForeignKey<TravelerEventsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.TRAVELER_EVENTS__TRAVELER_EVENT_TYPE_ID_FK, Keys.TRAVELER_EVENTS__TRAVELER_EVENT_STATE_ID_FK);
    }

    public TravelerEvents as(String alias) {
        return new TravelerEvents(alias, this);
    }

    public TravelerEvents rename(String name) {
        return new TravelerEvents(name, null);
    }
}

