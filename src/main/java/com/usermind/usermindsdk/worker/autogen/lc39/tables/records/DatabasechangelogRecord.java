/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.Databasechangelog;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record14;
import org.jooq.Row;
import org.jooq.Row14;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.TableRecordImpl;

public class DatabasechangelogRecord
extends TableRecordImpl<DatabasechangelogRecord>
implements Record14<String, String, String, Timestamp, Integer, String, String, String, String, String, String, String, String, String> {
    private static final long serialVersionUID = -204312228L;

    public void setId(String value) {
        this.set(0, value);
    }

    public String getId() {
        return (String)this.get(0);
    }

    public void setAuthor(String value) {
        this.set(1, value);
    }

    public String getAuthor() {
        return (String)this.get(1);
    }

    public void setFilename(String value) {
        this.set(2, value);
    }

    public String getFilename() {
        return (String)this.get(2);
    }

    public void setDateexecuted(Timestamp value) {
        this.set(3, value);
    }

    public Timestamp getDateexecuted() {
        return (Timestamp)this.get(3);
    }

    public void setOrderexecuted(Integer value) {
        this.set(4, value);
    }

    public Integer getOrderexecuted() {
        return (Integer)this.get(4);
    }

    public void setExectype(String value) {
        this.set(5, value);
    }

    public String getExectype() {
        return (String)this.get(5);
    }

    public void setMd5sum(String value) {
        this.set(6, value);
    }

    public String getMd5sum() {
        return (String)this.get(6);
    }

    public void setDescription(String value) {
        this.set(7, value);
    }

    public String getDescription() {
        return (String)this.get(7);
    }

    public void setComments(String value) {
        this.set(8, value);
    }

    public String getComments() {
        return (String)this.get(8);
    }

    public void setTag(String value) {
        this.set(9, value);
    }

    public String getTag() {
        return (String)this.get(9);
    }

    public void setLiquibase(String value) {
        this.set(10, value);
    }

    public String getLiquibase() {
        return (String)this.get(10);
    }

    public void setContexts(String value) {
        this.set(11, value);
    }

    public String getContexts() {
        return (String)this.get(11);
    }

    public void setLabels(String value) {
        this.set(12, value);
    }

    public String getLabels() {
        return (String)this.get(12);
    }

    public void setDeploymentId(String value) {
        this.set(13, value);
    }

    public String getDeploymentId() {
        return (String)this.get(13);
    }

    @Override
    public Row14<String, String, String, Timestamp, Integer, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row14)super.fieldsRow();
    }

    @Override
    public Row14<String, String, String, Timestamp, Integer, String, String, String, String, String, String, String, String, String> valuesRow() {
        return (Row14)super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Databasechangelog.DATABASECHANGELOG.ID;
    }

    @Override
    public Field<String> field2() {
        return Databasechangelog.DATABASECHANGELOG.AUTHOR;
    }

    @Override
    public Field<String> field3() {
        return Databasechangelog.DATABASECHANGELOG.FILENAME;
    }

    @Override
    public Field<Timestamp> field4() {
        return Databasechangelog.DATABASECHANGELOG.DATEEXECUTED;
    }

    @Override
    public Field<Integer> field5() {
        return Databasechangelog.DATABASECHANGELOG.ORDEREXECUTED;
    }

    @Override
    public Field<String> field6() {
        return Databasechangelog.DATABASECHANGELOG.EXECTYPE;
    }

    @Override
    public Field<String> field7() {
        return Databasechangelog.DATABASECHANGELOG.MD5SUM;
    }

    @Override
    public Field<String> field8() {
        return Databasechangelog.DATABASECHANGELOG.DESCRIPTION;
    }

    @Override
    public Field<String> field9() {
        return Databasechangelog.DATABASECHANGELOG.COMMENTS;
    }

    @Override
    public Field<String> field10() {
        return Databasechangelog.DATABASECHANGELOG.TAG;
    }

    @Override
    public Field<String> field11() {
        return Databasechangelog.DATABASECHANGELOG.LIQUIBASE;
    }

    @Override
    public Field<String> field12() {
        return Databasechangelog.DATABASECHANGELOG.CONTEXTS;
    }

    @Override
    public Field<String> field13() {
        return Databasechangelog.DATABASECHANGELOG.LABELS;
    }

    @Override
    public Field<String> field14() {
        return Databasechangelog.DATABASECHANGELOG.DEPLOYMENT_ID;
    }

    @Override
    public String value1() {
        return this.getId();
    }

    @Override
    public String value2() {
        return this.getAuthor();
    }

    @Override
    public String value3() {
        return this.getFilename();
    }

    @Override
    public Timestamp value4() {
        return this.getDateexecuted();
    }

    @Override
    public Integer value5() {
        return this.getOrderexecuted();
    }

    @Override
    public String value6() {
        return this.getExectype();
    }

    @Override
    public String value7() {
        return this.getMd5sum();
    }

    @Override
    public String value8() {
        return this.getDescription();
    }

    @Override
    public String value9() {
        return this.getComments();
    }

    @Override
    public String value10() {
        return this.getTag();
    }

    @Override
    public String value11() {
        return this.getLiquibase();
    }

    @Override
    public String value12() {
        return this.getContexts();
    }

    @Override
    public String value13() {
        return this.getLabels();
    }

    @Override
    public String value14() {
        return this.getDeploymentId();
    }

    public DatabasechangelogRecord value1(String value) {
        this.setId(value);
        return this;
    }

    public DatabasechangelogRecord value2(String value) {
        this.setAuthor(value);
        return this;
    }

    public DatabasechangelogRecord value3(String value) {
        this.setFilename(value);
        return this;
    }

    public DatabasechangelogRecord value4(Timestamp value) {
        this.setDateexecuted(value);
        return this;
    }

    public DatabasechangelogRecord value5(Integer value) {
        this.setOrderexecuted(value);
        return this;
    }

    public DatabasechangelogRecord value6(String value) {
        this.setExectype(value);
        return this;
    }

    public DatabasechangelogRecord value7(String value) {
        this.setMd5sum(value);
        return this;
    }

    public DatabasechangelogRecord value8(String value) {
        this.setDescription(value);
        return this;
    }

    public DatabasechangelogRecord value9(String value) {
        this.setComments(value);
        return this;
    }

    public DatabasechangelogRecord value10(String value) {
        this.setTag(value);
        return this;
    }

    public DatabasechangelogRecord value11(String value) {
        this.setLiquibase(value);
        return this;
    }

    public DatabasechangelogRecord value12(String value) {
        this.setContexts(value);
        return this;
    }

    public DatabasechangelogRecord value13(String value) {
        this.setLabels(value);
        return this;
    }

    public DatabasechangelogRecord value14(String value) {
        this.setDeploymentId(value);
        return this;
    }

    public DatabasechangelogRecord values(String value1, String value2, String value3, Timestamp value4, Integer value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, String value14) {
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
        this.value13(value13);
        this.value14(value14);
        return this;
    }

    public DatabasechangelogRecord() {
        super(Databasechangelog.DATABASECHANGELOG);
    }

    public DatabasechangelogRecord(String id, String author, String filename, Timestamp dateexecuted, Integer orderexecuted, String exectype, String md5sum, String description, String comments, String tag, String liquibase, String contexts, String labels, String deploymentId) {
        super(Databasechangelog.DATABASECHANGELOG);
        this.set(0, id);
        this.set(1, author);
        this.set(2, filename);
        this.set(3, dateexecuted);
        this.set(4, orderexecuted);
        this.set(5, exectype);
        this.set(6, md5sum);
        this.set(7, description);
        this.set(8, comments);
        this.set(9, tag);
        this.set(10, liquibase);
        this.set(11, contexts);
        this.set(12, labels);
        this.set(13, deploymentId);
    }
}

