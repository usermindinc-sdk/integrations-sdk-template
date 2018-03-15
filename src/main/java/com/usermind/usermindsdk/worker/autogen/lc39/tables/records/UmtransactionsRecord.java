/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Umtransactions;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row;
import org.jooq.Row7;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class UmtransactionsRecord
extends UpdatableRecordImpl<UmtransactionsRecord>
implements Record7<Long, String, Long, Boolean, Timestamp, Timestamp, String> {
    private static final long serialVersionUID = 2010577733L;

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

    public void setOrganizationId(Long value) {
        this.set(2, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(2);
    }

    public void setReplay(Boolean value) {
        this.set(3, value);
    }

    public Boolean getReplay() {
        return (Boolean)this.get(3);
    }

    public void setTimestamp(Timestamp value) {
        this.set(4, value);
    }

    public Timestamp getTimestamp() {
        return (Timestamp)this.get(4);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(5, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(5);
    }

    public void setEventId(String value) {
        this.set(6, value);
    }

    public String getEventId() {
        return (String)this.get(6);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row7<Long, String, Long, Boolean, Timestamp, Timestamp, String> fieldsRow() {
        return (Row7)super.fieldsRow();
    }

    @Override
    public Row7<Long, String, Long, Boolean, Timestamp, Timestamp, String> valuesRow() {
        return (Row7)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Umtransactions.UMTRANSACTIONS.ID;
    }

    @Override
    public Field<String> field2() {
        return Umtransactions.UMTRANSACTIONS.TRANSACTION_ID;
    }

    @Override
    public Field<Long> field3() {
        return Umtransactions.UMTRANSACTIONS.ORGANIZATION_ID;
    }

    @Override
    public Field<Boolean> field4() {
        return Umtransactions.UMTRANSACTIONS.REPLAY;
    }

    @Override
    public Field<Timestamp> field5() {
        return Umtransactions.UMTRANSACTIONS.TIMESTAMP;
    }

    @Override
    public Field<Timestamp> field6() {
        return Umtransactions.UMTRANSACTIONS.CREATED_AT;
    }

    @Override
    public Field<String> field7() {
        return Umtransactions.UMTRANSACTIONS.EVENT_ID;
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
    public Long value3() {
        return this.getOrganizationId();
    }

    @Override
    public Boolean value4() {
        return this.getReplay();
    }

    @Override
    public Timestamp value5() {
        return this.getTimestamp();
    }

    @Override
    public Timestamp value6() {
        return this.getCreatedAt();
    }

    @Override
    public String value7() {
        return this.getEventId();
    }

    public UmtransactionsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public UmtransactionsRecord value2(String value) {
        this.setTransactionId(value);
        return this;
    }

    public UmtransactionsRecord value3(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public UmtransactionsRecord value4(Boolean value) {
        this.setReplay(value);
        return this;
    }

    public UmtransactionsRecord value5(Timestamp value) {
        this.setTimestamp(value);
        return this;
    }

    public UmtransactionsRecord value6(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public UmtransactionsRecord value7(String value) {
        this.setEventId(value);
        return this;
    }

    public UmtransactionsRecord values(Long value1, String value2, Long value3, Boolean value4, Timestamp value5, Timestamp value6, String value7) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        return this;
    }

    public UmtransactionsRecord() {
        super(Umtransactions.UMTRANSACTIONS);
    }

    public UmtransactionsRecord(Long id, String transactionId, Long organizationId, Boolean replay, Timestamp timestamp, Timestamp createdAt, String eventId) {
        super(Umtransactions.UMTRANSACTIONS);
        this.set(0, id);
        this.set(1, transactionId);
        this.set(2, organizationId);
        this.set(3, replay);
        this.set(4, timestamp);
        this.set(5, createdAt);
        this.set(6, eventId);
    }
}

