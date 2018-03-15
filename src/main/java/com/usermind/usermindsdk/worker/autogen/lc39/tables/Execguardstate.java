/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ExecguardstateRecord;
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

public class Execguardstate
extends TableImpl<ExecguardstateRecord> {
    private static final long serialVersionUID = 510289514L;
    public static final Execguardstate EXECGUARDSTATE = new Execguardstate();
    public final TableField<ExecguardstateRecord, Long> ID;
    public final TableField<ExecguardstateRecord, String> KEY;
    public final TableField<ExecguardstateRecord, String> VALUE;
    public final TableField<ExecguardstateRecord, String> GROUP_ID;
    public final TableField<ExecguardstateRecord, Timestamp> CREATED_AT;
    public final TableField<ExecguardstateRecord, Timestamp> UPDATED_AT;
    public final TableField<ExecguardstateRecord, String> TRANSACTION_ID;

    @Override
    public Class<ExecguardstateRecord> getRecordType() {
        return ExecguardstateRecord.class;
    }

    public Execguardstate() {
        this("ExecGuardState", null);
    }

    public Execguardstate(String alias) {
        this(alias, EXECGUARDSTATE);
    }

    private Execguardstate(String alias, Table<ExecguardstateRecord> aliased) {
        this(alias, aliased, null);
    }

    private Execguardstate(String alias, Table<ExecguardstateRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Execguardstate.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"ExecGuardState_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.KEY = Execguardstate.createField("key", SQLDataType.VARCHAR.length(1024), this, "");
        this.VALUE = Execguardstate.createField("value", SQLDataType.VARCHAR.length(1024), this, "");
        this.GROUP_ID = Execguardstate.createField("group_id", SQLDataType.VARCHAR.length(255), this, "");
        this.CREATED_AT = Execguardstate.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Execguardstate.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.TRANSACTION_ID = Execguardstate.createField("transaction_id", SQLDataType.CLOB.nullable(false), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<ExecguardstateRecord, Long> getIdentity() {
        return Keys.IDENTITY_EXECGUARDSTATE;
    }

    @Override
    public UniqueKey<ExecguardstateRecord> getPrimaryKey() {
        return Keys.EXECGUARDSTATE_PKEY;
    }

    @Override
    public List<UniqueKey<ExecguardstateRecord>> getKeys() {
        return Arrays.asList(Keys.EXECGUARDSTATE_PKEY);
    }

    public Execguardstate as(String alias) {
        return new Execguardstate(alias, this);
    }

    public Execguardstate rename(String name) {
        return new Execguardstate(name, null);
    }
}

