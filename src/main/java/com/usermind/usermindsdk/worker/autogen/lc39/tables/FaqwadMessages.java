/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FaqwadMessagesRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FaqwadQueueDefinitionsRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FaqwadStatesRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FaqwadTypesRecord;
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

public class FaqwadMessages
extends TableImpl<FaqwadMessagesRecord> {
    private static final long serialVersionUID = -250953823L;
    public static final FaqwadMessages FAQWAD_MESSAGES = new FaqwadMessages();
    public final TableField<FaqwadMessagesRecord, Long> FAQWAD_MESSAGE_ID;
    public final TableField<FaqwadMessagesRecord, Long> FAQWAD_QUEUE_DEFINITION_ID;
    public final TableField<FaqwadMessagesRecord, String> FAQWAD_OBJECT_ID;
    public final TableField<FaqwadMessagesRecord, String> FAQWAD_SINGLETON_ID;
    public final TableField<FaqwadMessagesRecord, Long> FAQWAD_MESSAGE_TIMESTAMP;
    public final TableField<FaqwadMessagesRecord, Long> FAQWAD_MESSAGE_OFFSET;
    public final TableField<FaqwadMessagesRecord, Long> FAQWAD_TYPE_ID;
    public final TableField<FaqwadMessagesRecord, Long> FAQWAD_STATE_ID;
    public final TableField<FaqwadMessagesRecord, Object> FAQWAD_MESSAGE_DATA;
    public final TableField<FaqwadMessagesRecord, Long> ORGANIZATION_ID;
    public final TableField<FaqwadMessagesRecord, Long> PRIORITY;
    public final TableField<FaqwadMessagesRecord, Long> RETRY_COUNT;
    public final TableField<FaqwadMessagesRecord, Timestamp> NEXT_RETRY_TIME_UTC;
    public final TableField<FaqwadMessagesRecord, Long> CHECKOUT_COUNT;
    public final TableField<FaqwadMessagesRecord, Timestamp> CHECKOUT_UNTIL_UTC;
    public final TableField<FaqwadMessagesRecord, String> LAST_CHECKED_OUT_BY;
    public final TableField<FaqwadMessagesRecord, String> LAST_WORKED_BY;
    public final TableField<FaqwadMessagesRecord, Timestamp> LAST_WORK_START_TIME_UTC;
    public final TableField<FaqwadMessagesRecord, Timestamp> FIRST_WORK_START_TIME_UTC;
    public final TableField<FaqwadMessagesRecord, Timestamp> LAST_WORK_UPDATE_TIME_UTC;
    public final TableField<FaqwadMessagesRecord, Timestamp> MARKED_DONE_TIME_UTC;
    public final TableField<FaqwadMessagesRecord, Timestamp> CREATED_AT;
    public final TableField<FaqwadMessagesRecord, Timestamp> UPDATED_AT;
    public final TableField<FaqwadMessagesRecord, Long> RVN;

    @Override
    public Class<FaqwadMessagesRecord> getRecordType() {
        return FaqwadMessagesRecord.class;
    }

    public FaqwadMessages() {
        this("faqwad_messages", null);
    }

    public FaqwadMessages(String alias) {
        this(alias, FAQWAD_MESSAGES);
    }

    private FaqwadMessages(String alias, Table<FaqwadMessagesRecord> aliased) {
        this(alias, aliased, null);
    }

    private FaqwadMessages(String alias, Table<FaqwadMessagesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.FAQWAD_MESSAGE_ID = FaqwadMessages.createField("faqwad_message_id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('faqwad_messages_faqwad_message_id_seq'::regclass)", SQLDataType.BIGINT)), this, "Artificial primary key to make referring to a specific FAQWAD message easier.");
        this.FAQWAD_QUEUE_DEFINITION_ID = FaqwadMessages.createField("faqwad_queue_definition_id", SQLDataType.BIGINT.nullable(false), this, "Foreign key that refers to the name and settings for a specific use of FAQWAD. Queue definitions serve as an namespacing mechanism for different FAQWAD uses. See the faqwad_queue_definitions table for more info..");
        this.FAQWAD_OBJECT_ID = FaqwadMessages.createField("faqwad_object_id", SQLDataType.CLOB.nullable(false), this, "The faqwad_queue_definition_id, faqwad_object_id, faqwad_message_timestamp, and faqwad_message_offset combination must be unique, and messages can only be added in ascending timestamp then offset order. These values must be provided on creation, and there is no reasonable reason for them to ever be mutated, except for rows to be deleted to re-run a wave. The type of value is a string so FAQWAD can work with objects where the ID values are not just integer values. I also debated naming this just object_id, but since object is so broadly used and so is ID I decided to include the faqwad prefix to avoid name collisions.");
        this.FAQWAD_SINGLETON_ID = FaqwadMessages.createField("faqwad_singleton_id", SQLDataType.CLOB.nullable(false), this, "The faqwad_singleton_id is used in conjunction with the faqwad_object_id and state to allow idempotent message create operations, and still play well with re-running workflows or re-sending messages. For the messages sharing the same faqwad_queue_defintion_id, and faqwad_object_id, in non-terminal states the value of faqwad_singleton_id must be unique. Attempts to insert a message will be rejected as long as there is an existing open message sharing the same values for faqwad_queue_definition_id, faqwad_object_id, faqwad_singleton_id. Once  the open message is moved to a terminal state, new messages sharing those values are allowed. The reason to allow new messages in is to allow reposting messages, though that has to be done very carefully. We expect reposting messages is something that on-calls will do on occasion, so we don't want to make it impossible. It is expected that the most frequent use of the singleton constraint is to stop duplicate message creation requests when a client crashed before it got a successful create response.");
        this.FAQWAD_MESSAGE_TIMESTAMP = FaqwadMessages.createField("faqwad_message_timestamp", SQLDataType.BIGINT.nullable(false), this, "Timestamp to associate with the message, for messages from a wave the value is the timestamp of the wave. For before-time-tick messages the timestamp is 1 ms before the wave timestamp. The order messages are inserted in must only be ascending by timestamp and offset.");
        this.FAQWAD_MESSAGE_OFFSET = FaqwadMessages.createField("faqwad_message_offset", SQLDataType.BIGINT.nullable(false), this, "There are cases where we need to have multiple faqwad_messages for the same object at the same timestamp. When that happens the faqwad_messages will have the same faqwad_object_id, and faqwad_message_timestamp value, to make them be unique we need some other value that is also strictly ordered. The offset values should reset for each faqwad_message_timestamp value. I debated naming the field sequence, index, or number but all of those names seemed to be overloaded in one way or another. Offsets when combined with the faqwad_message_timestamp field form an always increasing value, and all new faqwad_messages for a faqwad_object_id must occur after the last existing value.");
        this.FAQWAD_TYPE_ID = FaqwadMessages.createField("faqwad_type_id", SQLDataType.BIGINT.nullable(false), this, "Reference to the exact type of the FAQWAD message.");
        this.FAQWAD_STATE_ID = FaqwadMessages.createField("faqwad_state_id", SQLDataType.BIGINT.nullable(false), this, "Because the faqwad_states is so small, and should almost never be updated, the database will cache the entire table in RAM, and will be able to join against values in it trivally. This is the sort of thing where it makes sense to have the DB enforce foreign key constraints. Normally I don't like using constraints because they incur overhead inside the DB to enforce. But lookup tables like this are so trivial that ensuring correct values inside the database is super cheap and it turns out to be worth it.");
        this.FAQWAD_MESSAGE_DATA = FaqwadMessages.createField("faqwad_message_data", DefaultDataType.getDefaultDataType("jsonb"), this, "A place to put any information that is needed for the processing of the the faqwad message. What is put in here will depend on the type of the message. And I'm not really sure exactly what it is going to have when.");
        this.ORGANIZATION_ID = FaqwadMessages.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "Keep track of the org because it is easier to do this than join back later");
        this.PRIORITY = FaqwadMessages.createField("priority", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("100", SQLDataType.BIGINT)), this, "Priority for processing the faqwad_message. The code that picks up faqwad_messages will pick up ones with lower priority (closer to 1) first. Using a BIGINT here just to be consistent with all the other fields.");
        this.RETRY_COUNT = FaqwadMessages.createField("retry_count", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "Retry count and the next retry time are for governing how often actual processing is retried. The retry_count is incremented when a process actually starts working, at that time is when the next_retry_time is set as well.");
        this.NEXT_RETRY_TIME_UTC = FaqwadMessages.createField("next_retry_time_utc", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("'1970-01-01'::date", SQLDataType.TIMESTAMP)), this, "The next time that the FAQWAD message should start being worked on again.");
        this.CHECKOUT_COUNT = FaqwadMessages.createField("checkout_count", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "Checkout keeps track of how many times the process that hunts for FAQWAD message has tried to get one processed. To be checked out BOTH the next_retry_time_utc and the checkout_until_utc have to be in the past. Checkout keeps us from resending the same FAQWAD message when the worker processes that are doing work are not moving things to active (and causing next_retry_time_utc to get bumped out) quickly.");
        this.CHECKOUT_UNTIL_UTC = FaqwadMessages.createField("checkout_until_utc", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("'1970-01-01'::date", SQLDataType.TIMESTAMP)), this, "Time to keep from handing out a FAQWAD message until. This keeps the system pulling messages from spamming or beating the database to death re-reading the same data over and over.");
        this.LAST_CHECKED_OUT_BY = FaqwadMessages.createField("last_checked_out_by", SQLDataType.CLOB.nullable(false).defaultValue(DSL.field("'none'::text", SQLDataType.CLOB)), this, "Set using the scheme above when the message is checked out.");
        this.LAST_WORKED_BY = FaqwadMessages.createField("last_worked_by", SQLDataType.CLOB.nullable(false).defaultValue(DSL.field("'none'::text", SQLDataType.CLOB)), this, "Set using the scheme explained for last_checked_out_by when the message state is moved to Active.");
        this.LAST_WORK_START_TIME_UTC = FaqwadMessages.createField("last_work_start_time_utc", SQLDataType.TIMESTAMP, this, "The time the FAQWAD message last started work. If the FAQWAD message has never been started the value is null. We try to not use nulls as much as possible, but we can't set the values properly at creation, and often need to set them only once. That is easy to manage with nulls so whoever has to try to do interval match with nulls, forgive me.");
        this.FIRST_WORK_START_TIME_UTC = FaqwadMessages.createField("first_work_start_time_utc", SQLDataType.TIMESTAMP, this, "First time we start processing the message.");
        this.LAST_WORK_UPDATE_TIME_UTC = FaqwadMessages.createField("last_work_update_time_utc", SQLDataType.TIMESTAMP, this, "Used to keep track of long running FAQWAD message");
        this.MARKED_DONE_TIME_UTC = FaqwadMessages.createField("marked_done_time_utc", SQLDataType.TIMESTAMP, this, "Used to track when we are done with a FAQWAD message");
        this.CREATED_AT = FaqwadMessages.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "The time the value was first created in the database. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.UPDATED_AT = FaqwadMessages.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "The last time the value was updated at. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.RVN = FaqwadMessages.createField("rvn", SQLDataType.BIGINT.nullable(false), this, "Record version number used for optimistic locking, value is managed by triggers. All queries to update a record must include the rvn with the value the changes they are requesting was based on.");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<FaqwadMessagesRecord, Long> getIdentity() {
        return Keys.IDENTITY_FAQWAD_MESSAGES;
    }

    @Override
    public UniqueKey<FaqwadMessagesRecord> getPrimaryKey() {
        return Keys.PK_FAQWAD_MESSAGES;
    }

    @Override
    public List<UniqueKey<FaqwadMessagesRecord>> getKeys() {
        return Arrays.asList(Keys.PK_FAQWAD_MESSAGES, Keys.FAQWAD_UNIQUE_MESSAGE_TIMESTAMP_OFFSET_CONSTRAINT);
    }

    @Override
    public List<ForeignKey<FaqwadMessagesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FAQWAD_MESSAGES__FAQWAD_QUEUE_DEFINITION_ID_FK, Keys.FAQWAD_MESSAGES__FAQWAD_TYPE_ID_FK, Keys.FAQWAD_MESSAGES__FAQWAD_STATE_ID_FK);
    }

    public FaqwadMessages as(String alias) {
        return new FaqwadMessages(alias, this);
    }

    public FaqwadMessages rename(String name) {
        return new FaqwadMessages(name, null);
    }
}

