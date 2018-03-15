/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Travelerstate;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row;
import org.jooq.Row11;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class TravelerstateRecord
extends UpdatableRecordImpl<TravelerstateRecord>
implements Record11<Long, String, Long, Timestamp, Long, Boolean, Timestamp, Timestamp, Long, String, String> {
    private static final long serialVersionUID = 1288763575L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setName(String value) {
        this.set(1, value);
    }

    public String getName() {
        return (String)this.get(1);
    }

    public void setOrganizationId(Long value) {
        this.set(2, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(2);
    }

    public void setTimestamp(Timestamp value) {
        this.set(3, value);
    }

    public Timestamp getTimestamp() {
        return (Timestamp)this.get(3);
    }

    public void setTravelerId(Long value) {
        this.set(4, value);
    }

    public Long getTravelerId() {
        return (Long)this.get(4);
    }

    public void setIsTerminal(Boolean value) {
        this.set(5, value);
    }

    public Boolean getIsTerminal() {
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

    public void setJourneyId(Long value) {
        this.set(8, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(8);
    }

    public void setTransactionId(String value) {
        this.set(9, value);
    }

    public String getTransactionId() {
        return (String)this.get(9);
    }

    public void setRuleId(String value) {
        this.set(10, value);
    }

    public String getRuleId() {
        return (String)this.get(10);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row11<Long, String, Long, Timestamp, Long, Boolean, Timestamp, Timestamp, Long, String, String> fieldsRow() {
        return (Row11)super.fieldsRow();
    }

    @Override
    public Row11<Long, String, Long, Timestamp, Long, Boolean, Timestamp, Timestamp, Long, String, String> valuesRow() {
        return (Row11)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Travelerstate.TRAVELERSTATE.ID;
    }

    @Override
    public Field<String> field2() {
        return Travelerstate.TRAVELERSTATE.NAME;
    }

    @Override
    public Field<Long> field3() {
        return Travelerstate.TRAVELERSTATE.ORGANIZATION_ID;
    }

    @Override
    public Field<Timestamp> field4() {
        return Travelerstate.TRAVELERSTATE.TIMESTAMP;
    }

    @Override
    public Field<Long> field5() {
        return Travelerstate.TRAVELERSTATE.TRAVELER_ID;
    }

    @Override
    public Field<Boolean> field6() {
        return Travelerstate.TRAVELERSTATE.IS_TERMINAL;
    }

    @Override
    public Field<Timestamp> field7() {
        return Travelerstate.TRAVELERSTATE.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field8() {
        return Travelerstate.TRAVELERSTATE.UPDATED_AT;
    }

    @Override
    public Field<Long> field9() {
        return Travelerstate.TRAVELERSTATE.JOURNEY_ID;
    }

    @Override
    public Field<String> field10() {
        return Travelerstate.TRAVELERSTATE.TRANSACTION_ID;
    }

    @Override
    public Field<String> field11() {
        return Travelerstate.TRAVELERSTATE.RULE_ID;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public String value2() {
        return this.getName();
    }

    @Override
    public Long value3() {
        return this.getOrganizationId();
    }

    @Override
    public Timestamp value4() {
        return this.getTimestamp();
    }

    @Override
    public Long value5() {
        return this.getTravelerId();
    }

    @Override
    public Boolean value6() {
        return this.getIsTerminal();
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
        return this.getJourneyId();
    }

    @Override
    public String value10() {
        return this.getTransactionId();
    }

    @Override
    public String value11() {
        return this.getRuleId();
    }

    public TravelerstateRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public TravelerstateRecord value2(String value) {
        this.setName(value);
        return this;
    }

    public TravelerstateRecord value3(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public TravelerstateRecord value4(Timestamp value) {
        this.setTimestamp(value);
        return this;
    }

    public TravelerstateRecord value5(Long value) {
        this.setTravelerId(value);
        return this;
    }

    public TravelerstateRecord value6(Boolean value) {
        this.setIsTerminal(value);
        return this;
    }

    public TravelerstateRecord value7(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public TravelerstateRecord value8(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public TravelerstateRecord value9(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public TravelerstateRecord value10(String value) {
        this.setTransactionId(value);
        return this;
    }

    public TravelerstateRecord value11(String value) {
        this.setRuleId(value);
        return this;
    }

    public TravelerstateRecord values(Long value1, String value2, Long value3, Timestamp value4, Long value5, Boolean value6, Timestamp value7, Timestamp value8, Long value9, String value10, String value11) {
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
        return this;
    }

    public TravelerstateRecord() {
        super(Travelerstate.TRAVELERSTATE);
    }

    public TravelerstateRecord(Long id, String name, Long organizationId, Timestamp timestamp, Long travelerId, Boolean isTerminal, Timestamp createdAt, Timestamp updatedAt, Long journeyId, String transactionId, String ruleId) {
        super(Travelerstate.TRAVELERSTATE);
        this.set(0, id);
        this.set(1, name);
        this.set(2, organizationId);
        this.set(3, timestamp);
        this.set(4, travelerId);
        this.set(5, isTerminal);
        this.set(6, createdAt);
        this.set(7, updatedAt);
        this.set(8, journeyId);
        this.set(9, transactionId);
        this.set(10, ruleId);
    }
}

