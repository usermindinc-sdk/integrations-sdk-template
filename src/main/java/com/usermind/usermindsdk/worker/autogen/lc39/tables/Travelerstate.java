/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.TravelerstateRecord;
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

public class Travelerstate
extends TableImpl<TravelerstateRecord> {
    private static final long serialVersionUID = -1421978805L;
    public static final Travelerstate TRAVELERSTATE = new Travelerstate();
    public final TableField<TravelerstateRecord, Long> ID;
    public final TableField<TravelerstateRecord, String> NAME;
    public final TableField<TravelerstateRecord, Long> ORGANIZATION_ID;
    public final TableField<TravelerstateRecord, Timestamp> TIMESTAMP;
    public final TableField<TravelerstateRecord, Long> TRAVELER_ID;
    public final TableField<TravelerstateRecord, Boolean> IS_TERMINAL;
    public final TableField<TravelerstateRecord, Timestamp> CREATED_AT;
    public final TableField<TravelerstateRecord, Timestamp> UPDATED_AT;
    public final TableField<TravelerstateRecord, Long> JOURNEY_ID;
    public final TableField<TravelerstateRecord, String> TRANSACTION_ID;
    public final TableField<TravelerstateRecord, String> RULE_ID;

    @Override
    public Class<TravelerstateRecord> getRecordType() {
        return TravelerstateRecord.class;
    }

    public Travelerstate() {
        this("TravelerState", null);
    }

    public Travelerstate(String alias) {
        this(alias, TRAVELERSTATE);
    }

    private Travelerstate(String alias, Table<TravelerstateRecord> aliased) {
        this(alias, aliased, null);
    }

    private Travelerstate(String alias, Table<TravelerstateRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Travelerstate.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Status_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.NAME = Travelerstate.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.ORGANIZATION_ID = Travelerstate.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.TIMESTAMP = Travelerstate.createField("timestamp", SQLDataType.TIMESTAMP, this, "");
        this.TRAVELER_ID = Travelerstate.createField("traveler_id", SQLDataType.BIGINT, this, "");
        this.IS_TERMINAL = Travelerstate.createField("is_terminal", SQLDataType.BOOLEAN, this, "");
        this.CREATED_AT = Travelerstate.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Travelerstate.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.JOURNEY_ID = Travelerstate.createField("journey_id", SQLDataType.BIGINT, this, "");
        this.TRANSACTION_ID = Travelerstate.createField("transaction_id", SQLDataType.CLOB, this, "");
        this.RULE_ID = Travelerstate.createField("rule_id", SQLDataType.CLOB, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<TravelerstateRecord, Long> getIdentity() {
        return Keys.IDENTITY_TRAVELERSTATE;
    }

    @Override
    public UniqueKey<TravelerstateRecord> getPrimaryKey() {
        return Keys.STATUS_PKEY;
    }

    @Override
    public List<UniqueKey<TravelerstateRecord>> getKeys() {
        return Arrays.asList(Keys.STATUS_PKEY);
    }

    public Travelerstate as(String alias) {
        return new Travelerstate(alias, this);
    }

    public Travelerstate rename(String name) {
        return new Travelerstate(name, null);
    }
}

