/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables;

import com.usermind.usermindsdk.worker.autogen.lc39.Public;
import com.usermind.usermindsdk.worker.autogen.lc39.tables.records.DatabasechangelogRecord;
import java.sql.Timestamp;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Databasechangelog
extends TableImpl<DatabasechangelogRecord> {
    private static final long serialVersionUID = -1513312870L;
    public static final Databasechangelog DATABASECHANGELOG = new Databasechangelog();
    public final TableField<DatabasechangelogRecord, String> ID;
    public final TableField<DatabasechangelogRecord, String> AUTHOR;
    public final TableField<DatabasechangelogRecord, String> FILENAME;
    public final TableField<DatabasechangelogRecord, Timestamp> DATEEXECUTED;
    public final TableField<DatabasechangelogRecord, Integer> ORDEREXECUTED;
    public final TableField<DatabasechangelogRecord, String> EXECTYPE;
    public final TableField<DatabasechangelogRecord, String> MD5SUM;
    public final TableField<DatabasechangelogRecord, String> DESCRIPTION;
    public final TableField<DatabasechangelogRecord, String> COMMENTS;
    public final TableField<DatabasechangelogRecord, String> TAG;
    public final TableField<DatabasechangelogRecord, String> LIQUIBASE;
    public final TableField<DatabasechangelogRecord, String> CONTEXTS;
    public final TableField<DatabasechangelogRecord, String> LABELS;
    public final TableField<DatabasechangelogRecord, String> DEPLOYMENT_ID;

    @Override
    public Class<DatabasechangelogRecord> getRecordType() {
        return DatabasechangelogRecord.class;
    }

    public Databasechangelog() {
        this("databasechangelog", null);
    }

    public Databasechangelog(String alias) {
        this(alias, DATABASECHANGELOG);
    }

    private Databasechangelog(String alias, Table<DatabasechangelogRecord> aliased) {
        this(alias, aliased, null);
    }

    private Databasechangelog(String alias, Table<DatabasechangelogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
        this.ID = Databasechangelog.createField("id", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.AUTHOR = Databasechangelog.createField("author", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.FILENAME = Databasechangelog.createField("filename", SQLDataType.VARCHAR.length(255).nullable(false), this, "");
        this.DATEEXECUTED = Databasechangelog.createField("dateexecuted", SQLDataType.TIMESTAMP.nullable(false), this, "");
        this.ORDEREXECUTED = Databasechangelog.createField("orderexecuted", SQLDataType.INTEGER.nullable(false), this, "");
        this.EXECTYPE = Databasechangelog.createField("exectype", SQLDataType.VARCHAR.length(10).nullable(false), this, "");
        this.MD5SUM = Databasechangelog.createField("md5sum", SQLDataType.VARCHAR.length(35), this, "");
        this.DESCRIPTION = Databasechangelog.createField("description", SQLDataType.VARCHAR.length(255), this, "");
        this.COMMENTS = Databasechangelog.createField("comments", SQLDataType.VARCHAR.length(255), this, "");
        this.TAG = Databasechangelog.createField("tag", SQLDataType.VARCHAR.length(255), this, "");
        this.LIQUIBASE = Databasechangelog.createField("liquibase", SQLDataType.VARCHAR.length(20), this, "");
        this.CONTEXTS = Databasechangelog.createField("contexts", SQLDataType.VARCHAR.length(255), this, "");
        this.LABELS = Databasechangelog.createField("labels", SQLDataType.VARCHAR.length(255), this, "");
        this.DEPLOYMENT_ID = Databasechangelog.createField("deployment_id", SQLDataType.VARCHAR.length(10), this, "");
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    public Databasechangelog as(String alias) {
        return new Databasechangelog(alias, this);
    }

    public Databasechangelog rename(String name) {
        return new Databasechangelog(name, null);
    }
}

