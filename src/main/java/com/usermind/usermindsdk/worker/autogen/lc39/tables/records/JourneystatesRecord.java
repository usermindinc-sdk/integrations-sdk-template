/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Journeystates;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row;
import org.jooq.Row2;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class JourneystatesRecord
extends UpdatableRecordImpl<JourneystatesRecord>
implements Record2<Long, String> {
    private static final long serialVersionUID = 575616134L;

    public void setJourneyStateId(Long value) {
        this.set(0, value);
    }

    public Long getJourneyStateId() {
        return (Long)this.get(0);
    }

    public void setJourneyStateName(String value) {
        this.set(1, value);
    }

    public String getJourneyStateName() {
        return (String)this.get(1);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2)super.fieldsRow();
    }

    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Journeystates.JOURNEYSTATES.JOURNEY_STATE_ID;
    }

    @Override
    public Field<String> field2() {
        return Journeystates.JOURNEYSTATES.JOURNEY_STATE_NAME;
    }

    @Override
    public Long value1() {
        return this.getJourneyStateId();
    }

    @Override
    public String value2() {
        return this.getJourneyStateName();
    }

    public JourneystatesRecord value1(Long value) {
        this.setJourneyStateId(value);
        return this;
    }

    public JourneystatesRecord value2(String value) {
        this.setJourneyStateName(value);
        return this;
    }

    public JourneystatesRecord values(Long value1, String value2) {
        this.value1(value1);
        this.value2(value2);
        return this;
    }

    public JourneystatesRecord() {
        super(Journeystates.JOURNEYSTATES);
    }

    public JourneystatesRecord(Long journeyStateId, String journeyStateName) {
        super(Journeystates.JOURNEYSTATES);
        this.set(0, journeyStateId);
        this.set(1, journeyStateName);
    }
}

