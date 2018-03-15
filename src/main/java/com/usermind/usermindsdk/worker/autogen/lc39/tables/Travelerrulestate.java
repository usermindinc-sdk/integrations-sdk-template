/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.TravelerrulestateRecord;
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

public class Travelerrulestate
extends TableImpl<TravelerrulestateRecord> {
    private static final long serialVersionUID = 1226561338L;
    public static final Travelerrulestate TRAVELERRULESTATE = new Travelerrulestate();
    public final TableField<TravelerrulestateRecord, Long> ID;
    public final TableField<TravelerrulestateRecord, String> KEY;
    public final TableField<TravelerrulestateRecord, String> VALUE;
    public final TableField<TravelerrulestateRecord, Timestamp> CREATED_AT;
    public final TableField<TravelerrulestateRecord, Timestamp> UPDATED_AT;
    public final TableField<TravelerrulestateRecord, String> TRANSACTION_ID;

    @Override
    public Class<TravelerrulestateRecord> getRecordType() {
        return TravelerrulestateRecord.class;
    }

    public Travelerrulestate() {
        this("TravelerRuleState", null);
    }

    public Travelerrulestate(String alias) {
        this(alias, TRAVELERRULESTATE);
    }

    private Travelerrulestate(String alias, Table<TravelerrulestateRecord> aliased) {
        this(alias, aliased, null);
    }

    private Travelerrulestate(String alias, Table<TravelerrulestateRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Travelerrulestate.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"TravelerRuleState_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.KEY = Travelerrulestate.createField("key", SQLDataType.VARCHAR.length(255), this, "");
        this.VALUE = Travelerrulestate.createField("value", SQLDataType.VARCHAR.length(255), this, "");
        this.CREATED_AT = Travelerrulestate.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Travelerrulestate.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.TRANSACTION_ID = Travelerrulestate.createField("transaction_id", SQLDataType.CLOB.nullable(false), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<TravelerrulestateRecord, Long> getIdentity() {
        return Keys.IDENTITY_TRAVELERRULESTATE;
    }

    @Override
    public UniqueKey<TravelerrulestateRecord> getPrimaryKey() {
        return Keys.TRAVELERRULESTATE_PKEY;
    }

    @Override
    public List<UniqueKey<TravelerrulestateRecord>> getKeys() {
        return Arrays.asList(Keys.TRAVELERRULESTATE_PKEY);
    }

    public Travelerrulestate as(String alias) {
        return new Travelerrulestate(alias, this);
    }

    public Travelerrulestate rename(String name) {
        return new Travelerrulestate(name, null);
    }
}

