/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.UmtransactionsRecord;
import java.sql.Timestamp;
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

public class Umtransactions
extends TableImpl<UmtransactionsRecord> {
    private static final long serialVersionUID = 792791736L;
    public static final Umtransactions UMTRANSACTIONS = new Umtransactions();
    public final TableField<UmtransactionsRecord, Long> ID;
    public final TableField<UmtransactionsRecord, String> TRANSACTION_ID;
    public final TableField<UmtransactionsRecord, Long> ORGANIZATION_ID;
    public final TableField<UmtransactionsRecord, Boolean> REPLAY;
    public final TableField<UmtransactionsRecord, Timestamp> TIMESTAMP;
    public final TableField<UmtransactionsRecord, Timestamp> CREATED_AT;
    public final TableField<UmtransactionsRecord, String> EVENT_ID;

    @Override
    public Class<UmtransactionsRecord> getRecordType() {
        return UmtransactionsRecord.class;
    }

    public Umtransactions() {
        this("UMTransactions", null);
    }

    public Umtransactions(String alias) {
        this(alias, UMTRANSACTIONS);
    }

    private Umtransactions(String alias, Table<UmtransactionsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Umtransactions(String alias, Table<UmtransactionsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Umtransactions.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"UMTransactions_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.TRANSACTION_ID = Umtransactions.createField("transaction_id", SQLDataType.CLOB.nullable(false), this, "");
        this.ORGANIZATION_ID = Umtransactions.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.REPLAY = Umtransactions.createField("replay", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.TIMESTAMP = Umtransactions.createField("timestamp", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.CREATED_AT = Umtransactions.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.EVENT_ID = Umtransactions.createField("event_id", SQLDataType.CLOB.nullable(false), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<UmtransactionsRecord, Long> getIdentity() {
        return Keys.IDENTITY_UMTRANSACTIONS;
    }

    @Override
    public UniqueKey<UmtransactionsRecord> getPrimaryKey() {
        return Keys.UMTRANSACTIONS_PKEY;
    }

    @Override
    public List<UniqueKey<UmtransactionsRecord>> getKeys() {
        return Arrays.asList(Keys.UMTRANSACTIONS_PKEY);
    }

    public Umtransactions as(String alias) {
        return new Umtransactions(alias, this);
    }

    public Umtransactions rename(String name) {
        return new Umtransactions(name, null);
    }
}

