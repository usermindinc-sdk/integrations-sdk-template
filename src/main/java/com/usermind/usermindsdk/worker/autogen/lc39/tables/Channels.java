/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ChannelsRecord;
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

public class Channels
extends TableImpl<ChannelsRecord> {
    private static final long serialVersionUID = 1587969462L;
    public static final Channels CHANNELS = new Channels();
    public final TableField<ChannelsRecord, Long> ID;
    public final TableField<ChannelsRecord, String> NAME;
    public final TableField<ChannelsRecord, Long> ORGANIZATION_ID;
    public final TableField<ChannelsRecord, Boolean> HAS_ENTITIES;
    public final TableField<ChannelsRecord, Boolean> HAS_ACTIONS;
    public final TableField<ChannelsRecord, Timestamp> CREATED_AT;
    public final TableField<ChannelsRecord, Timestamp> UPDATED_AT;
    public final TableField<ChannelsRecord, String> DISPLAY_NAME;
    public final TableField<ChannelsRecord, String> ENVIRONMENT;

    @Override
    public Class<ChannelsRecord> getRecordType() {
        return ChannelsRecord.class;
    }

    public Channels() {
        this("Channels", null);
    }

    public Channels(String alias) {
        this(alias, CHANNELS);
    }

    private Channels(String alias, Table<ChannelsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Channels(String alias, Table<ChannelsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Channels.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Channels_generated_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.NAME = Channels.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.ORGANIZATION_ID = Channels.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.HAS_ENTITIES = Channels.createField("has_entities", SQLDataType.BOOLEAN, this, "");
        this.HAS_ACTIONS = Channels.createField("has_actions", SQLDataType.BOOLEAN, this, "");
        this.CREATED_AT = Channels.createField("created_at", SQLDataType.TIMESTAMP, this, "");
        this.UPDATED_AT = Channels.createField("updated_at", SQLDataType.TIMESTAMP, this, "");
        this.DISPLAY_NAME = Channels.createField("display_name", SQLDataType.VARCHAR.length(255), this, "");
        this.ENVIRONMENT = Channels.createField("environment", SQLDataType.CLOB, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<ChannelsRecord, Long> getIdentity() {
        return Keys.IDENTITY_CHANNELS;
    }

    @Override
    public UniqueKey<ChannelsRecord> getPrimaryKey() {
        return Keys.CHANNELS_PKEY;
    }

    @Override
    public List<UniqueKey<ChannelsRecord>> getKeys() {
        return Arrays.asList(Keys.CHANNELS_PKEY);
    }

    public Channels as(String alias) {
        return new Channels(alias, this);
    }

    public Channels rename(String name) {
        return new Channels(name, null);
    }
}

