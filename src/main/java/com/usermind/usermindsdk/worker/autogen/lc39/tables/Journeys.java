/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.JourneysRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.JourneystatesRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.MapsRecord;
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
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Journeys
extends TableImpl<JourneysRecord> {
    private static final long serialVersionUID = 766982651L;
    public static final Journeys JOURNEYS = new Journeys();
    public final TableField<JourneysRecord, Long> ID;
    public final TableField<JourneysRecord, Long> JOURNEY_ID;
    public final TableField<JourneysRecord, Long> ORGANIZATION_ID;
    public final TableField<JourneysRecord, String> NAME;
    public final TableField<JourneysRecord, String> DESCRIPTION;
    public final TableField<JourneysRecord, Boolean> DELETED;
    public final TableField<JourneysRecord, Timestamp> CREATED_AT;
    public final TableField<JourneysRecord, Timestamp> UPDATED_AT;
    public final TableField<JourneysRecord, Boolean> ENABLED;
    public final TableField<JourneysRecord, String> MILESTONE_ORDER;
    public final TableField<JourneysRecord, Long> MAP_SPEC_ID;
    public final TableField<JourneysRecord, Long> JOURNEY_STATE;

    @Override
    public Class<JourneysRecord> getRecordType() {
        return JourneysRecord.class;
    }

    public Journeys() {
        this("Journeys", null);
    }

    public Journeys(String alias) {
        this(alias, JOURNEYS);
    }

    private Journeys(String alias, Table<JourneysRecord> aliased) {
        this(alias, aliased, null);
    }

    private Journeys(String alias, Table<JourneysRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Journeys.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Journeys_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.JOURNEY_ID = Journeys.createField("journey_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.ORGANIZATION_ID = Journeys.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.NAME = Journeys.createField("name", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.DESCRIPTION = Journeys.createField("description", SQLDataType.VARCHAR.length(255), this, "");
        this.DELETED = Journeys.createField("deleted", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.CREATED_AT = Journeys.createField("created_at", SQLDataType.TIMESTAMP, this, "");
        this.UPDATED_AT = Journeys.createField("updated_at", SQLDataType.TIMESTAMP, this, "");
        this.ENABLED = Journeys.createField("enabled", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");
        this.MILESTONE_ORDER = Journeys.createField("milestone_order", SQLDataType.CLOB, this, "");
        this.MAP_SPEC_ID = Journeys.createField("map_spec_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.JOURNEY_STATE = Journeys.createField("journey_state", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("1", SQLDataType.BIGINT)), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<JourneysRecord, Long> getIdentity() {
        return Keys.IDENTITY_JOURNEYS;
    }

    @Override
    public UniqueKey<JourneysRecord> getPrimaryKey() {
        return Keys.JOURNEYS_PKEY;
    }

    @Override
    public List<UniqueKey<JourneysRecord>> getKeys() {
        return Arrays.asList(Keys.JOURNEYS_PKEY);
    }

    @Override
    public List<ForeignKey<JourneysRecord, ?>> getReferences() {
        return Arrays.asList(Keys.JOURNEYS__FK_JOURNEY_MAP_SPEC, Keys.JOURNEYS__FK_JOURNEYS_STATE);
    }

    public Journeys as(String alias) {
        return new Journeys(alias, this);
    }

    public Journeys rename(String name) {
        return new Journeys(name, null);
    }
}

