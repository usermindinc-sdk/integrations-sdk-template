/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Actionargs;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row;
import org.jooq.Row10;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class ActionargsRecord
extends UpdatableRecordImpl<ActionargsRecord>
implements Record10<Long, String, String, String, Long, Long, Timestamp, Timestamp, Boolean, String> {
    private static final long serialVersionUID = -486872885L;

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

    public void setActionId(Long value) {
        this.set(4, value);
    }

    public Long getActionId() {
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

    public void setAssignable(Boolean value) {
        this.set(8, value);
    }

    public Boolean getAssignable() {
        return (Boolean)this.get(8);
    }

    public void setPath(String value) {
        this.set(9, value);
    }

    public String getPath() {
        return (String)this.get(9);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row10<Long, String, String, String, Long, Long, Timestamp, Timestamp, Boolean, String> fieldsRow() {
        return (Row10)super.fieldsRow();
    }

    @Override
    public Row10<Long, String, String, String, Long, Long, Timestamp, Timestamp, Boolean, String> valuesRow() {
        return (Row10)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Actionargs.ACTIONARGS.ID;
    }

    @Override
    public Field<String> field2() {
        return Actionargs.ACTIONARGS.NAME;
    }

    @Override
    public Field<String> field3() {
        return Actionargs.ACTIONARGS.DISPLAY_NAME;
    }

    @Override
    public Field<String> field4() {
        return Actionargs.ACTIONARGS.TYPE;
    }

    @Override
    public Field<Long> field5() {
        return Actionargs.ACTIONARGS.ACTION_ID;
    }

    @Override
    public Field<Long> field6() {
        return Actionargs.ACTIONARGS.ORGANIZATION_ID;
    }

    @Override
    public Field<Timestamp> field7() {
        return Actionargs.ACTIONARGS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field8() {
        return Actionargs.ACTIONARGS.UPDATED_AT;
    }

    @Override
    public Field<Boolean> field9() {
        return Actionargs.ACTIONARGS.ASSIGNABLE;
    }

    @Override
    public Field<String> field10() {
        return Actionargs.ACTIONARGS.PATH;
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
        return this.getActionId();
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
        return this.getAssignable();
    }

    @Override
    public String value10() {
        return this.getPath();
    }

    public ActionargsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public ActionargsRecord value2(String value) {
        this.setName(value);
        return this;
    }

    public ActionargsRecord value3(String value) {
        this.setDisplayName(value);
        return this;
    }

    public ActionargsRecord value4(String value) {
        this.setType(value);
        return this;
    }

    public ActionargsRecord value5(Long value) {
        this.setActionId(value);
        return this;
    }

    public ActionargsRecord value6(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public ActionargsRecord value7(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public ActionargsRecord value8(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public ActionargsRecord value9(Boolean value) {
        this.setAssignable(value);
        return this;
    }

    public ActionargsRecord value10(String value) {
        this.setPath(value);
        return this;
    }

    public ActionargsRecord values(Long value1, String value2, String value3, String value4, Long value5, Long value6, Timestamp value7, Timestamp value8, Boolean value9, String value10) {
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
        return this;
    }

    public ActionargsRecord() {
        super(Actionargs.ACTIONARGS);
    }

    public ActionargsRecord(Long id, String name, String displayName, String type, Long actionId, Long organizationId, Timestamp createdAt, Timestamp updatedAt, Boolean assignable, String path) {
        super(Actionargs.ACTIONARGS);
        this.set(0, id);
        this.set(1, name);
        this.set(2, displayName);
        this.set(3, type);
        this.set(4, actionId);
        this.set(5, organizationId);
        this.set(6, createdAt);
        this.set(7, updatedAt);
        this.set(8, assignable);
        this.set(9, path);
    }
}

