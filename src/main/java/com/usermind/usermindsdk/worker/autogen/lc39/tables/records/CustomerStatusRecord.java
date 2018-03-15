/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.CustomerStatus;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row;
import org.jooq.Row2;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class CustomerStatusRecord
extends UpdatableRecordImpl<CustomerStatusRecord>
implements Record2<Long, String> {
    private static final long serialVersionUID = 930381170L;

    public void setCustomerStatusId(Long value) {
        this.set(0, value);
    }

    public Long getCustomerStatusId() {
        return (Long)this.get(0);
    }

    public void setCustomerStatusName(String value) {
        this.set(1, value);
    }

    public String getCustomerStatusName() {
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
        return CustomerStatus.CUSTOMER_STATUS.CUSTOMER_STATUS_ID;
    }

    @Override
    public Field<String> field2() {
        return CustomerStatus.CUSTOMER_STATUS.CUSTOMER_STATUS_NAME;
    }

    @Override
    public Long value1() {
        return this.getCustomerStatusId();
    }

    @Override
    public String value2() {
        return this.getCustomerStatusName();
    }

    public CustomerStatusRecord value1(Long value) {
        this.setCustomerStatusId(value);
        return this;
    }

    public CustomerStatusRecord value2(String value) {
        this.setCustomerStatusName(value);
        return this;
    }

    public CustomerStatusRecord values(Long value1, String value2) {
        this.value1(value1);
        this.value2(value2);
        return this;
    }

    public CustomerStatusRecord() {
        super(CustomerStatus.CUSTOMER_STATUS);
    }

    public CustomerStatusRecord(Long customerStatusId, String customerStatusName) {
        super(CustomerStatus.CUSTOMER_STATUS);
        this.set(0, customerStatusId);
        this.set(1, customerStatusName);
    }
}

