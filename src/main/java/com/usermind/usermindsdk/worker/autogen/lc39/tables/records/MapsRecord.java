/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Maps;
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

public class MapsRecord
extends UpdatableRecordImpl<MapsRecord>
implements Record7<Long, String, Timestamp, Timestamp, Long, Boolean, Boolean> {
    private static final long serialVersionUID = 703571412L;

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

    public void setCreatedAt(Timestamp value) {
        this.set(2, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(2);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(3, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(3);
    }

    public void setOrganizationId(Long value) {
        this.set(4, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(4);
    }

    public void setDeleted(Boolean value) {
        this.set(5, value);
    }

    public Boolean getDeleted() {
        return (Boolean)this.get(5);
    }

    public void setEditable(Boolean value) {
        this.set(6, value);
    }

    public Boolean getEditable() {
        return (Boolean)this.get(6);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row7<Long, String, Timestamp, Timestamp, Long, Boolean, Boolean> fieldsRow() {
        return (Row7)super.fieldsRow();
    }

    @Override
    public Row7<Long, String, Timestamp, Timestamp, Long, Boolean, Boolean> valuesRow() {
        return (Row7)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Maps.MAPS.ID;
    }

    @Override
    public Field<String> field2() {
        return Maps.MAPS.NAME;
    }

    @Override
    public Field<Timestamp> field3() {
        return Maps.MAPS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field4() {
        return Maps.MAPS.UPDATED_AT;
    }

    @Override
    public Field<Long> field5() {
        return Maps.MAPS.ORGANIZATION_ID;
    }

    @Override
    public Field<Boolean> field6() {
        return Maps.MAPS.DELETED;
    }

    @Override
    public Field<Boolean> field7() {
        return Maps.MAPS.EDITABLE;
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
    public Timestamp value3() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value4() {
        return this.getUpdatedAt();
    }

    @Override
    public Long value5() {
        return this.getOrganizationId();
    }

    @Override
    public Boolean value6() {
        return this.getDeleted();
    }

    @Override
    public Boolean value7() {
        return this.getEditable();
    }

    public MapsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public MapsRecord value2(String value) {
        this.setName(value);
        return this;
    }

    public MapsRecord value3(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public MapsRecord value4(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public MapsRecord value5(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public MapsRecord value6(Boolean value) {
        this.setDeleted(value);
        return this;
    }

    public MapsRecord value7(Boolean value) {
        this.setEditable(value);
        return this;
    }

    public MapsRecord values(Long value1, String value2, Timestamp value3, Timestamp value4, Long value5, Boolean value6, Boolean value7) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        return this;
    }

    public MapsRecord() {
        super(Maps.MAPS);
    }

    public MapsRecord(Long id, String name, Timestamp createdAt, Timestamp updatedAt, Long organizationId, Boolean deleted, Boolean editable) {
        super(Maps.MAPS);
        this.set(0, id);
        this.set(1, name);
        this.set(2, createdAt);
        this.set(3, updatedAt);
        this.set(4, organizationId);
        this.set(5, deleted);
        this.set(6, editable);
    }
}

