/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.TravelerEventStatesRecord;
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

public class TravelerEventStates
extends TableImpl<TravelerEventStatesRecord> {
    private static final long serialVersionUID = 468595926L;
    public static final TravelerEventStates TRAVELER_EVENT_STATES = new TravelerEventStates();
    public final TableField<TravelerEventStatesRecord, Long> TRAVELER_EVENT_STATE_ID;
    public final TableField<TravelerEventStatesRecord, String> TRAVELER_EVENT_STATE_NAME;
    public final TableField<TravelerEventStatesRecord, Boolean> IS_TERMINAL;
    public final TableField<TravelerEventStatesRecord, Boolean> IS_EXECUTABLE;
    public final TableField<TravelerEventStatesRecord, String> DESCRIPTION;
    public final TableField<TravelerEventStatesRecord, Timestamp> CREATED_AT;
    public final TableField<TravelerEventStatesRecord, Timestamp> UPDATED_AT;
    public final TableField<TravelerEventStatesRecord, Long> RVN;

    @Override
    public Class<TravelerEventStatesRecord> getRecordType() {
        return TravelerEventStatesRecord.class;
    }

    public TravelerEventStates() {
        this("traveler_event_states", null);
    }

    public TravelerEventStates(String alias) {
        this(alias, TRAVELER_EVENT_STATES);
    }

    private TravelerEventStates(String alias, Table<TravelerEventStatesRecord> aliased) {
        this(alias, aliased, null);
    }

    private TravelerEventStates(String alias, Table<TravelerEventStatesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.TRAVELER_EVENT_STATE_ID = TravelerEventStates.createField("traveler_event_state_id", SQLDataType.BIGINT.nullable(false), this, "Surrogate key for event states. This is used for joining to the traveler_events table.");
        this.TRAVELER_EVENT_STATE_NAME = TravelerEventStates.createField("traveler_event_state_name", SQLDataType.CLOB.nullable(false), this, "The nice human readable form of the state. This column is forced to be unique. While this field is unbounded, please keep the values small. We want the values to be index friendly, and end up joining to pull this field out on query results all the time.");
        this.IS_TERMINAL = TravelerEventStates.createField("is_terminal", SQLDataType.BOOLEAN.nullable(false), this, "Indicates if the state is terminal. That means that the traveler event has been processed, and will never be processed again (without someone wiping entries from the DB). The traveler_event infrastructure only indexes events in non-terminal states to keep the massive amount of historical processed events from plugging up indexes.");
        this.IS_EXECUTABLE = TravelerEventStates.createField("is_executable", SQLDataType.BOOLEAN.nullable(false), this, "Indicates if the state is one that action can take place in, things that are queued, or in error states are not executable, but the events are not terminal either. No terminal things are executable, but not all non-terminal things are executable. We ensure that only one traveler_event can be active for a traveler at a time, and that they are made active in strict insertion order. Things that are not at the front of the line aren't executable, and things that have been marked as error states are not executable either. Only the things that are active, or are ready are executable.");
        this.DESCRIPTION = TravelerEventStates.createField("description", SQLDataType.CLOB.nullable(false).defaultValue(DSL.field("'Not specified'::text", SQLDataType.CLOB)), this, "User friendly description of what the state is.");
        this.CREATED_AT = TravelerEventStates.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "The time the value was first created in the database. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.UPDATED_AT = TravelerEventStates.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "The last time the value was updated at. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.RVN = TravelerEventStates.createField("rvn", SQLDataType.BIGINT.nullable(false), this, "Record version number used for optimistic locking, value is managed by triggers. All queries to update a record must include the rvn with the value the changes they are requesting was based on.");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<TravelerEventStatesRecord> getPrimaryKey() {
        return Keys.PK_TRAVELER_EVENT_STATES;
    }

    @Override
    public List<UniqueKey<TravelerEventStatesRecord>> getKeys() {
        return Arrays.asList(Keys.PK_TRAVELER_EVENT_STATES, Keys.TRAVELER_EVENT_STATES_TRAVELER_EVENT_STATE_NAME_KEY);
    }

    public TravelerEventStates as(String alias) {
        return new TravelerEventStates(alias, this);
    }

    public TravelerEventStates rename(String name) {
        return new TravelerEventStates(name, null);
    }
}

