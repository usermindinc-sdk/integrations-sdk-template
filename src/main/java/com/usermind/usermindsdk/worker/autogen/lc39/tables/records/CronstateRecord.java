/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Cronstate;
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

public class CronstateRecord
extends UpdatableRecordImpl<CronstateRecord>
implements Record7<Long, String, Timestamp, Timestamp, Timestamp, Timestamp, Long> {
    private static final long serialVersionUID = -712660882L;

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

    public void setPrevFire(Timestamp value) {
        this.set(2, value);
    }

    public Timestamp getPrevFire() {
        return (Timestamp)this.get(2);
    }

    public void setNextFire(Timestamp value) {
        this.set(3, value);
    }

    public Timestamp getNextFire() {
        return (Timestamp)this.get(3);
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

    public void setVersion(Long value) {
        this.set(6, value);
    }

    public Long getVersion() {
        return (Long)this.get(6);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row7<Long, String, Timestamp, Timestamp, Timestamp, Timestamp, Long> fieldsRow() {
        return (Row7)super.fieldsRow();
    }

    @Override
    public Row7<Long, String, Timestamp, Timestamp, Timestamp, Timestamp, Long> valuesRow() {
        return (Row7)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Cronstate.CRONSTATE.ID;
    }

    @Override
    public Field<String> field2() {
        return Cronstate.CRONSTATE.KEY;
    }

    @Override
    public Field<Timestamp> field3() {
        return Cronstate.CRONSTATE.PREV_FIRE;
    }

    @Override
    public Field<Timestamp> field4() {
        return Cronstate.CRONSTATE.NEXT_FIRE;
    }

    @Override
    public Field<Timestamp> field5() {
        return Cronstate.CRONSTATE.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field6() {
        return Cronstate.CRONSTATE.UPDATED_AT;
    }

    @Override
    public Field<Long> field7() {
        return Cronstate.CRONSTATE.VERSION;
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
    public Timestamp value3() {
        return this.getPrevFire();
    }

    @Override
    public Timestamp value4() {
        return this.getNextFire();
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
    public Long value7() {
        return this.getVersion();
    }

    public CronstateRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public CronstateRecord value2(String value) {
        this.setKey(value);
        return this;
    }

    public CronstateRecord value3(Timestamp value) {
        this.setPrevFire(value);
        return this;
    }

    public CronstateRecord value4(Timestamp value) {
        this.setNextFire(value);
        return this;
    }

    public CronstateRecord value5(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public CronstateRecord value6(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public CronstateRecord value7(Long value) {
        this.setVersion(value);
        return this;
    }

    public CronstateRecord values(Long value1, String value2, Timestamp value3, Timestamp value4, Timestamp value5, Timestamp value6, Long value7) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        return this;
    }

    public CronstateRecord() {
        super(Cronstate.CRONSTATE);
    }

    public CronstateRecord(Long id, String key, Timestamp prevFire, Timestamp nextFire, Timestamp createdAt, Timestamp updatedAt, Long version) {
        super(Cronstate.CRONSTATE);
        this.set(0, id);
        this.set(1, key);
        this.set(2, prevFire);
        this.set(3, nextFire);
        this.set(4, createdAt);
        this.set(5, updatedAt);
        this.set(6, version);
    }
}

