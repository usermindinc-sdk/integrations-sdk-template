/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.EntitygraphsRecord;
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

public class Entitygraphs
extends TableImpl<EntitygraphsRecord> {
    private static final long serialVersionUID = -1279424217L;
    public static final Entitygraphs ENTITYGRAPHS = new Entitygraphs();
    public final TableField<EntitygraphsRecord, Long> ID;
    public final TableField<EntitygraphsRecord, Long> ORGANIZATION_ID;
    public final TableField<EntitygraphsRecord, Long> MAP_SPEC_ID;
    public final TableField<EntitygraphsRecord, Long> TIMESTAMP;
    public final TableField<EntitygraphsRecord, String> CHANNEL_ENTITY_ID;
    public final TableField<EntitygraphsRecord, Boolean> IS_DELETED;
    public final TableField<EntitygraphsRecord, String[]> MEMBERS;
    public final TableField<EntitygraphsRecord, Timestamp> PROCESSING_FINISHED;
    public final TableField<EntitygraphsRecord, Object> GRAPHS;
    public final TableField<EntitygraphsRecord, Long> PREVIOUS_ENTITYGRAPH_ID;

    @Override
    public Class<EntitygraphsRecord> getRecordType() {
        return EntitygraphsRecord.class;
    }

    public Entitygraphs() {
        this("EntityGraphs", null);
    }

    public Entitygraphs(String alias) {
        this(alias, ENTITYGRAPHS);
    }

    private Entitygraphs(String alias, Table<EntitygraphsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Entitygraphs(String alias, Table<EntitygraphsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Entitygraphs.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"EntityGraphs_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ORGANIZATION_ID = Entitygraphs.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.MAP_SPEC_ID = Entitygraphs.createField("map_spec_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.TIMESTAMP = Entitygraphs.createField("timestamp", SQLDataType.BIGINT.nullable(false), this, "");
        this.CHANNEL_ENTITY_ID = Entitygraphs.createField("channel_entity_id", SQLDataType.CLOB.nullable(false), this, "");
        this.IS_DELETED = Entitygraphs.createField("is_deleted", SQLDataType.BOOLEAN, this, "");
        this.MEMBERS = Entitygraphs.createField("members", SQLDataType.CLOB.getArrayDataType(), this, "");
        this.PROCESSING_FINISHED = Entitygraphs.createField("processing_finished", SQLDataType.TIMESTAMP, this, "");
        this.GRAPHS = Entitygraphs.createField("graphs", SQLDataType.OTHER, this, "");
        this.PREVIOUS_ENTITYGRAPH_ID = Entitygraphs.createField("previous_entitygraph_id", SQLDataType.BIGINT, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<EntitygraphsRecord, Long> getIdentity() {
        return Keys.IDENTITY_ENTITYGRAPHS;
    }

    @Override
    public UniqueKey<EntitygraphsRecord> getPrimaryKey() {
        return Keys.ENTITYGRAPHS_PKEY;
    }

    @Override
    public List<UniqueKey<EntitygraphsRecord>> getKeys() {
        return Arrays.asList(Keys.ENTITYGRAPHS_PKEY);
    }

    @Override
    public List<ForeignKey<EntitygraphsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ENTITYGRAPHS__FK_ENTITYGRAPHS_MAP_SPEC);
    }

    public Entitygraphs as(String alias) {
        return new Entitygraphs(alias, this);
    }

    public Entitygraphs rename(String name) {
        return new Entitygraphs(name, null);
    }
}

