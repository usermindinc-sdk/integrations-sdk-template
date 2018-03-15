/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Entitytravelermap;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row;
import org.jooq.Row6;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class EntitytravelermapRecord
extends UpdatableRecordImpl<EntitytravelermapRecord>
implements Record6<Long, Long, String, String, String, String> {
    private static final long serialVersionUID = -1524850876L;

    public void setId(Long value) {
        this.set(0, value);
    }

    public Long getId() {
        return (Long)this.get(0);
    }

    public void setJourneyId(Long value) {
        this.set(1, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(1);
    }

    public void setChannelName(String value) {
        this.set(2, value);
    }

    public String getChannelName() {
        return (String)this.get(2);
    }

    public void setEntityType(String value) {
        this.set(3, value);
    }

    public String getEntityType() {
        return (String)this.get(3);
    }

    public void setEntityId(String value) {
        this.set(4, value);
    }

    public String getEntityId() {
        return (String)this.get(4);
    }

    public void setTravelerIds(String value) {
        this.set(5, value);
    }

    public String getTravelerIds() {
        return (String)this.get(5);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row6<Long, Long, String, String, String, String> fieldsRow() {
        return (Row6)super.fieldsRow();
    }

    @Override
    public Row6<Long, Long, String, String, String, String> valuesRow() {
        return (Row6)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Entitytravelermap.ENTITYTRAVELERMAP.ID;
    }

    @Override
    public Field<Long> field2() {
        return Entitytravelermap.ENTITYTRAVELERMAP.JOURNEY_ID;
    }

    @Override
    public Field<String> field3() {
        return Entitytravelermap.ENTITYTRAVELERMAP.CHANNEL_NAME;
    }

    @Override
    public Field<String> field4() {
        return Entitytravelermap.ENTITYTRAVELERMAP.ENTITY_TYPE;
    }

    @Override
    public Field<String> field5() {
        return Entitytravelermap.ENTITYTRAVELERMAP.ENTITY_ID;
    }

    @Override
    public Field<String> field6() {
        return Entitytravelermap.ENTITYTRAVELERMAP.TRAVELER_IDS;
    }

    @Override
    public Long value1() {
        return this.getId();
    }

    @Override
    public Long value2() {
        return this.getJourneyId();
    }

    @Override
    public String value3() {
        return this.getChannelName();
    }

    @Override
    public String value4() {
        return this.getEntityType();
    }

    @Override
    public String value5() {
        return this.getEntityId();
    }

    @Override
    public String value6() {
        return this.getTravelerIds();
    }

    public EntitytravelermapRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public EntitytravelermapRecord value2(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public EntitytravelermapRecord value3(String value) {
        this.setChannelName(value);
        return this;
    }

    public EntitytravelermapRecord value4(String value) {
        this.setEntityType(value);
        return this;
    }

    public EntitytravelermapRecord value5(String value) {
        this.setEntityId(value);
        return this;
    }

    public EntitytravelermapRecord value6(String value) {
        this.setTravelerIds(value);
        return this;
    }

    public EntitytravelermapRecord values(Long value1, Long value2, String value3, String value4, String value5, String value6) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        this.value4(value4);
        this.value5(value5);
        this.value6(value6);
        return this;
    }

    public EntitytravelermapRecord() {
        super(Entitytravelermap.ENTITYTRAVELERMAP);
    }

    public EntitytravelermapRecord(Long id, Long journeyId, String channelName, String entityType, String entityId, String travelerIds) {
        super(Entitytravelermap.ENTITYTRAVELERMAP);
        this.set(0, id);
        this.set(1, journeyId);
        this.set(2, channelName);
        this.set(3, entityType);
        this.set(4, entityId);
        this.set(5, travelerIds);
    }
}

