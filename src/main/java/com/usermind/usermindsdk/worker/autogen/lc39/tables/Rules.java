/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.RulesRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.SmilestonesRecord;
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
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Rules
extends TableImpl<RulesRecord> {
    private static final long serialVersionUID = -1289474724L;
    public static final Rules RULES = new Rules();
    public final TableField<RulesRecord, Long> ID;
    public final TableField<RulesRecord, Long> ORGANIZATION_ID;
    public final TableField<RulesRecord, String> NAME;
    public final TableField<RulesRecord, String> RULE;
    public final TableField<RulesRecord, Long> VERSION;
    public final TableField<RulesRecord, Boolean> DELETED;
    public final TableField<RulesRecord, Timestamp> CREATED_AT;
    public final TableField<RulesRecord, Timestamp> UPDATED_AT;
    public final TableField<RulesRecord, String> RULE_ID;
    public final TableField<RulesRecord, Long> JOURNEY_ID;
    public final TableField<RulesRecord, Boolean> ENABLED;
    public final TableField<RulesRecord, Long> MILESTONE_ID;
    public final TableField<RulesRecord, Long> MILESTONE_VERSION;

    @Override
    public Class<RulesRecord> getRecordType() {
        return RulesRecord.class;
    }

    public Rules() {
        this("Rules", null);
    }

    public Rules(String alias) {
        this(alias, RULES);
    }

    private Rules(String alias, Table<RulesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Rules(String alias, Table<RulesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Rules.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"Rules_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ORGANIZATION_ID = Rules.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.NAME = Rules.createField("name", SQLDataType.VARCHAR.length(255).nullable(false).defaultValue(DSL.field("''::character varying", SQLDataType.VARCHAR)), this, "");
        this.RULE = Rules.createField("rule", SQLDataType.CLOB.nullable(false), this, "");
        this.VERSION = Rules.createField("version", SQLDataType.BIGINT.nullable(false), this, "");
        this.DELETED = Rules.createField("deleted", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.CREATED_AT = Rules.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Rules.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.RULE_ID = Rules.createField("rule_id", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.JOURNEY_ID = Rules.createField("journey_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.ENABLED = Rules.createField("enabled", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");
        this.MILESTONE_ID = Rules.createField("milestone_id", SQLDataType.BIGINT, this, "");
        this.MILESTONE_VERSION = Rules.createField("milestone_version", SQLDataType.BIGINT, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<RulesRecord, Long> getIdentity() {
        return Keys.IDENTITY_RULES;
    }

    @Override
    public UniqueKey<RulesRecord> getPrimaryKey() {
        return Keys.RULES_PKEY;
    }

    @Override
    public List<UniqueKey<RulesRecord>> getKeys() {
        return Arrays.asList(Keys.RULES_PKEY);
    }

    @Override
    public List<ForeignKey<RulesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.RULES__FK_RULESMILESTONEIDANDVERSION);
    }

    public Rules as(String alias) {
        return new Rules(alias, this);
    }

    public Rules rename(String name) {
        return new Rules(name, null);
    }
}

