/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ChannelentitiesRecord;
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

public class Channelentities
extends TableImpl<ChannelentitiesRecord> {
    private static final long serialVersionUID = 1390319918L;
    public static final Channelentities CHANNELENTITIES = new Channelentities();
    public final TableField<ChannelentitiesRecord, Long> ID;
    public final TableField<ChannelentitiesRecord, String> KEY;
    public final TableField<ChannelentitiesRecord, Long> TIMESTAMP;
    public final TableField<ChannelentitiesRecord, Long> ORGANIZATION_ID;
    public final TableField<ChannelentitiesRecord, String> CHANNEL_NAME;
    public final TableField<ChannelentitiesRecord, String> ENTITY_TYPE;
    public final TableField<ChannelentitiesRecord, String> ENTITY_ID;
    public final TableField<ChannelentitiesRecord, Object> DATA;
    public final TableField<ChannelentitiesRecord, Integer> RECORD_ID;
    public final TableField<ChannelentitiesRecord, String> RECORD_NAME;

    @Override
    public Class<ChannelentitiesRecord> getRecordType() {
        return ChannelentitiesRecord.class;
    }

    public Channelentities() {
        this("ChannelEntities", null);
    }

    public Channelentities(String alias) {
        this(alias, CHANNELENTITIES);
    }

    private Channelentities(String alias, Table<ChannelentitiesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Channelentities(String alias, Table<ChannelentitiesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Channelentities.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"ChannelEntities_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.KEY = Channelentities.createField("key", SQLDataType.CLOB.nullable(false), this, "");
        this.TIMESTAMP = Channelentities.createField("timestamp", SQLDataType.BIGINT.nullable(false), this, "");
        this.ORGANIZATION_ID = Channelentities.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.CHANNEL_NAME = Channelentities.createField("channel_name", SQLDataType.CLOB.nullable(false), this, "");
        this.ENTITY_TYPE = Channelentities.createField("entity_type", SQLDataType.CLOB.nullable(false), this, "");
        this.ENTITY_ID = Channelentities.createField("entity_id", SQLDataType.CLOB.nullable(false), this, "");
        this.DATA = Channelentities.createField("data", SQLDataType.OTHER.nullable(false), this, "");
        this.RECORD_ID = Channelentities.createField("record_id", SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");
        this.RECORD_NAME = Channelentities.createField("record_name", SQLDataType.CLOB, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<ChannelentitiesRecord, Long> getIdentity() {
        return Keys.IDENTITY_CHANNELENTITIES;
    }

    @Override
    public UniqueKey<ChannelentitiesRecord> getPrimaryKey() {
        return Keys.CHANNELENTITIES_PKEY;
    }

    @Override
    public List<UniqueKey<ChannelentitiesRecord>> getKeys() {
        return Arrays.asList(Keys.CHANNELENTITIES_PKEY);
    }

    public Channelentities as(String alias) {
        return new Channelentities(alias, this);
    }

    public Channelentities rename(String name) {
        return new Channelentities(name, null);
    }
}

