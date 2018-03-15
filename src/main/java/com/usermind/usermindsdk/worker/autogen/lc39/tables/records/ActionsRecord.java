/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Actions;
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

public class ActionsRecord
extends UpdatableRecordImpl<ActionsRecord>
implements Record11<Long, String, Long, Long, String, Timestamp, Timestamp, Boolean, Long, Boolean, String> {
    private static final long serialVersionUID = -1684359404L;

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

    public void setChannelId(Long value) {
        this.set(2, value);
    }

    public Long getChannelId() {
        return (Long)this.get(2);
    }

    public void setOrganizationId(Long value) {
        this.set(3, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(3);
    }

    public void setDisplayName(String value) {
        this.set(4, value);
    }

    public String getDisplayName() {
        return (String)this.get(4);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(5, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(5);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(6, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(6);
    }

    public void setHasVariables(Boolean value) {
        this.set(7, value);
    }

    public Boolean getHasVariables() {
        return (Boolean)this.get(7);
    }

    public void setParentId(Long value) {
        this.set(8, value);
    }

    public Long getParentId() {
        return (Long)this.get(8);
    }

    public void setHasActions(Boolean value) {
        this.set(9, value);
    }

    public Boolean getHasActions() {
        return (Boolean)this.get(9);
    }

    public void setForEntity(String value) {
        this.set(10, value);
    }

    public String getForEntity() {
        return (String)this.get(10);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row11<Long, String, Long, Long, String, Timestamp, Timestamp, Boolean, Long, Boolean, String> fieldsRow() {
        return (Row11)super.fieldsRow();
    }

    @Override
    public Row11<Long, String, Long, Long, String, Timestamp, Timestamp, Boolean, Long, Boolean, String> valuesRow() {
        return (Row11)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Actions.ACTIONS.ID;
    }

    @Override
    public Field<String> field2() {
        return Actions.ACTIONS.NAME;
    }

    @Override
    public Field<Long> field3() {
        return Actions.ACTIONS.CHANNEL_ID;
    }

    @Override
    public Field<Long> field4() {
        return Actions.ACTIONS.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field5() {
        return Actions.ACTIONS.DISPLAY_NAME;
    }

    @Override
    public Field<Timestamp> field6() {
        return Actions.ACTIONS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field7() {
        return Actions.ACTIONS.UPDATED_AT;
    }

    @Override
    public Field<Boolean> field8() {
        return Actions.ACTIONS.HAS_VARIABLES;
    }

    @Override
    public Field<Long> field9() {
        return Actions.ACTIONS.PARENT_ID;
    }

    @Override
    public Field<Boolean> field10() {
        return Actions.ACTIONS.HAS_ACTIONS;
    }

    @Override
    public Field<String> field11() {
        return Actions.ACTIONS.FOR_ENTITY;
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
    public Long value3() {
        return this.getChannelId();
    }

    @Override
    public Long value4() {
        return this.getOrganizationId();
    }

    @Override
    public String value5() {
        return this.getDisplayName();
    }

    @Override
    public Timestamp value6() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value7() {
        return this.getUpdatedAt();
    }

    @Override
    public Boolean value8() {
        return this.getHasVariables();
    }

    @Override
    public Long value9() {
        return this.getParentId();
    }

    @Override
    public Boolean value10() {
        return this.getHasActions();
    }

    @Override
    public String value11() {
        return this.getForEntity();
    }

    public ActionsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public ActionsRecord value2(String value) {
        this.setName(value);
        return this;
    }

    public ActionsRecord value3(Long value) {
        this.setChannelId(value);
        return this;
    }

    public ActionsRecord value4(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public ActionsRecord value5(String value) {
        this.setDisplayName(value);
        return this;
    }

    public ActionsRecord value6(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public ActionsRecord value7(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public ActionsRecord value8(Boolean value) {
        this.setHasVariables(value);
        return this;
    }

    public ActionsRecord value9(Long value) {
        this.setParentId(value);
        return this;
    }

    public ActionsRecord value10(Boolean value) {
        this.setHasActions(value);
        return this;
    }

    public ActionsRecord value11(String value) {
        this.setForEntity(value);
        return this;
    }

    public ActionsRecord values(Long value1, String value2, Long value3, Long value4, String value5, Timestamp value6, Timestamp value7, Boolean value8, Long value9, Boolean value10, String value11) {
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

    public ActionsRecord() {
        super(Actions.ACTIONS);
    }

    public ActionsRecord(Long id, String name, Long channelId, Long organizationId, String displayName, Timestamp createdAt, Timestamp updatedAt, Boolean hasVariables, Long parentId, Boolean hasActions, String forEntity) {
        super(Actions.ACTIONS);
        this.set(0, id);
        this.set(1, name);
        this.set(2, channelId);
        this.set(3, organizationId);
        this.set(4, displayName);
        this.set(5, createdAt);
        this.set(6, updatedAt);
        this.set(7, hasVariables);
        this.set(8, parentId);
        this.set(9, hasActions);
        this.set(10, forEntity);
    }
}

