/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.LatestjourneysRecord;
import java.sql.Timestamp;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Latestjourneys
extends TableImpl<LatestjourneysRecord> {
    private static final long serialVersionUID = 492435296L;
    public static final Latestjourneys LATESTJOURNEYS = new Latestjourneys();
    public final TableField<LatestjourneysRecord, Long> ID;
    public final TableField<LatestjourneysRecord, Long> JOURNEY_ID;
    public final TableField<LatestjourneysRecord, Long> ORGANIZATION_ID;
    public final TableField<LatestjourneysRecord, String> NAME;
    public final TableField<LatestjourneysRecord, String> DESCRIPTION;
    public final TableField<LatestjourneysRecord, Boolean> DELETED;
    public final TableField<LatestjourneysRecord, Timestamp> CREATED_AT;
    public final TableField<LatestjourneysRecord, Timestamp> UPDATED_AT;
    public final TableField<LatestjourneysRecord, Boolean> ENABLED;
    public final TableField<LatestjourneysRecord, String> MILESTONE_ORDER;
    public final TableField<LatestjourneysRecord, Long> MAP_SPEC_ID;
    public final TableField<LatestjourneysRecord, Long> JOURNEY_STATE;

    @Override
    public Class<LatestjourneysRecord> getRecordType() {
        return LatestjourneysRecord.class;
    }

    public Latestjourneys() {
        this("LatestJourneys", null);
    }

    public Latestjourneys(String alias) {
        this(alias, LATESTJOURNEYS);
    }

    private Latestjourneys(String alias, Table<LatestjourneysRecord> aliased) {
        this(alias, aliased, null);
    }

    private Latestjourneys(String alias, Table<LatestjourneysRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Latestjourneys.createField("id", SQLDataType.BIGINT, this, "");
        this.JOURNEY_ID = Latestjourneys.createField("journey_id", SQLDataType.BIGINT, this, "");
        this.ORGANIZATION_ID = Latestjourneys.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.NAME = Latestjourneys.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.DESCRIPTION = Latestjourneys.createField("description", SQLDataType.VARCHAR.length(255), this, "");
        this.DELETED = Latestjourneys.createField("deleted", SQLDataType.BOOLEAN, this, "");
        this.CREATED_AT = Latestjourneys.createField("created_at", SQLDataType.TIMESTAMP, this, "");
        this.UPDATED_AT = Latestjourneys.createField("updated_at", SQLDataType.TIMESTAMP, this, "");
        this.ENABLED = Latestjourneys.createField("enabled", SQLDataType.BOOLEAN, this, "");
        this.MILESTONE_ORDER = Latestjourneys.createField("milestone_order", SQLDataType.CLOB, this, "");
        this.MAP_SPEC_ID = Latestjourneys.createField("map_spec_id", SQLDataType.BIGINT, this, "");
        this.JOURNEY_STATE = Latestjourneys.createField("journey_state", SQLDataType.BIGINT, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    public Latestjourneys as(String alias) {
        return new Latestjourneys(alias, this);
    }

    public Latestjourneys rename(String name) {
        return new Latestjourneys(name, null);
    }
}

