/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.FaqwadQueueDefinitions;
import java.sql.Timestamp;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row;
import org.jooq.Row11;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.UpdatableRecordImpl;

public class FaqwadQueueDefinitionsRecord
extends UpdatableRecordImpl<FaqwadQueueDefinitionsRecord>
implements Record11<Long, String, Long, Long, Double, Long, Long, String, Timestamp, Timestamp, Long> {
    private static final long serialVersionUID = -176744452L;

    public void setFaqwadQueueDefinitionId(Long value) {
        this.set(0, value);
    }

    public Long getFaqwadQueueDefinitionId() {
        return (Long)this.get(0);
    }

    public void setFaqwadQueueName(String value) {
        this.set(1, value);
    }

    public String getFaqwadQueueName() {
        return (String)this.get(1);
    }

    public void setBaseRetryIntervalSeconds(Long value) {
        this.set(2, value);
    }

    public Long getBaseRetryIntervalSeconds() {
        return (Long)this.get(2);
    }

    public void setRetryLimit(Long value) {
        this.set(3, value);
    }

    public Long getRetryLimit() {
        return (Long)this.get(3);
    }

    public void setRetryBackoffFactor(Double value) {
        this.set(4, value);
    }

    public Double getRetryBackoffFactor() {
        return (Double)this.get(4);
    }

    public void setCheckoutIntervalSeconds(Long value) {
        this.set(5, value);
    }

    public Long getCheckoutIntervalSeconds() {
        return (Long)this.get(5);
    }

    public void setCheckoutLimit(Long value) {
        this.set(6, value);
    }

    public Long getCheckoutLimit() {
        return (Long)this.get(6);
    }

    public void setDescription(String value) {
        this.set(7, value);
    }

    public String getDescription() {
        return (String)this.get(7);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(8, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(8);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(9, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(9);
    }

    public void setRvn(Long value) {
        this.set(10, value);
    }

    public Long getRvn() {
        return (Long)this.get(10);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    @Override
    public Row11<Long, String, Long, Long, Double, Long, Long, String, Timestamp, Timestamp, Long> fieldsRow() {
        return (Row11)super.fieldsRow();
    }

    @Override
    public Row11<Long, String, Long, Long, Double, Long, Long, String, Timestamp, Timestamp, Long> valuesRow() {
        return (Row11)super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.FAQWAD_QUEUE_DEFINITION_ID;
    }

    @Override
    public Field<String> field2() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.FAQWAD_QUEUE_NAME;
    }

    @Override
    public Field<Long> field3() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.BASE_RETRY_INTERVAL_SECONDS;
    }

    @Override
    public Field<Long> field4() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.RETRY_LIMIT;
    }

    @Override
    public Field<Double> field5() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.RETRY_BACKOFF_FACTOR;
    }

    @Override
    public Field<Long> field6() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.CHECKOUT_INTERVAL_SECONDS;
    }

    @Override
    public Field<Long> field7() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.CHECKOUT_LIMIT;
    }

    @Override
    public Field<String> field8() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.DESCRIPTION;
    }

    @Override
    public Field<Timestamp> field9() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field10() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.UPDATED_AT;
    }

    @Override
    public Field<Long> field11() {
        return FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS.RVN;
    }

    @Override
    public Long value1() {
        return this.getFaqwadQueueDefinitionId();
    }

    @Override
    public String value2() {
        return this.getFaqwadQueueName();
    }

    @Override
    public Long value3() {
        return this.getBaseRetryIntervalSeconds();
    }

    @Override
    public Long value4() {
        return this.getRetryLimit();
    }

    @Override
    public Double value5() {
        return this.getRetryBackoffFactor();
    }

    @Override
    public Long value6() {
        return this.getCheckoutIntervalSeconds();
    }

    @Override
    public Long value7() {
        return this.getCheckoutLimit();
    }

    @Override
    public String value8() {
        return this.getDescription();
    }

    @Override
    public Timestamp value9() {
        return this.getCreatedAt();
    }

    @Override
    public Timestamp value10() {
        return this.getUpdatedAt();
    }

    @Override
    public Long value11() {
        return this.getRvn();
    }

    public FaqwadQueueDefinitionsRecord value1(Long value) {
        this.setFaqwadQueueDefinitionId(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord value2(String value) {
        this.setFaqwadQueueName(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord value3(Long value) {
        this.setBaseRetryIntervalSeconds(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord value4(Long value) {
        this.setRetryLimit(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord value5(Double value) {
        this.setRetryBackoffFactor(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord value6(Long value) {
        this.setCheckoutIntervalSeconds(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord value7(Long value) {
        this.setCheckoutLimit(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord value8(String value) {
        this.setDescription(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord value9(Timestamp value) {
        this.setCreatedAt(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord value10(Timestamp value) {
        this.setUpdatedAt(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord value11(Long value) {
        this.setRvn(value);
        return this;
    }

    public FaqwadQueueDefinitionsRecord values(Long value1, String value2, Long value3, Long value4, Double value5, Long value6, Long value7, String value8, Timestamp value9, Timestamp value10, Long value11) {
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
        return this;
    }

    public FaqwadQueueDefinitionsRecord() {
        super(FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS);
    }

    public FaqwadQueueDefinitionsRecord(Long faqwadQueueDefinitionId, String faqwadQueueName, Long baseRetryIntervalSeconds, Long retryLimit, Double retryBackoffFactor, Long checkoutIntervalSeconds, Long checkoutLimit, String description, Timestamp createdAt, Timestamp updatedAt, Long rvn) {
        super(FaqwadQueueDefinitions.FAQWAD_QUEUE_DEFINITIONS);
        this.set(0, faqwadQueueDefinitionId);
        this.set(1, faqwadQueueName);
        this.set(2, baseRetryIntervalSeconds);
        this.set(3, retryLimit);
        this.set(4, retryBackoffFactor);
        this.set(5, checkoutIntervalSeconds);
        this.set(6, checkoutLimit);
        this.set(7, description);
        this.set(8, createdAt);
        this.set(9, updatedAt);
        this.set(10, rvn);
    }
}

