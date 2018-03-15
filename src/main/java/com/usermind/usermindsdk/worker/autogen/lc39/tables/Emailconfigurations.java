/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.EmailconfigurationsRecord;
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
import org.jooq.impl.DefaultDataType;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Emailconfigurations
extends TableImpl<EmailconfigurationsRecord> {
    private static final long serialVersionUID = -1194720700L;
    public static final Emailconfigurations EMAILCONFIGURATIONS = new Emailconfigurations();
    public final TableField<EmailconfigurationsRecord, Long> ID;
    public final TableField<EmailconfigurationsRecord, Long> ORGANIZATION_ID;
    public final TableField<EmailconfigurationsRecord, Long> JOURNEY_ID;
    public final TableField<EmailconfigurationsRecord, Long> RULE_ID;
    public final TableField<EmailconfigurationsRecord, Boolean> IS_DELETED;
    public final TableField<EmailconfigurationsRecord, Long> VERSION;
    public final TableField<EmailconfigurationsRecord, Object> CONFIGURATION;
    public final TableField<EmailconfigurationsRecord, Timestamp> CREATED_AT;

    @Override
    public Class<EmailconfigurationsRecord> getRecordType() {
        return EmailconfigurationsRecord.class;
    }

    public Emailconfigurations() {
        this("EmailConfigurations", null);
    }

    public Emailconfigurations(String alias) {
        this(alias, EMAILCONFIGURATIONS);
    }

    private Emailconfigurations(String alias, Table<EmailconfigurationsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Emailconfigurations(String alias, Table<EmailconfigurationsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Emailconfigurations.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"EmailConfigurations_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ORGANIZATION_ID = Emailconfigurations.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.JOURNEY_ID = Emailconfigurations.createField("journey_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.RULE_ID = Emailconfigurations.createField("rule_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.IS_DELETED = Emailconfigurations.createField("is_deleted", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.VERSION = Emailconfigurations.createField("version", SQLDataType.BIGINT.nullable(false), this, "");
        this.CONFIGURATION = Emailconfigurations.createField("configuration", DefaultDataType.getDefaultDataType("jsonb"), this, "");
        this.CREATED_AT = Emailconfigurations.createField("created_at", SQLDataType.TIMESTAMP.nullable(false).defaultValue(DSL.field("now()", SQLDataType.TIMESTAMP)), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<EmailconfigurationsRecord, Long> getIdentity() {
        return Keys.IDENTITY_EMAILCONFIGURATIONS;
    }

    @Override
    public UniqueKey<EmailconfigurationsRecord> getPrimaryKey() {
        return Keys.PK_EMAILCONFIGURATIONS;
    }

    @Override
    public List<UniqueKey<EmailconfigurationsRecord>> getKeys() {
        return Arrays.asList(Keys.PK_EMAILCONFIGURATIONS, Keys.EMAILCONFIGURATIONS_ORGANIZATION_ID_JOURNEY_ID_RULE_ID_VERSION_);
    }

    public Emailconfigurations as(String alias) {
        return new Emailconfigurations(alias, this);
    }

    public Emailconfigurations rename(String name) {
        return new Emailconfigurations(name, null);
    }
}

