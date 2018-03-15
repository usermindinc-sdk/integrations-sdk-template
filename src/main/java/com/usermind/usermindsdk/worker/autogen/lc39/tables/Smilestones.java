/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.SmilestonesRecord;
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

public class Smilestones
extends TableImpl<SmilestonesRecord> {
    private static final long serialVersionUID = -1082007316L;
    public static final Smilestones SMILESTONES = new Smilestones();
    public final TableField<SmilestonesRecord, Long> ID;
    public final TableField<SmilestonesRecord, Long> JOURNEY_ID;
    public final TableField<SmilestonesRecord, Long> ORGANIZATION_ID;
    public final TableField<SmilestonesRecord, Long> MILESTONE_ID;
    public final TableField<SmilestonesRecord, Long> VERSION;
    public final TableField<SmilestonesRecord, String> MILESTONE;
    public final TableField<SmilestonesRecord, Boolean> ENABLED;
    public final TableField<SmilestonesRecord, Boolean> DELETED;
    public final TableField<SmilestonesRecord, Timestamp> CREATED_AT;
    public final TableField<SmilestonesRecord, Timestamp> UPDATED_AT;
    public final TableField<SmilestonesRecord, String> NAME;
    public final TableField<SmilestonesRecord, String> DESCRIPTION;

    @Override
    public Class<SmilestonesRecord> getRecordType() {
        return SmilestonesRecord.class;
    }

    public Smilestones() {
        this("Smilestones", null);
    }

    public Smilestones(String alias) {
        this(alias, SMILESTONES);
    }

    private Smilestones(String alias, Table<SmilestonesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Smilestones(String alias, Table<SmilestonesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Smilestones.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Smilestones_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.JOURNEY_ID = Smilestones.createField("journey_id", SQLDataType.BIGINT, this, "");
        this.ORGANIZATION_ID = Smilestones.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.MILESTONE_ID = Smilestones.createField("milestone_id", SQLDataType.BIGINT, this, "");
        this.VERSION = Smilestones.createField("version", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("0", SQLDataType.BIGINT)), this, "");
        this.MILESTONE = Smilestones.createField("milestone", SQLDataType.CLOB.nullable(false), this, "");
        this.ENABLED = Smilestones.createField("enabled", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");
        this.DELETED = Smilestones.createField("deleted", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.CREATED_AT = Smilestones.createField("created_at", SQLDataType.TIMESTAMP, this, "");
        this.UPDATED_AT = Smilestones.createField("updated_at", SQLDataType.TIMESTAMP, this, "");
        this.NAME = Smilestones.createField("name", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.DESCRIPTION = Smilestones.createField("description", SQLDataType.CLOB, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<SmilestonesRecord, Long> getIdentity() {
        return Keys.IDENTITY_SMILESTONES;
    }

    @Override
    public UniqueKey<SmilestonesRecord> getPrimaryKey() {
        return Keys.SMILESTONES_PKEY;
    }

    @Override
    public List<UniqueKey<SmilestonesRecord>> getKeys() {
        return Arrays.asList(Keys.SMILESTONES_PKEY, Keys.SMILESTONES_MILESTONE_ID_VERSION_KEY);
    }

    public Smilestones as(String alias) {
        return new Smilestones(alias, this);
    }

    public Smilestones rename(String name) {
        return new Smilestones(name, null);
    }
}

