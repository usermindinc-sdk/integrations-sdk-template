/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FieldsRecord;
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

public class Fields
extends TableImpl<FieldsRecord> {
    private static final long serialVersionUID = -995757043L;
    public static final Fields FIELDS = new Fields();
    public final TableField<FieldsRecord, Long> ID;
    public final TableField<FieldsRecord, String> NAME;
    public final TableField<FieldsRecord, String> DISPLAY_NAME;
    public final TableField<FieldsRecord, String> TYPE;
    public final TableField<FieldsRecord, Long> ENTITY_ID;
    public final TableField<FieldsRecord, Long> ORGANIZATION_ID;
    public final TableField<FieldsRecord, Timestamp> CREATED_AT;
    public final TableField<FieldsRecord, Timestamp> UPDATED_AT;
    public final TableField<FieldsRecord, Boolean> READ_ONLY;
    public final TableField<FieldsRecord, String> PATH;
    public final TableField<FieldsRecord, Boolean> IS_REQUIRED;

    @Override
    public Class<FieldsRecord> getRecordType() {
        return FieldsRecord.class;
    }

    public Fields() {
        this("Fields", null);
    }

    public Fields(String alias) {
        this(alias, FIELDS);
    }

    private Fields(String alias, Table<FieldsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Fields(String alias, Table<FieldsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Fields.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Fields_generated_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.NAME = Fields.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.DISPLAY_NAME = Fields.createField("display_name", SQLDataType.VARCHAR.length(255), this, "");
        this.TYPE = Fields.createField("type", SQLDataType.VARCHAR.length(255), this, "");
        this.ENTITY_ID = Fields.createField("entity_id", SQLDataType.BIGINT, this, "");
        this.ORGANIZATION_ID = Fields.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.CREATED_AT = Fields.createField("created_at", SQLDataType.TIMESTAMP, this, "");
        this.UPDATED_AT = Fields.createField("updated_at", SQLDataType.TIMESTAMP, this, "");
        this.READ_ONLY = Fields.createField("read_only", SQLDataType.BOOLEAN, this, "");
        this.PATH = Fields.createField("path", SQLDataType.VARCHAR.length(255), this, "");
        this.IS_REQUIRED = Fields.createField("is_required", SQLDataType.BOOLEAN.defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<FieldsRecord, Long> getIdentity() {
        return Keys.IDENTITY_FIELDS;
    }

    @Override
    public UniqueKey<FieldsRecord> getPrimaryKey() {
        return Keys.FIELDS_PKEY;
    }

    @Override
    public List<UniqueKey<FieldsRecord>> getKeys() {
        return Arrays.asList(Keys.FIELDS_PKEY);
    }

    public Fields as(String alias) {
        return new Fields(alias, this);
    }

    public Fields rename(String name) {
        return new Fields(name, null);
    }
}

