/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.StaticentitymapspecificationRecord;
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

public class Staticentitymapspecification
extends TableImpl<StaticentitymapspecificationRecord> {
    private static final long serialVersionUID = -2116368131L;
    public static final Staticentitymapspecification STATICENTITYMAPSPECIFICATION = new Staticentitymapspecification();
    public final TableField<StaticentitymapspecificationRecord, Long> ID;
    public final TableField<StaticentitymapspecificationRecord, Long> ORGANIZATION_ID;
    public final TableField<StaticentitymapspecificationRecord, String> FROM_CHANNEL_NAME;
    public final TableField<StaticentitymapspecificationRecord, String> FROM_ENTITY_TYPE;
    public final TableField<StaticentitymapspecificationRecord, String> TO_CHANNEL_NAME;
    public final TableField<StaticentitymapspecificationRecord, String> TO_ENTITY_TYPE;
    public final TableField<StaticentitymapspecificationRecord, String> ASSOCIATION_KEY;
    public final TableField<StaticentitymapspecificationRecord, String> ASSOCIATION_CARDINALITY;
    public final TableField<StaticentitymapspecificationRecord, Timestamp> CREATED_AT;
    public final TableField<StaticentitymapspecificationRecord, Boolean> CASE_INSENSITIVE;
    public final TableField<StaticentitymapspecificationRecord, String> EDGE_LABEL;

    @Override
    public Class<StaticentitymapspecificationRecord> getRecordType() {
        return StaticentitymapspecificationRecord.class;
    }

    public Staticentitymapspecification() {
        this("StaticEntityMapSpecification", null);
    }

    public Staticentitymapspecification(String alias) {
        this(alias, STATICENTITYMAPSPECIFICATION);
    }

    private Staticentitymapspecification(String alias, Table<StaticentitymapspecificationRecord> aliased) {
        this(alias, aliased, null);
    }

    private Staticentitymapspecification(String alias, Table<StaticentitymapspecificationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Staticentitymapspecification.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"StaticEntityMapSpecification_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ORGANIZATION_ID = Staticentitymapspecification.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.FROM_CHANNEL_NAME = Staticentitymapspecification.createField("from_channel_name", SQLDataType.CLOB.nullable(false), this, "");
        this.FROM_ENTITY_TYPE = Staticentitymapspecification.createField("from_entity_type", SQLDataType.CLOB.nullable(false), this, "");
        this.TO_CHANNEL_NAME = Staticentitymapspecification.createField("to_channel_name", SQLDataType.CLOB.nullable(false), this, "");
        this.TO_ENTITY_TYPE = Staticentitymapspecification.createField("to_entity_type", SQLDataType.CLOB.nullable(false), this, "");
        this.ASSOCIATION_KEY = Staticentitymapspecification.createField("association_key", SQLDataType.CLOB.nullable(false), this, "");
        this.ASSOCIATION_CARDINALITY = Staticentitymapspecification.createField("association_cardinality", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.CREATED_AT = Staticentitymapspecification.createField("created_at", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("now()", SQLDataType.TIMESTAMP)), this, "");
        this.CASE_INSENSITIVE = Staticentitymapspecification.createField("case_insensitive", SQLDataType.BOOLEAN.defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.EDGE_LABEL = Staticentitymapspecification.createField("edge_label", SQLDataType.CLOB, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<StaticentitymapspecificationRecord, Long> getIdentity() {
        return Keys.IDENTITY_STATICENTITYMAPSPECIFICATION;
    }

    @Override
    public UniqueKey<StaticentitymapspecificationRecord> getPrimaryKey() {
        return Keys.STATICENTITYMAPSPECIFICATION_PKEY;
    }

    @Override
    public List<UniqueKey<StaticentitymapspecificationRecord>> getKeys() {
        return Arrays.asList(Keys.STATICENTITYMAPSPECIFICATION_PKEY, Keys.PERORGEDGELABEL_UNIQUE);
    }

    public Staticentitymapspecification as(String alias) {
        return new Staticentitymapspecification(alias, this);
    }

    public Staticentitymapspecification rename(String name) {
        return new Staticentitymapspecification(name, null);
    }
}

