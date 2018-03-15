/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.TravelerchannelsRecord;
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

public class Travelerchannels
extends TableImpl<TravelerchannelsRecord> {
    private static final long serialVersionUID = 910643045L;
    public static final Travelerchannels TRAVELERCHANNELS = new Travelerchannels();
    public final TableField<TravelerchannelsRecord, Long> ID;
    public final TableField<TravelerchannelsRecord, Long> TRAVELER_ID;
    public final TableField<TravelerchannelsRecord, Long> ORGANIZATION_ID;
    public final TableField<TravelerchannelsRecord, String> CHANNEL_NAME;
    public final TableField<TravelerchannelsRecord, String> ENTITY_TYPE;
    public final TableField<TravelerchannelsRecord, String> ENTITY_ID;
    public final TableField<TravelerchannelsRecord, Timestamp> CREATED_AT;
    public final TableField<TravelerchannelsRecord, Timestamp> UPDATED_AT;
    public final TableField<TravelerchannelsRecord, String> STATE;
    public final TableField<TravelerchannelsRecord, Long> JOURNEY_ID;
    public final TableField<TravelerchannelsRecord, Boolean> IS_LEADER;
    public final TableField<TravelerchannelsRecord, Boolean> IS_DISABLED;
    public final TableField<TravelerchannelsRecord, String[]> PATHS;
    public final TableField<TravelerchannelsRecord, Long> UPDATED_TIMESTAMP;
    public final TableField<TravelerchannelsRecord, Long> NEW_TIMESTAMP;
    public final TableField<TravelerchannelsRecord, Long> VERSION;

    @Override
    public Class<TravelerchannelsRecord> getRecordType() {
        return TravelerchannelsRecord.class;
    }

    public Travelerchannels() {
        this("TravelerChannels", null);
    }

    public Travelerchannels(String alias) {
        this(alias, TRAVELERCHANNELS);
    }

    private Travelerchannels(String alias, Table<TravelerchannelsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Travelerchannels(String alias, Table<TravelerchannelsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Travelerchannels.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"TravelerChannels_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.TRAVELER_ID = Travelerchannels.createField("traveler_id", SQLDataType.BIGINT, this, "");
        this.ORGANIZATION_ID = Travelerchannels.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.CHANNEL_NAME = Travelerchannels.createField("channel_name", SQLDataType.CLOB, this, "");
        this.ENTITY_TYPE = Travelerchannels.createField("entity_type", SQLDataType.CLOB, this, "");
        this.ENTITY_ID = Travelerchannels.createField("entity_id", SQLDataType.CLOB, this, "");
        this.CREATED_AT = Travelerchannels.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Travelerchannels.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.STATE = Travelerchannels.createField("state", SQLDataType.CLOB, this, "");
        this.JOURNEY_ID = Travelerchannels.createField("journey_id", SQLDataType.BIGINT, this, "");
        this.IS_LEADER = Travelerchannels.createField("is_leader", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.IS_DISABLED = Travelerchannels.createField("is_disabled", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.PATHS = Travelerchannels.createField("paths", SQLDataType.CLOB.getArrayDataType(), this, "");
        this.UPDATED_TIMESTAMP = Travelerchannels.createField("updated_timestamp", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "");
        this.NEW_TIMESTAMP = Travelerchannels.createField("new_timestamp", SQLDataType.BIGINT, this, "");
        this.VERSION = Travelerchannels.createField("version", SQLDataType.BIGINT.nullable(false), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<TravelerchannelsRecord, Long> getIdentity() {
        return Keys.IDENTITY_TRAVELERCHANNELS;
    }

    @Override
    public UniqueKey<TravelerchannelsRecord> getPrimaryKey() {
        return Keys.TRAVELERCHANNELS_PKEY;
    }

    @Override
    public List<UniqueKey<TravelerchannelsRecord>> getKeys() {
        return Arrays.asList(Keys.TRAVELERCHANNELS_PKEY);
    }

    public Travelerchannels as(String alias) {
        return new Travelerchannels(alias, this);
    }

    public Travelerchannels rename(String name) {
        return new Travelerchannels(name, null);
    }
}

