/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Entityinstanceedges;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record15;
import org.jooq.Row;
import org.jooq.Row15;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class EntityinstanceedgesRecord
extends UpdatableRecordImpl<EntityinstanceedgesRecord>
implements Record15<Long, Long, Long, String, String, String, String, String, String, String, String, Boolean, Boolean, Long, String> {
    private static final long serialVersionUID = -2122329856L;

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

    public void setMapSpecId(Long value) {
        this.set(2, value);
    }

    public Long getMapSpecId() {
        return (Long)this.get(2);
    }

    public void setFromChannelName(String value) {
        this.set(3, value);
    }

    public String getFromChannelName() {
        return (String)this.get(3);
    }

    public void setFromEntityType(String value) {
        this.set(4, value);
    }

    public String getFromEntityType() {
        return (String)this.get(4);
    }

    public void setFromEntityId(String value) {
        this.set(5, value);
    }

    public String getFromEntityId() {
        return (String)this.get(5);
    }

    public void setToChannelName(String value) {
        this.set(6, value);
    }

    public String getToChannelName() {
        return (String)this.get(6);
    }

    public void setToEntityType(String value) {
        this.set(7, value);
    }

    public String getToEntityType() {
        return (String)this.get(7);
    }

    public void setToEntityId(String value) {
        this.set(8, value);
    }

    public String getToEntityId() {
        return (String)this.get(8);
    }

    public void setAssociationKey(String value) {
        this.set(9, value);
    }

    public String getAssociationKey() {
        return (String)this.get(9);
    }

    public void setAssociationCardinality(String value) {
        this.set(10, value);
    }

    public String getAssociationCardinality() {
        return (String)this.get(10);
    }

    public void setIsDeleted(Boolean value) {
        this.set(11, value);
    }

    public Boolean getIsDeleted() {
        return (Boolean)this.get(11);
    }

    public void setCaseInsensitive(Boolean value) {
        this.set(12, value);
    }

    public Boolean getCaseInsensitive() {
        return (Boolean)this.get(12);
    }

    public void setTimestamp(Long value) {
        this.set(13, value);
    }

    public Long getTimestamp() {
        return (Long)this.get(13);
    }

    public void setChannelEntityKey(String value) {
        this.set(14, value);
    }

    public String getChannelEntityKey() {
        return (String)this.get(14);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row15<Long, Long, Long, String, String, String, String, String, String, String, String, Boolean, Boolean, Long, String> fieldsRow() {
        return (Row15)super.fieldsRow();
    }

    @Override
    public Row15<Long, Long, Long, String, String, String, String, String, String, String, String, Boolean, Boolean, Long, String> valuesRow() {
        return (Row15)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.ID;
    }

    @Override
    public Field<Long> field2() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.ORGANIZATION_ID;
    }

    @Override
    public Field<Long> field3() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.MAP_SPEC_ID;
    }

    @Override
    public Field<String> field4() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.FROM_CHANNEL_NAME;
    }

    @Override
    public Field<String> field5() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.FROM_ENTITY_TYPE;
    }

    @Override
    public Field<String> field6() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.FROM_ENTITY_ID;
    }

    @Override
    public Field<String> field7() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.TO_CHANNEL_NAME;
    }

    @Override
    public Field<String> field8() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.TO_ENTITY_TYPE;
    }

    @Override
    public Field<String> field9() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.TO_ENTITY_ID;
    }

    @Override
    public Field<String> field10() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.ASSOCIATION_KEY;
    }

    @Override
    public Field<String> field11() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.ASSOCIATION_CARDINALITY;
    }

    @Override
    public Field<Boolean> field12() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.IS_DELETED;
    }

    @Override
    public Field<Boolean> field13() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.CASE_INSENSITIVE;
    }

    @Override
    public Field<Long> field14() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.TIMESTAMP;
    }

    @Override
    public Field<String> field15() {
        return Entityinstanceedges.ENTITYINSTANCEEDGES.CHANNEL_ENTITY_KEY;
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
        return this.getMapSpecId();
    }

    @Override
    public String value4() {
        return this.getFromChannelName();
    }

    @Override
    public String value5() {
        return this.getFromEntityType();
    }

    @Override
    public String value6() {
        return this.getFromEntityId();
    }

    @Override
    public String value7() {
        return this.getToChannelName();
    }

    @Override
    public String value8() {
        return this.getToEntityType();
    }

    @Override
    public String value9() {
        return this.getToEntityId();
    }

    @Override
    public String value10() {
        return this.getAssociationKey();
    }

    @Override
    public String value11() {
        return this.getAssociationCardinality();
    }

    @Override
    public Boolean value12() {
        return this.getIsDeleted();
    }

    @Override
    public Boolean value13() {
        return this.getCaseInsensitive();
    }

    @Override
    public Long value14() {
        return this.getTimestamp();
    }

    @Override
    public String value15() {
        return this.getChannelEntityKey();
    }

    public EntityinstanceedgesRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public EntityinstanceedgesRecord value2(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public EntityinstanceedgesRecord value3(Long value) {
        this.setMapSpecId(value);
        return this;
    }

    public EntityinstanceedgesRecord value4(String value) {
        this.setFromChannelName(value);
        return this;
    }

    public EntityinstanceedgesRecord value5(String value) {
        this.setFromEntityType(value);
        return this;
    }

    public EntityinstanceedgesRecord value6(String value) {
        this.setFromEntityId(value);
        return this;
    }

    public EntityinstanceedgesRecord value7(String value) {
        this.setToChannelName(value);
        return this;
    }

    public EntityinstanceedgesRecord value8(String value) {
        this.setToEntityType(value);
        return this;
    }

    public EntityinstanceedgesRecord value9(String value) {
        this.setToEntityId(value);
        return this;
    }

    public EntityinstanceedgesRecord value10(String value) {
        this.setAssociationKey(value);
        return this;
    }

    public EntityinstanceedgesRecord value11(String value) {
        this.setAssociationCardinality(value);
        return this;
    }

    public EntityinstanceedgesRecord value12(Boolean value) {
        this.setIsDeleted(value);
        return this;
    }

    public EntityinstanceedgesRecord value13(Boolean value) {
        this.setCaseInsensitive(value);
        return this;
    }

    public EntityinstanceedgesRecord value14(Long value) {
        this.setTimestamp(value);
        return this;
    }

    public EntityinstanceedgesRecord value15(String value) {
        this.setChannelEntityKey(value);
        return this;
    }

    public EntityinstanceedgesRecord values(Long value1, Long value2, Long value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, Boolean value12, Boolean value13, Long value14, String value15) {
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
        return this;
    }

    public EntityinstanceedgesRecord() {
        super(Entityinstanceedges.ENTITYINSTANCEEDGES);
    }

    public EntityinstanceedgesRecord(Long id, Long organizationId, Long mapSpecId, String fromChannelName, String fromEntityType, String fromEntityId, String toChannelName, String toEntityType, String toEntityId, String associationKey, String associationCardinality, Boolean isDeleted, Boolean caseInsensitive, Long timestamp, String channelEntityKey) {
        super(Entityinstanceedges.ENTITYINSTANCEEDGES);
        this.set(0, id);
        this.set(1, organizationId);
        this.set(2, mapSpecId);
        this.set(3, fromChannelName);
        this.set(4, fromEntityType);
        this.set(5, fromEntityId);
        this.set(6, toChannelName);
        this.set(7, toEntityType);
        this.set(8, toEntityId);
        this.set(9, associationKey);
        this.set(10, associationCardinality);
        this.set(11, isDeleted);
        this.set(12, caseInsensitive);
        this.set(13, timestamp);
        this.set(14, channelEntityKey);
    }
}

