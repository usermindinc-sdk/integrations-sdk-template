/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ActionJournalStatusRecord;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ActionjournalRecord;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Actionjournal
extends TableImpl<ActionjournalRecord> {
    private static final long serialVersionUID = 717610089L;
    public static final Actionjournal ACTIONJOURNAL = new Actionjournal();
    public final TableField<ActionjournalRecord, Long> ID;
    public final TableField<ActionjournalRecord, String> TRANSACTION_ID;
    public final TableField<ActionjournalRecord, String> ACTION_TYPE;
    public final TableField<ActionjournalRecord, Long> ORGANIZATION_ID;
    public final TableField<ActionjournalRecord, String> PAYLOAD;
    public final TableField<ActionjournalRecord, Boolean> REPLAY;
    public final TableField<ActionjournalRecord, Long> JOURNEY_ID;
    public final TableField<ActionjournalRecord, Long> RULE_VERSION;
    public final TableField<ActionjournalRecord, Timestamp> SERVICED_AT;
    public final TableField<ActionjournalRecord, Timestamp> CREATED_AT;
    public final TableField<ActionjournalRecord, Timestamp> TIMESTAMP;
    public final TableField<ActionjournalRecord, String> RULE_ID;
    public final TableField<ActionjournalRecord, Long> CONNECTION_ID;
    public final TableField<ActionjournalRecord, Long> TRAVELER_ID;
    public final TableField<ActionjournalRecord, String> EVENT_ID;
    public final TableField<ActionjournalRecord, Integer> STATUS;

    @Override
    public Class<ActionjournalRecord> getRecordType() {
        return ActionjournalRecord.class;
    }

    public Actionjournal() {
        this("ActionJournal", null);
    }

    public Actionjournal(String alias) {
        this(alias, ACTIONJOURNAL);
    }

    private Actionjournal(String alias, Table<ActionjournalRecord> aliased) {
        this(alias, aliased, null);
    }

    private Actionjournal(String alias, Table<ActionjournalRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Actionjournal.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"ActionJournal_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.TRANSACTION_ID = Actionjournal.createField("transaction_id", SQLDataType.CLOB.nullable(false), this, "");
        this.ACTION_TYPE = Actionjournal.createField("action_type", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.ORGANIZATION_ID = Actionjournal.createField("organization_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.PAYLOAD = Actionjournal.createField("payload", SQLDataType.CLOB.nullable(false), this, "");
        this.REPLAY = Actionjournal.createField("replay", SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");
        this.JOURNEY_ID = Actionjournal.createField("journey_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.RULE_VERSION = Actionjournal.createField("rule_version", SQLDataType.BIGINT.nullable(false), this, "");
        this.SERVICED_AT = Actionjournal.createField("serviced_at", SQLDataType.TIMESTAMP, this, "");
        this.CREATED_AT = Actionjournal.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.TIMESTAMP = Actionjournal.createField("timestamp", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.RULE_ID = Actionjournal.createField("rule_id", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.CONNECTION_ID = Actionjournal.createField("connection_id", SQLDataType.BIGINT.nullable(false), this, "");
        this.TRAVELER_ID = Actionjournal.createField("traveler_id", SQLDataType.BIGINT, this, "");
        this.EVENT_ID = Actionjournal.createField("event_id", SQLDataType.CLOB.nullable(false), this, "");
        this.STATUS = Actionjournal.createField("status", SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("4", SQLDataType.INTEGER)), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<ActionjournalRecord, Long> getIdentity() {
        return Keys.IDENTITY_ACTIONJOURNAL;
    }

    @Override
    public UniqueKey<ActionjournalRecord> getPrimaryKey() {
        return Keys.ACTIONJOURNAL_PKEY;
    }

    @Override
    public List<UniqueKey<ActionjournalRecord>> getKeys() {
        return Arrays.asList(Keys.ACTIONJOURNAL_PKEY);
    }

    @Override
    public List<ForeignKey<ActionjournalRecord, ?>> getReferences() {
        return Arrays.asList(Keys.ACTIONJOURNAL__FK_ACTIONJOURNALSTATUS);
    }

    public Actionjournal as(String alias) {
        return new Actionjournal(alias, this);
    }

    public Actionjournal rename(String name) {
        return new Actionjournal(name, null);
    }
}

