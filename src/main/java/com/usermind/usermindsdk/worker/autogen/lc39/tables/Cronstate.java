/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.CronstateRecord;
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

public class Cronstate
extends TableImpl<CronstateRecord> {
    private static final long serialVersionUID = -910914149L;
    public static final Cronstate CRONSTATE = new Cronstate();
    public final TableField<CronstateRecord, Long> ID;
    public final TableField<CronstateRecord, String> KEY;
    public final TableField<CronstateRecord, Timestamp> PREV_FIRE;
    public final TableField<CronstateRecord, Timestamp> NEXT_FIRE;
    public final TableField<CronstateRecord, Timestamp> CREATED_AT;
    public final TableField<CronstateRecord, Timestamp> UPDATED_AT;
    public final TableField<CronstateRecord, Long> VERSION;

    @Override
    public Class<CronstateRecord> getRecordType() {
        return CronstateRecord.class;
    }

    public Cronstate() {
        this("CronState", null);
    }

    public Cronstate(String alias) {
        this(alias, CRONSTATE);
    }

    private Cronstate(String alias, Table<CronstateRecord> aliased) {
        this(alias, aliased, null);
    }

    private Cronstate(String alias, Table<CronstateRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Cronstate.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"CronState_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.KEY = Cronstate.createField("key", SQLDataType.VARCHAR.length(255), this, "");
        this.PREV_FIRE = Cronstate.createField("prev_fire", SQLDataType.TIMESTAMP, this, "");
        this.NEXT_FIRE = Cronstate.createField("next_fire", SQLDataType.TIMESTAMP, this, "");
        this.CREATED_AT = Cronstate.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Cronstate.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.VERSION = Cronstate.createField("version", SQLDataType.BIGINT, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<CronstateRecord, Long> getIdentity() {
        return Keys.IDENTITY_CRONSTATE;
    }

    @Override
    public UniqueKey<CronstateRecord> getPrimaryKey() {
        return Keys.CRONSTATE_PKEY;
    }

    @Override
    public List<UniqueKey<CronstateRecord>> getKeys() {
        return Arrays.asList(Keys.CRONSTATE_PKEY);
    }

    public Cronstate as(String alias) {
        return new Cronstate(alias, this);
    }

    public Cronstate rename(String name) {
        return new Cronstate(name, null);
    }
}

