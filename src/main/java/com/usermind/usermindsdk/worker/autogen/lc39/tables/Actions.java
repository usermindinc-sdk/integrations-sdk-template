/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ActionsRecord;
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

public class Actions
extends TableImpl<ActionsRecord> {
    private static final long serialVersionUID = -1436184658L;
    public static final Actions ACTIONS = new Actions();
    public final TableField<ActionsRecord, Long> ID;
    public final TableField<ActionsRecord, String> NAME;
    public final TableField<ActionsRecord, Long> CHANNEL_ID;
    public final TableField<ActionsRecord, Long> ORGANIZATION_ID;
    public final TableField<ActionsRecord, String> DISPLAY_NAME;
    public final TableField<ActionsRecord, Timestamp> CREATED_AT;
    public final TableField<ActionsRecord, Timestamp> UPDATED_AT;
    public final TableField<ActionsRecord, Boolean> HAS_VARIABLES;
    public final TableField<ActionsRecord, Long> PARENT_ID;
    public final TableField<ActionsRecord, Boolean> HAS_ACTIONS;
    public final TableField<ActionsRecord, String> FOR_ENTITY;

    @Override
    public Class<ActionsRecord> getRecordType() {
        return ActionsRecord.class;
    }

    public Actions() {
        this("Actions", null);
    }

    public Actions(String alias) {
        this(alias, ACTIONS);
    }

    private Actions(String alias, Table<ActionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Actions(String alias, Table<ActionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Actions.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Actions_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.NAME = Actions.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.CHANNEL_ID = Actions.createField("channel_id", SQLDataType.BIGINT, this, "");
        this.ORGANIZATION_ID = Actions.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.DISPLAY_NAME = Actions.createField("display_name", SQLDataType.VARCHAR.length(255), this, "");
        this.CREATED_AT = Actions.createField("created_at", SQLDataType.TIMESTAMP, this, "");
        this.UPDATED_AT = Actions.createField("updated_at", SQLDataType.TIMESTAMP, this, "");
        this.HAS_VARIABLES = Actions.createField("has_variables", SQLDataType.BOOLEAN, this, "");
        this.PARENT_ID = Actions.createField("parent_id", SQLDataType.BIGINT.defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "");
        this.HAS_ACTIONS = Actions.createField("has_actions", SQLDataType.BOOLEAN.defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.FOR_ENTITY = Actions.createField("for_entity", SQLDataType.VARCHAR.length(255), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<ActionsRecord, Long> getIdentity() {
        return Keys.IDENTITY_ACTIONS;
    }

    @Override
    public UniqueKey<ActionsRecord> getPrimaryKey() {
        return Keys.ACTIONS_PKEY;
    }

    @Override
    public List<UniqueKey<ActionsRecord>> getKeys() {
        return Arrays.asList(Keys.ACTIONS_PKEY);
    }

    public Actions as(String alias) {
        return new Actions(alias, this);
    }

    public Actions rename(String name) {
        return new Actions(name, null);
    }
}

