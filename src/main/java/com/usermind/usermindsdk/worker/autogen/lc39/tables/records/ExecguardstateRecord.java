/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Execguardstate;
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

public class ExecguardstateRecord
extends UpdatableRecordImpl<ExecguardstateRecord>
implements Record7<Long, String, String, String, Timestamp, Timestamp, String> {
    private static final long serialVersionUID = 178017079L;

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

    public void setGroupId(String value) {
        this.set(3, value);
    }

    public String getGroupId() {
        return (String)this.get(3);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(4, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(4);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(5, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(5);
    }

    public void setTransactionId(String value) {
        this.set(6, value);
    }

    public String getTransactionId() {
        return (String)this.get(6);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row7<Long, String, String, String, Timestamp, Timestamp, String> fieldsRow() {
        return (Row7)super.fieldsRow();
    }

    @Override
    public Row7<Long, String, String, String, Timestamp, Timestamp, String> valuesRow() {
        return (Row7)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Execguardstate.EXECGUARDSTATE.ID;
    }

    @Override
    public Field<String> field2() {
        return Execguardstate.EXECGUARDSTATE.KEY;
    }

    @Override
    public Field<String> field3() {
        return Execguardstate.EXECGUARDSTATE.VALUE;
    }

    @Override
    public Field<String> field4() {
        return Execguardstate.EXECGUARDSTATE.GROUP_ID;
    }

    @Override
    public Field<Timestamp> field5() {
        return Execguardstate.EXECGUARDSTATE.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field6() {
        return Execguardstate.EXECGUARDSTATE.UPDATED_AT;
    }

    @Override
    public Field<String> field7() {
        return Execguardstate.EXECGUARDSTATE.TRANSACTION_ID;
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
    public String value4() {
        return this.getGroupId();
    }

    @Override
    public Timestamp value5() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value6() {
        return this.getUpdatedAt();
    }

    @Override
    public String value7() {
        return this.getTransactionId();
    }

    public ExecguardstateRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public ExecguardstateRecord value2(String value) {
        this.setKey(value);
        return this;
    }

    public ExecguardstateRecord value3(String value) {
        this.setValue(value);
        return this;
    }

    public ExecguardstateRecord value4(String value) {
        this.setGroupId(value);
        return this;
    }

    public ExecguardstateRecord value5(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public ExecguardstateRecord value6(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public ExecguardstateRecord value7(String value) {
        this.setTransactionId(value);
        return this;
    }

    public ExecguardstateRecord values(Long value1, String value2, String value3, String value4, Timestamp value5, Timestamp value6, String value7) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        return this;
    }

    public ExecguardstateRecord() {
        super(Execguardstate.EXECGUARDSTATE);
    }

    public ExecguardstateRecord(Long id, String key, String value, String groupId, Timestamp createdAt, Timestamp updatedAt, String transactionId) {
        super(Execguardstate.EXECGUARDSTATE);
        this.set(0, id);
        this.set(1, key);
        this.set(2, value);
        this.set(3, groupId);
        this.set(4, createdAt);
        this.set(5, updatedAt);
        this.set(6, transactionId);
    }
}

