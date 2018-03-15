/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Tickschedules;
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

public class TickschedulesRecord
extends UpdatableRecordImpl<TickschedulesRecord>
implements Record10<Long, Long, Long, Long, Long, Long, Long, Boolean, String, Timestamp> {
    private static final long serialVersionUID = 1821718366L;

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

    public void setJourneyId(Long value) {
        this.set(2, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(2);
    }

    public void setRuleId(Long value) {
        this.set(3, value);
    }

    public Long getRuleId() {
        return (Long)this.get(3);
    }

    public void setTravelerId(Long value) {
        this.set(4, value);
    }

    public Long getTravelerId() {
        return (Long)this.get(4);
    }

    public void setVersion(Long value) {
        this.set(5, value);
    }

    public Long getVersion() {
        return (Long)this.get(5);
    }

    public void setNextTick(Long value) {
        this.set(6, value);
    }

    public Long getNextTick() {
        return (Long)this.get(6);
    }

    public void setIsDeleted(Boolean value) {
        this.set(7, value);
    }

    public Boolean getIsDeleted() {
        return (Boolean)this.get(7);
    }

    public void setDeletedReason(String value) {
        this.set(8, value);
    }

    public String getDeletedReason() {
        return (String)this.get(8);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(9, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(9);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row10<Long, Long, Long, Long, Long, Long, Long, Boolean, String, Timestamp> fieldsRow() {
        return (Row10)super.fieldsRow();
    }

    @Override
    public Row10<Long, Long, Long, Long, Long, Long, Long, Boolean, String, Timestamp> valuesRow() {
        return (Row10)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Tickschedules.TICKSCHEDULES.ID;
    }

    @Override
    public Field<Long> field2() {
        return Tickschedules.TICKSCHEDULES.ORGANIZATION_ID;
    }

    @Override
    public Field<Long> field3() {
        return Tickschedules.TICKSCHEDULES.JOURNEY_ID;
    }

    @Override
    public Field<Long> field4() {
        return Tickschedules.TICKSCHEDULES.RULE_ID;
    }

    @Override
    public Field<Long> field5() {
        return Tickschedules.TICKSCHEDULES.TRAVELER_ID;
    }

    @Override
    public Field<Long> field6() {
        return Tickschedules.TICKSCHEDULES.VERSION;
    }

    @Override
    public Field<Long> field7() {
        return Tickschedules.TICKSCHEDULES.NEXT_TICK;
    }

    @Override
    public Field<Boolean> field8() {
        return Tickschedules.TICKSCHEDULES.IS_DELETED;
    }

    @Override
    public Field<String> field9() {
        return Tickschedules.TICKSCHEDULES.DELETED_REASON;
    }

    @Override
    public Field<Timestamp> field10() {
        return Tickschedules.TICKSCHEDULES.CREATED_AT;
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
    public Long value3() {
        return this.getJourneyId();
    }

    @Override
    public Long value4() {
        return this.getRuleId();
    }

    @Override
    public Long value5() {
        return this.getTravelerId();
    }

    @Override
    public Long value6() {
        return this.getVersion();
    }

    @Override
    public Long value7() {
        return this.getNextTick();
    }

    @Override
    public Boolean value8() {
        return this.getIsDeleted();
    }

    @Override
    public String value9() {
        return this.getDeletedReason();
    }

    @Override
    public Timestamp value10() {
        return this.getCreatedAt();
    }

    public TickschedulesRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public TickschedulesRecord value2(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public TickschedulesRecord value3(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public TickschedulesRecord value4(Long value) {
        this.setRuleId(value);
        return this;
    }

    public TickschedulesRecord value5(Long value) {
        this.setTravelerId(value);
        return this;
    }

    public TickschedulesRecord value6(Long value) {
        this.setVersion(value);
        return this;
    }

    public TickschedulesRecord value7(Long value) {
        this.setNextTick(value);
        return this;
    }

    public TickschedulesRecord value8(Boolean value) {
        this.setIsDeleted(value);
        return this;
    }

    public TickschedulesRecord value9(String value) {
        this.setDeletedReason(value);
        return this;
    }

    public TickschedulesRecord value10(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public TickschedulesRecord values(Long value1, Long value2, Long value3, Long value4, Long value5, Long value6, Long value7, Boolean value8, String value9, Timestamp value10) {
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

    public TickschedulesRecord() {
        super(Tickschedules.TICKSCHEDULES);
    }

    public TickschedulesRecord(Long id, Long organizationId, Long journeyId, Long ruleId, Long travelerId, Long version, Long nextTick, Boolean isDeleted, String deletedReason, Timestamp createdAt) {
        super(Tickschedules.TICKSCHEDULES);
        this.set(0, id);
        this.set(1, organizationId);
        this.set(2, journeyId);
        this.set(3, ruleId);
        this.set(4, travelerId);
        this.set(5, version);
        this.set(6, nextTick);
        this.set(7, isDeleted);
        this.set(8, deletedReason);
        this.set(9, createdAt);
    }
}

