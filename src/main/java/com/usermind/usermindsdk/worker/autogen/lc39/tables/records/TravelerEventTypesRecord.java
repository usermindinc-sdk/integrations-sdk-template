/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.TravelerEventTypes;
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

public class TravelerEventTypesRecord
extends UpdatableRecordImpl<TravelerEventTypesRecord>
implements Record6<Long, String, String, Timestamp, Timestamp, Long> {
    private static final long serialVersionUID = -1336386384L;

    public void setTravelerEventTypeId(Long value) {
        this.set(0, value);
    }

    public Long getTravelerEventTypeId() {
        return (Long)this.get(0);
    }

    public void setTravelerEventTypeName(String value) {
        this.set(1, value);
    }

    public String getTravelerEventTypeName() {
        return (String)this.get(1);
    }

    public void setDescription(String value) {
        this.set(2, value);
    }

    public String getDescription() {
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

    public void setRvn(Long value) {
        this.set(5, value);
    }

    public Long getRvn() {
        return (Long)this.get(5);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row6<Long, String, String, Timestamp, Timestamp, Long> fieldsRow() {
        return (Row6)super.fieldsRow();
    }

    @Override
    public Row6<Long, String, String, Timestamp, Timestamp, Long> valuesRow() {
        return (Row6)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return TravelerEventTypes.TRAVELER_EVENT_TYPES.TRAVELER_EVENT_TYPE_ID;
    }

    @Override
    public Field<String> field2() {
        return TravelerEventTypes.TRAVELER_EVENT_TYPES.TRAVELER_EVENT_TYPE_NAME;
    }

    @Override
    public Field<String> field3() {
        return TravelerEventTypes.TRAVELER_EVENT_TYPES.DESCRIPTION;
    }

    @Override
    public Field<Timestamp> field4() {
        return TravelerEventTypes.TRAVELER_EVENT_TYPES.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field5() {
        return TravelerEventTypes.TRAVELER_EVENT_TYPES.UPDATED_AT;
    }

    @Override
    public Field<Long> field6() {
        return TravelerEventTypes.TRAVELER_EVENT_TYPES.RVN;
    }

    @Override
    public Long value1() {
        return this.getTravelerEventTypeId();
    }

    @Override
    public String value2() {
        return this.getTravelerEventTypeName();
    }

    @Override
    public String value3() {
        return this.getDescription();
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
        return this.getRvn();
    }

    public TravelerEventTypesRecord value1(Long value) {
        this.setTravelerEventTypeId(value);
        return this;
    }

    public TravelerEventTypesRecord value2(String value) {
        this.setTravelerEventTypeName(value);
        return this;
    }

    public TravelerEventTypesRecord value3(String value) {
        this.setDescription(value);
        return this;
    }

    public TravelerEventTypesRecord value4(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public TravelerEventTypesRecord value5(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public TravelerEventTypesRecord value6(Long value) {
        this.setRvn(value);
        return this;
    }

    public TravelerEventTypesRecord values(Long value1, String value2, String value3, Timestamp value4, Timestamp value5, Long value6) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        return this;
    }

    public TravelerEventTypesRecord() {
        super(TravelerEventTypes.TRAVELER_EVENT_TYPES);
    }

    public TravelerEventTypesRecord(Long travelerEventTypeId, String travelerEventTypeName, String description, Timestamp createdAt, Timestamp updatedAt, Long rvn) {
        super(TravelerEventTypes.TRAVELER_EVENT_TYPES);
        this.set(0, travelerEventTypeId);
        this.set(1, travelerEventTypeName);
        this.set(2, description);
        this.set(3, createdAt);
        this.set(4, updatedAt);
        this.set(5, rvn);
    }
}

