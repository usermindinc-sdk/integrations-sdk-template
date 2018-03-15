/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Integrationconfigs;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row;
import org.jooq.Row8;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class IntegrationconfigsRecord
extends UpdatableRecordImpl<IntegrationconfigsRecord>
implements Record8<Long, Long, String, String, Timestamp, Timestamp, Integer, String> {
    private static final long serialVersionUID = 1738834936L;

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

    public void setName(String value) {
        this.set(2, value);
    }

    public String getName() {
        return (String)this.get(2);
    }

    public void setConfig(String value) {
        this.set(3, value);
    }

    public String getConfig() {
        return (String)this.get(3);
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

    public void setVersion(Integer value) {
        this.set(6, value);
    }

    public Integer getVersion() {
        return (Integer)this.get(6);
    }

    public void setChannelName(String value) {
        this.set(7, value);
    }

    public String getChannelName() {
        return (String)this.get(7);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row8<Long, Long, String, String, Timestamp, Timestamp, Integer, String> fieldsRow() {
        return (Row8)super.fieldsRow();
    }

    @Override
    public Row8<Long, Long, String, String, Timestamp, Timestamp, Integer, String> valuesRow() {
        return (Row8)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Integrationconfigs.INTEGRATIONCONFIGS.ID;
    }

    @Override
    public Field<Long> field2() {
        return Integrationconfigs.INTEGRATIONCONFIGS.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field3() {
        return Integrationconfigs.INTEGRATIONCONFIGS.NAME;
    }

    @Override
    public Field<String> field4() {
        return Integrationconfigs.INTEGRATIONCONFIGS.CONFIG;
    }

    @Override
    public Field<Timestamp> field5() {
        return Integrationconfigs.INTEGRATIONCONFIGS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field6() {
        return Integrationconfigs.INTEGRATIONCONFIGS.UPDATED_AT;
    }

    @Override
    public Field<Integer> field7() {
        return Integrationconfigs.INTEGRATIONCONFIGS.VERSION;
    }

    @Override
    public Field<String> field8() {
        return Integrationconfigs.INTEGRATIONCONFIGS.CHANNEL_NAME;
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
        return this.getName();
    }

    @Override
    public String value4() {
        return this.getConfig();
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
    public Integer value7() {
        return this.getVersion();
    }

    @Override
    public String value8() {
        return this.getChannelName();
    }

    public IntegrationconfigsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public IntegrationconfigsRecord value2(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public IntegrationconfigsRecord value3(String value) {
        this.setName(value);
        return this;
    }

    public IntegrationconfigsRecord value4(String value) {
        this.setConfig(value);
        return this;
    }

    public IntegrationconfigsRecord value5(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public IntegrationconfigsRecord value6(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public IntegrationconfigsRecord value7(Integer value) {
        this.setVersion(value);
        return this;
    }

    public IntegrationconfigsRecord value8(String value) {
        this.setChannelName(value);
        return this;
    }

    public IntegrationconfigsRecord values(Long value1, Long value2, String value3, String value4, Timestamp value5, Timestamp value6, Integer value7, String value8) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        this.value7(value7);
        this.value8(value8);
        return this;
    }

    public IntegrationconfigsRecord() {
        super(Integrationconfigs.INTEGRATIONCONFIGS);
    }

    public IntegrationconfigsRecord(Long id, Long organizationId, String name, String config, Timestamp createdAt, Timestamp updatedAt, Integer version, String channelName) {
        super(Integrationconfigs.INTEGRATIONCONFIGS);
        this.set(0, id);
        this.set(1, organizationId);
        this.set(2, name);
        this.set(3, config);
        this.set(4, createdAt);
        this.set(5, updatedAt);
        this.set(6, version);
        this.set(7, channelName);
    }
}

