/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.CustomerStatusRecord;
import java.util.Arrays;
import java.util.List;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class CustomerStatus
extends TableImpl<CustomerStatusRecord> {
    private static final long serialVersionUID = -883858317L;
    public static final CustomerStatus CUSTOMER_STATUS = new CustomerStatus();
    public final TableField<CustomerStatusRecord, Long> CUSTOMER_STATUS_ID;
    public final TableField<CustomerStatusRecord, String> CUSTOMER_STATUS_NAME;

    @Override
    public Class<CustomerStatusRecord> getRecordType() {
        return CustomerStatusRecord.class;
    }

    public CustomerStatus() {
        this("customer_status", null);
    }

    public CustomerStatus(String alias) {
        this(alias, CUSTOMER_STATUS);
    }

    private CustomerStatus(String alias, Table<CustomerStatusRecord> aliased) {
        this(alias, aliased, null);
    }

    private CustomerStatus(String alias, Table<CustomerStatusRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.CUSTOMER_STATUS_ID = CustomerStatus.createField("customer_status_id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('customer_status_customer_status_id_seq'::regclass)", SQLDataType.BIGINT)), this, "");
        this.CUSTOMER_STATUS_NAME = CustomerStatus.createField("customer_status_name", SQLDataType.CLOB.nullable(false), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<CustomerStatusRecord, Long> getIdentity() {
        return Keys.IDENTITY_CUSTOMER_STATUS;
    }

    @Override
    public UniqueKey<CustomerStatusRecord> getPrimaryKey() {
        return Keys.PK_CUSTOMER_STATUS;
    }

    @Override
    public List<UniqueKey<CustomerStatusRecord>> getKeys() {
        return Arrays.asList(Keys.PK_CUSTOMER_STATUS, Keys.CUSTOMER_STATUS_CUSTOMER_STATUS_NAME_KEY);
    }

    public CustomerStatus as(String alias) {
        return new CustomerStatus(alias, this);
    }

    public CustomerStatus rename(String name) {
        return new CustomerStatus(name, null);
    }
}

