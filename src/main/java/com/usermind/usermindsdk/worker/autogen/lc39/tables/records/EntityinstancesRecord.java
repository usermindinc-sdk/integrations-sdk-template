/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Entityinstances;
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

public class EntityinstancesRecord
extends UpdatableRecordImpl<EntityinstancesRecord>
implements Record9<Long, Long, String, String, String, Timestamp, Timestamp, Long, Long> {
    private static final long serialVersionUID = -312987469L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setOrganizationId(Long value) {
        this.set(1, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(1);
    }

    public void setChannelName(String value) {
        this.set(2, value);
    }

    public String getChannelName() {
        return (String)this.get(2);
    }

    public void setEntityType(String value) {
        this.set(3, value);
    }

    public String getEntityType() {
        return (String)this.get(3);
    }

    public void setEntityId(String value) {
        this.set(4, value);
    }

    public String getEntityId() {
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

    public void setTimestamp(Long value) {
        this.set(7, value);
    }

    public Long getTimestamp() {
        return (Long)this.get(7);
    }

    public void setMapSpecId(Long value) {
        this.set(8, value);
    }

    public Long getMapSpecId() {
        return (Long)this.get(8);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row9<Long, Long, String, String, String, Timestamp, Timestamp, Long, Long> fieldsRow() {
        return (Row9)super.fieldsRow();
    }

    @Override
    public Row9<Long, Long, String, String, String, Timestamp, Timestamp, Long, Long> valuesRow() {
        return (Row9)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Entityinstances.ENTITYINSTANCES.ID;
    }

    @Override
    public Field<Long> field2() {
        return Entityinstances.ENTITYINSTANCES.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field3() {
        return Entityinstances.ENTITYINSTANCES.CHANNEL_NAME;
    }

    @Override
    public Field<String> field4() {
        return Entityinstances.ENTITYINSTANCES.ENTITY_TYPE;
    }

    @Override
    public Field<String> field5() {
        return Entityinstances.ENTITYINSTANCES.ENTITY_ID;
    }

    @Override
    public Field<Timestamp> field6() {
        return Entityinstances.ENTITYINSTANCES.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field7() {
        return Entityinstances.ENTITYINSTANCES.UPDATED_AT;
    }

    @Override
    public Field<Long> field8() {
        return Entityinstances.ENTITYINSTANCES.TIMESTAMP;
    }

    @Override
    public Field<Long> field9() {
        return Entityinstances.ENTITYINSTANCES.MAP_SPEC_ID;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public Long value2() {
        return this.getOrganizationId();
    }

    @Override
    public String value3() {
        return this.getChannelName();
    }

    @Override
    public String value4() {
        return this.getEntityType();
    }

    @Override
    public String value5() {
        return this.getEntityId();
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
    public Long value8() {
        return this.getTimestamp();
    }

    @Override
    public Long value9() {
        return this.getMapSpecId();
    }

    public EntityinstancesRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public EntityinstancesRecord value2(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public EntityinstancesRecord value3(String value) {
        this.setChannelName(value);
        return this;
    }

    public EntityinstancesRecord value4(String value) {
        this.setEntityType(value);
        return this;
    }

    public EntityinstancesRecord value5(String value) {
        this.setEntityId(value);
        return this;
    }

    public EntityinstancesRecord value6(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public EntityinstancesRecord value7(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public EntityinstancesRecord value8(Long value) {
        this.setTimestamp(value);
        return this;
    }

    public EntityinstancesRecord value9(Long value) {
        this.setMapSpecId(value);
        return this;
    }

    public EntityinstancesRecord values(Long value1, Long value2, String value3, String value4, String value5, Timestamp value6, Timestamp value7, Long value8, Long value9) {
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

    public EntityinstancesRecord() {
        super(Entityinstances.ENTITYINSTANCES);
    }

    public EntityinstancesRecord(Long id, Long organizationId, String channelName, String entityType, String entityId, Timestamp createdAt, Timestamp updatedAt, Long timestamp, Long mapSpecId) {
        super(Entityinstances.ENTITYINSTANCES);
        this.set(0, id);
        this.set(1, organizationId);
        this.set(2, channelName);
        this.set(3, entityType);
        this.set(4, entityId);
        this.set(5, createdAt);
        this.set(6, updatedAt);
        this.set(7, timestamp);
        this.set(8, mapSpecId);
    }
}

