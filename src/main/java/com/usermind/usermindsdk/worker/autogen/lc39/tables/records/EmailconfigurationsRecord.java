/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Emailconfigurations;
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

public class EmailconfigurationsRecord
extends UpdatableRecordImpl<EmailconfigurationsRecord>
implements Record8<Long, Long, Long, Long, Boolean, Long, Object, Timestamp> {
    private static final long serialVersionUID = -1027529345L;

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

    public void setJourneyId(Long value) {
        this.set(2, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(2);
    }

    public void setRuleId(Long value) {
        this.set(3, value);
    }

    public Long getRuleId() {
        return (Long)this.get(3);
    }

    public void setIsDeleted(Boolean value) {
        this.set(4, value);
    }

    public Boolean getIsDeleted() {
        return (Boolean)this.get(4);
    }

    public void setVersion(Long value) {
        this.set(5, value);
    }

    public Long getVersion() {
        return (Long)this.get(5);
    }

    public void setConfiguration(Object value) {
        this.set(6, value);
    }

    public Object getConfiguration() {
        return this.get(6);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(7, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(7);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row8<Long, Long, Long, Long, Boolean, Long, Object, Timestamp> fieldsRow() {
        return (Row8)super.fieldsRow();
    }

    @Override
    public Row8<Long, Long, Long, Long, Boolean, Long, Object, Timestamp> valuesRow() {
        return (Row8)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Emailconfigurations.EMAILCONFIGURATIONS.ID;
    }

    @Override
    public Field<Long> field2() {
        return Emailconfigurations.EMAILCONFIGURATIONS.ORGANIZATION_ID;
    }

    @Override
    public Field<Long> field3() {
        return Emailconfigurations.EMAILCONFIGURATIONS.JOURNEY_ID;
    }

    @Override
    public Field<Long> field4() {
        return Emailconfigurations.EMAILCONFIGURATIONS.RULE_ID;
    }

    @Override
    public Field<Boolean> field5() {
        return Emailconfigurations.EMAILCONFIGURATIONS.IS_DELETED;
    }

    @Override
    public Field<Long> field6() {
        return Emailconfigurations.EMAILCONFIGURATIONS.VERSION;
    }

    @Override
    public Field<Object> field7() {
        return Emailconfigurations.EMAILCONFIGURATIONS.CONFIGURATION;
    }

    @Override
    public Field<Timestamp> field8() {
        return Emailconfigurations.EMAILCONFIGURATIONS.CREATED_AT;
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
    public Long value3() {
        return this.getJourneyId();
    }

    @Override
    public Long value4() {
        return this.getRuleId();
    }

    @Override
    public Boolean value5() {
        return this.getIsDeleted();
    }

    @Override
    public Long value6() {
        return this.getVersion();
    }

    @Override
    public Object value7() {
        return this.getConfiguration();
    }

    @Override
    public Timestamp value8() {
        return this.getCreatedAt();
    }

    public EmailconfigurationsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public EmailconfigurationsRecord value2(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public EmailconfigurationsRecord value3(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public EmailconfigurationsRecord value4(Long value) {
        this.setRuleId(value);
        return this;
    }

    public EmailconfigurationsRecord value5(Boolean value) {
        this.setIsDeleted(value);
        return this;
    }

    public EmailconfigurationsRecord value6(Long value) {
        this.setVersion(value);
        return this;
    }

    public EmailconfigurationsRecord value7(Object value) {
        this.setConfiguration(value);
        return this;
    }

    public EmailconfigurationsRecord value8(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public EmailconfigurationsRecord values(Long value1, Long value2, Long value3, Long value4, Boolean value5, Long value6, Object value7, Timestamp value8) {
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

    public EmailconfigurationsRecord() {
        super(Emailconfigurations.EMAILCONFIGURATIONS);
    }

    public EmailconfigurationsRecord(Long id, Long organizationId, Long journeyId, Long ruleId, Boolean isDeleted, Long version, Object configuration, Timestamp createdAt) {
        super(Emailconfigurations.EMAILCONFIGURATIONS);
        this.set(0, id);
        this.set(1, organizationId);
        this.set(2, journeyId);
        this.set(3, ruleId);
        this.set(4, isDeleted);
        this.set(5, version);
        this.set(6, configuration);
        this.set(7, createdAt);
    }
}

