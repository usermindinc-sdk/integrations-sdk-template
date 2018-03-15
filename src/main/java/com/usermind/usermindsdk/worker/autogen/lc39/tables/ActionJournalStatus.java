/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.ActionJournalStatusRecord;
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

public class ActionJournalStatus
extends TableImpl<ActionJournalStatusRecord> {
    private static final long serialVersionUID = 1468387452L;
    public static final ActionJournalStatus ACTION_JOURNAL_STATUS = new ActionJournalStatus();
    public final TableField<ActionJournalStatusRecord, Long> ACTION_JOURNAL_STATUS_ID;
    public final TableField<ActionJournalStatusRecord, String> ACTION_JOURNAL_STATUS_NAME;
    public final TableField<ActionJournalStatusRecord, String> ACTION_JOURNAL_STATUS_DESCRIPTION;

    @Override
    public Class<ActionJournalStatusRecord> getRecordType() {
        return ActionJournalStatusRecord.class;
    }

    public ActionJournalStatus() {
        this("action_journal_status", null);
    }

    public ActionJournalStatus(String alias) {
        this(alias, ACTION_JOURNAL_STATUS);
    }

    private ActionJournalStatus(String alias, Table<ActionJournalStatusRecord> aliased) {
        this(alias, aliased, null);
    }

    private ActionJournalStatus(String alias, Table<ActionJournalStatusRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ACTION_JOURNAL_STATUS_ID = ActionJournalStatus.createField("action_journal_status_id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('action_journal_status_action_journal_status_id_seq'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ACTION_JOURNAL_STATUS_NAME = ActionJournalStatus.createField("action_journal_status_name", SQLDataType.CLOB.nullable(false), this, "");
        this.ACTION_JOURNAL_STATUS_DESCRIPTION = ActionJournalStatus.createField("action_journal_status_description", SQLDataType.CLOB, this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<ActionJournalStatusRecord, Long> getIdentity() {
        return Keys.IDENTITY_ACTION_JOURNAL_STATUS;
    }

    @Override
    public UniqueKey<ActionJournalStatusRecord> getPrimaryKey() {
        return Keys.PK_ACTION_JOURNAL_STATUS;
    }

    @Override
    public List<UniqueKey<ActionJournalStatusRecord>> getKeys() {
        return Arrays.asList(Keys.PK_ACTION_JOURNAL_STATUS, Keys.ACTION_JOURNAL_STATUS_ACTION_JOURNAL_STATUS_NAME_KEY);
    }

    public ActionJournalStatus as(String alias) {
        return new ActionJournalStatus(alias, this);
    }

    public ActionJournalStatus rename(String name) {
        return new ActionJournalStatus(name, null);
    }
}

