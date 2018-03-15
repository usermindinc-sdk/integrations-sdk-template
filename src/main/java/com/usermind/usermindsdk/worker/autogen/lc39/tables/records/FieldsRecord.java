/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Fields;
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

public class FieldsRecord
extends UpdatableRecordImpl<FieldsRecord>
implements Record11<Long, String, String, String, Long, Long, Timestamp, Timestamp, Boolean, String, Boolean> {
    private static final long serialVersionUID = 400661582L;

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

    public void setDisplayName(String value) {
        this.set(2, value);
    }

    public String getDisplayName() {
        return (String)this.get(2);
    }

    public void setType(String value) {
        this.set(3, value);
    }

    public String getType() {
        return (String)this.get(3);
    }

    public void setEntityId(Long value) {
        this.set(4, value);
    }

    public Long getEntityId() {
        return (Long)this.get(4);
    }

    public void setOrganizationId(Long value) {
        this.set(5, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(5);
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

    public void setReadOnly(Boolean value) {
        this.set(8, value);
    }

    public Boolean getReadOnly() {
        return (Boolean)this.get(8);
    }

    public void setPath(String value) {
        this.set(9, value);
    }

    public String getPath() {
        return (String)this.get(9);
    }

    public void setIsRequired(Boolean value) {
        this.set(10, value);
    }

    public Boolean getIsRequired() {
        return (Boolean)this.get(10);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row11<Long, String, String, String, Long, Long, Timestamp, Timestamp, Boolean, String, Boolean> fieldsRow() {
        return (Row11)super.fieldsRow();
    }

    @Override
    public Row11<Long, String, String, String, Long, Long, Timestamp, Timestamp, Boolean, String, Boolean> valuesRow() {
        return (Row11)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Fields.FIELDS.ID;
    }

    @Override
    public Field<String> field2() {
        return Fields.FIELDS.NAME;
    }

    @Override
    public Field<String> field3() {
        return Fields.FIELDS.DISPLAY_NAME;
    }

    @Override
    public Field<String> field4() {
        return Fields.FIELDS.TYPE;
    }

    @Override
    public Field<Long> field5() {
        return Fields.FIELDS.ENTITY_ID;
    }

    @Override
    public Field<Long> field6() {
        return Fields.FIELDS.ORGANIZATION_ID;
    }

    @Override
    public Field<Timestamp> field7() {
        return Fields.FIELDS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field8() {
        return Fields.FIELDS.UPDATED_AT;
    }

    @Override
    public Field<Boolean> field9() {
        return Fields.FIELDS.READ_ONLY;
    }

    @Override
    public Field<String> field10() {
        return Fields.FIELDS.PATH;
    }

    @Override
    public Field<Boolean> field11() {
        return Fields.FIELDS.IS_REQUIRED;
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
    public String value3() {
        return this.getDisplayName();
    }

    @Override
    public String value4() {
        return this.getType();
    }

    @Override
    public Long value5() {
        return this.getEntityId();
    }

    @Override
    public Long value6() {
        return this.getOrganizationId();
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
    public Boolean value9() {
        return this.getReadOnly();
    }

    @Override
    public String value10() {
        return this.getPath();
    }

    @Override
    public Boolean value11() {
        return this.getIsRequired();
    }

    public FieldsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public FieldsRecord value2(String value) {
        this.setName(value);
        return this;
    }

    public FieldsRecord value3(String value) {
        this.setDisplayName(value);
        return this;
    }

    public FieldsRecord value4(String value) {
        this.setType(value);
        return this;
    }

    public FieldsRecord value5(Long value) {
        this.setEntityId(value);
        return this;
    }

    public FieldsRecord value6(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public FieldsRecord value7(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public FieldsRecord value8(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public FieldsRecord value9(Boolean value) {
        this.setReadOnly(value);
        return this;
    }

    public FieldsRecord value10(String value) {
        this.setPath(value);
        return this;
    }

    public FieldsRecord value11(Boolean value) {
        this.setIsRequired(value);
        return this;
    }

    public FieldsRecord values(Long value1, String value2, String value3, String value4, Long value5, Long value6, Timestamp value7, Timestamp value8, Boolean value9, String value10, Boolean value11) {
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

    public FieldsRecord() {
        super(Fields.FIELDS);
    }

    public FieldsRecord(Long id, String name, String displayName, String type, Long entityId, Long organizationId, Timestamp createdAt, Timestamp updatedAt, Boolean readOnly, String path, Boolean isRequired) {
        super(Fields.FIELDS);
        this.set(0, id);
        this.set(1, name);
        this.set(2, displayName);
        this.set(3, type);
        this.set(4, entityId);
        this.set(5, organizationId);
        this.set(6, createdAt);
        this.set(7, updatedAt);
        this.set(8, readOnly);
        this.set(9, path);
        this.set(10, isRequired);
    }
}

