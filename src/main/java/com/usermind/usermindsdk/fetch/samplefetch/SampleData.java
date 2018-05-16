package com.usermind.usermindsdk.fetch.samplefetch;

import com.usermind.usermindsdk.fetch.FetchedData;
import com.usermind.usermindsdk.fetch.json.events.DataItem;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.google.common.base.Preconditions.checkNotNull;

public class SampleData implements FetchedData {
    private ConcurrentLinkedQueue<SampleDataItem> sampleDataItems = new ConcurrentLinkedQueue<>();

    public void addItem(DataItem dataItem, Registrations registrations) {
        SampleDataItem sdi = new SampleDataItem(dataItem, registrations);
        sampleDataItems.add(sdi);
    }

    public Queue<SampleDataItem> getSampleDataItems() {
        return sampleDataItems;
    }

    public void setSampleDataItems(ConcurrentLinkedQueue<SampleDataItem> sampleDataItems) { //NOSONAR - this needs to be a concurrent queue for thread safety
        this.sampleDataItems = checkNotNull(sampleDataItems);
    }
}
