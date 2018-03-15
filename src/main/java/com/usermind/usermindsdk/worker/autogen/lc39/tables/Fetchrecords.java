/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Keys;
import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.FetchrecordsRecord;
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

public class Fetchrecords
extends TableImpl<FetchrecordsRecord> {
    private static final long serialVersionUID = 1169148180L;
    public static final Fetchrecords FETCHRECORDS = new Fetchrecords();
    public final TableField<FetchrecordsRecord, Long> ID;
    public final TableField<FetchrecordsRecord, Long> ORGANIZATION_ID;
    public final TableField<FetchrecordsRecord, Long> TIMESTAMP;
    public final TableField<FetchrecordsRecord, String> TYPE;
    public final TableField<FetchrecordsRecord, String> NAME;
    public final TableField<FetchrecordsRecord, Timestamp> CREATED_AT;
    public final TableField<FetchrecordsRecord, Timestamp> UPDATED_AT;
    public final TableField<FetchrecordsRecord, String> ENTITY_TYPE;
    public final TableField<FetchrecordsRecord, String> STATUS;
    public final TableField<FetchrecordsRecord, String> JOB_ID;
    public final TableField<FetchrecordsRecord, Integer> SPEC_VERSION;
    public final TableField<FetchrecordsRecord, Integer> DEFAULTS_VERSION;
    public final TableField<FetchrecordsRecord, String> TRANSLATION;
    public final TableField<FetchrecordsRecord, Integer> TRANSLATION_VERSION;
    public final TableField<FetchrecordsRecord, String> CHANNEL_NAME;
    public final TableField<FetchrecordsRecord, Long> WATERMARK;
    public final TableField<FetchrecordsRecord, Timestamp> PROCESSING_STARTED;
    public final TableField<FetchrecordsRecord, Timestamp> PROCESSING_FINISHED;
    public final TableField<FetchrecordsRecord, String> PATH;
    public final TableField<FetchrecordsRecord, Integer> PROC_STATE;
    public final TableField<FetchrecordsRecord, Integer> REC_PROCESSED;

    @Override
    public Class<FetchrecordsRecord> getRecordType() {
        return FetchrecordsRecord.class;
    }

    public Fetchrecords() {
        this("FetchRecords", null);
    }

    public Fetchrecords(String alias) {
        this(alias, FETCHRECORDS);
    }

    private Fetchrecords(String alias, Table<FetchrecordsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Fetchrecords(String alias, Table<FetchrecordsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Fetchrecords.createField("id", SQLDataType.BIGINT.nullable(false).defaultValue(DSL.field("nextval('\"SFFetchRecords_id_seq\"'::regclass)", SQLDataType.BIGINT)), this, "");
        this.ORGANIZATION_ID = Fetchrecords.createField("organization_id", SQLDataType.BIGINT, this, "");
        this.TIMESTAMP = Fetchrecords.createField("timestamp", SQLDataType.BIGINT, this, "");
        this.TYPE = Fetchrecords.createField("type", SQLDataType.VARCHAR.length(255), this, "");
        this.NAME = Fetchrecords.createField("name", SQLDataType.VARCHAR.length(255), this, "");
        this.CREATED_AT = Fetchrecords.createField("created_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.UPDATED_AT = Fetchrecords.createField("updated_at", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.ENTITY_TYPE = Fetchrecords.createField("entity_type", SQLDataType.VARCHAR.length(255), this, "");
        this.STATUS = Fetchrecords.createField("status", SQLDataType.VARCHAR.length(40), this, "");
        this.JOB_ID = Fetchrecords.createField("job_id", SQLDataType.VARCHAR.length(40), this, "");
        this.SPEC_VERSION = Fetchrecords.createField("spec_version", SQLDataType.INTEGER, this, "");
        this.DEFAULTS_VERSION = Fetchrecords.createField("defaults_version", SQLDataType.INTEGER, this, "");
        this.TRANSLATION = Fetchrecords.createField("translation", SQLDataType.VARCHAR.length(255), this, "");
        this.TRANSLATION_VERSION = Fetchrecords.createField("translation_version", SQLDataType.INTEGER, this, "");
        this.CHANNEL_NAME = Fetchrecords.createField("channel_name", SQLDataType.VARCHAR.length(255), this, "");
        this.WATERMARK = Fetchrecords.createField("watermark", SQLDataType.BIGINT, this, "");
        this.PROCESSING_STARTED = Fetchrecords.createField("processing_started", SQLDataType.TIMESTAMP, this, "");
        this.PROCESSING_FINISHED = Fetchrecords.createField("processing_finished", SQLDataType.TIMESTAMP, this, "");
        this.PATH = Fetchrecords.createField("path", SQLDataType.VARCHAR.length(255), this, "");
        this.PROC_STATE = Fetchrecords.createField("proc_state", SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");
        this.REC_PROCESSED = Fetchrecords.createField("rec_processed", SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public Identity<FetchrecordsRecord, Long> getIdentity() {
        return Keys.IDENTITY_FETCHRECORDS;
    }

    @Override
    public UniqueKey<FetchrecordsRecord> getPrimaryKey() {
        return Keys.SFFETCHRECORDS_PKEY;
    }

    @Override
    public List<UniqueKey<FetchrecordsRecord>> getKeys() {
        return Arrays.asList(Keys.SFFETCHRECORDS_PKEY);
    }

    public Fetchrecords as(String alias) {
        return new Fetchrecords(alias, this);
    }

    public Fetchrecords rename(String name) {
        return new Fetchrecords(name, null);
    }
}

