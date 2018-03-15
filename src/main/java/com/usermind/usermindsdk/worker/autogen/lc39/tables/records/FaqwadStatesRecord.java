/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.FaqwadStates;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row;
import org.jooq.Row9;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class FaqwadStatesRecord
extends UpdatableRecordImpl<FaqwadStatesRecord>
implements Record9<Long, String, Boolean, Boolean, Long, String, Timestamp, Timestamp, Long> {
    private static final long serialVersionUID = 773302942L;

    public void setFaqwadStateId(Long value) {
        this.set(0, value);
    }

    public Long getFaqwadStateId() {
        return (Long)this.get(0);
    }

    public void setFaqwadStateName(String value) {
        this.set(1, value);
    }

    public String getFaqwadStateName() {
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

    public void setPriorityModifier(Long value) {
        this.set(4, value);
    }

    public Long getPriorityModifier() {
        return (Long)this.get(4);
    }

    public void setDescription(String value) {
        this.set(5, value);
    }

    public String getDescription() {
        return (String)this.get(5);
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

    public void setRvn(Long value) {
        this.set(8, value);
    }

    public Long getRvn() {
        return (Long)this.get(8);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row9<Long, String, Boolean, Boolean, Long, String, Timestamp, Timestamp, Long> fieldsRow() {
        return (Row9)super.fieldsRow();
    }

    @Override
    public Row9<Long, String, Boolean, Boolean, Long, String, Timestamp, Timestamp, Long> valuesRow() {
        return (Row9)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return FaqwadStates.FAQWAD_STATES.FAQWAD_STATE_ID;
    }

    @Override
    public Field<String> field2() {
        return FaqwadStates.FAQWAD_STATES.FAQWAD_STATE_NAME;
    }

    @Override
    public Field<Boolean> field3() {
        return FaqwadStates.FAQWAD_STATES.IS_TERMINAL;
    }

    @Override
    public Field<Boolean> field4() {
        return FaqwadStates.FAQWAD_STATES.IS_EXECUTABLE;
    }

    @Override
    public Field<Long> field5() {
        return FaqwadStates.FAQWAD_STATES.PRIORITY_MODIFIER;
    }

    @Override
    public Field<String> field6() {
        return FaqwadStates.FAQWAD_STATES.DESCRIPTION;
    }

    @Override
    public Field<Timestamp> field7() {
        return FaqwadStates.FAQWAD_STATES.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field8() {
        return FaqwadStates.FAQWAD_STATES.UPDATED_AT;
    }

    @Override
    public Field<Long> field9() {
        return FaqwadStates.FAQWAD_STATES.RVN;
    }

    @Override
    public Long value1() {
        return this.getFaqwadStateId();
    }

    @Override
    public String value2() {
        return this.getFaqwadStateName();
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
    public Long value5() {
        return this.getPriorityModifier();
    }

    @Override
    public String value6() {
        return this.getDescription();
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
    public Long value9() {
        return this.getRvn();
    }

    public FaqwadStatesRecord value1(Long value) {
        this.setFaqwadStateId(value);
        return this;
    }

    public FaqwadStatesRecord value2(String value) {
        this.setFaqwadStateName(value);
        return this;
    }

    public FaqwadStatesRecord value3(Boolean value) {
        this.setIsTerminal(value);
        return this;
    }

    public FaqwadStatesRecord value4(Boolean value) {
        this.setIsExecutable(value);
        return this;
    }

    public FaqwadStatesRecord value5(Long value) {
        this.setPriorityModifier(value);
        return this;
    }

    public FaqwadStatesRecord value6(String value) {
        this.setDescription(value);
        return this;
    }

    public FaqwadStatesRecord value7(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public FaqwadStatesRecord value8(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public FaqwadStatesRecord value9(Long value) {
        this.setRvn(value);
        return this;
    }

    public FaqwadStatesRecord values(Long value1, String value2, Boolean value3, Boolean value4, Long value5, String value6, Timestamp value7, Timestamp value8, Long value9) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        this.value8(value8);
        this.value9(value9);
        return this;
    }

    public FaqwadStatesRecord() {
        super(FaqwadStates.FAQWAD_STATES);
    }

    public FaqwadStatesRecord(Long faqwadStateId, String faqwadStateName, Boolean isTerminal, Boolean isExecutable, Long priorityModifier, String description, Timestamp createdAt, Timestamp updatedAt, Long rvn) {
        super(FaqwadStates.FAQWAD_STATES);
        this.set(0, faqwadStateId);
        this.set(1, faqwadStateName);
        this.set(2, isTerminal);
        this.set(3, isExecutable);
        this.set(4, priorityModifier);
        this.set(5, description);
        this.set(6, createdAt);
        this.set(7, updatedAt);
        this.set(8, rvn);
    }
}

