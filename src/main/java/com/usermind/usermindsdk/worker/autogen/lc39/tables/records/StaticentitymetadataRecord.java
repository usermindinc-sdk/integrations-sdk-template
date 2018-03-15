/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Staticentitymetadata;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row;
import org.jooq.Row10;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class StaticentitymetadataRecord
extends UpdatableRecordImpl<StaticentitymetadataRecord>
implements Record10<Long, Long, Long, String, String, Integer, Boolean, Timestamp, Boolean, Object> {
    private static final long serialVersionUID = -1325599008L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setMapSpecId(Long value) {
        this.set(1, value);
    }

    public Long getMapSpecId() {
        return (Long)this.get(1);
    }

    public void setOrganizationId(Long value) {
        this.set(2, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(2);
    }

    public void setChannelName(String value) {
        this.set(3, value);
    }

    public String getChannelName() {
        return (String)this.get(3);
    }

    public void setEntityType(String value) {
        this.set(4, value);
    }

    public String getEntityType() {
        return (String)this.get(4);
    }

    public void setRank(Integer value) {
        this.set(5, value);
    }

    public Integer getRank() {
        return (Integer)this.get(5);
    }

    public void setIsMappingOnly(Boolean value) {
        this.set(6, value);
    }

    public Boolean getIsMappingOnly() {
        return (Boolean)this.get(6);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(7, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(7);
    }

    public void setCombinePaths(Boolean value) {
        this.set(8, value);
    }

    public Boolean getCombinePaths() {
        return (Boolean)this.get(8);
    }

    public void setPathSpecification(Object value) {
        this.set(9, value);
    }

    public Object getPathSpecification() {
        return this.get(9);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row10<Long, Long, Long, String, String, Integer, Boolean, Timestamp, Boolean, Object> fieldsRow() {
        return (Row10)super.fieldsRow();
    }

    @Override
    public Row10<Long, Long, Long, String, String, Integer, Boolean, Timestamp, Boolean, Object> valuesRow() {
        return (Row10)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Staticentitymetadata.STATICENTITYMETADATA.ID;
    }

    @Override
    public Field<Long> field2() {
        return Staticentitymetadata.STATICENTITYMETADATA.MAP_SPEC_ID;
    }

    @Override
    public Field<Long> field3() {
        return Staticentitymetadata.STATICENTITYMETADATA.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field4() {
        return Staticentitymetadata.STATICENTITYMETADATA.CHANNEL_NAME;
    }

    @Override
    public Field<String> field5() {
        return Staticentitymetadata.STATICENTITYMETADATA.ENTITY_TYPE;
    }

    @Override
    public Field<Integer> field6() {
        return Staticentitymetadata.STATICENTITYMETADATA.RANK;
    }

    @Override
    public Field<Boolean> field7() {
        return Staticentitymetadata.STATICENTITYMETADATA.IS_MAPPING_ONLY;
    }

    @Override
    public Field<Timestamp> field8() {
        return Staticentitymetadata.STATICENTITYMETADATA.CREATED_AT;
    }

    @Override
    public Field<Boolean> field9() {
        return Staticentitymetadata.STATICENTITYMETADATA.COMBINE_PATHS;
    }

    @Override
    public Field<Object> field10() {
        return Staticentitymetadata.STATICENTITYMETADATA.PATH_SPECIFICATION;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public Long value2() {
        return this.getMapSpecId();
    }

    @Override
    public Long value3() {
        return this.getOrganizationId();
    }

    @Override
    public String value4() {
        return this.getChannelName();
    }

    @Override
    public String value5() {
        return this.getEntityType();
    }

    @Override
    public Integer value6() {
        return this.getRank();
    }

    @Override
    public Boolean value7() {
        return this.getIsMappingOnly();
    }

    @Override
    public Timestamp value8() {
        return this.getCreatedAt();
    }

    @Override
    public Boolean value9() {
        return this.getCombinePaths();
    }

    @Override
    public Object value10() {
        return this.getPathSpecification();
    }

    public StaticentitymetadataRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public StaticentitymetadataRecord value2(Long value) {
        this.setMapSpecId(value);
        return this;
    }

    public StaticentitymetadataRecord value3(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public StaticentitymetadataRecord value4(String value) {
        this.setChannelName(value);
        return this;
    }

    public StaticentitymetadataRecord value5(String value) {
        this.setEntityType(value);
        return this;
    }

    public StaticentitymetadataRecord value6(Integer value) {
        this.setRank(value);
        return this;
    }

    public StaticentitymetadataRecord value7(Boolean value) {
        this.setIsMappingOnly(value);
        return this;
    }

    public StaticentitymetadataRecord value8(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public StaticentitymetadataRecord value9(Boolean value) {
        this.setCombinePaths(value);
        return this;
    }

    public StaticentitymetadataRecord value10(Object value) {
        this.setPathSpecification(value);
        return this;
    }

    public StaticentitymetadataRecord values(Long value1, Long value2, Long value3, String value4, String value5, Integer value6, Boolean value7, Timestamp value8, Boolean value9, Object value10) {
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

    public StaticentitymetadataRecord() {
        super(Staticentitymetadata.STATICENTITYMETADATA);
    }

    public StaticentitymetadataRecord(Long id, Long mapSpecId, Long organizationId, String channelName, String entityType, Integer rank, Boolean isMappingOnly, Timestamp createdAt, Boolean combinePaths, Object pathSpecification) {
        super(Staticentitymetadata.STATICENTITYMETADATA);
        this.set(0, id);
        this.set(1, mapSpecId);
        this.set(2, organizationId);
        this.set(3, channelName);
        this.set(4, entityType);
        this.set(5, rank);
        this.set(6, isMappingOnly);
        this.set(7, createdAt);
        this.set(8, combinePaths);
        this.set(9, pathSpecification);
    }
}

