/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Milestones;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row;
import org.jooq.Row10;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class MilestonesRecord
extends UpdatableRecordImpl<MilestonesRecord>
implements Record10<Long, String, Timestamp, Timestamp, Timestamp, Long, Long, Long, String, String> {
    private static final long serialVersionUID = -1418541552L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setMilestone(String value) {
        this.set(1, value);
    }

    public String getMilestone() {
        return (String)this.get(1);
    }

    public void setTimestamp(Timestamp value) {
        this.set(2, value);
    }

    public Timestamp getTimestamp() {
        return (Timestamp)this.get(2);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(3, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(3);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(4, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(4);
    }

    public void setOrganizationId(Long value) {
        this.set(5, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(5);
    }

    public void setTravelerId(Long value) {
        this.set(6, value);
    }

    public Long getTravelerId() {
        return (Long)this.get(6);
    }

    public void setJourneyId(Long value) {
        this.set(7, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(7);
    }

    public void setTransactionId(String value) {
        this.set(8, value);
    }

    public String getTransactionId() {
        return (String)this.get(8);
    }

    public void setRuleId(String value) {
        this.set(9, value);
    }

    public String getRuleId() {
        return (String)this.get(9);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row10<Long, String, Timestamp, Timestamp, Timestamp, Long, Long, Long, String, String> fieldsRow() {
        return (Row10)super.fieldsRow();
    }

    @Override
    public Row10<Long, String, Timestamp, Timestamp, Timestamp, Long, Long, Long, String, String> valuesRow() {
        return (Row10)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Milestones.MILESTONES.ID;
    }

    @Override
    public Field<String> field2() {
        return Milestones.MILESTONES.MILESTONE;
    }

    @Override
    public Field<Timestamp> field3() {
        return Milestones.MILESTONES.TIMESTAMP;
    }

    @Override
    public Field<Timestamp> field4() {
        return Milestones.MILESTONES.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field5() {
        return Milestones.MILESTONES.UPDATED_AT;
    }

    @Override
    public Field<Long> field6() {
        return Milestones.MILESTONES.ORGANIZATION_ID;
    }

    @Override
    public Field<Long> field7() {
        return Milestones.MILESTONES.TRAVELER_ID;
    }

    @Override
    public Field<Long> field8() {
        return Milestones.MILESTONES.JOURNEY_ID;
    }

    @Override
    public Field<String> field9() {
        return Milestones.MILESTONES.TRANSACTION_ID;
    }

    @Override
    public Field<String> field10() {
        return Milestones.MILESTONES.RULE_ID;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public String value2() {
        return this.getMilestone();
    }

    @Override
    public Timestamp value3() {
        return this.getTimestamp();
    }

    @Override
    public Timestamp value4() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value5() {
        return this.getUpdatedAt();
    }

    @Override
    public Long value6() {
        return this.getOrganizationId();
    }

    @Override
    public Long value7() {
        return this.getTravelerId();
    }

    @Override
    public Long value8() {
        return this.getJourneyId();
    }

    @Override
    public String value9() {
        return this.getTransactionId();
    }

    @Override
    public String value10() {
        return this.getRuleId();
    }

    public MilestonesRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public MilestonesRecord value2(String value) {
        this.setMilestone(value);
        return this;
    }

    public MilestonesRecord value3(Timestamp value) {
        this.setTimestamp(value);
        return this;
    }

    public MilestonesRecord value4(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public MilestonesRecord value5(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public MilestonesRecord value6(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public MilestonesRecord value7(Long value) {
        this.setTravelerId(value);
        return this;
    }

    public MilestonesRecord value8(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public MilestonesRecord value9(String value) {
        this.setTransactionId(value);
        return this;
    }

    public MilestonesRecord value10(String value) {
        this.setRuleId(value);
        return this;
    }

    public MilestonesRecord values(Long value1, String value2, Timestamp value3, Timestamp value4, Timestamp value5, Long value6, Long value7, Long value8, String value9, String value10) {
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
        return this;
    }

    public MilestonesRecord() {
        super(Milestones.MILESTONES);
    }

    public MilestonesRecord(Long id, String milestone, Timestamp timestamp, Timestamp createdAt, Timestamp updatedAt, Long organizationId, Long travelerId, Long journeyId, String transactionId, String ruleId) {
        super(Milestones.MILESTONES);
        this.set(0, id);
        this.set(1, milestone);
        this.set(2, timestamp);
        this.set(3, createdAt);
        this.set(4, updatedAt);
        this.set(5, organizationId);
        this.set(6, travelerId);
        this.set(7, journeyId);
        this.set(8, transactionId);
        this.set(9, ruleId);
    }
}

