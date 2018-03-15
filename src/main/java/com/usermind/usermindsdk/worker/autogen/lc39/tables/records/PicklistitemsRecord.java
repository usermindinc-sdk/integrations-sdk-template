/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Picklistitems;
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

public class PicklistitemsRecord
extends UpdatableRecordImpl<PicklistitemsRecord>
implements Record11<Long, Long, Long, Long, Long, String, String, Boolean, Timestamp, Timestamp, String> {
    private static final long serialVersionUID = 55040026L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setJourneyId(Long value) {
        this.set(1, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(1);
    }

    public void setOrganizationId(Long value) {
        this.set(2, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(2);
    }

    public void setPickListItemId(Long value) {
        this.set(3, value);
    }

    public Long getPickListItemId() {
        return (Long)this.get(3);
    }

    public void setVersion(Long value) {
        this.set(4, value);
    }

    public Long getVersion() {
        return (Long)this.get(4);
    }

    public void setPath(String value) {
        this.set(5, value);
    }

    public String getPath() {
        return (String)this.get(5);
    }

    public void setValue(String value) {
        this.set(6, value);
    }

    public String getValue() {
        return (String)this.get(6);
    }

    public void setDeleted(Boolean value) {
        this.set(7, value);
    }

    public Boolean getDeleted() {
        return (Boolean)this.get(7);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(8, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(8);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(9, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(9);
    }

    public void setLabel(String value) {
        this.set(10, value);
    }

    public String getLabel() {
        return (String)this.get(10);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row11<Long, Long, Long, Long, Long, String, String, Boolean, Timestamp, Timestamp, String> fieldsRow() {
        return (Row11)super.fieldsRow();
    }

    @Override
    public Row11<Long, Long, Long, Long, Long, String, String, Boolean, Timestamp, Timestamp, String> valuesRow() {
        return (Row11)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Picklistitems.PICKLISTITEMS.ID;
    }

    @Override
    public Field<Long> field2() {
        return Picklistitems.PICKLISTITEMS.JOURNEY_ID;
    }

    @Override
    public Field<Long> field3() {
        return Picklistitems.PICKLISTITEMS.ORGANIZATION_ID;
    }

    @Override
    public Field<Long> field4() {
        return Picklistitems.PICKLISTITEMS.PICK_LIST_ITEM_ID;
    }

    @Override
    public Field<Long> field5() {
        return Picklistitems.PICKLISTITEMS.VERSION;
    }

    @Override
    public Field<String> field6() {
        return Picklistitems.PICKLISTITEMS.PATH;
    }

    @Override
    public Field<String> field7() {
        return Picklistitems.PICKLISTITEMS.VALUE;
    }

    @Override
    public Field<Boolean> field8() {
        return Picklistitems.PICKLISTITEMS.DELETED;
    }

    @Override
    public Field<Timestamp> field9() {
        return Picklistitems.PICKLISTITEMS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field10() {
        return Picklistitems.PICKLISTITEMS.UPDATED_AT;
    }

    @Override
    public Field<String> field11() {
        return Picklistitems.PICKLISTITEMS.LABEL;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public Long value2() {
        return this.getJourneyId();
    }

    @Override
    public Long value3() {
        return this.getOrganizationId();
    }

    @Override
    public Long value4() {
        return this.getPickListItemId();
    }

    @Override
    public Long value5() {
        return this.getVersion();
    }

    @Override
    public String value6() {
        return this.getPath();
    }

    @Override
    public String value7() {
        return this.getValue();
    }

    @Override
    public Boolean value8() {
        return this.getDeleted();
    }

    @Override
    public Timestamp value9() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value10() {
        return this.getUpdatedAt();
    }

    @Override
    public String value11() {
        return this.getLabel();
    }

    public PicklistitemsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public PicklistitemsRecord value2(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public PicklistitemsRecord value3(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public PicklistitemsRecord value4(Long value) {
        this.setPickListItemId(value);
        return this;
    }

    public PicklistitemsRecord value5(Long value) {
        this.setVersion(value);
        return this;
    }

    public PicklistitemsRecord value6(String value) {
        this.setPath(value);
        return this;
    }

    public PicklistitemsRecord value7(String value) {
        this.setValue(value);
        return this;
    }

    public PicklistitemsRecord value8(Boolean value) {
        this.setDeleted(value);
        return this;
    }

    public PicklistitemsRecord value9(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public PicklistitemsRecord value10(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public PicklistitemsRecord value11(String value) {
        this.setLabel(value);
        return this;
    }

    public PicklistitemsRecord values(Long value1, Long value2, Long value3, Long value4, Long value5, String value6, String value7, Boolean value8, Timestamp value9, Timestamp value10, String value11) {
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

    public PicklistitemsRecord() {
        super(Picklistitems.PICKLISTITEMS);
    }

    public PicklistitemsRecord(Long id, Long journeyId, Long organizationId, Long pickListItemId, Long version, String path, String value, Boolean deleted, Timestamp createdAt, Timestamp updatedAt, String label) {
        super(Picklistitems.PICKLISTITEMS);
        this.set(0, id);
        this.set(1, journeyId);
        this.set(2, organizationId);
        this.set(3, pickListItemId);
        this.set(4, version);
        this.set(5, path);
        this.set(6, value);
        this.set(7, deleted);
        this.set(8, createdAt);
        this.set(9, updatedAt);
        this.set(10, label);
    }
}

