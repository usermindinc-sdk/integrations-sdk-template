/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FaqwadStatesRecord;
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

public class FaqwadStates
extends TableImpl<FaqwadStatesRecord> {
    private static final long serialVersionUID = -1148122366L;
    public static final FaqwadStates FAQWAD_STATES = new FaqwadStates();
    public final TableField<FaqwadStatesRecord, Long> FAQWAD_STATE_ID;
    public final TableField<FaqwadStatesRecord, String> FAQWAD_STATE_NAME;
    public final TableField<FaqwadStatesRecord, Boolean> IS_TERMINAL;
    public final TableField<FaqwadStatesRecord, Boolean> IS_EXECUTABLE;
    public final TableField<FaqwadStatesRecord, Long> PRIORITY_MODIFIER;
    public final TableField<FaqwadStatesRecord, String> DESCRIPTION;
    public final TableField<FaqwadStatesRecord, Timestamp> CREATED_AT;
    public final TableField<FaqwadStatesRecord, Timestamp> UPDATED_AT;
    public final TableField<FaqwadStatesRecord, Long> RVN;

    @Override
    public Class<FaqwadStatesRecord> getRecordType() {
        return FaqwadStatesRecord.class;
    }

    public FaqwadStates() {
        this("faqwad_states", null);
    }

    public FaqwadStates(String alias) {
        this(alias, FAQWAD_STATES);
    }

    private FaqwadStates(String alias, Table<FaqwadStatesRecord> aliased) {
        this(alias, aliased, null);
    }

    private FaqwadStates(String alias, Table<FaqwadStatesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.FAQWAD_STATE_ID = FaqwadStates.createField("faqwad_state_id", SQLDataType.BIGINT.nullable(false), this, "Surrogate key for message states. This is used for joining to the faqwad_messages table.");
        this.FAQWAD_STATE_NAME = FaqwadStates.createField("faqwad_state_name", SQLDataType.CLOB.nullable(false), this, "The nice human readable form of the state. This column is forced to be unique. While this field is unbounded, please keep the values small. We want the values to be index friendly, and end up joining to pull this field out on query results all the time.");
        this.IS_TERMINAL = FaqwadStates.createField("is_terminal", SQLDataType.BOOLEAN.nullable(false), this, "Indicates if the state is terminal. That means that the faqwad message has been processed, and will never be processed again (without someone wiping entries from the DB). The faqwad infrastructure only indexes messages in non-terminal states to keep the massive amount of historical processed messages from plugging up indexes.");
        this.IS_EXECUTABLE = FaqwadStates.createField("is_executable", SQLDataType.BOOLEAN.nullable(false), this, "Indicates if the state is one that action can take place in, things that are queued, or in error states are not executable, but the messages are not terminal either. No terminal things are executable, but not all non-terminal things are executable. We ensure that only one faqwad message can be active for an object_id at a time, and that they are made active in strict insertion order. Things that are not at the front of the line aren't executable, and things that have been marked as error states are not executable either. Only the things that are active, or are ready are executable.");
        this.PRIORITY_MODIFIER = FaqwadStates.createField("priority_modifier", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "The amount to add to the message priority when entering this state. Most urgent priority numbers are lower. Values may be positive or negative.");
        this.DESCRIPTION = FaqwadStates.createField("description", SQLDataType.CLOB.nullable(false).defaultValue(DSL.field("'Not specified'::text", SQLDataType.CLOB)), this, "User friendly description of what the state is.");
        this.CREATED_AT = FaqwadStates.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "The time the value was first created in the database. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.UPDATED_AT = FaqwadStates.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "The last time the value was updated at. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.RVN = FaqwadStates.createField("rvn", SQLDataType.BIGINT.nullable(false), this, "Record version number used for optimistic locking, value is managed by triggers. All queries to update a record must include the rvn with the value the changes they are requesting was based on.");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<FaqwadStatesRecord> getPrimaryKey() {
        return Keys.PK_FAQWAD_STATES;
    }

    @Override
    public List<UniqueKey<FaqwadStatesRecord>> getKeys() {
        return Arrays.asList(Keys.PK_FAQWAD_STATES, Keys.FAQWAD_STATES_FAQWAD_STATE_NAME_KEY);
    }

    public FaqwadStates as(String alias) {
        return new FaqwadStates(alias, this);
    }

    public FaqwadStates rename(String name) {
        return new FaqwadStates(name, null);
    }
}

