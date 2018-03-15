/*
 * Decompiled with CFR 0_125.
 */
package com.usermind.usermindsdk.worker.autogen.lc39.tables.records;

import com.usermind.usermindsdk.worker.autogen.lc39.tables.TravelerEvents;
import java.sql.Timestamp;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Table;
import org.jooq.impl.UpdatableRecordImpl;

public class TravelerEventsRecord
extends UpdatableRecordImpl<TravelerEventsRecord> {
    private static final long serialVersionUID = -2092102562L;

    public void setTravelerEventId(Long value) {
        this.set(0, value);
    }

    public Long getTravelerEventId() {
        return (Long)this.get(0);
    }

    public void setTravelerId(Long value) {
        this.set(1, value);
    }

    public Long getTravelerId() {
        return (Long)this.get(1);
    }

    public void setTravelerEventTimestamp(Long value) {
        this.set(2, value);
    }

    public Long getTravelerEventTimestamp() {
        return (Long)this.get(2);
    }

    public void setTravelerEventOffset(Long value) {
        this.set(3, value);
    }

    public Long getTravelerEventOffset() {
        return (Long)this.get(3);
    }

    public void setTravelerEventTypeId(Long value) {
        this.set(4, value);
    }

    public Long getTravelerEventTypeId() {
        return (Long)this.get(4);
    }

    public void setTravelerEventStateId(Long value) {
        this.set(5, value);
    }

    public Long getTravelerEventStateId() {
        return (Long)this.get(5);
    }

    public void setTravelerEventData(Object value) {
        this.set(6, value);
    }

    public Object getTravelerEventData() {
        return this.get(6);
    }

    public void setProcTriggerId(Long value) {
        this.set(7, value);
    }

    public Long getProcTriggerId() {
        return (Long)this.get(7);
    }

    public void setJourneyId(Long value) {
        this.set(8, value);
    }

    public Long getJourneyId() {
        return (Long)this.get(8);
    }

    public void setJourneyVersion(Long value) {
        this.set(9, value);
    }

    public Long getJourneyVersion() {
        return (Long)this.get(9);
    }

    public void setOrganizationId(Long value) {
        this.set(10, value);
    }

    public Long getOrganizationId() {
        return (Long)this.get(10);
    }

    public void setPriority(Long value) {
        this.set(11, value);
    }

    public Long getPriority() {
        return (Long)this.get(11);
    }

    public void setRetryCount(Long value) {
        this.set(12, value);
    }

    public Long getRetryCount() {
        return (Long)this.get(12);
    }

    public void setNextRetryTimeUtc(Timestamp value) {
        this.set(13, value);
    }

    public Timestamp getNextRetryTimeUtc() {
        return (Timestamp)this.get(13);
    }

    public void setCheckoutCount(Long value) {
        this.set(14, value);
    }

    public Long getCheckoutCount() {
        return (Long)this.get(14);
    }

    public void setCheckoutUntilUtc(Timestamp value) {
        this.set(15, value);
    }

    public Timestamp getCheckoutUntilUtc() {
        return (Timestamp)this.get(15);
    }

    public void setLastCheckedOutBy(String value) {
        this.set(16, value);
    }

    public String getLastCheckedOutBy() {
        return (String)this.get(16);
    }

    public void setLastWorkedBy(String value) {
        this.set(17, value);
    }

    public String getLastWorkedBy() {
        return (String)this.get(17);
    }

    public void setLastWorkStartTimeUtc(Timestamp value) {
        this.set(18, value);
    }

    public Timestamp getLastWorkStartTimeUtc() {
        return (Timestamp)this.get(18);
    }

    public void setFirstWorkStartTimeUtc(Timestamp value) {
        this.set(19, value);
    }

    public Timestamp getFirstWorkStartTimeUtc() {
        return (Timestamp)this.get(19);
    }

    public void setLastWorkUpdateTimeUtc(Timestamp value) {
        this.set(20, value);
    }

    public Timestamp getLastWorkUpdateTimeUtc() {
        return (Timestamp)this.get(20);
    }

    public void setMarkedDoneTimeUtc(Timestamp value) {
        this.set(21, value);
    }

    public Timestamp getMarkedDoneTimeUtc() {
        return (Timestamp)this.get(21);
    }

    public void setCreatedAt(Timestamp value) {
        this.set(22, value);
    }

    public Timestamp getCreatedAt() {
        return (Timestamp)this.get(22);
    }

    public void setUpdatedAt(Timestamp value) {
        this.set(23, value);
    }

    public Timestamp getUpdatedAt() {
        return (Timestamp)this.get(23);
    }

    public void setRvn(Long value) {
        this.set(24, value);
    }

    public Long getRvn() {
        return (Long)this.get(24);
    }

    @Override
    public Record1<Long> key() {
        return (Record1)super.key();
    }

    public TravelerEventsRecord() {
        super(TravelerEvents.TRAVELER_EVENTS);
    }

    public TravelerEventsRecord(Long travelerEventId, Long travelerId, Long travelerEventTimestamp, Long travelerEventOffset, Long travelerEventTypeId, Long travelerEventStateId, Object travelerEventData, Long procTriggerId, Long journeyId, Long journeyVersion, Long organizationId, Long priority, Long retryCount, Timestamp nextRetryTimeUtc, Long checkoutCount, Timestamp checkoutUntilUtc, String lastCheckedOutBy, String lastWorkedBy, Timestamp lastWorkStartTimeUtc, Timestamp firstWorkStartTimeUtc, Timestamp lastWorkUpdateTimeUtc, Timestamp markedDoneTimeUtc, Timestamp createdAt, Timestamp updatedAt, Long rvn) {
        super(TravelerEvents.TRAVELER_EVENTS);
        this.set(0, travelerEventId);
        this.set(1, travelerId);
        this.set(2, travelerEventTimestamp);
        this.set(3, travelerEventOffset);
        this.set(4, travelerEventTypeId);
        this.set(5, travelerEventStateId);
        this.set(6, travelerEventData);
        this.set(7, procTriggerId);
        this.set(8, journeyId);
        this.set(9, journeyVersion);
        this.set(10, organizationId);
        this.set(11, priority);
        this.set(12, retryCount);
        this.set(13, nextRetryTimeUtc);
        this.set(14, checkoutCount);
        this.set(15, checkoutUntilUtc);
        this.set(16, lastCheckedOutBy);
        this.set(17, lastWorkedBy);
        this.set(18, lastWorkStartTimeUtc);
        this.set(19, firstWorkStartTimeUtc);
        this.set(20, lastWorkUpdateTimeUtc);
        this.set(21, markedDoneTimeUtc);
        this.set(22, createdAt);
        this.set(23, updatedAt);
        this.set(24, rvn);
    }
}

