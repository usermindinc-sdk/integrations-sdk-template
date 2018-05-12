package com.usermind.usermindsdk.fetch.samplefetch;

import com.usermind.usermindsdk.fetch.json.events.DataItem;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.google.common.base.Preconditions.checkNotNull;

public class SampleData {
    private ConcurrentLinkedQueue<SampleDataItem> sampleDataItems = new ConcurrentLinkedQueue<>();

    public void addItem(DataItem dataItem, Registrations registrations) {
        SampleDataItem sdi = new SampleDataItem(dataItem, registrations);
        sampleDataItems.add(sdi);
    }

    public ConcurrentLinkedQueue<SampleDataItem> getSampleDataItems() {
        return sampleDataItems;
    }

    public void setSampleDataItems(ConcurrentLinkedQueue<SampleDataItem> sampleDataItems) {
        this.sampleDataItems = checkNotNull(sampleDataItems);
    }
}
