/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Rules;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record13;
import org.jooq.Row;
import org.jooq.Row13;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class RulesRecord
extends UpdatableRecordImpl<RulesRecord>
implements Record13<Long, Long, String, String, Long, Boolean, Timestamp, Timestamp, String, Long, Boolean, Long, Long> {
    private static final long serialVersionUID = -793269808L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setOrganizationId(Long value) {
        this.set(1, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(1);
    }

    public void setName(String value) {
        this.set(2, value);
    }

    public String getName() {
        return (String)this.get(2);
    }

    public void setRule(String value) {
        this.set(3, value);
    }

    public String getRule() {
        return (String)this.get(3);
    }

    public void setVersion(Long value) {
        this.set(4, value);
    }

    public Long getVersion() {
        return (Long)this.get(4);
    }

    public void setDeleted(Boolean value) {
        this.set(5, value);
    }

    public Boolean getDeleted() {
        return (Boolean)this.get(5);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(6, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(6);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(7, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(7);
    }

    public void setRuleId(String value) {
        this.set(8, value);
    }

    public String getRuleId() {
        return (String)this.get(8);
    }

    public void setJourneyId(Long value) {
        this.set(9, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(9);
    }

    public void setEnabled(Boolean value) {
        this.set(10, value);
    }

    public Boolean getEnabled() {
        return (Boolean)this.get(10);
    }

    public void setMilestoneId(Long value) {
        this.set(11, value);
    }

    public Long getMilestoneId() {
        return (Long)this.get(11);
    }

    public void setMilestoneVersion(Long value) {
        this.set(12, value);
    }

    public Long getMilestoneVersion() {
        return (Long)this.get(12);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row13<Long, Long, String, String, Long, Boolean, Timestamp, Timestamp, String, Long, Boolean, Long, Long> fieldsRow() {
        return (Row13)super.fieldsRow();
    }

    @Override
    public Row13<Long, Long, String, String, Long, Boolean, Timestamp, Timestamp, String, Long, Boolean, Long, Long> valuesRow() {
        return (Row13)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Rules.RULES.ID;
    }

    @Override
    public Field<Long> field2() {
        return Rules.RULES.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field3() {
        return Rules.RULES.NAME;
    }

    @Override
    public Field<String> field4() {
        return Rules.RULES.RULE;
    }

    @Override
    public Field<Long> field5() {
        return Rules.RULES.VERSION;
    }

    @Override
    public Field<Boolean> field6() {
        return Rules.RULES.DELETED;
    }

    @Override
    public Field<Timestamp> field7() {
        return Rules.RULES.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field8() {
        return Rules.RULES.UPDATED_AT;
    }

    @Override
    public Field<String> field9() {
        return Rules.RULES.RULE_ID;
    }

    @Override
    public Field<Long> field10() {
        return Rules.RULES.JOURNEY_ID;
    }

    @Override
    public Field<Boolean> field11() {
        return Rules.RULES.ENABLED;
    }

    @Override
    public Field<Long> field12() {
        return Rules.RULES.MILESTONE_ID;
    }

    @Override
    public Field<Long> field13() {
        return Rules.RULES.MILESTONE_VERSION;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public Long value2() {
        return this.getOrganizationId();
    }

    @Override
    public String value3() {
        return this.getName();
    }

    @Override
    public String value4() {
        return this.getRule();
    }

    @Override
    public Long value5() {
        return this.getVersion();
    }

    @Override
    public Boolean value6() {
        return this.getDeleted();
    }

    @Override
    public Timestamp value7() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value8() {
        return this.getUpdatedAt();
    }

    @Override
    public String value9() {
        return this.getRuleId();
    }

    @Override
    public Long value10() {
        return this.getJourneyId();
    }

    @Override
    public Boolean value11() {
        return this.getEnabled();
    }

    @Override
    public Long value12() {
        return this.getMilestoneId();
    }

    @Override
    public Long value13() {
        return this.getMilestoneVersion();
    }

    public RulesRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public RulesRecord value2(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public RulesRecord value3(String value) {
        this.setName(value);
        return this;
    }

    public RulesRecord value4(String value) {
        this.setRule(value);
        return this;
    }

    public RulesRecord value5(Long value) {
        this.setVersion(value);
        return this;
    }

    public RulesRecord value6(Boolean value) {
        this.setDeleted(value);
        return this;
    }

    public RulesRecord value7(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public RulesRecord value8(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public RulesRecord value9(String value) {
        this.setRuleId(value);
        return this;
    }

    public RulesRecord value10(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public RulesRecord value11(Boolean value) {
        this.setEnabled(value);
        return this;
    }

    public RulesRecord value12(Long value) {
        this.setMilestoneId(value);
        return this;
    }

    public RulesRecord value13(Long value) {
        this.setMilestoneVersion(value);
        return this;
    }

    public RulesRecord values(Long value1, Long value2, String value3, String value4, Long value5, Boolean value6, Timestamp value7, Timestamp value8, String value9, Long value10, Boolean value11, Long value12, Long value13) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        this.value8(value8);
        this.value9(value9);
        this.value10(value10);
        this.value11(value11);
        this.value12(value12);
        this.value13(value13);
        return this;
    }

    public RulesRecord() {
        super(Rules.RULES);
    }

    public RulesRecord(Long id, Long organizationId, String name, String rule, Long version, Boolean deleted, Timestamp createdAt, Timestamp updatedAt, String ruleId, Long journeyId, Boolean enabled, Long milestoneId, Long milestoneVersion) {
        super(Rules.RULES);
        this.set(0, id);
        this.set(1, organizationId);
        this.set(2, name);
        this.set(3, rule);
        this.set(4, version);
        this.set(5, deleted);
        this.set(6, createdAt);
        this.set(7, updatedAt);
        this.set(8, ruleId);
        this.set(9, journeyId);
        this.set(10, enabled);
        this.set(11, milestoneId);
        this.set(12, milestoneVersion);
    }
}

