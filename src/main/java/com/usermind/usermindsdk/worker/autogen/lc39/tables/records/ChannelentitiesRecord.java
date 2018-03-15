/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Channelentities;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row;
import org.jooq.Row10;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class ChannelentitiesRecord
extends UpdatableRecordImpl<ChannelentitiesRecord>
implements Record10<Long, String, Long, Long, String, String, String, Object, Integer, String> {
    private static final long serialVersionUID = 1278377606L;

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

    public void setTimestamp(Long value) {
        this.set(2, value);
    }

    public Long getTimestamp() {
        return (Long)this.get(2);
    }

    public void setOrganizationId(Long value) {
        this.set(3, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(3);
    }

    public void setChannelName(String value) {
        this.set(4, value);
    }

    public String getChannelName() {
        return (String)this.get(4);
    }

    public void setEntityType(String value) {
        this.set(5, value);
    }

    public String getEntityType() {
        return (String)this.get(5);
    }

    public void setEntityId(String value) {
        this.set(6, value);
    }

    public String getEntityId() {
        return (String)this.get(6);
    }

    public void setData(Object value) {
        this.set(7, value);
    }

    public Object getData() {
        return this.get(7);
    }

    public void setRecordId(Integer value) {
        this.set(8, value);
    }

    public Integer getRecordId() {
        return (Integer)this.get(8);
    }

    public void setRecordName(String value) {
        this.set(9, value);
    }

    public String getRecordName() {
        return (String)this.get(9);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row10<Long, String, Long, Long, String, String, String, Object, Integer, String> fieldsRow() {
        return (Row10)super.fieldsRow();
    }

    @Override
    public Row10<Long, String, Long, Long, String, String, String, Object, Integer, String> valuesRow() {
        return (Row10)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Channelentities.CHANNELENTITIES.ID;
    }

    @Override
    public Field<String> field2() {
        return Channelentities.CHANNELENTITIES.KEY;
    }

    @Override
    public Field<Long> field3() {
        return Channelentities.CHANNELENTITIES.TIMESTAMP;
    }

    @Override
    public Field<Long> field4() {
        return Channelentities.CHANNELENTITIES.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field5() {
        return Channelentities.CHANNELENTITIES.CHANNEL_NAME;
    }

    @Override
    public Field<String> field6() {
        return Channelentities.CHANNELENTITIES.ENTITY_TYPE;
    }

    @Override
    public Field<String> field7() {
        return Channelentities.CHANNELENTITIES.ENTITY_ID;
    }

    @Override
    public Field<Object> field8() {
        return Channelentities.CHANNELENTITIES.DATA;
    }

    @Override
    public Field<Integer> field9() {
        return Channelentities.CHANNELENTITIES.RECORD_ID;
    }

    @Override
    public Field<String> field10() {
        return Channelentities.CHANNELENTITIES.RECORD_NAME;
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
    public Long value3() {
        return this.getTimestamp();
    }

    @Override
    public Long value4() {
        return this.getOrganizationId();
    }

    @Override
    public String value5() {
        return this.getChannelName();
    }

    @Override
    public String value6() {
        return this.getEntityType();
    }

    @Override
    public String value7() {
        return this.getEntityId();
    }

    @Override
    public Object value8() {
        return this.getData();
    }

    @Override
    public Integer value9() {
        return this.getRecordId();
    }

    @Override
    public String value10() {
        return this.getRecordName();
    }

    public ChannelentitiesRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public ChannelentitiesRecord value2(String value) {
        this.setKey(value);
        return this;
    }

    public ChannelentitiesRecord value3(Long value) {
        this.setTimestamp(value);
        return this;
    }

    public ChannelentitiesRecord value4(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public ChannelentitiesRecord value5(String value) {
        this.setChannelName(value);
        return this;
    }

    public ChannelentitiesRecord value6(String value) {
        this.setEntityType(value);
        return this;
    }

    public ChannelentitiesRecord value7(String value) {
        this.setEntityId(value);
        return this;
    }

    public ChannelentitiesRecord value8(Object value) {
        this.setData(value);
        return this;
    }

    public ChannelentitiesRecord value9(Integer value) {
        this.setRecordId(value);
        return this;
    }

    public ChannelentitiesRecord value10(String value) {
        this.setRecordName(value);
        return this;
    }

    public ChannelentitiesRecord values(Long value1, String value2, Long value3, Long value4, String value5, String value6, String value7, Object value8, Integer value9, String value10) {
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

    public ChannelentitiesRecord() {
        super(Channelentities.CHANNELENTITIES);
    }

    public ChannelentitiesRecord(Long id, String key, Long timestamp, Long organizationId, String channelName, String entityType, String entityId, Object data, Integer recordId, String recordName) {
        super(Channelentities.CHANNELENTITIES);
        this.set(0, id);
        this.set(1, key);
        this.set(2, timestamp);
        this.set(3, organizationId);
        this.set(4, channelName);
        this.set(5, entityType);
        this.set(6, entityId);
        this.set(7, data);
        this.set(8, recordId);
        this.set(9, recordName);
    }
}

