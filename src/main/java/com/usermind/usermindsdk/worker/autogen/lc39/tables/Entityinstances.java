/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.EntityinstancesRecord;
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

public class Entityinstances
extends TableImpl<EntityinstancesRecord> {
    private static final long serialVersionUID = 2077130969L;
    public static final Entityinstances ENTITYINSTANCES = new Entityinstances();
    public final TableField<EntityinstancesRecord, Long> ID;
    public final TableField<EntityinstancesRecord, Long> ORGANIZATION_ID;
    public final TableField<EntityinstancesRecord, String> CHANNEL_NAME;
    public final TableField<EntityinstancesRecord, String> ENTITY_TYPE;
    public final TableField<EntityinstancesRecord, String> ENTITY_ID;
    public final TableField<EntityinstancesRecord, Timestamp> CREATED_AT;
    public final TableField<EntityinstancesRecord, Timestamp> UPDATED_AT;
    public final TableField<EntityinstancesRecord, Long> TIMESTAMP;
    public final TableField<EntityinstancesRecord, Long> MAP_SPEC_ID;

    @Override
    public Class<EntityinstancesRecord> getRecordType() {
        return EntityinstancesRecord.class;
    }

    public Entityinstances() {
        this("EntityInstances", null);
    }

    public Entityinstances(String alias) {
        this(alias, ENTITYINSTANCES);
    }

    private Entityinstances(String alias, Table<EntityinstancesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Entityinstances(String alias, Table<EntityinstancesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Entityinstances.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"EntityInstances_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ORGANIZATION_ID = Entityinstances.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.CHANNEL_NAME = Entityinstances.createField("channel_name", SQLDataType.CLOB.nullable(false), this, "");
        this.ENTITY_TYPE = Entityinstances.createField("entity_type", SQLDataType.CLOB.nullable(false), this, "");
        this.ENTITY_ID = Entityinstances.createField("entity_id", SQLDataType.CLOB.nullable(false), this, "");
        this.CREATED_AT = Entityinstances.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Entityinstances.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.TIMESTAMP = Entityinstances.createField("timestamp", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "");
        this.MAP_SPEC_ID = Entityinstances.createField("map_spec_id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<EntityinstancesRecord, Long> getIdentity() {
        return Keys.IDENTITY_ENTITYINSTANCES;
    }

    @Override
    public UniqueKey<EntityinstancesRecord> getPrimaryKey() {
        return Keys.ENTITYINSTANCES_PKEY;
    }

    @Override
    public List<UniqueKey<EntityinstancesRecord>> getKeys() {
        return Arrays.asList(Keys.ENTITYINSTANCES_PKEY);
    }

    @Override
    public List<ForeignKey<EntityinstancesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ENTITYINSTANCES__FK_ENTITYINSTANCES_MAP_SPEC);
    }

    public Entityinstances as(String alias) {
        return new Entityinstances(alias, this);
    }

    public Entityinstances rename(String name) {
        return new Entityinstances(name, null);
    }
}

