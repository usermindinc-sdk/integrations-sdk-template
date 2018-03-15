/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Smilestones;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record12;
import org.jooq.Row;
import org.jooq.Row12;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class SmilestonesRecord
extends UpdatableRecordImpl<SmilestonesRecord>
implements Record12<Long, Long, Long, Long, Long, String, Boolean, Boolean, Timestamp, Timestamp, String, String> {
    private static final long serialVersionUID = -556610747L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setJourneyId(Long value) {
        this.set(1, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(1);
    }

    public void setOrganizationId(Long value) {
        this.set(2, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(2);
    }

    public void setMilestoneId(Long value) {
        this.set(3, value);
    }

    public Long getMilestoneId() {
        return (Long)this.get(3);
    }

    public void setVersion(Long value) {
        this.set(4, value);
    }

    public Long getVersion() {
        return (Long)this.get(4);
    }

    public void setMilestone(String value) {
        this.set(5, value);
    }

    public String getMilestone() {
        return (String)this.get(5);
    }

    public void setEnabled(Boolean value) {
        this.set(6, value);
    }

    public Boolean getEnabled() {
        return (Boolean)this.get(6);
    }

    public void setDeleted(Boolean value) {
        this.set(7, value);
    }

    public Boolean getDeleted() {
        return (Boolean)this.get(7);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(8, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(8);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(9, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(9);
    }

    public void setName(String value) {
        this.set(10, value);
    }

    public String getName() {
        return (String)this.get(10);
    }

    public void setDescription(String value) {
        this.set(11, value);
    }

    public String getDescription() {
        return (String)this.get(11);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row12<Long, Long, Long, Long, Long, String, Boolean, Boolean, Timestamp, Timestamp, String, String> fieldsRow() {
        return (Row12)super.fieldsRow();
    }

    @Override
    public Row12<Long, Long, Long, Long, Long, String, Boolean, Boolean, Timestamp, Timestamp, String, String> valuesRow() {
        return (Row12)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Smilestones.SMILESTONES.ID;
    }

    @Override
    public Field<Long> field2() {
        return Smilestones.SMILESTONES.JOURNEY_ID;
    }

    @Override
    public Field<Long> field3() {
        return Smilestones.SMILESTONES.ORGANIZATION_ID;
    }

    @Override
    public Field<Long> field4() {
        return Smilestones.SMILESTONES.MILESTONE_ID;
    }

    @Override
    public Field<Long> field5() {
        return Smilestones.SMILESTONES.VERSION;
    }

    @Override
    public Field<String> field6() {
        return Smilestones.SMILESTONES.MILESTONE;
    }

    @Override
    public Field<Boolean> field7() {
        return Smilestones.SMILESTONES.ENABLED;
    }

    @Override
    public Field<Boolean> field8() {
        return Smilestones.SMILESTONES.DELETED;
    }

    @Override
    public Field<Timestamp> field9() {
        return Smilestones.SMILESTONES.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field10() {
        return Smilestones.SMILESTONES.UPDATED_AT;
    }

    @Override
    public Field<String> field11() {
        return Smilestones.SMILESTONES.NAME;
    }

    @Override
    public Field<String> field12() {
        return Smilestones.SMILESTONES.DESCRIPTION;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public Long value2() {
        return this.getJourneyId();
    }

    @Override
    public Long value3() {
        return this.getOrganizationId();
    }

    @Override
    public Long value4() {
        return this.getMilestoneId();
    }

    @Override
    public Long value5() {
        return this.getVersion();
    }

    @Override
    public String value6() {
        return this.getMilestone();
    }

    @Override
    public Boolean value7() {
        return this.getEnabled();
    }

    @Override
    public Boolean value8() {
        return this.getDeleted();
    }

    @Override
    public Timestamp value9() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value10() {
        return this.getUpdatedAt();
    }

    @Override
    public String value11() {
        return this.getName();
    }

    @Override
    public String value12() {
        return this.getDescription();
    }

    public SmilestonesRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public SmilestonesRecord value2(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public SmilestonesRecord value3(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public SmilestonesRecord value4(Long value) {
        this.setMilestoneId(value);
        return this;
    }

    public SmilestonesRecord value5(Long value) {
        this.setVersion(value);
        return this;
    }

    public SmilestonesRecord value6(String value) {
        this.setMilestone(value);
        return this;
    }

    public SmilestonesRecord value7(Boolean value) {
        this.setEnabled(value);
        return this;
    }

    public SmilestonesRecord value8(Boolean value) {
        this.setDeleted(value);
        return this;
    }

    public SmilestonesRecord value9(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public SmilestonesRecord value10(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public SmilestonesRecord value11(String value) {
        this.setName(value);
        return this;
    }

    public SmilestonesRecord value12(String value) {
        this.setDescription(value);
        return this;
    }

    public SmilestonesRecord values(Long value1, Long value2, Long value3, Long value4, Long value5, String value6, Boolean value7, Boolean value8, Timestamp value9, Timestamp value10, String value11, String value12) {
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
        return this;
    }

    public SmilestonesRecord() {
        super(Smilestones.SMILESTONES);
    }

    public SmilestonesRecord(Long id, Long journeyId, Long organizationId, Long milestoneId, Long version, String milestone, Boolean enabled, Boolean deleted, Timestamp createdAt, Timestamp updatedAt, String name, String description) {
        super(Smilestones.SMILESTONES);
        this.set(0, id);
        this.set(1, journeyId);
        this.set(2, organizationId);
        this.set(3, milestoneId);
        this.set(4, version);
        this.set(5, milestone);
        this.set(6, enabled);
        this.set(7, deleted);
        this.set(8, createdAt);
        this.set(9, updatedAt);
        this.set(10, name);
        this.set(11, description);
    }
}

