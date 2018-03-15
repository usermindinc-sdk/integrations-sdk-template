/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Actionjournal;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record16;
import org.jooq.Row;
import org.jooq.Row16;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class ActionjournalRecord
extends UpdatableRecordImpl<ActionjournalRecord>
implements Record16<Long, String, String, Long, String, Boolean, Long, Long, Timestamp, Timestamp, Timestamp, String, Long, Long, String, Integer> {
    private static final long serialVersionUID = 391671076L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setTransactionId(String value) {
        this.set(1, value);
    }

    public String getTransactionId() {
        return (String)this.get(1);
    }

    public void setActionType(String value) {
        this.set(2, value);
    }

    public String getActionType() {
        return (String)this.get(2);
    }

    public void setOrganizationId(Long value) {
        this.set(3, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(3);
    }

    public void setPayload(String value) {
        this.set(4, value);
    }

    public String getPayload() {
        return (String)this.get(4);
    }

    public void setReplay(Boolean value) {
        this.set(5, value);
    }

    public Boolean getReplay() {
        return (Boolean)this.get(5);
    }

    public void setJourneyId(Long value) {
        this.set(6, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(6);
    }

    public void setRuleVersion(Long value) {
        this.set(7, value);
    }

    public Long getRuleVersion() {
        return (Long)this.get(7);
    }

    public void setServicedAt(Timestamp value) {
        this.set(8, value);
    }

    public Timestamp getServicedAt() {
        return (Timestamp)this.get(8);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(9, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(9);
    }

    public void setTimestamp(Timestamp value) {
        this.set(10, value);
    }

    public Timestamp getTimestamp() {
        return (Timestamp)this.get(10);
    }

    public void setRuleId(String value) {
        this.set(11, value);
    }

    public String getRuleId() {
        return (String)this.get(11);
    }

    public void setConnectionId(Long value) {
        this.set(12, value);
    }

    public Long getConnectionId() {
        return (Long)this.get(12);
    }

    public void setTravelerId(Long value) {
        this.set(13, value);
    }

    public Long getTravelerId() {
        return (Long)this.get(13);
    }

    public void setEventId(String value) {
        this.set(14, value);
    }

    public String getEventId() {
        return (String)this.get(14);
    }

    public void setStatus(Integer value) {
        this.set(15, value);
    }

    public Integer getStatus() {
        return (Integer)this.get(15);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row16<Long, String, String, Long, String, Boolean, Long, Long, Timestamp, Timestamp, Timestamp, String, Long, Long, String, Integer> fieldsRow() {
        return (Row16)super.fieldsRow();
    }

    @Override
    public Row16<Long, String, String, Long, String, Boolean, Long, Long, Timestamp, Timestamp, Timestamp, String, Long, Long, String, Integer> valuesRow() {
        return (Row16)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Actionjournal.ACTIONJOURNAL.ID;
    }

    @Override
    public Field<String> field2() {
        return Actionjournal.ACTIONJOURNAL.TRANSACTION_ID;
    }

    @Override
    public Field<String> field3() {
        return Actionjournal.ACTIONJOURNAL.ACTION_TYPE;
    }

    @Override
    public Field<Long> field4() {
        return Actionjournal.ACTIONJOURNAL.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field5() {
        return Actionjournal.ACTIONJOURNAL.PAYLOAD;
    }

    @Override
    public Field<Boolean> field6() {
        return Actionjournal.ACTIONJOURNAL.REPLAY;
    }

    @Override
    public Field<Long> field7() {
        return Actionjournal.ACTIONJOURNAL.JOURNEY_ID;
    }

    @Override
    public Field<Long> field8() {
        return Actionjournal.ACTIONJOURNAL.RULE_VERSION;
    }

    @Override
    public Field<Timestamp> field9() {
        return Actionjournal.ACTIONJOURNAL.SERVICED_AT;
    }

    @Override
    public Field<Timestamp> field10() {
        return Actionjournal.ACTIONJOURNAL.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field11() {
        return Actionjournal.ACTIONJOURNAL.TIMESTAMP;
    }

    @Override
    public Field<String> field12() {
        return Actionjournal.ACTIONJOURNAL.RULE_ID;
    }

    @Override
    public Field<Long> field13() {
        return Actionjournal.ACTIONJOURNAL.CONNECTION_ID;
    }

    @Override
    public Field<Long> field14() {
        return Actionjournal.ACTIONJOURNAL.TRAVELER_ID;
    }

    @Override
    public Field<String> field15() {
        return Actionjournal.ACTIONJOURNAL.EVENT_ID;
    }

    @Override
    public Field<Integer> field16() {
        return Actionjournal.ACTIONJOURNAL.STATUS;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public String value2() {
        return this.getTransactionId();
    }

    @Override
    public String value3() {
        return this.getActionType();
    }

    @Override
    public Long value4() {
        return this.getOrganizationId();
    }

    @Override
    public String value5() {
        return this.getPayload();
    }

    @Override
    public Boolean value6() {
        return this.getReplay();
    }

    @Override
    public Long value7() {
        return this.getJourneyId();
    }

    @Override
    public Long value8() {
        return this.getRuleVersion();
    }

    @Override
    public Timestamp value9() {
        return this.getServicedAt();
    }

    @Override
    public Timestamp value10() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value11() {
        return this.getTimestamp();
    }

    @Override
    public String value12() {
        return this.getRuleId();
    }

    @Override
    public Long value13() {
        return this.getConnectionId();
    }

    @Override
    public Long value14() {
        return this.getTravelerId();
    }

    @Override
    public String value15() {
        return this.getEventId();
    }

    @Override
    public Integer value16() {
        return this.getStatus();
    }

    public ActionjournalRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public ActionjournalRecord value2(String value) {
        this.setTransactionId(value);
        return this;
    }

    public ActionjournalRecord value3(String value) {
        this.setActionType(value);
        return this;
    }

    public ActionjournalRecord value4(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public ActionjournalRecord value5(String value) {
        this.setPayload(value);
        return this;
    }

    public ActionjournalRecord value6(Boolean value) {
        this.setReplay(value);
        return this;
    }

    public ActionjournalRecord value7(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public ActionjournalRecord value8(Long value) {
        this.setRuleVersion(value);
        return this;
    }

    public ActionjournalRecord value9(Timestamp value) {
        this.setServicedAt(value);
        return this;
    }

    public ActionjournalRecord value10(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public ActionjournalRecord value11(Timestamp value) {
        this.setTimestamp(value);
        return this;
    }

    public ActionjournalRecord value12(String value) {
        this.setRuleId(value);
        return this;
    }

    public ActionjournalRecord value13(Long value) {
        this.setConnectionId(value);
        return this;
    }

    public ActionjournalRecord value14(Long value) {
        this.setTravelerId(value);
        return this;
    }

    public ActionjournalRecord value15(String value) {
        this.setEventId(value);
        return this;
    }

    public ActionjournalRecord value16(Integer value) {
        this.setStatus(value);
        return this;
    }

    public ActionjournalRecord values(Long value1, String value2, String value3, Long value4, String value5, Boolean value6, Long value7, Long value8, Timestamp value9, Timestamp value10, Timestamp value11, String value12, Long value13, Long value14, String value15, Integer value16) {
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
        this.value14(value14);
        this.value15(value15);
        this.value16(value16);
        return this;
    }

    public ActionjournalRecord() {
        super(Actionjournal.ACTIONJOURNAL);
    }

    public ActionjournalRecord(Long id, String transactionId, String actionType, Long organizationId, String payload, Boolean replay, Long journeyId, Long ruleVersion, Timestamp servicedAt, Timestamp createdAt, Timestamp timestamp, String ruleId, Long connectionId, Long travelerId, String eventId, Integer status) {
        super(Actionjournal.ACTIONJOURNAL);
        this.set(0, id);
        this.set(1, transactionId);
        this.set(2, actionType);
        this.set(3, organizationId);
        this.set(4, payload);
        this.set(5, replay);
        this.set(6, journeyId);
        this.set(7, ruleVersion);
        this.set(8, servicedAt);
        this.set(9, createdAt);
        this.set(10, timestamp);
        this.set(11, ruleId);
        this.set(12, connectionId);
        this.set(13, travelerId);
        this.set(14, eventId);
        this.set(15, status);
    }
}

