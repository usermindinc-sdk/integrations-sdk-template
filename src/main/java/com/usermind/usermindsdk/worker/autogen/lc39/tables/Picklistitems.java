/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.PicklistitemsRecord;
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

public class Picklistitems
extends TableImpl<PicklistitemsRecord> {
    private static final long serialVersionUID = -1387370710L;
    public static final Picklistitems PICKLISTITEMS = new Picklistitems();
    public final TableField<PicklistitemsRecord, Long> ID;
    public final TableField<PicklistitemsRecord, Long> JOURNEY_ID;
    public final TableField<PicklistitemsRecord, Long> ORGANIZATION_ID;
    public final TableField<PicklistitemsRecord, Long> PICK_LIST_ITEM_ID;
    public final TableField<PicklistitemsRecord, Long> VERSION;
    public final TableField<PicklistitemsRecord, String> PATH;
    public final TableField<PicklistitemsRecord, String> VALUE;
    public final TableField<PicklistitemsRecord, Boolean> DELETED;
    public final TableField<PicklistitemsRecord, Timestamp> CREATED_AT;
    public final TableField<PicklistitemsRecord, Timestamp> UPDATED_AT;
    public final TableField<PicklistitemsRecord, String> LABEL;

    @Override
    public Class<PicklistitemsRecord> getRecordType() {
        return PicklistitemsRecord.class;
    }

    public Picklistitems() {
        this("PickListItems", null);
    }

    public Picklistitems(String alias) {
        this(alias, PICKLISTITEMS);
    }

    private Picklistitems(String alias, Table<PicklistitemsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Picklistitems(String alias, Table<PicklistitemsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Picklistitems.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"PickListItems_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.JOURNEY_ID = Picklistitems.createField("journey_id", SQLDataType.BIGINT, this, "");
        this.ORGANIZATION_ID = Picklistitems.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.PICK_LIST_ITEM_ID = Picklistitems.createField("pick_list_item_id", SQLDataType.BIGINT, this, "");
        this.VERSION = Picklistitems.createField("version", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "");
        this.PATH = Picklistitems.createField("path", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.VALUE = Picklistitems.createField("value", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.DELETED = Picklistitems.createField("deleted", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.CREATED_AT = Picklistitems.createField("created_at", SQLDataType.TIMESTAMP, this, "");
        this.UPDATED_AT = Picklistitems.createField("updated_at", SQLDataType.TIMESTAMP, this, "");
        this.LABEL = Picklistitems.createField("label", SQLDataType.VARCHAR.length(255), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<PicklistitemsRecord, Long> getIdentity() {
        return Keys.IDENTITY_PICKLISTITEMS;
    }

    @Override
    public UniqueKey<PicklistitemsRecord> getPrimaryKey() {
        return Keys.PICKLISTITEMS_PKEY;
    }

    @Override
    public List<UniqueKey<PicklistitemsRecord>> getKeys() {
        return Arrays.asList(Keys.PICKLISTITEMS_PKEY);
    }

    public Picklistitems as(String alias) {
        return new Picklistitems(alias, this);
    }

    public Picklistitems rename(String name) {
        return new Picklistitems(name, null);
    }
}

