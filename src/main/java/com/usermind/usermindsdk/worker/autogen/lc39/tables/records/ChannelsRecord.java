/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Channels;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row;
import org.jooq.Row9;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class ChannelsRecord
extends UpdatableRecordImpl<ChannelsRecord>
implements Record9<Long, String, Long, Boolean, Boolean, Timestamp, Timestamp, String, String> {
    private static final long serialVersionUID = 1299609222L;

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

    public void setOrganizationId(Long value) {
        this.set(2, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(2);
    }

    public void setHasEntities(Boolean value) {
        this.set(3, value);
    }

    public Boolean getHasEntities() {
        return (Boolean)this.get(3);
    }

    public void setHasActions(Boolean value) {
        this.set(4, value);
    }

    public Boolean getHasActions() {
        return (Boolean)this.get(4);
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

    public void setDisplayName(String value) {
        this.set(7, value);
    }

    public String getDisplayName() {
        return (String)this.get(7);
    }

    public void setEnvironment(String value) {
        this.set(8, value);
    }

    public String getEnvironment() {
        return (String)this.get(8);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row9<Long, String, Long, Boolean, Boolean, Timestamp, Timestamp, String, String> fieldsRow() {
        return (Row9)super.fieldsRow();
    }

    @Override
    public Row9<Long, String, Long, Boolean, Boolean, Timestamp, Timestamp, String, String> valuesRow() {
        return (Row9)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Channels.CHANNELS.ID;
    }

    @Override
    public Field<String> field2() {
        return Channels.CHANNELS.NAME;
    }

    @Override
    public Field<Long> field3() {
        return Channels.CHANNELS.ORGANIZATION_ID;
    }

    @Override
    public Field<Boolean> field4() {
        return Channels.CHANNELS.HAS_ENTITIES;
    }

    @Override
    public Field<Boolean> field5() {
        return Channels.CHANNELS.HAS_ACTIONS;
    }

    @Override
    public Field<Timestamp> field6() {
        return Channels.CHANNELS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field7() {
        return Channels.CHANNELS.UPDATED_AT;
    }

    @Override
    public Field<String> field8() {
        return Channels.CHANNELS.DISPLAY_NAME;
    }

    @Override
    public Field<String> field9() {
        return Channels.CHANNELS.ENVIRONMENT;
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
        return this.getOrganizationId();
    }

    @Override
    public Boolean value4() {
        return this.getHasEntities();
    }

    @Override
    public Boolean value5() {
        return this.getHasActions();
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
    public String value8() {
        return this.getDisplayName();
    }

    @Override
    public String value9() {
        return this.getEnvironment();
    }

    public ChannelsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public ChannelsRecord value2(String value) {
        this.setName(value);
        return this;
    }

    public ChannelsRecord value3(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public ChannelsRecord value4(Boolean value) {
        this.setHasEntities(value);
        return this;
    }

    public ChannelsRecord value5(Boolean value) {
        this.setHasActions(value);
        return this;
    }

    public ChannelsRecord value6(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public ChannelsRecord value7(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public ChannelsRecord value8(String value) {
        this.setDisplayName(value);
        return this;
    }

    public ChannelsRecord value9(String value) {
        this.setEnvironment(value);
        return this;
    }

    public ChannelsRecord values(Long value1, String value2, Long value3, Boolean value4, Boolean value5, Timestamp value6, Timestamp value7, String value8, String value9) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        this.value8(value8);
        this.value9(value9);
        return this;
    }

    public ChannelsRecord() {
        super(Channels.CHANNELS);
    }

    public ChannelsRecord(Long id, String name, Long organizationId, Boolean hasEntities, Boolean hasActions, Timestamp createdAt, Timestamp updatedAt, String displayName, String environment) {
        super(Channels.CHANNELS);
        this.set(0, id);
        this.set(1, name);
        this.set(2, organizationId);
        this.set(3, hasEntities);
        this.set(4, hasActions);
        this.set(5, createdAt);
        this.set(6, updatedAt);
        this.set(7, displayName);
        this.set(8, environment);
    }
}

