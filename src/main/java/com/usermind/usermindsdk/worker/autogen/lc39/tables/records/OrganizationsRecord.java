/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Organizations;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record16;
import org.jooq.Row;
import org.jooq.Row16;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class OrganizationsRecord
extends UpdatableRecordImpl<OrganizationsRecord>
implements Record16<Long, String, Timestamp, Timestamp, Boolean, Integer, String, Boolean, String, Boolean, Long, Boolean, Boolean, Object, Boolean, Long> {
    private static final long serialVersionUID = -1266858345L;

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

    public void setCreatedAt(Timestamp value) {
        this.set(2, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(2);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(3, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(3);
    }

    public void setReplay(Boolean value) {
        this.set(4, value);
    }

    public Boolean getReplay() {
        return (Boolean)this.get(4);
    }

    public void setReplayFlags(Integer value) {
        this.set(5, value);
    }

    public Integer getReplayFlags() {
        return (Integer)this.get(5);
    }

    public void setEntityOverwrites(String value) {
        this.set(6, value);
    }

    public String getEntityOverwrites() {
        return (String)this.get(6);
    }

    public void setProcDisabled(Boolean value) {
        this.set(7, value);
    }

    public Boolean getProcDisabled() {
        return (Boolean)this.get(7);
    }

    public void setDisabledReason(String value) {
        this.set(8, value);
    }

    public String getDisabledReason() {
        return (String)this.get(8);
    }

    public void setDisableIfErr(Boolean value) {
        this.set(9, value);
    }

    public Boolean getDisableIfErr() {
        return (Boolean)this.get(9);
    }

    public void setAliasOrganizationId(Long value) {
        this.set(10, value);
    }

    public Long getAliasOrganizationId() {
        return (Long)this.get(10);
    }

    public void setUseCaseSensitiveIds(Boolean value) {
        this.set(11, value);
    }

    public Boolean getUseCaseSensitiveIds() {
        return (Boolean)this.get(11);
    }

    public void setCustomer(Boolean value) {
        this.set(12, value);
    }

    public Boolean getCustomer() {
        return (Boolean)this.get(12);
    }

    public void setProcMetadata(Object value) {
        this.set(13, value);
    }

    public Object getProcMetadata() {
        return this.get(13);
    }

    public void setRetain(Boolean value) {
        this.set(14, value);
    }

    public Boolean getRetain() {
        return (Boolean)this.get(14);
    }

    public void setCustomerStatus(Long value) {
        this.set(15, value);
    }

    public Long getCustomerStatus() {
        return (Long)this.get(15);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row16<Long, String, Timestamp, Timestamp, Boolean, Integer, String, Boolean, String, Boolean, Long, Boolean, Boolean, Object, Boolean, Long> fieldsRow() {
        return (Row16)super.fieldsRow();
    }

    @Override
    public Row16<Long, String, Timestamp, Timestamp, Boolean, Integer, String, Boolean, String, Boolean, Long, Boolean, Boolean, Object, Boolean, Long> valuesRow() {
        return (Row16)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Organizations.ORGANIZATIONS.ID;
    }

    @Override
    public Field<String> field2() {
        return Organizations.ORGANIZATIONS.NAME;
    }

    @Override
    public Field<Timestamp> field3() {
        return Organizations.ORGANIZATIONS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field4() {
        return Organizations.ORGANIZATIONS.UPDATED_AT;
    }

    @Override
    public Field<Boolean> field5() {
        return Organizations.ORGANIZATIONS.REPLAY;
    }

    @Override
    public Field<Integer> field6() {
        return Organizations.ORGANIZATIONS.REPLAY_FLAGS;
    }

    @Override
    public Field<String> field7() {
        return Organizations.ORGANIZATIONS.ENTITY_OVERWRITES;
    }

    @Override
    public Field<Boolean> field8() {
        return Organizations.ORGANIZATIONS.PROC_DISABLED;
    }

    @Override
    public Field<String> field9() {
        return Organizations.ORGANIZATIONS.DISABLED_REASON;
    }

    @Override
    public Field<Boolean> field10() {
        return Organizations.ORGANIZATIONS.DISABLE_IF_ERR;
    }

    @Override
    public Field<Long> field11() {
        return Organizations.ORGANIZATIONS.ALIAS_ORGANIZATION_ID;
    }

    @Override
    public Field<Boolean> field12() {
        return Organizations.ORGANIZATIONS.USE_CASE_SENSITIVE_IDS;
    }

    @Override
    public Field<Boolean> field13() {
        return Organizations.ORGANIZATIONS.CUSTOMER;
    }

    @Override
    public Field<Object> field14() {
        return Organizations.ORGANIZATIONS.PROC_METADATA;
    }

    @Override
    public Field<Boolean> field15() {
        return Organizations.ORGANIZATIONS.RETAIN;
    }

    @Override
    public Field<Long> field16() {
        return Organizations.ORGANIZATIONS.CUSTOMER_STATUS;
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
    public Timestamp value3() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value4() {
        return this.getUpdatedAt();
    }

    @Override
    public Boolean value5() {
        return this.getReplay();
    }

    @Override
    public Integer value6() {
        return this.getReplayFlags();
    }

    @Override
    public String value7() {
        return this.getEntityOverwrites();
    }

    @Override
    public Boolean value8() {
        return this.getProcDisabled();
    }

    @Override
    public String value9() {
        return this.getDisabledReason();
    }

    @Override
    public Boolean value10() {
        return this.getDisableIfErr();
    }

    @Override
    public Long value11() {
        return this.getAliasOrganizationId();
    }

    @Override
    public Boolean value12() {
        return this.getUseCaseSensitiveIds();
    }

    @Override
    public Boolean value13() {
        return this.getCustomer();
    }

    @Override
    public Object value14() {
        return this.getProcMetadata();
    }

    @Override
    public Boolean value15() {
        return this.getRetain();
    }

    @Override
    public Long value16() {
        return this.getCustomerStatus();
    }

    public OrganizationsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public OrganizationsRecord value2(String value) {
        this.setName(value);
        return this;
    }

    public OrganizationsRecord value3(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public OrganizationsRecord value4(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public OrganizationsRecord value5(Boolean value) {
        this.setReplay(value);
        return this;
    }

    public OrganizationsRecord value6(Integer value) {
        this.setReplayFlags(value);
        return this;
    }

    public OrganizationsRecord value7(String value) {
        this.setEntityOverwrites(value);
        return this;
    }

    public OrganizationsRecord value8(Boolean value) {
        this.setProcDisabled(value);
        return this;
    }

    public OrganizationsRecord value9(String value) {
        this.setDisabledReason(value);
        return this;
    }

    public OrganizationsRecord value10(Boolean value) {
        this.setDisableIfErr(value);
        return this;
    }

    public OrganizationsRecord value11(Long value) {
        this.setAliasOrganizationId(value);
        return this;
    }

    public OrganizationsRecord value12(Boolean value) {
        this.setUseCaseSensitiveIds(value);
        return this;
    }

    public OrganizationsRecord value13(Boolean value) {
        this.setCustomer(value);
        return this;
    }

    public OrganizationsRecord value14(Object value) {
        this.setProcMetadata(value);
        return this;
    }

    public OrganizationsRecord value15(Boolean value) {
        this.setRetain(value);
        return this;
    }

    public OrganizationsRecord value16(Long value) {
        this.setCustomerStatus(value);
        return this;
    }

    public OrganizationsRecord values(Long value1, String value2, Timestamp value3, Timestamp value4, Boolean value5, Integer value6, String value7, Boolean value8, String value9, Boolean value10, Long value11, Boolean value12, Boolean value13, Object value14, Boolean value15, Long value16) {
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
        this.value12(value12);
        this.value13(value13);
        this.value14(value14);
        this.value15(value15);
        this.value16(value16);
        return this;
    }

    public OrganizationsRecord() {
        super(Organizations.ORGANIZATIONS);
    }

    public OrganizationsRecord(Long id, String name, Timestamp createdAt, Timestamp updatedAt, Boolean replay, Integer replayFlags, String entityOverwrites, Boolean procDisabled, String disabledReason, Boolean disableIfErr, Long aliasOrganizationId, Boolean useCaseSensitiveIds, Boolean customer, Object procMetadata, Boolean retain, Long customerStatus) {
        super(Organizations.ORGANIZATIONS);
        this.set(0, id);
        this.set(1, name);
        this.set(2, createdAt);
        this.set(3, updatedAt);
        this.set(4, replay);
        this.set(5, replayFlags);
        this.set(6, entityOverwrites);
        this.set(7, procDisabled);
        this.set(8, disabledReason);
        this.set(9, disableIfErr);
        this.set(10, aliasOrganizationId);
        this.set(11, useCaseSensitiveIds);
        this.set(12, customer);
        this.set(13, procMetadata);
        this.set(14, retain);
        this.set(15, customerStatus);
    }
}

