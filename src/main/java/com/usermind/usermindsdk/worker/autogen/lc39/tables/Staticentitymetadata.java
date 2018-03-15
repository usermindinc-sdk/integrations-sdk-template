/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.MapsRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.StaticentitymetadataRecord;
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

public class Staticentitymetadata
extends TableImpl<StaticentitymetadataRecord> {
    private static final long serialVersionUID = -1962321282L;
    public static final Staticentitymetadata STATICENTITYMETADATA = new Staticentitymetadata();
    public final TableField<StaticentitymetadataRecord, Long> ID;
    public final TableField<StaticentitymetadataRecord, Long> MAP_SPEC_ID;
    public final TableField<StaticentitymetadataRecord, Long> ORGANIZATION_ID;
    public final TableField<StaticentitymetadataRecord, String> CHANNEL_NAME;
    public final TableField<StaticentitymetadataRecord, String> ENTITY_TYPE;
    public final TableField<StaticentitymetadataRecord, Integer> RANK;
    public final TableField<StaticentitymetadataRecord, Boolean> IS_MAPPING_ONLY;
    public final TableField<StaticentitymetadataRecord, Timestamp> CREATED_AT;
    public final TableField<StaticentitymetadataRecord, Boolean> COMBINE_PATHS;
    public final TableField<StaticentitymetadataRecord, Object> PATH_SPECIFICATION;

    @Override
    public Class<StaticentitymetadataRecord> getRecordType() {
        return StaticentitymetadataRecord.class;
    }

    public Staticentitymetadata() {
        this("StaticEntityMetadata", null);
    }

    public Staticentitymetadata(String alias) {
        this(alias, STATICENTITYMETADATA);
    }

    private Staticentitymetadata(String alias, Table<StaticentitymetadataRecord> aliased) {
        this(alias, aliased, null);
    }

    private Staticentitymetadata(String alias, Table<StaticentitymetadataRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Staticentitymetadata.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"StaticEntityMetadata_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.MAP_SPEC_ID = Staticentitymetadata.createField("map_spec_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.ORGANIZATION_ID = Staticentitymetadata.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.CHANNEL_NAME = Staticentitymetadata.createField("channel_name", SQLDataType.CLOB.nullable(false), this, "");
        this.ENTITY_TYPE = Staticentitymetadata.createField("entity_type", SQLDataType.CLOB.nullable(false), this, "");
        this.RANK = Staticentitymetadata.createField("rank", SQLDataType.INTEGER, this, "");
        this.IS_MAPPING_ONLY = Staticentitymetadata.createField("is_mapping_only", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.CREATED_AT = Staticentitymetadata.createField("created_at", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("now()", SQLDataType.TIMESTAMP)), this, "");
        this.COMBINE_PATHS = Staticentitymetadata.createField("combine_paths", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.PATH_SPECIFICATION = Staticentitymetadata.createField("path_specification", SQLDataType.OTHER.nullable(false), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<StaticentitymetadataRecord, Long> getIdentity() {
        return Keys.IDENTITY_STATICENTITYMETADATA;
    }

    @Override
    public UniqueKey<StaticentitymetadataRecord> getPrimaryKey() {
        return Keys.STATICENTITYMETADATA_PKEY;
    }

    @Override
    public List<UniqueKey<StaticentitymetadataRecord>> getKeys() {
        return Arrays.asList(Keys.STATICENTITYMETADATA_PKEY);
    }

    @Override
    public List<ForeignKey<StaticentitymetadataRecord, ?>> getReferences() {
        return Arrays.asList(Keys.STATICENTITYMETADATA__FK_STATICENTITYMETADATA_MAP_SPEC);
    }

    public Staticentitymetadata as(String alias) {
        return new Staticentitymetadata(alias, this);
    }

    public Staticentitymetadata rename(String name) {
        return new Staticentitymetadata(name, null);
    }
}

