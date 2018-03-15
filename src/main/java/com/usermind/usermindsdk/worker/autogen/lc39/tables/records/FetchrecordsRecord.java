/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Fetchrecords;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record21;
import org.jooq.Row;
import org.jooq.Row21;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class FetchrecordsRecord
extends UpdatableRecordImpl<FetchrecordsRecord>
implements Record21<Long, Long, Long, String, String, Timestamp, Timestamp, String, String, String, Integer, Integer, String, Integer, String, Long, Timestamp, Timestamp, String, Integer, Integer> {
    private static final long serialVersionUID = -1637106767L;

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

    public void setTimestamp(Long value) {
        this.set(2, value);
    }

    public Long getTimestamp() {
        return (Long)this.get(2);
    }

    public void setType(String value) {
        this.set(3, value);
    }

    public String getType() {
        return (String)this.get(3);
    }

    public void setName(String value) {
        this.set(4, value);
    }

    public String getName() {
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

    public void setEntityType(String value) {
        this.set(7, value);
    }

    public String getEntityType() {
        return (String)this.get(7);
    }

    public void setStatus(String value) {
        this.set(8, value);
    }

    public String getStatus() {
        return (String)this.get(8);
    }

    public void setJobId(String value) {
        this.set(9, value);
    }

    public String getJobId() {
        return (String)this.get(9);
    }

    public void setSpecVersion(Integer value) {
        this.set(10, value);
    }

    public Integer getSpecVersion() {
        return (Integer)this.get(10);
    }

    public void setDefaultsVersion(Integer value) {
        this.set(11, value);
    }

    public Integer getDefaultsVersion() {
        return (Integer)this.get(11);
    }

    public void setTranslation(String value) {
        this.set(12, value);
    }

    public String getTranslation() {
        return (String)this.get(12);
    }

    public void setTranslationVersion(Integer value) {
        this.set(13, value);
    }

    public Integer getTranslationVersion() {
        return (Integer)this.get(13);
    }

    public void setChannelName(String value) {
        this.set(14, value);
    }

    public String getChannelName() {
        return (String)this.get(14);
    }

    public void setWatermark(Long value) {
        this.set(15, value);
    }

    public Long getWatermark() {
        return (Long)this.get(15);
    }

    public void setProcessingStarted(Timestamp value) {
        this.set(16, value);
    }

    public Timestamp getProcessingStarted() {
        return (Timestamp)this.get(16);
    }

    public void setProcessingFinished(Timestamp value) {
        this.set(17, value);
    }

    public Timestamp getProcessingFinished() {
        return (Timestamp)this.get(17);
    }

    public void setPath(String value) {
        this.set(18, value);
    }

    public String getPath() {
        return (String)this.get(18);
    }

    public void setProcState(Integer value) {
        this.set(19, value);
    }

    public Integer getProcState() {
        return (Integer)this.get(19);
    }

    public void setRecProcessed(Integer value) {
        this.set(20, value);
    }

    public Integer getRecProcessed() {
        return (Integer)this.get(20);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row21<Long, Long, Long, String, String, Timestamp, Timestamp, String, String, String, Integer, Integer, String, Integer, String, Long, Timestamp, Timestamp, String, Integer, Integer> fieldsRow() {
        return (Row21)super.fieldsRow();
    }

    @Override
    public Row21<Long, Long, Long, String, String, Timestamp, Timestamp, String, String, String, Integer, Integer, String, Integer, String, Long, Timestamp, Timestamp, String, Integer, Integer> valuesRow() {
        return (Row21)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Fetchrecords.FETCHRECORDS.ID;
    }

    @Override
    public Field<Long> field2() {
        return Fetchrecords.FETCHRECORDS.ORGANIZATION_ID;
    }

    @Override
    public Field<Long> field3() {
        return Fetchrecords.FETCHRECORDS.TIMESTAMP;
    }

    @Override
    public Field<String> field4() {
        return Fetchrecords.FETCHRECORDS.TYPE;
    }

    @Override
    public Field<String> field5() {
        return Fetchrecords.FETCHRECORDS.NAME;
    }

    @Override
    public Field<Timestamp> field6() {
        return Fetchrecords.FETCHRECORDS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field7() {
        return Fetchrecords.FETCHRECORDS.UPDATED_AT;
    }

    @Override
    public Field<String> field8() {
        return Fetchrecords.FETCHRECORDS.ENTITY_TYPE;
    }

    @Override
    public Field<String> field9() {
        return Fetchrecords.FETCHRECORDS.STATUS;
    }

    @Override
    public Field<String> field10() {
        return Fetchrecords.FETCHRECORDS.JOB_ID;
    }

    @Override
    public Field<Integer> field11() {
        return Fetchrecords.FETCHRECORDS.SPEC_VERSION;
    }

    @Override
    public Field<Integer> field12() {
        return Fetchrecords.FETCHRECORDS.DEFAULTS_VERSION;
    }

    @Override
    public Field<String> field13() {
        return Fetchrecords.FETCHRECORDS.TRANSLATION;
    }

    @Override
    public Field<Integer> field14() {
        return Fetchrecords.FETCHRECORDS.TRANSLATION_VERSION;
    }

    @Override
    public Field<String> field15() {
        return Fetchrecords.FETCHRECORDS.CHANNEL_NAME;
    }

    @Override
    public Field<Long> field16() {
        return Fetchrecords.FETCHRECORDS.WATERMARK;
    }

    @Override
    public Field<Timestamp> field17() {
        return Fetchrecords.FETCHRECORDS.PROCESSING_STARTED;
    }

    @Override
    public Field<Timestamp> field18() {
        return Fetchrecords.FETCHRECORDS.PROCESSING_FINISHED;
    }

    @Override
    public Field<String> field19() {
        return Fetchrecords.FETCHRECORDS.PATH;
    }

    @Override
    public Field<Integer> field20() {
        return Fetchrecords.FETCHRECORDS.PROC_STATE;
    }

    @Override
    public Field<Integer> field21() {
        return Fetchrecords.FETCHRECORDS.REC_PROCESSED;
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
        return this.getTimestamp();
    }

    @Override
    public String value4() {
        return this.getType();
    }

    @Override
    public String value5() {
        return this.getName();
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
    public String value8() {
        return this.getEntityType();
    }

    @Override
    public String value9() {
        return this.getStatus();
    }

    @Override
    public String value10() {
        return this.getJobId();
    }

    @Override
    public Integer value11() {
        return this.getSpecVersion();
    }

    @Override
    public Integer value12() {
        return this.getDefaultsVersion();
    }

    @Override
    public String value13() {
        return this.getTranslation();
    }

    @Override
    public Integer value14() {
        return this.getTranslationVersion();
    }

    @Override
    public String value15() {
        return this.getChannelName();
    }

    @Override
    public Long value16() {
        return this.getWatermark();
    }

    @Override
    public Timestamp value17() {
        return this.getProcessingStarted();
    }

    @Override
    public Timestamp value18() {
        return this.getProcessingFinished();
    }

    @Override
    public String value19() {
        return this.getPath();
    }

    @Override
    public Integer value20() {
        return this.getProcState();
    }

    @Override
    public Integer value21() {
        return this.getRecProcessed();
    }

    public FetchrecordsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public FetchrecordsRecord value2(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public FetchrecordsRecord value3(Long value) {
        this.setTimestamp(value);
        return this;
    }

    public FetchrecordsRecord value4(String value) {
        this.setType(value);
        return this;
    }

    public FetchrecordsRecord value5(String value) {
        this.setName(value);
        return this;
    }

    public FetchrecordsRecord value6(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public FetchrecordsRecord value7(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public FetchrecordsRecord value8(String value) {
        this.setEntityType(value);
        return this;
    }

    public FetchrecordsRecord value9(String value) {
        this.setStatus(value);
        return this;
    }

    public FetchrecordsRecord value10(String value) {
        this.setJobId(value);
        return this;
    }

    public FetchrecordsRecord value11(Integer value) {
        this.setSpecVersion(value);
        return this;
    }

    public FetchrecordsRecord value12(Integer value) {
        this.setDefaultsVersion(value);
        return this;
    }

    public FetchrecordsRecord value13(String value) {
        this.setTranslation(value);
        return this;
    }

    public FetchrecordsRecord value14(Integer value) {
        this.setTranslationVersion(value);
        return this;
    }

    public FetchrecordsRecord value15(String value) {
        this.setChannelName(value);
        return this;
    }

    public FetchrecordsRecord value16(Long value) {
        this.setWatermark(value);
        return this;
    }

    public FetchrecordsRecord value17(Timestamp value) {
        this.setProcessingStarted(value);
        return this;
    }

    public FetchrecordsRecord value18(Timestamp value) {
        this.setProcessingFinished(value);
        return this;
    }

    public FetchrecordsRecord value19(String value) {
        this.setPath(value);
        return this;
    }

    public FetchrecordsRecord value20(Integer value) {
        this.setProcState(value);
        return this;
    }

    public FetchrecordsRecord value21(Integer value) {
        this.setRecProcessed(value);
        return this;
    }

    public FetchrecordsRecord values(Long value1, Long value2, Long value3, String value4, String value5, Timestamp value6, Timestamp value7, String value8, String value9, String value10, Integer value11, Integer value12, String value13, Integer value14, String value15, Long value16, Timestamp value17, Timestamp value18, String value19, Integer value20, Integer value21) {
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
        this.value17(value17);
        this.value18(value18);
        this.value19(value19);
        this.value20(value20);
        this.value21(value21);
        return this;
    }

    public FetchrecordsRecord() {
        super(Fetchrecords.FETCHRECORDS);
    }

    public FetchrecordsRecord(Long id, Long organizationId, Long timestamp, String type, String name, Timestamp createdAt, Timestamp updatedAt, String entityType, String status, String jobId, Integer specVersion, Integer defaultsVersion, String translation, Integer translationVersion, String channelName, Long watermark, Timestamp processingStarted, Timestamp processingFinished, String path, Integer procState, Integer recProcessed) {
        super(Fetchrecords.FETCHRECORDS);
        this.set(0, id);
        this.set(1, organizationId);
        this.set(2, timestamp);
        this.set(3, type);
        this.set(4, name);
        this.set(5, createdAt);
        this.set(6, updatedAt);
        this.set(7, entityType);
        this.set(8, status);
        this.set(9, jobId);
        this.set(10, specVersion);
        this.set(11, defaultsVersion);
        this.set(12, translation);
        this.set(13, translationVersion);
        this.set(14, channelName);
        this.set(15, watermark);
        this.set(16, processingStarted);
        this.set(17, processingFinished);
        this.set(18, path);
        this.set(19, procState);
        this.set(20, recProcessed);
    }
}

