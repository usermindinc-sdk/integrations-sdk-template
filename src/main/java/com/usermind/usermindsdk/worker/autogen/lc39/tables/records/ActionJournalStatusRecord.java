/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.ActionJournalStatus;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row;
import org.jooq.Row3;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class ActionJournalStatusRecord
extends UpdatableRecordImpl<ActionJournalStatusRecord>
implements Record3<Long, String, String> {
    private static final long serialVersionUID = 2036588336L;

    public void setActionJournalStatusId(Long value) {
        this.set(0, value);
    }

    public Long getActionJournalStatusId() {
        return (Long)this.get(0);
    }

    public void setActionJournalStatusName(String value) {
        this.set(1, value);
    }

    public String getActionJournalStatusName() {
        return (String)this.get(1);
    }

    public void setActionJournalStatusDescription(String value) {
        this.set(2, value);
    }

    public String getActionJournalStatusDescription() {
        return (String)this.get(2);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row3<Long, String, String> fieldsRow() {
        return (Row3)super.fieldsRow();
    }

    @Override
    public Row3<Long, String, String> valuesRow() {
        return (Row3)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return ActionJournalStatus.ACTION_JOURNAL_STATUS.ACTION_JOURNAL_STATUS_ID;
    }

    @Override
    public Field<String> field2() {
        return ActionJournalStatus.ACTION_JOURNAL_STATUS.ACTION_JOURNAL_STATUS_NAME;
    }

    @Override
    public Field<String> field3() {
        return ActionJournalStatus.ACTION_JOURNAL_STATUS.ACTION_JOURNAL_STATUS_DESCRIPTION;
    }

    @Override
    public Long value1() {
        return this.getActionJournalStatusId();
    }

    @Override
    public String value2() {
        return this.getActionJournalStatusName();
    }

    @Override
    public String value3() {
        return this.getActionJournalStatusDescription();
    }

    public ActionJournalStatusRecord value1(Long value) {
        this.setActionJournalStatusId(value);
        return this;
    }

    public ActionJournalStatusRecord value2(String value) {
        this.setActionJournalStatusName(value);
        return this;
    }

    public ActionJournalStatusRecord value3(String value) {
        this.setActionJournalStatusDescription(value);
        return this;
    }

    public ActionJournalStatusRecord values(Long value1, String value2, String value3) {
        this.value1(value1);
        this.value2(value2);
        this.value3(value3);
        return this;
    }

    public ActionJournalStatusRecord() {
        super(ActionJournalStatus.ACTION_JOURNAL_STATUS);
    }

    public ActionJournalStatusRecord(Long actionJournalStatusId, String actionJournalStatusName, String actionJournalStatusDescription) {
        super(ActionJournalStatus.ACTION_JOURNAL_STATUS);
        this.set(0, actionJournalStatusId);
        this.set(1, actionJournalStatusName);
        this.set(2, actionJournalStatusDescription);
    }
}

