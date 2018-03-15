/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.TickschedulesRecord;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Tickschedules
extends TableImpl<TickschedulesRecord> {
    private static final long serialVersionUID = 216445688L;
    public static final Tickschedules TICKSCHEDULES = new Tickschedules();
    public final TableField<TickschedulesRecord, Long> ID;
    public final TableField<TickschedulesRecord, Long> ORGANIZATION_ID;
    public final TableField<TickschedulesRecord, Long> JOURNEY_ID;
    public final TableField<TickschedulesRecord, Long> RULE_ID;
    public final TableField<TickschedulesRecord, Long> TRAVELER_ID;
    public final TableField<TickschedulesRecord, Long> VERSION;
    public final TableField<TickschedulesRecord, Long> NEXT_TICK;
    public final TableField<TickschedulesRecord, Boolean> IS_DELETED;
    public final TableField<TickschedulesRecord, String> DELETED_REASON;
    public final TableField<TickschedulesRecord, Timestamp> CREATED_AT;

    @Override
    public Class<TickschedulesRecord> getRecordType() {
        return TickschedulesRecord.class;
    }

    public Tickschedules() {
        this("TickSchedules", null);
    }

    public Tickschedules(String alias) {
        this(alias, TICKSCHEDULES);
    }

    private Tickschedules(String alias, Table<TickschedulesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Tickschedules(String alias, Table<TickschedulesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Tickschedules.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"TickSchedules_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ORGANIZATION_ID = Tickschedules.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.JOURNEY_ID = Tickschedules.createField("journey_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.RULE_ID = Tickschedules.createField("rule_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.TRAVELER_ID = Tickschedules.createField("traveler_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.VERSION = Tickschedules.createField("version", SQLDataType.BIGINT.nullable(false), this, "");
        this.NEXT_TICK = Tickschedules.createField("next_tick", SQLDataType.BIGINT.nullable(false), this, "");
        this.IS_DELETED = Tickschedules.createField("is_deleted", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.DELETED_REASON = Tickschedules.createField("deleted_reason", SQLDataType.CLOB, this, "");
        this.CREATED_AT = Tickschedules.createField("created_at", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("now()", SQLDataType.TIMESTAMP)), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<TickschedulesRecord, Long> getIdentity() {
        return Keys.IDENTITY_TICKSCHEDULES;
    }

    @Override
    public UniqueKey<TickschedulesRecord> getPrimaryKey() {
        return Keys.PK_TICKSCHEDULES;
    }

    @Override
    public List<UniqueKey<TickschedulesRecord>> getKeys() {
        return Arrays.asList(Keys.PK_TICKSCHEDULES);
    }

    public Tickschedules as(String alias) {
        return new Tickschedules(alias, this);
    }

    public Tickschedules rename(String name) {
        return new Tickschedules(name, null);
    }
}

