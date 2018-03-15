/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.TravelerEventStates;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row;
import org.jooq.Row8;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class TravelerEventStatesRecord
extends UpdatableRecordImpl<TravelerEventStatesRecord>
implements Record8<Long, String, Boolean, Boolean, String, Timestamp, Timestamp, Long> {
    private static final long serialVersionUID = -1537758037L;

    public void setTravelerEventStateId(Long value) {
        this.set(0, value);
    }

    public Long getTravelerEventStateId() {
        return (Long)this.get(0);
    }

    public void setTravelerEventStateName(String value) {
        this.set(1, value);
    }

    public String getTravelerEventStateName() {
        return (String)this.get(1);
    }

    public void setIsTerminal(Boolean value) {
        this.set(2, value);
    }

    public Boolean getIsTerminal() {
        return (Boolean)this.get(2);
    }

    public void setIsExecutable(Boolean value) {
        this.set(3, value);
    }

    public Boolean getIsExecutable() {
        return (Boolean)this.get(3);
    }

    public void setDescription(String value) {
        this.set(4, value);
    }

    public String getDescription() {
        return (String)this.get(4);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(5, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(5);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(6, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(6);
    }

    public void setRvn(Long value) {
        this.set(7, value);
    }

    public Long getRvn() {
        return (Long)this.get(7);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row8<Long, String, Boolean, Boolean, String, Timestamp, Timestamp, Long> fieldsRow() {
        return (Row8)super.fieldsRow();
    }

    @Override
    public Row8<Long, String, Boolean, Boolean, String, Timestamp, Timestamp, Long> valuesRow() {
        return (Row8)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return TravelerEventStates.TRAVELER_EVENT_STATES.TRAVELER_EVENT_STATE_ID;
    }

    @Override
    public Field<String> field2() {
        return TravelerEventStates.TRAVELER_EVENT_STATES.TRAVELER_EVENT_STATE_NAME;
    }

    @Override
    public Field<Boolean> field3() {
        return TravelerEventStates.TRAVELER_EVENT_STATES.IS_TERMINAL;
    }

    @Override
    public Field<Boolean> field4() {
        return TravelerEventStates.TRAVELER_EVENT_STATES.IS_EXECUTABLE;
    }

    @Override
    public Field<String> field5() {
        return TravelerEventStates.TRAVELER_EVENT_STATES.DESCRIPTION;
    }

    @Override
    public Field<Timestamp> field6() {
        return TravelerEventStates.TRAVELER_EVENT_STATES.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field7() {
        return TravelerEventStates.TRAVELER_EVENT_STATES.UPDATED_AT;
    }

    @Override
    public Field<Long> field8() {
        return TravelerEventStates.TRAVELER_EVENT_STATES.RVN;
    }

    @Override
    public Long value1() {
        return this.getTravelerEventStateId();
    }

    @Override
    public String value2() {
        return this.getTravelerEventStateName();
    }

    @Override
    public Boolean value3() {
        return this.getIsTerminal();
    }

    @Override
    public Boolean value4() {
        return this.getIsExecutable();
    }

    @Override
    public String value5() {
        return this.getDescription();
    }

    @Override
    public Timestamp value6() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value7() {
        return this.getUpdatedAt();
    }

    @Override
    public Long value8() {
        return this.getRvn();
    }

    public TravelerEventStatesRecord value1(Long value) {
        this.setTravelerEventStateId(value);
        return this;
    }

    public TravelerEventStatesRecord value2(String value) {
        this.setTravelerEventStateName(value);
        return this;
    }

    public TravelerEventStatesRecord value3(Boolean value) {
        this.setIsTerminal(value);
        return this;
    }

    public TravelerEventStatesRecord value4(Boolean value) {
        this.setIsExecutable(value);
        return this;
    }

    public TravelerEventStatesRecord value5(String value) {
        this.setDescription(value);
        return this;
    }

    public TravelerEventStatesRecord value6(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public TravelerEventStatesRecord value7(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public TravelerEventStatesRecord value8(Long value) {
        this.setRvn(value);
        return this;
    }

    public TravelerEventStatesRecord values(Long value1, String value2, Boolean value3, Boolean value4, String value5, Timestamp value6, Timestamp value7, Long value8) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        this.value8(value8);
        return this;
    }

    public TravelerEventStatesRecord() {
        super(TravelerEventStates.TRAVELER_EVENT_STATES);
    }

    public TravelerEventStatesRecord(Long travelerEventStateId, String travelerEventStateName, Boolean isTerminal, Boolean isExecutable, String description, Timestamp createdAt, Timestamp updatedAt, Long rvn) {
        super(TravelerEventStates.TRAVELER_EVENT_STATES);
        this.set(0, travelerEventStateId);
        this.set(1, travelerEventStateName);
        this.set(2, isTerminal);
        this.set(3, isExecutable);
        this.set(4, description);
        this.set(5, createdAt);
        this.set(6, updatedAt);
        this.set(7, rvn);
    }
}

