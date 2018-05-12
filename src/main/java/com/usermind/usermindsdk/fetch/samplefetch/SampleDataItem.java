package com.usermind.usermindsdk.fetch.samplefetch;

import com.usermind.usermindsdk.fetch.json.events.DataItem;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;

import static com.google.common.base.Preconditions.checkNotNull;

public class SampleDataItem {

    private DataItem event;
    private Registrations registrations;

    public SampleDataItem() {
    }

    public SampleDataItem(DataItem event, Registrations registrations) {
        this.event = event;
        this.registrations = registrations;
    }

    public DataItem getEvent() {
        return event;
    }

    public void setEvent(DataItem event) {
        this.event = checkNotNull(event);
    }

    public Registrations getRegistrations() {
        return registrations;
    }

    public void setRegistrations(Registrations registrations) {
        this.registrations = checkNotNull(registrations);
    }
}
