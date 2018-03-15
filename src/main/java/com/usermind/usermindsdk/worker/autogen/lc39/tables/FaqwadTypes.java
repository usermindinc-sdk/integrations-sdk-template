/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FaqwadTypesRecord;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class FaqwadTypes
extends TableImpl<FaqwadTypesRecord> {
    private static final long serialVersionUID = -684825092L;
    public static final FaqwadTypes FAQWAD_TYPES = new FaqwadTypes();
    public final TableField<FaqwadTypesRecord, Long> FAQWAD_TYPE_ID;
    public final TableField<FaqwadTypesRecord, String> FAQWAD_TYPE_NAME;
    public final TableField<FaqwadTypesRecord, String> DESCRIPTION;
    public final TableField<FaqwadTypesRecord, Timestamp> CREATED_AT;
    public final TableField<FaqwadTypesRecord, Timestamp> UPDATED_AT;
    public final TableField<FaqwadTypesRecord, Long> RVN;

    @Override
    public Class<FaqwadTypesRecord> getRecordType() {
        return FaqwadTypesRecord.class;
    }

    public FaqwadTypes() {
        this("faqwad_types", null);
    }

    public FaqwadTypes(String alias) {
        this(alias, FAQWAD_TYPES);
    }

    private FaqwadTypes(String alias, Table<FaqwadTypesRecord> aliased) {
        this(alias, aliased, null);
    }

    private FaqwadTypes(String alias, Table<FaqwadTypesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.FAQWAD_TYPE_ID = FaqwadTypes.createField("faqwad_type_id", SQLDataType.BIGINT.nullable(false), this, "Surrogate key for FAQWAD message types. This is used for joining to the faqwad_messages table.");
        this.FAQWAD_TYPE_NAME = FaqwadTypes.createField("faqwad_type_name", SQLDataType.CLOB.nullable(false), this, "The program friendly, and less human hostile name the FAQWAD message type. FAQWAD message types should not be added by programs, they require coordinating the types with the code that processes the types. The values here are required to be globally unique. Please keep values fairly short, do not go write a novel in the name field.");
        this.DESCRIPTION = FaqwadTypes.createField("description", SQLDataType.CLOB.nullable(false).defaultValue(DSL.field("'Not specified'::text", SQLDataType.CLOB)), this, "User friendly description of what the type is.");
        this.CREATED_AT = FaqwadTypes.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "The time the value was first created in the database. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.UPDATED_AT = FaqwadTypes.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "The last time the value was updated at. Managed by triggers, inserts and updates should not touch this field, values will be ignored.");
        this.RVN = FaqwadTypes.createField("rvn", SQLDataType.BIGINT.nullable(false), this, "Record version number used for optimistic locking, value is managed by triggers. All queries to update a record must include the rvn with the value the changes they are requesting was based on.");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<FaqwadTypesRecord> getPrimaryKey() {
        return Keys.PK_FAQWAD_TYPES;
    }

    @Override
    public List<UniqueKey<FaqwadTypesRecord>> getKeys() {
        return Arrays.asList(Keys.PK_FAQWAD_TYPES, Keys.FAQWAD_TYPES_FAQWAD_TYPE_NAME_KEY);
    }

    public FaqwadTypes as(String alias) {
        return new FaqwadTypes(alias, this);
    }

    public FaqwadTypes rename(String name) {
        return new FaqwadTypes(name, null);
    }
}

