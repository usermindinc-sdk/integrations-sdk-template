/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Entities;
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

public class EntitiesRecord
extends UpdatableRecordImpl<EntitiesRecord>
implements Record7<Long, String, Long, Long, Timestamp, Timestamp, String> {
    private static final long serialVersionUID = 2044349403L;

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

    public void setDisplayName(String value) {
        this.set(6, value);
    }

    public String getDisplayName() {
        return (String)this.get(6);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row7<Long, String, Long, Long, Timestamp, Timestamp, String> fieldsRow() {
        return (Row7)super.fieldsRow();
    }

    @Override
    public Row7<Long, String, Long, Long, Timestamp, Timestamp, String> valuesRow() {
        return (Row7)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Entities.ENTITIES.ID;
    }

    @Override
    public Field<String> field2() {
        return Entities.ENTITIES.NAME;
    }

    @Override
    public Field<Long> field3() {
        return Entities.ENTITIES.CHANNEL_ID;
    }

    @Override
    public Field<Long> field4() {
        return Entities.ENTITIES.ORGANIZATION_ID;
    }

    @Override
    public Field<Timestamp> field5() {
        return Entities.ENTITIES.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field6() {
        return Entities.ENTITIES.UPDATED_AT;
    }

    @Override
    public Field<String> field7() {
        return Entities.ENTITIES.DISPLAY_NAME;
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
    public Timestamp value5() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value6() {
        return this.getUpdatedAt();
    }

    @Override
    public String value7() {
        return this.getDisplayName();
    }

    public EntitiesRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public EntitiesRecord value2(String value) {
        this.setName(value);
        return this;
    }

    public EntitiesRecord value3(Long value) {
        this.setChannelId(value);
        return this;
    }

    public EntitiesRecord value4(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public EntitiesRecord value5(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public EntitiesRecord value6(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public EntitiesRecord value7(String value) {
        this.setDisplayName(value);
        return this;
    }

    public EntitiesRecord values(Long value1, String value2, Long value3, Long value4, Timestamp value5, Timestamp value6, String value7) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        return this;
    }

    public EntitiesRecord() {
        super(Entities.ENTITIES);
    }

    public EntitiesRecord(Long id, String name, Long channelId, Long organizationId, Timestamp createdAt, Timestamp updatedAt, String displayName) {
        super(Entities.ENTITIES);
        this.set(0, id);
        this.set(1, name);
        this.set(2, channelId);
        this.set(3, organizationId);
        this.set(4, createdAt);
        this.set(5, updatedAt);
        this.set(6, displayName);
    }
}

