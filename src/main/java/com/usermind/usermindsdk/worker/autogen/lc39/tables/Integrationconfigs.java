/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.IntegrationconfigsRecord;
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

public class Integrationconfigs
extends TableImpl<IntegrationconfigsRecord> {
    private static final long serialVersionUID = 1904691193L;
    public static final Integrationconfigs INTEGRATIONCONFIGS = new Integrationconfigs();
    public final TableField<IntegrationconfigsRecord, Long> ID;
    public final TableField<IntegrationconfigsRecord, Long> ORGANIZATION_ID;
    public final TableField<IntegrationconfigsRecord, String> NAME;
    public final TableField<IntegrationconfigsRecord, String> CONFIG;
    public final TableField<IntegrationconfigsRecord, Timestamp> CREATED_AT;
    public final TableField<IntegrationconfigsRecord, Timestamp> UPDATED_AT;
    public final TableField<IntegrationconfigsRecord, Integer> VERSION;
    public final TableField<IntegrationconfigsRecord, String> CHANNEL_NAME;

    @Override
    public Class<IntegrationconfigsRecord> getRecordType() {
        return IntegrationconfigsRecord.class;
    }

    public Integrationconfigs() {
        this("IntegrationConfigs", null);
    }

    public Integrationconfigs(String alias) {
        this(alias, INTEGRATIONCONFIGS);
    }

    private Integrationconfigs(String alias, Table<IntegrationconfigsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Integrationconfigs(String alias, Table<IntegrationconfigsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Integrationconfigs.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"SFConfigs_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ORGANIZATION_ID = Integrationconfigs.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.NAME = Integrationconfigs.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.CONFIG = Integrationconfigs.createField("config", SQLDataType.CLOB, this, "");
        this.CREATED_AT = Integrationconfigs.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Integrationconfigs.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.VERSION = Integrationconfigs.createField("version", SQLDataType.INTEGER, this, "");
        this.CHANNEL_NAME = Integrationconfigs.createField("channel_name", SQLDataType.VARCHAR.length(255), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<IntegrationconfigsRecord, Long> getIdentity() {
        return Keys.IDENTITY_INTEGRATIONCONFIGS;
    }

    @Override
    public UniqueKey<IntegrationconfigsRecord> getPrimaryKey() {
        return Keys.INTEGRATIONCONFIGS_PKEY;
    }

    @Override
    public List<UniqueKey<IntegrationconfigsRecord>> getKeys() {
        return Arrays.asList(Keys.INTEGRATIONCONFIGS_PKEY);
    }

    public Integrationconfigs as(String alias) {
        return new Integrationconfigs(alias, this);
    }

    public Integrationconfigs rename(String name) {
        return new Integrationconfigs(name, null);
    }
}

