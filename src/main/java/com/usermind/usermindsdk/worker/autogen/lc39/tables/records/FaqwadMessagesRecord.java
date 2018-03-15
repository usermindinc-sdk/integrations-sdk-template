/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.FaqwadMessages;
import java.sql.Timestamp;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Table;
import org.jooq.impl.UpdatableRecordImpl;

public class FaqwadMessagesRecord
extends UpdatableRecordImpl<FaqwadMessagesRecord> {
    private static final long serialVersionUID = 9786755L;

    public void setFaqwadMessageId(Long value) {
        this.set(0, value);
    }

    public Long getFaqwadMessageId() {
        return (Long)this.get(0);
    }

    public void setFaqwadQueueDefinitionId(Long value) {
        this.set(1, value);
    }

    public Long getFaqwadQueueDefinitionId() {
        return (Long)this.get(1);
    }

    public void setFaqwadObjectId(String value) {
        this.set(2, value);
    }

    public String getFaqwadObjectId() {
        return (String)this.get(2);
    }

    public void setFaqwadSingletonId(String value) {
        this.set(3, value);
    }

    public String getFaqwadSingletonId() {
        return (String)this.get(3);
    }

    public void setFaqwadMessageTimestamp(Long value) {
        this.set(4, value);
    }

    public Long getFaqwadMessageTimestamp() {
        return (Long)this.get(4);
    }

    public void setFaqwadMessageOffset(Long value) {
        this.set(5, value);
    }

    public Long getFaqwadMessageOffset() {
        return (Long)this.get(5);
    }

    public void setFaqwadTypeId(Long value) {
        this.set(6, value);
    }

    public Long getFaqwadTypeId() {
        return (Long)this.get(6);
    }

    public void setFaqwadStateId(Long value) {
        this.set(7, value);
    }

    public Long getFaqwadStateId() {
        return (Long)this.get(7);
    }

    public void setFaqwadMessageData(Object value) {
        this.set(8, value);
    }

    public Object getFaqwadMessageData() {
        return this.get(8);
    }

    public void setOrganizationId(Long value) {
        this.set(9, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(9);
    }

    public void setPriority(Long value) {
        this.set(10, value);
    }

    public Long getPriority() {
        return (Long)this.get(10);
    }

    public void setRetryCount(Long value) {
        this.set(11, value);
    }

    public Long getRetryCount() {
        return (Long)this.get(11);
    }

    public void setNextRetryTimeUtc(Timestamp value) {
        this.set(12, value);
    }

    public Timestamp getNextRetryTimeUtc() {
        return (Timestamp)this.get(12);
    }

    public void setCheckoutCount(Long value) {
        this.set(13, value);
    }

    public Long getCheckoutCount() {
        return (Long)this.get(13);
    }

    public void setCheckoutUntilUtc(Timestamp value) {
        this.set(14, value);
    }

    public Timestamp getCheckoutUntilUtc() {
        return (Timestamp)this.get(14);
    }

    public void setLastCheckedOutBy(String value) {
        this.set(15, value);
    }

    public String getLastCheckedOutBy() {
        return (String)this.get(15);
    }

    public void setLastWorkedBy(String value) {
        this.set(16, value);
    }

    public String getLastWorkedBy() {
        return (String)this.get(16);
    }

    public void setLastWorkStartTimeUtc(Timestamp value) {
        this.set(17, value);
    }

    public Timestamp getLastWorkStartTimeUtc() {
        return (Timestamp)this.get(17);
    }

    public void setFirstWorkStartTimeUtc(Timestamp value) {
        this.set(18, value);
    }

    public Timestamp getFirstWorkStartTimeUtc() {
        return (Timestamp)this.get(18);
    }

    public void setLastWorkUpdateTimeUtc(Timestamp value) {
        this.set(19, value);
    }

    public Timestamp getLastWorkUpdateTimeUtc() {
        return (Timestamp)this.get(19);
    }

    public void setMarkedDoneTimeUtc(Timestamp value) {
        this.set(20, value);
    }

    public Timestamp getMarkedDoneTimeUtc() {
        return (Timestamp)this.get(20);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(21, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(21);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(22, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(22);
    }

    public void setRvn(Long value) {
        this.set(23, value);
    }

    public Long getRvn() {
        return (Long)this.get(23);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    public FaqwadMessagesRecord() {
        super(FaqwadMessages.FAQWAD_MESSAGES);
    }

    public FaqwadMessagesRecord(Long faqwadMessageId, Long faqwadQueueDefinitionId, String faqwadObjectId, String faqwadSingletonId, Long faqwadMessageTimestamp, Long faqwadMessageOffset, Long faqwadTypeId, Long faqwadStateId, Object faqwadMessageData, Long organizationId, Long priority, Long retryCount, Timestamp nextRetryTimeUtc, Long checkoutCount, Timestamp checkoutUntilUtc, String lastCheckedOutBy, String lastWorkedBy, Timestamp lastWorkStartTimeUtc, Timestamp firstWorkStartTimeUtc, Timestamp lastWorkUpdateTimeUtc, Timestamp markedDoneTimeUtc, Timestamp createdAt, Timestamp updatedAt, Long rvn) {
        super(FaqwadMessages.FAQWAD_MESSAGES);
        this.set(0, faqwadMessageId);
        this.set(1, faqwadQueueDefinitionId);
        this.set(2, faqwadObjectId);
        this.set(3, faqwadSingletonId);
        this.set(4, faqwadMessageTimestamp);
        this.set(5, faqwadMessageOffset);
        this.set(6, faqwadTypeId);
        this.set(7, faqwadStateId);
        this.set(8, faqwadMessageData);
        this.set(9, organizationId);
        this.set(10, priority);
        this.set(11, retryCount);
        this.set(12, nextRetryTimeUtc);
        this.set(13, checkoutCount);
        this.set(14, checkoutUntilUtc);
        this.set(15, lastCheckedOutBy);
        this.set(16, lastWorkedBy);
        this.set(17, lastWorkStartTimeUtc);
        this.set(18, firstWorkStartTimeUtc);
        this.set(19, lastWorkUpdateTimeUtc);
        this.set(20, markedDoneTimeUtc);
        this.set(21, createdAt);
        this.set(22, updatedAt);
        this.set(23, rvn);
    }
}

