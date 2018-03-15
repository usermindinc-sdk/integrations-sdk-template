/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Proctriggers;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record13;
import org.jooq.Row;
import org.jooq.Row13;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableRecordImpl;

public class ProctriggersRecord
extends TableRecordImpl<ProctriggersRecord>
implements Record13<Long, String, Long, Long, Long, Long, Long, Integer, Timestamp, Timestamp, Object, Timestamp, Timestamp> {
    private static final long serialVersionUID = 712714354L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setType(String value) {
        this.set(1, value);
    }

    public String getType() {
        return (String)this.get(1);
    }

    public void setOrganizationId(Long value) {
        this.set(2, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(2);
    }

    public void setTimestamp(Long value) {
        this.set(3, value);
    }

    public Long getTimestamp() {
        return (Long)this.get(3);
    }

    public void setMapSpecId(Long value) {
        this.set(4, value);
    }

    public Long getMapSpecId() {
        return (Long)this.get(4);
    }

    public void setJourneyId(Long value) {
        this.set(5, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(5);
    }

    public void setParentId(Long value) {
        this.set(6, value);
    }

    public Long getParentId() {
        return (Long)this.get(6);
    }

    public void setProcState(Integer value) {
        this.set(7, value);
    }

    public Integer getProcState() {
        return (Integer)this.get(7);
    }

    public void setProcessingStarted(Timestamp value) {
        this.set(8, value);
    }

    public Timestamp getProcessingStarted() {
        return (Timestamp)this.get(8);
    }

    public void setProcessingFinished(Timestamp value) {
        this.set(9, value);
    }

    public Timestamp getProcessingFinished() {
        return (Timestamp)this.get(9);
    }

    public void setCheckpointData(Object value) {
        this.set(10, value);
    }

    public Object getCheckpointData() {
        return this.get(10);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(11, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(11);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(12, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(12);
    }

    @Override
    public Row13<Long, String, Long, Long, Long, Long, Long, Integer, Timestamp, Timestamp, Object, Timestamp, Timestamp> fieldsRow() {
        return (Row13)super.fieldsRow();
    }

    @Override
    public Row13<Long, String, Long, Long, Long, Long, Long, Integer, Timestamp, Timestamp, Object, Timestamp, Timestamp> valuesRow() {
        return (Row13)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Proctriggers.PROCTRIGGERS.ID;
    }

    @Override
    public Field<String> field2() {
        return Proctriggers.PROCTRIGGERS.TYPE;
    }

    @Override
    public Field<Long> field3() {
        return Proctriggers.PROCTRIGGERS.ORGANIZATION_ID;
    }

    @Override
    public Field<Long> field4() {
        return Proctriggers.PROCTRIGGERS.TIMESTAMP;
    }

    @Override
    public Field<Long> field5() {
        return Proctriggers.PROCTRIGGERS.MAP_SPEC_ID;
    }

    @Override
    public Field<Long> field6() {
        return Proctriggers.PROCTRIGGERS.JOURNEY_ID;
    }

    @Override
    public Field<Long> field7() {
        return Proctriggers.PROCTRIGGERS.PARENT_ID;
    }

    @Override
    public Field<Integer> field8() {
        return Proctriggers.PROCTRIGGERS.PROC_STATE;
    }

    @Override
    public Field<Timestamp> field9() {
        return Proctriggers.PROCTRIGGERS.PROCESSING_STARTED;
    }

    @Override
    public Field<Timestamp> field10() {
        return Proctriggers.PROCTRIGGERS.PROCESSING_FINISHED;
    }

    @Override
    public Field<Object> field11() {
        return Proctriggers.PROCTRIGGERS.CHECKPOINT_DATA;
    }

    @Override
    public Field<Timestamp> field12() {
        return Proctriggers.PROCTRIGGERS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field13() {
        return Proctriggers.PROCTRIGGERS.UPDATED_AT;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public String value2() {
        return this.getType();
    }

    @Override
    public Long value3() {
        return this.getOrganizationId();
    }

    @Override
    public Long value4() {
        return this.getTimestamp();
    }

    @Override
    public Long value5() {
        return this.getMapSpecId();
    }

    @Override
    public Long value6() {
        return this.getJourneyId();
    }

    @Override
    public Long value7() {
        return this.getParentId();
    }

    @Override
    public Integer value8() {
        return this.getProcState();
    }

    @Override
    public Timestamp value9() {
        return this.getProcessingStarted();
    }

    @Override
    public Timestamp value10() {
        return this.getProcessingFinished();
    }

    @Override
    public Object value11() {
        return this.getCheckpointData();
    }

    @Override
    public Timestamp value12() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value13() {
        return this.getUpdatedAt();
    }

    public ProctriggersRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public ProctriggersRecord value2(String value) {
        this.setType(value);
        return this;
    }

    public ProctriggersRecord value3(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public ProctriggersRecord value4(Long value) {
        this.setTimestamp(value);
        return this;
    }

    public ProctriggersRecord value5(Long value) {
        this.setMapSpecId(value);
        return this;
    }

    public ProctriggersRecord value6(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public ProctriggersRecord value7(Long value) {
        this.setParentId(value);
        return this;
    }

    public ProctriggersRecord value8(Integer value) {
        this.setProcState(value);
        return this;
    }

    public ProctriggersRecord value9(Timestamp value) {
        this.setProcessingStarted(value);
        return this;
    }

    public ProctriggersRecord value10(Timestamp value) {
        this.setProcessingFinished(value);
        return this;
    }

    public ProctriggersRecord value11(Object value) {
        this.setCheckpointData(value);
        return this;
    }

    public ProctriggersRecord value12(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public ProctriggersRecord value13(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public ProctriggersRecord values(Long value1, String value2, Long value3, Long value4, Long value5, Long value6, Long value7, Integer value8, Timestamp value9, Timestamp value10, Object value11, Timestamp value12, Timestamp value13) {
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
        return this;
    }

    public ProctriggersRecord() {
        super(Proctriggers.PROCTRIGGERS);
    }

    public ProctriggersRecord(Long id, String type, Long organizationId, Long timestamp, Long mapSpecId, Long journeyId, Long parentId, Integer procState, Timestamp processingStarted, Timestamp processingFinished, Object checkpointData, Timestamp createdAt, Timestamp updatedAt) {
        super(Proctriggers.PROCTRIGGERS);
        this.set(0, id);
        this.set(1, type);
        this.set(2, organizationId);
        this.set(3, timestamp);
        this.set(4, mapSpecId);
        this.set(5, journeyId);
        this.set(6, parentId);
        this.set(7, procState);
        this.set(8, processingStarted);
        this.set(9, processingFinished);
        this.set(10, checkpointData);
        this.set(11, createdAt);
        this.set(12, updatedAt);
    }
}

