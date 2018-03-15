/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Staticentitymapspecification;
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

public class StaticentitymapspecificationRecord
extends UpdatableRecordImpl<StaticentitymapspecificationRecord>
implements Record11<Long, Long, String, String, String, String, String, String, Timestamp, Boolean, String> {
    private static final long serialVersionUID = -386524644L;

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

    public void setFromChannelName(String value) {
        this.set(2, value);
    }

    public String getFromChannelName() {
        return (String)this.get(2);
    }

    public void setFromEntityType(String value) {
        this.set(3, value);
    }

    public String getFromEntityType() {
        return (String)this.get(3);
    }

    public void setToChannelName(String value) {
        this.set(4, value);
    }

    public String getToChannelName() {
        return (String)this.get(4);
    }

    public void setToEntityType(String value) {
        this.set(5, value);
    }

    public String getToEntityType() {
        return (String)this.get(5);
    }

    public void setAssociationKey(String value) {
        this.set(6, value);
    }

    public String getAssociationKey() {
        return (String)this.get(6);
    }

    public void setAssociationCardinality(String value) {
        this.set(7, value);
    }

    public String getAssociationCardinality() {
        return (String)this.get(7);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(8, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(8);
    }

    public void setCaseInsensitive(Boolean value) {
        this.set(9, value);
    }

    public Boolean getCaseInsensitive() {
        return (Boolean)this.get(9);
    }

    public void setEdgeLabel(String value) {
        this.set(10, value);
    }

    public String getEdgeLabel() {
        return (String)this.get(10);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row11<Long, Long, String, String, String, String, String, String, Timestamp, Boolean, String> fieldsRow() {
        return (Row11)super.fieldsRow();
    }

    @Override
    public Row11<Long, Long, String, String, String, String, String, String, Timestamp, Boolean, String> valuesRow() {
        return (Row11)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.ID;
    }

    @Override
    public Field<Long> field2() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field3() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.FROM_CHANNEL_NAME;
    }

    @Override
    public Field<String> field4() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.FROM_ENTITY_TYPE;
    }

    @Override
    public Field<String> field5() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.TO_CHANNEL_NAME;
    }

    @Override
    public Field<String> field6() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.TO_ENTITY_TYPE;
    }

    @Override
    public Field<String> field7() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.ASSOCIATION_KEY;
    }

    @Override
    public Field<String> field8() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.ASSOCIATION_CARDINALITY;
    }

    @Override
    public Field<Timestamp> field9() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.CREATED_AT;
    }

    @Override
    public Field<Boolean> field10() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.CASE_INSENSITIVE;
    }

    @Override
    public Field<String> field11() {
        return Staticentitymapspecification.STATICENTITYMAPSPECIFICATION.EDGE_LABEL;
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
        return this.getFromChannelName();
    }

    @Override
    public String value4() {
        return this.getFromEntityType();
    }

    @Override
    public String value5() {
        return this.getToChannelName();
    }

    @Override
    public String value6() {
        return this.getToEntityType();
    }

    @Override
    public String value7() {
        return this.getAssociationKey();
    }

    @Override
    public String value8() {
        return this.getAssociationCardinality();
    }

    @Override
    public Timestamp value9() {
        return this.getCreatedAt();
    }

    @Override
    public Boolean value10() {
        return this.getCaseInsensitive();
    }

    @Override
    public String value11() {
        return this.getEdgeLabel();
    }

    public StaticentitymapspecificationRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public StaticentitymapspecificationRecord value2(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public StaticentitymapspecificationRecord value3(String value) {
        this.setFromChannelName(value);
        return this;
    }

    public StaticentitymapspecificationRecord value4(String value) {
        this.setFromEntityType(value);
        return this;
    }

    public StaticentitymapspecificationRecord value5(String value) {
        this.setToChannelName(value);
        return this;
    }

    public StaticentitymapspecificationRecord value6(String value) {
        this.setToEntityType(value);
        return this;
    }

    public StaticentitymapspecificationRecord value7(String value) {
        this.setAssociationKey(value);
        return this;
    }

    public StaticentitymapspecificationRecord value8(String value) {
        this.setAssociationCardinality(value);
        return this;
    }

    public StaticentitymapspecificationRecord value9(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public StaticentitymapspecificationRecord value10(Boolean value) {
        this.setCaseInsensitive(value);
        return this;
    }

    public StaticentitymapspecificationRecord value11(String value) {
        this.setEdgeLabel(value);
        return this;
    }

    public StaticentitymapspecificationRecord values(Long value1, Long value2, String value3, String value4, String value5, String value6, String value7, String value8, Timestamp value9, Boolean value10, String value11) {
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

    public StaticentitymapspecificationRecord() {
        super(Staticentitymapspecification.STATICENTITYMAPSPECIFICATION);
    }

    public StaticentitymapspecificationRecord(Long id, Long organizationId, String fromChannelName, String fromEntityType, String toChannelName, String toEntityType, String associationKey, String associationCardinality, Timestamp createdAt, Boolean caseInsensitive, String edgeLabel) {
        super(Staticentitymapspecification.STATICENTITYMAPSPECIFICATION);
        this.set(0, id);
        this.set(1, organizationId);
        this.set(2, fromChannelName);
        this.set(3, fromEntityType);
        this.set(4, toChannelName);
        this.set(5, toEntityType);
        this.set(6, associationKey);
        this.set(7, associationCardinality);
        this.set(8, createdAt);
        this.set(9, caseInsensitive);
        this.set(10, edgeLabel);
    }
}

