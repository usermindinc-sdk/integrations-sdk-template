/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Travelerrulestate;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row;
import org.jooq.Row6;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class TravelerrulestateRecord
extends UpdatableRecordImpl<TravelerrulestateRecord>
implements Record6<Long, String, String, Timestamp, Timestamp, String> {
    private static final long serialVersionUID = 1515064072L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setKey(String value) {
        this.set(1, value);
    }

    public String getKey() {
        return (String)this.get(1);
    }

    public void setValue(String value) {
        this.set(2, value);
    }

    public String getValue() {
        return (String)this.get(2);
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

    public void setTransactionId(String value) {
        this.set(5, value);
    }

    public String getTransactionId() {
        return (String)this.get(5);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row6<Long, String, String, Timestamp, Timestamp, String> fieldsRow() {
        return (Row6)super.fieldsRow();
    }

    @Override
    public Row6<Long, String, String, Timestamp, Timestamp, String> valuesRow() {
        return (Row6)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Travelerrulestate.TRAVELERRULESTATE.ID;
    }

    @Override
    public Field<String> field2() {
        return Travelerrulestate.TRAVELERRULESTATE.KEY;
    }

    @Override
    public Field<String> field3() {
        return Travelerrulestate.TRAVELERRULESTATE.VALUE;
    }

    @Override
    public Field<Timestamp> field4() {
        return Travelerrulestate.TRAVELERRULESTATE.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field5() {
        return Travelerrulestate.TRAVELERRULESTATE.UPDATED_AT;
    }

    @Override
    public Field<String> field6() {
        return Travelerrulestate.TRAVELERRULESTATE.TRANSACTION_ID;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public String value2() {
        return this.getKey();
    }

    @Override
    public String value3() {
        return this.getValue();
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
    public String value6() {
        return this.getTransactionId();
    }

    public TravelerrulestateRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public TravelerrulestateRecord value2(String value) {
        this.setKey(value);
        return this;
    }

    public TravelerrulestateRecord value3(String value) {
        this.setValue(value);
        return this;
    }

    public TravelerrulestateRecord value4(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public TravelerrulestateRecord value5(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public TravelerrulestateRecord value6(String value) {
        this.setTransactionId(value);
        return this;
    }

    public TravelerrulestateRecord values(Long value1, String value2, String value3, Timestamp value4, Timestamp value5, String value6) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        return this;
    }

    public TravelerrulestateRecord() {
        super(Travelerrulestate.TRAVELERRULESTATE);
    }

    public TravelerrulestateRecord(Long id, String key, String value, Timestamp createdAt, Timestamp updatedAt, String transactionId) {
        super(Travelerrulestate.TRAVELERRULESTATE);
        this.set(0, id);
        this.set(1, key);
        this.set(2, value);
        this.set(3, createdAt);
        this.set(4, updatedAt);
        this.set(5, transactionId);
    }
}

