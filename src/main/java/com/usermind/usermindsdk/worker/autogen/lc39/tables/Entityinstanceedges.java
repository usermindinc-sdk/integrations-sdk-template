/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.EntityinstanceedgesRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.MapsRecord;
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

public class Entityinstanceedges
extends TableImpl<EntityinstanceedgesRecord> {
    private static final long serialVersionUID = 702310984L;
    public static final Entityinstanceedges ENTITYINSTANCEEDGES = new Entityinstanceedges();
    public final TableField<EntityinstanceedgesRecord, Long> ID;
    public final TableField<EntityinstanceedgesRecord, Long> ORGANIZATION_ID;
    public final TableField<EntityinstanceedgesRecord, Long> MAP_SPEC_ID;
    public final TableField<EntityinstanceedgesRecord, String> FROM_CHANNEL_NAME;
    public final TableField<EntityinstanceedgesRecord, String> FROM_ENTITY_TYPE;
    public final TableField<EntityinstanceedgesRecord, String> FROM_ENTITY_ID;
    public final TableField<EntityinstanceedgesRecord, String> TO_CHANNEL_NAME;
    public final TableField<EntityinstanceedgesRecord, String> TO_ENTITY_TYPE;
    public final TableField<EntityinstanceedgesRecord, String> TO_ENTITY_ID;
    public final TableField<EntityinstanceedgesRecord, String> ASSOCIATION_KEY;
    public final TableField<EntityinstanceedgesRecord, String> ASSOCIATION_CARDINALITY;
    public final TableField<EntityinstanceedgesRecord, Boolean> IS_DELETED;
    public final TableField<EntityinstanceedgesRecord, Boolean> CASE_INSENSITIVE;
    public final TableField<EntityinstanceedgesRecord, Long> TIMESTAMP;
    public final TableField<EntityinstanceedgesRecord, String> CHANNEL_ENTITY_KEY;

    @Override
    public Class<EntityinstanceedgesRecord> getRecordType() {
        return EntityinstanceedgesRecord.class;
    }

    public Entityinstanceedges() {
        this("EntityInstanceEdges", null);
    }

    public Entityinstanceedges(String alias) {
        this(alias, ENTITYINSTANCEEDGES);
    }

    private Entityinstanceedges(String alias, Table<EntityinstanceedgesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Entityinstanceedges(String alias, Table<EntityinstanceedgesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Entityinstanceedges.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"EntityInstanceEdges_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ORGANIZATION_ID = Entityinstanceedges.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.MAP_SPEC_ID = Entityinstanceedges.createField("map_spec_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.FROM_CHANNEL_NAME = Entityinstanceedges.createField("from_channel_name", SQLDataType.CLOB.nullable(false), this, "");
        this.FROM_ENTITY_TYPE = Entityinstanceedges.createField("from_entity_type", SQLDataType.CLOB.nullable(false), this, "");
        this.FROM_ENTITY_ID = Entityinstanceedges.createField("from_entity_id", SQLDataType.CLOB.nullable(false), this, "");
        this.TO_CHANNEL_NAME = Entityinstanceedges.createField("to_channel_name", SQLDataType.CLOB.nullable(false), this, "");
        this.TO_ENTITY_TYPE = Entityinstanceedges.createField("to_entity_type", SQLDataType.CLOB.nullable(false), this, "");
        this.TO_ENTITY_ID = Entityinstanceedges.createField("to_entity_id", SQLDataType.CLOB.nullable(false), this, "");
        this.ASSOCIATION_KEY = Entityinstanceedges.createField("association_key", SQLDataType.CLOB.nullable(false), this, "");
        this.ASSOCIATION_CARDINALITY = Entityinstanceedges.createField("association_cardinality", SQLDataType.CLOB.nullable(false), this, "");
        this.IS_DELETED = Entityinstanceedges.createField("is_deleted", SQLDataType.BOOLEAN.nullable(false), this, "");
        this.CASE_INSENSITIVE = Entityinstanceedges.createField("case_insensitive", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.TIMESTAMP = Entityinstanceedges.createField("timestamp", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "");
        this.CHANNEL_ENTITY_KEY = Entityinstanceedges.createField("channel_entity_key", SQLDataType.CLOB, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<EntityinstanceedgesRecord, Long> getIdentity() {
        return Keys.IDENTITY_ENTITYINSTANCEEDGES;
    }

    @Override
    public UniqueKey<EntityinstanceedgesRecord> getPrimaryKey() {
        return Keys.ENTITYINSTANCEEDGES_PKEY;
    }

    @Override
    public List<UniqueKey<EntityinstanceedgesRecord>> getKeys() {
        return Arrays.asList(Keys.ENTITYINSTANCEEDGES_PKEY);
    }

    @Override
    public List<ForeignKey<EntityinstanceedgesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ENTITYINSTANCEEDGES__FK_ENTITYINSTANCE_EDGES_MAP_SPEC);
    }

    public Entityinstanceedges as(String alias) {
        return new Entityinstanceedges(alias, this);
    }

    public Entityinstanceedges rename(String name) {
        return new Entityinstanceedges(name, null);
    }
}

