/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.MilestonesRecord;
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

public class Milestones
extends TableImpl<MilestonesRecord> {
    private static final long serialVersionUID = -595369799L;
    public static final Milestones MILESTONES = new Milestones();
    public final TableField<MilestonesRecord, Long> ID;
    public final TableField<MilestonesRecord, String> MILESTONE;
    public final TableField<MilestonesRecord, Timestamp> TIMESTAMP;
    public final TableField<MilestonesRecord, Timestamp> CREATED_AT;
    public final TableField<MilestonesRecord, Timestamp> UPDATED_AT;
    public final TableField<MilestonesRecord, Long> ORGANIZATION_ID;
    public final TableField<MilestonesRecord, Long> TRAVELER_ID;
    public final TableField<MilestonesRecord, Long> JOURNEY_ID;
    public final TableField<MilestonesRecord, String> TRANSACTION_ID;
    public final TableField<MilestonesRecord, String> RULE_ID;

    @Override
    public Class<MilestonesRecord> getRecordType() {
        return MilestonesRecord.class;
    }

    public Milestones() {
        this("Milestones", null);
    }

    public Milestones(String alias) {
        this(alias, MILESTONES);
    }

    private Milestones(String alias, Table<MilestonesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Milestones(String alias, Table<MilestonesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Milestones.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Milestones_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.MILESTONE = Milestones.createField("milestone", SQLDataType.VARCHAR.length(255), this, "");
        this.TIMESTAMP = Milestones.createField("timestamp", SQLDataType.TIMESTAMP, this, "");
        this.CREATED_AT = Milestones.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Milestones.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.ORGANIZATION_ID = Milestones.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.TRAVELER_ID = Milestones.createField("traveler_id", SQLDataType.BIGINT, this, "");
        this.JOURNEY_ID = Milestones.createField("journey_id", SQLDataType.BIGINT, this, "");
        this.TRANSACTION_ID = Milestones.createField("transaction_id", SQLDataType.CLOB, this, "");
        this.RULE_ID = Milestones.createField("rule_id", SQLDataType.CLOB, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<MilestonesRecord, Long> getIdentity() {
        return Keys.IDENTITY_MILESTONES;
    }

    @Override
    public UniqueKey<MilestonesRecord> getPrimaryKey() {
        return Keys.MILESTONES_PKEY;
    }

    @Override
    public List<UniqueKey<MilestonesRecord>> getKeys() {
        return Arrays.asList(Keys.MILESTONES_PKEY);
    }

    public Milestones as(String alias) {
        return new Milestones(alias, this);
    }

    public Milestones rename(String name) {
        return new Milestones(name, null);
    }
}

