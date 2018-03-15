/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.CustomerStatusRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.OrganizationsRecord;
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
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Organizations
extends TableImpl<OrganizationsRecord> {
    private static final long serialVersionUID = 754307720L;
    public static final Organizations ORGANIZATIONS = new Organizations();
    public final TableField<OrganizationsRecord, Long> ID;
    public final TableField<OrganizationsRecord, String> NAME;
    public final TableField<OrganizationsRecord, Timestamp> CREATED_AT;
    public final TableField<OrganizationsRecord, Timestamp> UPDATED_AT;
    public final TableField<OrganizationsRecord, Boolean> REPLAY;
    public final TableField<OrganizationsRecord, Integer> REPLAY_FLAGS;
    public final TableField<OrganizationsRecord, String> ENTITY_OVERWRITES;
    public final TableField<OrganizationsRecord, Boolean> PROC_DISABLED;
    public final TableField<OrganizationsRecord, String> DISABLED_REASON;
    public final TableField<OrganizationsRecord, Boolean> DISABLE_IF_ERR;
    public final TableField<OrganizationsRecord, Long> ALIAS_ORGANIZATION_ID;
    public final TableField<OrganizationsRecord, Boolean> USE_CASE_SENSITIVE_IDS;
    public final TableField<OrganizationsRecord, Boolean> CUSTOMER;
    public final TableField<OrganizationsRecord, Object> PROC_METADATA;
    public final TableField<OrganizationsRecord, Boolean> RETAIN;
    public final TableField<OrganizationsRecord, Long> CUSTOMER_STATUS;

    @Override
    public Class<OrganizationsRecord> getRecordType() {
        return OrganizationsRecord.class;
    }

    public Organizations() {
        this("Organizations", null);
    }

    public Organizations(String alias) {
        this(alias, ORGANIZATIONS);
    }

    private Organizations(String alias, Table<OrganizationsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Organizations(String alias, Table<OrganizationsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Organizations.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Customers_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.NAME = Organizations.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.CREATED_AT = Organizations.createField("created_at", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("now()", SQLDataType.TIMESTAMP)), this, "");
        this.UPDATED_AT = Organizations.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("now()", SQLDataType.TIMESTAMP)), this, "");
        this.REPLAY = Organizations.createField("replay", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.REPLAY_FLAGS = Organizations.createField("replay_flags", SQLDataType.INTEGER.defaultValue(DSL.field("12", SQLDataType.INTEGER)), this, "");
        this.ENTITY_OVERWRITES = Organizations.createField("entity_overwrites", SQLDataType.CLOB, this, "");
        this.PROC_DISABLED = Organizations.createField("proc_disabled", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.DISABLED_REASON = Organizations.createField("disabled_reason", SQLDataType.CLOB, this, "");
        this.DISABLE_IF_ERR = Organizations.createField("disable_if_err", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.ALIAS_ORGANIZATION_ID = Organizations.createField("alias_organization_id", SQLDataType.BIGINT, this, "");
        this.USE_CASE_SENSITIVE_IDS = Organizations.createField("use_case_sensitive_ids", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");
        this.CUSTOMER = Organizations.createField("customer", SQLDataType.BOOLEAN.defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.PROC_METADATA = Organizations.createField("proc_metadata", DefaultDataType.getDefaultDataType("jsonb"), this, "");
        this.RETAIN = Organizations.createField("retain", SQLDataType.BOOLEAN, this, "");
        this.CUSTOMER_STATUS = Organizations.createField("customer_status", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("1", SQLDataType.BIGINT)), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<OrganizationsRecord, Long> getIdentity() {
        return Keys.IDENTITY_ORGANIZATIONS;
    }

    @Override
    public UniqueKey<OrganizationsRecord> getPrimaryKey() {
        return Keys.ORGANIZATIONS_PKEY;
    }

    @Override
    public List<UniqueKey<OrganizationsRecord>> getKeys() {
        return Arrays.asList(Keys.ORGANIZATIONS_PKEY);
    }

    @Override
    public List<ForeignKey<OrganizationsRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ORGANIZATIONS__FK_CUSTOMER_STATUS);
    }

    public Organizations as(String alias) {
        return new Organizations(alias, this);
    }

    public Organizations rename(String name) {
        return new Organizations(name, null);
    }
}

