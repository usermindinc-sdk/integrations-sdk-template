/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ActionargsRecord;
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

public class Actionargs
extends TableImpl<ActionargsRecord> {
    private static final long serialVersionUID = -1113397049L;
    public static final Actionargs ACTIONARGS = new Actionargs();
    public final TableField<ActionargsRecord, Long> ID;
    public final TableField<ActionargsRecord, String> NAME;
    public final TableField<ActionargsRecord, String> DISPLAY_NAME;
    public final TableField<ActionargsRecord, String> TYPE;
    public final TableField<ActionargsRecord, Long> ACTION_ID;
    public final TableField<ActionargsRecord, Long> ORGANIZATION_ID;
    public final TableField<ActionargsRecord, Timestamp> CREATED_AT;
    public final TableField<ActionargsRecord, Timestamp> UPDATED_AT;
    public final TableField<ActionargsRecord, Boolean> ASSIGNABLE;
    public final TableField<ActionargsRecord, String> PATH;

    @Override
    public Class<ActionargsRecord> getRecordType() {
        return ActionargsRecord.class;
    }

    public Actionargs() {
        this("ActionArgs", null);
    }

    public Actionargs(String alias) {
        this(alias, ACTIONARGS);
    }

    private Actionargs(String alias, Table<ActionargsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Actionargs(String alias, Table<ActionargsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Actionargs.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"ActionArgs_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.NAME = Actionargs.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.DISPLAY_NAME = Actionargs.createField("display_name", SQLDataType.VARCHAR.length(255), this, "");
        this.TYPE = Actionargs.createField("type", SQLDataType.VARCHAR.length(255), this, "");
        this.ACTION_ID = Actionargs.createField("action_id", SQLDataType.BIGINT, this, "");
        this.ORGANIZATION_ID = Actionargs.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.CREATED_AT = Actionargs.createField("created_at", SQLDataType.TIMESTAMP, this, "");
        this.UPDATED_AT = Actionargs.createField("updated_at", SQLDataType.TIMESTAMP, this, "");
        this.ASSIGNABLE = Actionargs.createField("assignable", SQLDataType.BOOLEAN, this, "");
        this.PATH = Actionargs.createField("path", SQLDataType.VARCHAR.length(255), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<ActionargsRecord, Long> getIdentity() {
        return Keys.IDENTITY_ACTIONARGS;
    }

    @Override
    public UniqueKey<ActionargsRecord> getPrimaryKey() {
        return Keys.ACTIONARGS_PKEY;
    }

    @Override
    public List<UniqueKey<ActionargsRecord>> getKeys() {
        return Arrays.asList(Keys.ACTIONARGS_PKEY);
    }

    public Actionargs as(String alias) {
        return new Actionargs(alias, this);
    }

    public Actionargs rename(String name) {
        return new Actionargs(name, null);
    }
}

