/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.TravelerEventTypesRecord;
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

public class TravelerEventTypes
extends TableImpl<TravelerEventTypesRecord> {
    private static final long serialVersionUID = 66171228L;
    public static final TravelerEventTypes TRAVELER_EVENT_TYPES = new TravelerEventTypes();
    public final TableField<TravelerEventTypesRecord, Long> TRAVELER_EVENT_TYPE_ID;
    public final TableField<TravelerEventTypesRecord, String> TRAVELER_EVENT_TYPE_NAME;
    public final TableField<TravelerEventTypesRecord, String> DESCRIPTION;
    public final TableField<TravelerEventTypesRecord, Timestamp> CREATED_AT;
    public final TableField<TravelerEventTypesRecord, Timestamp> UPDATED_AT;
    public final TableField<TravelerEventTypesRecord, Long> RVN;

    @Override
    public Class<TravelerEventTypesRecord> getRecordType() {
        return TravelerEventTypesRecord.class;
    }

    public TravelerEventTypes() {
        this("traveler_event_types", null);
    }

    public TravelerEventTypes(String alias) {
        this(alias, TRAVELER_EVENT_TYPES);
    }

    private TravelerEventTypes(String alias, Table<TravelerEventTypesRecord> aliased) {
        this(alias, aliased, null);
    }

    private TravelerEventTypes(String alias, Table<TravelerEventTypesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.TRAVELER_EVENT_TYPE_ID = TravelerEventTypes.createField("traveler_event_type_id", SQLDataType.BIGINT.nullable(false), this, "Surrogate key for event types. This is used for joining to the traveler_events table.");
        this.TRAVELER_EVENT_TYPE_NAME = TravelerEventTypes.createField("traveler_event_type_name", SQLDataType.CLOB.nullable(false), this, "The program friendly, and less human hostile name the event type. Event types should not be added by programs, they require coordinating the types with the code that processes the types. The values here are required to be globally unique. Please keep values fairly short, do not go write a novel in the name field.");
        this.DESCRIPTION = TravelerEventTypes.createField("description", SQLDataType.CLOB.nullable(false).defaultValue(DSL.field("'Not specified'::text", SQLDataType.CLOB)), this, "User friendly description of what the type is.");
        this.CREATED_AT = TravelerEventTypes.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "The time the value was first created in the database. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.UPDATED_AT = TravelerEventTypes.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "The last time the value was updated at. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.RVN = TravelerEventTypes.createField("rvn", SQLDataType.BIGINT.nullable(false), this, "Record version number used for optimistic locking, value is managed by triggers. All queries to update a record must include the rvn with the value the changes they are requesting was based on.");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<TravelerEventTypesRecord> getPrimaryKey() {
        return Keys.PK_TRAVELER_EVENT_TYPES;
    }

    @Override
    public List<UniqueKey<TravelerEventTypesRecord>> getKeys() {
        return Arrays.asList(Keys.PK_TRAVELER_EVENT_TYPES, Keys.TRAVELER_EVENT_TYPES_TRAVELER_EVENT_TYPE_NAME_KEY);
    }

    public TravelerEventTypes as(String alias) {
        return new TravelerEventTypes(alias, this);
    }

    public TravelerEventTypes rename(String name) {
        return new TravelerEventTypes(name, null);
    }
}

