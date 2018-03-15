/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Entitygraphs;
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

public class EntitygraphsRecord
extends UpdatableRecordImpl<EntitygraphsRecord>
implements Record10<Long, Long, Long, Long, String, Boolean, String[], Timestamp, Object, Long> {
    private static final long serialVersionUID = -731820054L;

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

    public void setTimestamp(Long value) {
        this.set(3, value);
    }

    public Long getTimestamp() {
        return (Long)this.get(3);
    }

    public void setChannelEntityId(String value) {
        this.set(4, value);
    }

    public String getChannelEntityId() {
        return (String)this.get(4);
    }

    public void setIsDeleted(Boolean value) {
        this.set(5, value);
    }

    public Boolean getIsDeleted() {
        return (Boolean)this.get(5);
    }

    public void setMembers(String[] value) {
        this.set(6, value);
    }

    public String[] getMembers() {
        return (String[])this.get(6);
    }

    public void setProcessingFinished(Timestamp value) {
        this.set(7, value);
    }

    public Timestamp getProcessingFinished() {
        return (Timestamp)this.get(7);
    }

    public void setGraphs(Object value) {
        this.set(8, value);
    }

    public Object getGraphs() {
        return this.get(8);
    }

    public void setPreviousEntitygraphId(Long value) {
        this.set(9, value);
    }

    public Long getPreviousEntitygraphId() {
        return (Long)this.get(9);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row10<Long, Long, Long, Long, String, Boolean, String[], Timestamp, Object, Long> fieldsRow() {
        return (Row10)super.fieldsRow();
    }

    @Override
    public Row10<Long, Long, Long, Long, String, Boolean, String[], Timestamp, Object, Long> valuesRow() {
        return (Row10)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Entitygraphs.ENTITYGRAPHS.ID;
    }

    @Override
    public Field<Long> field2() {
        return Entitygraphs.ENTITYGRAPHS.ORGANIZATION_ID;
    }

    @Override
    public Field<Long> field3() {
        return Entitygraphs.ENTITYGRAPHS.MAP_SPEC_ID;
    }

    @Override
    public Field<Long> field4() {
        return Entitygraphs.ENTITYGRAPHS.TIMESTAMP;
    }

    @Override
    public Field<String> field5() {
        return Entitygraphs.ENTITYGRAPHS.CHANNEL_ENTITY_ID;
    }

    @Override
    public Field<Boolean> field6() {
        return Entitygraphs.ENTITYGRAPHS.IS_DELETED;
    }

    @Override
    public Field<String[]> field7() {
        return Entitygraphs.ENTITYGRAPHS.MEMBERS;
    }

    @Override
    public Field<Timestamp> field8() {
        return Entitygraphs.ENTITYGRAPHS.PROCESSING_FINISHED;
    }

    @Override
    public Field<Object> field9() {
        return Entitygraphs.ENTITYGRAPHS.GRAPHS;
    }

    @Override
    public Field<Long> field10() {
        return Entitygraphs.ENTITYGRAPHS.PREVIOUS_ENTITYGRAPH_ID;
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
    public Long value4() {
        return this.getTimestamp();
    }

    @Override
    public String value5() {
        return this.getChannelEntityId();
    }

    @Override
    public Boolean value6() {
        return this.getIsDeleted();
    }

    @Override
    public String[] value7() {
        return this.getMembers();
    }

    @Override
    public Timestamp value8() {
        return this.getProcessingFinished();
    }

    @Override
    public Object value9() {
        return this.getGraphs();
    }

    @Override
    public Long value10() {
        return this.getPreviousEntitygraphId();
    }

    public EntitygraphsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public EntitygraphsRecord value2(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public EntitygraphsRecord value3(Long value) {
        this.setMapSpecId(value);
        return this;
    }

    public EntitygraphsRecord value4(Long value) {
        this.setTimestamp(value);
        return this;
    }

    public EntitygraphsRecord value5(String value) {
        this.setChannelEntityId(value);
        return this;
    }

    public EntitygraphsRecord value6(Boolean value) {
        this.setIsDeleted(value);
        return this;
    }

    public EntitygraphsRecord value7(String[] value) {
        this.setMembers(value);
        return this;
    }

    public EntitygraphsRecord value8(Timestamp value) {
        this.setProcessingFinished(value);
        return this;
    }

    public EntitygraphsRecord value9(Object value) {
        this.setGraphs(value);
        return this;
    }

    public EntitygraphsRecord value10(Long value) {
        this.setPreviousEntitygraphId(value);
        return this;
    }

    public EntitygraphsRecord values(Long value1, Long value2, Long value3, Long value4, String value5, Boolean value6, String[] value7, Timestamp value8, Object value9, Long value10) {
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

    public EntitygraphsRecord() {
        super(Entitygraphs.ENTITYGRAPHS);
    }

    public EntitygraphsRecord(Long id, Long organizationId, Long mapSpecId, Long timestamp, String channelEntityId, Boolean isDeleted, String[] members, Timestamp processingFinished, Object graphs, Long previousEntitygraphId) {
        super(Entitygraphs.ENTITYGRAPHS);
        this.set(0, id);
        this.set(1, organizationId);
        this.set(2, mapSpecId);
        this.set(3, timestamp);
        this.set(4, channelEntityId);
        this.set(5, isDeleted);
        this.set(6, members);
        this.set(7, processingFinished);
        this.set(8, graphs);
        this.set(9, previousEntitygraphId);
    }
}

