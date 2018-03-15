/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.EntitiesRecord;
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

public class Entities
extends TableImpl<EntitiesRecord> {
    private static final long serialVersionUID = -1388742841L;
    public static final Entities ENTITIES = new Entities();
    public final TableField<EntitiesRecord, Long> ID;
    public final TableField<EntitiesRecord, String> NAME;
    public final TableField<EntitiesRecord, Long> CHANNEL_ID;
    public final TableField<EntitiesRecord, Long> ORGANIZATION_ID;
    public final TableField<EntitiesRecord, Timestamp> CREATED_AT;
    public final TableField<EntitiesRecord, Timestamp> UPDATED_AT;
    public final TableField<EntitiesRecord, String> DISPLAY_NAME;

    @Override
    public Class<EntitiesRecord> getRecordType() {
        return EntitiesRecord.class;
    }

    public Entities() {
        this("Entities", null);
    }

    public Entities(String alias) {
        this(alias, ENTITIES);
    }

    private Entities(String alias, Table<EntitiesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Entities(String alias, Table<EntitiesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Entities.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Entities_generated_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.NAME = Entities.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.CHANNEL_ID = Entities.createField("channel_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.ORGANIZATION_ID = Entities.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.CREATED_AT = Entities.createField("created_at", SQLDataType.TIMESTAMP, this, "");
        this.UPDATED_AT = Entities.createField("updated_at", SQLDataType.TIMESTAMP, this, "");
        this.DISPLAY_NAME = Entities.createField("display_name", SQLDataType.VARCHAR.length(255), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<EntitiesRecord, Long> getIdentity() {
        return Keys.IDENTITY_ENTITIES;
    }

    @Override
    public UniqueKey<EntitiesRecord> getPrimaryKey() {
        return Keys.ENTITIES_PKEY;
    }

    @Override
    public List<UniqueKey<EntitiesRecord>> getKeys() {
        return Arrays.asList(Keys.ENTITIES_PKEY, Keys.ENTITIES_ORGANIZATION_ID_CHANNEL_ID_NAME_KEY);
    }

    public Entities as(String alias) {
        return new Entities(alias, this);
    }

    public Entities rename(String name) {
        return new Entities(name, null);
    }
}

