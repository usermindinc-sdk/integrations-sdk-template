/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Travelerchannels;
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

public class TravelerchannelsRecord
extends UpdatableRecordImpl<TravelerchannelsRecord>
implements Record16<Long, Long, Long, String, String, String, Timestamp, Timestamp, String, Long, Boolean, Boolean, String[], Long, Long, Long> {
    private static final long serialVersionUID = 309063460L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setTravelerId(Long value) {
        this.set(1, value);
    }

    public Long getTravelerId() {
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

    public void setEntityId(String value) {
        this.set(5, value);
    }

    public String getEntityId() {
        return (String)this.get(5);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(6, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(6);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(7, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(7);
    }

    public void setState(String value) {
        this.set(8, value);
    }

    public String getState() {
        return (String)this.get(8);
    }

    public void setJourneyId(Long value) {
        this.set(9, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(9);
    }

    public void setIsLeader(Boolean value) {
        this.set(10, value);
    }

    public Boolean getIsLeader() {
        return (Boolean)this.get(10);
    }

    public void setIsDisabled(Boolean value) {
        this.set(11, value);
    }

    public Boolean getIsDisabled() {
        return (Boolean)this.get(11);
    }

    public void setPaths(String[] value) {
        this.set(12, value);
    }

    public String[] getPaths() {
        return (String[])this.get(12);
    }

    public void setUpdatedTimestamp(Long value) {
        this.set(13, value);
    }

    public Long getUpdatedTimestamp() {
        return (Long)this.get(13);
    }

    public void setNewTimestamp(Long value) {
        this.set(14, value);
    }

    public Long getNewTimestamp() {
        return (Long)this.get(14);
    }

    public void setVersion(Long value) {
        this.set(15, value);
    }

    public Long getVersion() {
        return (Long)this.get(15);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row16<Long, Long, Long, String, String, String, Timestamp, Timestamp, String, Long, Boolean, Boolean, String[], Long, Long, Long> fieldsRow() {
        return (Row16)super.fieldsRow();
    }

    @Override
    public Row16<Long, Long, Long, String, String, String, Timestamp, Timestamp, String, Long, Boolean, Boolean, String[], Long, Long, Long> valuesRow() {
        return (Row16)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Travelerchannels.TRAVELERCHANNELS.ID;
    }

    @Override
    public Field<Long> field2() {
        return Travelerchannels.TRAVELERCHANNELS.TRAVELER_ID;
    }

    @Override
    public Field<Long> field3() {
        return Travelerchannels.TRAVELERCHANNELS.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field4() {
        return Travelerchannels.TRAVELERCHANNELS.CHANNEL_NAME;
    }

    @Override
    public Field<String> field5() {
        return Travelerchannels.TRAVELERCHANNELS.ENTITY_TYPE;
    }

    @Override
    public Field<String> field6() {
        return Travelerchannels.TRAVELERCHANNELS.ENTITY_ID;
    }

    @Override
    public Field<Timestamp> field7() {
        return Travelerchannels.TRAVELERCHANNELS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field8() {
        return Travelerchannels.TRAVELERCHANNELS.UPDATED_AT;
    }

    @Override
    public Field<String> field9() {
        return Travelerchannels.TRAVELERCHANNELS.STATE;
    }

    @Override
    public Field<Long> field10() {
        return Travelerchannels.TRAVELERCHANNELS.JOURNEY_ID;
    }

    @Override
    public Field<Boolean> field11() {
        return Travelerchannels.TRAVELERCHANNELS.IS_LEADER;
    }

    @Override
    public Field<Boolean> field12() {
        return Travelerchannels.TRAVELERCHANNELS.IS_DISABLED;
    }

    @Override
    public Field<String[]> field13() {
        return Travelerchannels.TRAVELERCHANNELS.PATHS;
    }

    @Override
    public Field<Long> field14() {
        return Travelerchannels.TRAVELERCHANNELS.UPDATED_TIMESTAMP;
    }

    @Override
    public Field<Long> field15() {
        return Travelerchannels.TRAVELERCHANNELS.NEW_TIMESTAMP;
    }

    @Override
    public Field<Long> field16() {
        return Travelerchannels.TRAVELERCHANNELS.VERSION;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public Long value2() {
        return this.getTravelerId();
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
    public String value6() {
        return this.getEntityId();
    }

    @Override
    public Timestamp value7() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value8() {
        return this.getUpdatedAt();
    }

    @Override
    public String value9() {
        return this.getState();
    }

    @Override
    public Long value10() {
        return this.getJourneyId();
    }

    @Override
    public Boolean value11() {
        return this.getIsLeader();
    }

    @Override
    public Boolean value12() {
        return this.getIsDisabled();
    }

    @Override
    public String[] value13() {
        return this.getPaths();
    }

    @Override
    public Long value14() {
        return this.getUpdatedTimestamp();
    }

    @Override
    public Long value15() {
        return this.getNewTimestamp();
    }

    @Override
    public Long value16() {
        return this.getVersion();
    }

    public TravelerchannelsRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public TravelerchannelsRecord value2(Long value) {
        this.setTravelerId(value);
        return this;
    }

    public TravelerchannelsRecord value3(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public TravelerchannelsRecord value4(String value) {
        this.setChannelName(value);
        return this;
    }

    public TravelerchannelsRecord value5(String value) {
        this.setEntityType(value);
        return this;
    }

    public TravelerchannelsRecord value6(String value) {
        this.setEntityId(value);
        return this;
    }

    public TravelerchannelsRecord value7(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public TravelerchannelsRecord value8(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public TravelerchannelsRecord value9(String value) {
        this.setState(value);
        return this;
    }

    public TravelerchannelsRecord value10(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public TravelerchannelsRecord value11(Boolean value) {
        this.setIsLeader(value);
        return this;
    }

    public TravelerchannelsRecord value12(Boolean value) {
        this.setIsDisabled(value);
        return this;
    }

    public TravelerchannelsRecord value13(String[] value) {
        this.setPaths(value);
        return this;
    }

    public TravelerchannelsRecord value14(Long value) {
        this.setUpdatedTimestamp(value);
        return this;
    }

    public TravelerchannelsRecord value15(Long value) {
        this.setNewTimestamp(value);
        return this;
    }

    public TravelerchannelsRecord value16(Long value) {
        this.setVersion(value);
        return this;
    }

    public TravelerchannelsRecord values(Long value1, Long value2, Long value3, String value4, String value5, String value6, Timestamp value7, Timestamp value8, String value9, Long value10, Boolean value11, Boolean value12, String[] value13, Long value14, Long value15, Long value16) {
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

    public TravelerchannelsRecord() {
        super(Travelerchannels.TRAVELERCHANNELS);
    }

    public TravelerchannelsRecord(Long id, Long travelerId, Long organizationId, String channelName, String entityType, String entityId, Timestamp createdAt, Timestamp updatedAt, String state, Long journeyId, Boolean isLeader, Boolean isDisabled, String[] paths, Long updatedTimestamp, Long newTimestamp, Long version) {
        super(Travelerchannels.TRAVELERCHANNELS);
        this.set(0, id);
        this.set(1, travelerId);
        this.set(2, organizationId);
        this.set(3, channelName);
        this.set(4, entityType);
        this.set(5, entityId);
        this.set(6, createdAt);
        this.set(7, updatedAt);
        this.set(8, state);
        this.set(9, journeyId);
        this.set(10, isLeader);
        this.set(11, isDisabled);
        this.set(12, paths);
        this.set(13, updatedTimestamp);
        this.set(14, newTimestamp);
        this.set(15, version);
    }
}

