/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.MapsRecord;
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

public class Maps
extends TableImpl<MapsRecord> {
    private static final long serialVersionUID = -1708051987L;
    public static final Maps MAPS = new Maps();
    public final TableField<MapsRecord, Long> ID;
    public final TableField<MapsRecord, String> NAME;
    public final TableField<MapsRecord, Timestamp> CREATED_AT;
    public final TableField<MapsRecord, Timestamp> UPDATED_AT;
    public final TableField<MapsRecord, Long> ORGANIZATION_ID;
    public final TableField<MapsRecord, Boolean> DELETED;
    public final TableField<MapsRecord, Boolean> EDITABLE;

    @Override
    public Class<MapsRecord> getRecordType() {
        return MapsRecord.class;
    }

    public Maps() {
        this("Maps", null);
    }

    public Maps(String alias) {
        this(alias, MAPS);
    }

    private Maps(String alias, Table<MapsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Maps(String alias, Table<MapsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Maps.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Maps_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.NAME = Maps.createField("name", SQLDataType.CLOB.nullable(false), this, "");
        this.CREATED_AT = Maps.createField("created_at", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("now()", SQLDataType.TIMESTAMP)), this, "");
        this.UPDATED_AT = Maps.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("now()", SQLDataType.TIMESTAMP)), this, "");
        this.ORGANIZATION_ID = Maps.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.DELETED = Maps.createField("deleted", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.EDITABLE = Maps.createField("editable", SQLDataType.BOOLEAN.defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<MapsRecord, Long> getIdentity() {
        return Keys.IDENTITY_MAPS;
    }

    @Override
    public UniqueKey<MapsRecord> getPrimaryKey() {
        return Keys.PK_MAPS;
    }

    @Override
    public List<UniqueKey<MapsRecord>> getKeys() {
        return Arrays.asList(Keys.PK_MAPS);
    }

    public Maps as(String alias) {
        return new Maps(alias, this);
    }

    public Maps rename(String name) {
        return new Maps(name, null);
    }
}

