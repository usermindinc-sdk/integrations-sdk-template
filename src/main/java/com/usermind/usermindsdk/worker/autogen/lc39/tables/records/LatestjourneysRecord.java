/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Latestjourneys;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record12;
import org.jooq.Row;
import org.jooq.Row12;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableRecordImpl;

public class LatestjourneysRecord
extends TableRecordImpl<LatestjourneysRecord>
implements Record12<Long, Long, Long, String, String, Boolean, Timestamp, Timestamp, Boolean, String, Long, Long> {
    private static final long serialVersionUID = 910239967L;

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

    public void setOrganizationId(Long value) {
        this.set(2, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(2);
    }

    public void setName(String value) {
        this.set(3, value);
    }

    public String getName() {
        return (String)this.get(3);
    }

    public void setDescription(String value) {
        this.set(4, value);
    }

    public String getDescription() {
        return (String)this.get(4);
    }

    public void setDeleted(Boolean value) {
        this.set(5, value);
    }

    public Boolean getDeleted() {
        return (Boolean)this.get(5);
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

    public void setEnabled(Boolean value) {
        this.set(8, value);
    }

    public Boolean getEnabled() {
        return (Boolean)this.get(8);
    }

    public void setMilestoneOrder(String value) {
        this.set(9, value);
    }

    public String getMilestoneOrder() {
        return (String)this.get(9);
    }

    public void setMapSpecId(Long value) {
        this.set(10, value);
    }

    public Long getMapSpecId() {
        return (Long)this.get(10);
    }

    public void setJourneyState(Long value) {
        this.set(11, value);
    }

    public Long getJourneyState() {
        return (Long)this.get(11);
    }

    @Override
    public Row12<Long, Long, Long, String, String, Boolean, Timestamp, Timestamp, Boolean, String, Long, Long> fieldsRow() {
        return (Row12)super.fieldsRow();
    }

    @Override
    public Row12<Long, Long, Long, String, String, Boolean, Timestamp, Timestamp, Boolean, String, Long, Long> valuesRow() {
        return (Row12)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Latestjourneys.LATESTJOURNEYS.ID;
    }

    @Override
    public Field<Long> field2() {
        return Latestjourneys.LATESTJOURNEYS.JOURNEY_ID;
    }

    @Override
    public Field<Long> field3() {
        return Latestjourneys.LATESTJOURNEYS.ORGANIZATION_ID;
    }

    @Override
    public Field<String> field4() {
        return Latestjourneys.LATESTJOURNEYS.NAME;
    }

    @Override
    public Field<String> field5() {
        return Latestjourneys.LATESTJOURNEYS.DESCRIPTION;
    }

    @Override
    public Field<Boolean> field6() {
        return Latestjourneys.LATESTJOURNEYS.DELETED;
    }

    @Override
    public Field<Timestamp> field7() {
        return Latestjourneys.LATESTJOURNEYS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field8() {
        return Latestjourneys.LATESTJOURNEYS.UPDATED_AT;
    }

    @Override
    public Field<Boolean> field9() {
        return Latestjourneys.LATESTJOURNEYS.ENABLED;
    }

    @Override
    public Field<String> field10() {
        return Latestjourneys.LATESTJOURNEYS.MILESTONE_ORDER;
    }

    @Override
    public Field<Long> field11() {
        return Latestjourneys.LATESTJOURNEYS.MAP_SPEC_ID;
    }

    @Override
    public Field<Long> field12() {
        return Latestjourneys.LATESTJOURNEYS.JOURNEY_STATE;
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
    public Long value3() {
        return this.getOrganizationId();
    }

    @Override
    public String value4() {
        return this.getName();
    }

    @Override
    public String value5() {
        return this.getDescription();
    }

    @Override
    public Boolean value6() {
        return this.getDeleted();
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
    public Boolean value9() {
        return this.getEnabled();
    }

    @Override
    public String value10() {
        return this.getMilestoneOrder();
    }

    @Override
    public Long value11() {
        return this.getMapSpecId();
    }

    @Override
    public Long value12() {
        return this.getJourneyState();
    }

    public LatestjourneysRecord value1(Long value) {
        this.setId(value);
        return this;
    }

    public LatestjourneysRecord value2(Long value) {
        this.setJourneyId(value);
        return this;
    }

    public LatestjourneysRecord value3(Long value) {
        this.setOrganizationId(value);
        return this;
    }

    public LatestjourneysRecord value4(String value) {
        this.setName(value);
        return this;
    }

    public LatestjourneysRecord value5(String value) {
        this.setDescription(value);
        return this;
    }

    public LatestjourneysRecord value6(Boolean value) {
        this.setDeleted(value);
        return this;
    }

    public LatestjourneysRecord value7(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public LatestjourneysRecord value8(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public LatestjourneysRecord value9(Boolean value) {
        this.setEnabled(value);
        return this;
    }

    public LatestjourneysRecord value10(String value) {
        this.setMilestoneOrder(value);
        return this;
    }

    public LatestjourneysRecord value11(Long value) {
        this.setMapSpecId(value);
        return this;
    }

    public LatestjourneysRecord value12(Long value) {
        this.setJourneyState(value);
        return this;
    }

    public LatestjourneysRecord values(Long value1, Long value2, Long value3, String value4, String value5, Boolean value6, Timestamp value7, Timestamp value8, Boolean value9, String value10, Long value11, Long value12) {
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
        return this;
    }

    public LatestjourneysRecord() {
        super(Latestjourneys.LATESTJOURNEYS);
    }

    public LatestjourneysRecord(Long id, Long journeyId, Long organizationId, String name, String description, Boolean deleted, Timestamp createdAt, Timestamp updatedAt, Boolean enabled, String milestoneOrder, Long mapSpecId, Long journeyState) {
        super(Latestjourneys.LATESTJOURNEYS);
        this.set(0, id);
        this.set(1, journeyId);
        this.set(2, organizationId);
        this.set(3, name);
        this.set(4, description);
        this.set(5, deleted);
        this.set(6, createdAt);
        this.set(7, updatedAt);
        this.set(8, enabled);
        this.set(9, milestoneOrder);
        this.set(10, mapSpecId);
        this.set(11, journeyState);
    }
}

