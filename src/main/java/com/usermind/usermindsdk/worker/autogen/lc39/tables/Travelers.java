/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.TravelersRecord;
import java.sql.Timestamp;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Travelers
extends TableImpl<TravelersRecord> {
    private static final long serialVersionUID = -776007451L;
    public static final Travelers TRAVELERS = new Travelers();
    public final TableField<TravelersRecord, Long> ID;
    public final TableField<TravelersRecord, Long> ORGANIZATION_ID;
    public final TableField<TravelersRecord, String> NAME;
    public final TableField<TravelersRecord, Timestamp> CREATED_AT;
    public final TableField<TravelersRecord, Timestamp> UPDATED_AT;
    public final TableField<TravelersRecord, String> EMAIL;
    public final TableField<TravelersRecord, Timestamp> FINISH_DT;
    public final TableField<TravelersRecord, Timestamp> START_DT;
    public final TableField<TravelersRecord, Long> JOURNEY_ID;
    public final TableField<TravelersRecord, String> VAR_A;
    public final TableField<TravelersRecord, String> VAR_B;
    public final TableField<TravelersRecord, String> VAR_C;
    public final TableField<TravelersRecord, String> VAR_D;
    public final TableField<TravelersRecord, String> VAR_E;
    public final TableField<TravelersRecord, String> VAR_F;
    public final TableField<TravelersRecord, Long> ENTITYGRAPH_ID;
    public final TableField<TravelersRecord, Long> VERSION;
    public final TableField<TravelersRecord, String> VAR_G;
    public final TableField<TravelersRecord, String> VAR_H;
    public final TableField<TravelersRecord, String> VAR_I;
    public final TableField<TravelersRecord, String> VAR_J;

    @Override
    public Class<TravelersRecord> getRecordType() {
        return TravelersRecord.class;
    }

    public Travelers() {
        this("Travelers", null);
    }

    public Travelers(String alias) {
        this(alias, TRAVELERS);
    }

    private Travelers(String alias, Table<TravelersRecord> aliased) {
        this(alias, aliased, null);
    }

    private Travelers(String alias, Table<TravelersRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Travelers.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Users_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ORGANIZATION_ID = Travelers.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.NAME = Travelers.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.CREATED_AT = Travelers.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Travelers.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.EMAIL = Travelers.createField("email", SQLDataType.CLOB, this, "");
        this.FINISH_DT = Travelers.createField("finish_dt", SQLDataType.TIMESTAMP, this, "");
        this.START_DT = Travelers.createField("start_dt", SQLDataType.TIMESTAMP, this, "");
        this.JOURNEY_ID = Travelers.createField("journey_id", SQLDataType.BIGINT, this, "");
        this.VAR_A = Travelers.createField("var_a", SQLDataType.VARCHAR.length(255), this, "");
        this.VAR_B = Travelers.createField("var_b", SQLDataType.VARCHAR.length(255), this, "");
        this.VAR_C = Travelers.createField("var_c", SQLDataType.VARCHAR.length(255), this, "");
        this.VAR_D = Travelers.createField("var_d", SQLDataType.VARCHAR.length(255), this, "");
        this.VAR_E = Travelers.createField("var_e", SQLDataType.VARCHAR.length(255), this, "");
        this.VAR_F = Travelers.createField("var_f", SQLDataType.VARCHAR.length(255), this, "");
        this.ENTITYGRAPH_ID = Travelers.createField("entitygraph_id", SQLDataType.BIGINT, this, "");
        this.VERSION = Travelers.createField("version", SQLDataType.BIGINT.nullable(false), this, "");
        this.VAR_G = Travelers.createField("var_g", SQLDataType.VARCHAR.length(255), this, "");
        this.VAR_H = Travelers.createField("var_h", SQLDataType.VARCHAR.length(255), this, "");
        this.VAR_I = Travelers.createField("var_i", SQLDataType.VARCHAR.length(255), this, "");
        this.VAR_J = Travelers.createField("var_j", SQLDataType.VARCHAR.length(255), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<TravelersRecord, Long> getIdentity() {
        return Keys.IDENTITY_TRAVELERS;
    }

    public Travelers as(String alias) {
        return new Travelers(alias, this);
    }

    public Travelers rename(String name) {
        return new Travelers(name, null);
    }
}

