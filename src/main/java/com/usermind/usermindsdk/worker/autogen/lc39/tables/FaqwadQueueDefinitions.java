/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FaqwadQueueDefinitionsRecord;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class FaqwadQueueDefinitions
extends TableImpl<FaqwadQueueDefinitionsRecord> {
    private static final long serialVersionUID = -797035374L;
    public static final FaqwadQueueDefinitions FAQWAD_QUEUE_DEFINITIONS = new FaqwadQueueDefinitions();
    public final TableField<FaqwadQueueDefinitionsRecord, Long> FAQWAD_QUEUE_DEFINITION_ID;
    public final TableField<FaqwadQueueDefinitionsRecord, String> FAQWAD_QUEUE_NAME;
    public final TableField<FaqwadQueueDefinitionsRecord, Long> BASE_RETRY_INTERVAL_SECONDS;
    public final TableField<FaqwadQueueDefinitionsRecord, Long> RETRY_LIMIT;
    public final TableField<FaqwadQueueDefinitionsRecord, Double> RETRY_BACKOFF_FACTOR;
    public final TableField<FaqwadQueueDefinitionsRecord, Long> CHECKOUT_INTERVAL_SECONDS;
    public final TableField<FaqwadQueueDefinitionsRecord, Long> CHECKOUT_LIMIT;
    public final TableField<FaqwadQueueDefinitionsRecord, String> DESCRIPTION;
    public final TableField<FaqwadQueueDefinitionsRecord, Timestamp> CREATED_AT;
    public final TableField<FaqwadQueueDefinitionsRecord, Timestamp> UPDATED_AT;
    public final TableField<FaqwadQueueDefinitionsRecord, Long> RVN;

    @Override
    public Class<FaqwadQueueDefinitionsRecord> getRecordType() {
        return FaqwadQueueDefinitionsRecord.class;
    }

    public FaqwadQueueDefinitions() {
        this("faqwad_queue_definitions", null);
    }

    public FaqwadQueueDefinitions(String alias) {
        this(alias, FAQWAD_QUEUE_DEFINITIONS);
    }

    private FaqwadQueueDefinitions(String alias, Table<FaqwadQueueDefinitionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private FaqwadQueueDefinitions(String alias, Table<FaqwadQueueDefinitionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.FAQWAD_QUEUE_DEFINITION_ID = FaqwadQueueDefinitions.createField("faqwad_queue_definition_id", SQLDataType.BIGINT.nullable(false), this, "The primary key for the queue definitions, this is frequently joined with the faqwad_messages table.");
        this.FAQWAD_QUEUE_NAME = FaqwadQueueDefinitions.createField("faqwad_queue_name", SQLDataType.CLOB.nullable(false), this, "The nice human readable name for the queue.");
        this.BASE_RETRY_INTERVAL_SECONDS = FaqwadQueueDefinitions.createField("base_retry_interval_seconds", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("300", SQLDataType.BIGINT)), this, "The number of seconds to wait before the first retry of a message, later retries incorporate the retry_backoff_factor. Defaults to 300 seconds, which is 5 minutes.");
        this.RETRY_LIMIT = FaqwadQueueDefinitions.createField("retry_limit", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("20", SQLDataType.BIGINT)), this, "The maximum number of retries before a message in the queue is moved to the error state. Defaults to");
        this.RETRY_BACKOFF_FACTOR = FaqwadQueueDefinitions.createField("retry_backoff_factor", SQLDataType.DOUBLE.nullable(false).defaultValue(DSL.field("1.2", SQLDataType.DOUBLE)), this, "This value must be greater than 1. Standard exponential back-off would have a value of 2. Using a value of 2 backs off VERY quickly and typically doesn't behave well. It is better to have longer retry windows and more gradual back-off. But you do want back-off.");
        this.CHECKOUT_INTERVAL_SECONDS = FaqwadQueueDefinitions.createField("checkout_interval_seconds", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("60", SQLDataType.BIGINT)), this, "The number of seconds to wait before allowing an object to be checked out again. Checkout grabs a set of messages that could be marked active, but doesn't actually mark them active. The intention is that one process calls checkout and gets a set of possible messages. That process then passes out the messages to workers, when the worker starts processing it then marks the message active. This value controls the interval before allowing the checkout operation to find the message again. Having a message be passed out multiple times is fine, the contract is the worker must mark the thing active before starting work, and not work on the message if the call to mark it active fails.");
        this.CHECKOUT_LIMIT = FaqwadQueueDefinitions.createField("checkout_limit", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("100", SQLDataType.BIGINT)), this, "It is possible that an item gets repeatedly checked out but never marked active, if that happens the checkout limit will get hit and will throw the message into an error state. It is best to set the checkout limit high. The possibility of an error here is actually very low. But it is VERY probable that messages will be checked out multiple times. If a process checks out 1000 messages but is very slow processing those messages it is quite possible the checkout_interval_seconds expire and the message is checked out again. That is actually a desired behavior, it means that rather than wait for the one thing that is slow, work is being routed to hosts that can actually do it. In the event of a service outage, or losing a host for automatic recovery you want things to be checked out again. If this value is too low, you'll get bogus alarms that you don't want to see.");
        this.DESCRIPTION = FaqwadQueueDefinitions.createField("description", SQLDataType.CLOB.nullable(false).defaultValue(DSL.field("'Not specified'::text", SQLDataType.CLOB)), this, "User friendly description of what the queue is for.");
        this.CREATED_AT = FaqwadQueueDefinitions.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "The time the value was first created in the database. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.UPDATED_AT = FaqwadQueueDefinitions.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "The last time the value was updated at. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.RVN = FaqwadQueueDefinitions.createField("rvn", SQLDataType.BIGINT.nullable(false), this, "Record version number used for optimistic locking, value is managed by triggers. All queries to update a record must include the rvn with the value the changes they are requesting was based on.");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<FaqwadQueueDefinitionsRecord> getPrimaryKey() {
        return Keys.PK_FAQWAD_QUEUE_DEFINITIONS;
    }

    @Override
    public List<UniqueKey<FaqwadQueueDefinitionsRecord>> getKeys() {
        return Arrays.asList(Keys.PK_FAQWAD_QUEUE_DEFINITIONS, Keys.FAQWAD_QUEUE_DEFINITIONS_FAQWAD_QUEUE_NAME_KEY);
    }

    public FaqwadQueueDefinitions as(String alias) {
        return new FaqwadQueueDefinitions(alias, this);
    }

    public FaqwadQueueDefinitions rename(String name) {
        return new FaqwadQueueDefinitions(name, null);
    }
}

