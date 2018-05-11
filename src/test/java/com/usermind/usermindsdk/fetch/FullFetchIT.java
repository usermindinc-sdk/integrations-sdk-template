package com.usermind.usermindsdk.fetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.baselib.dataReaders.RunPoller;
import com.usermind.usermindsdk.baselib.dataReaders.WorkerInfo;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.dropwizard.urlhandlers.json.TitoInfo;
import com.usermind.usermindsdk.fetch.fullfetch.FullFetch;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FullFetchIT extends TestBase {

    private FullFetch fullFetch;
    private WorkerConfiguration workerConfiguration;
    private RunPoller runPoller;
    private WorkerInfo workerInfo;
    private MetadataFetch metadataFetch;

    @BeforeEach
    void setUp() {
        workerConfiguration = new WorkerConfiguration();
        runPoller = new RunPoller();
        workerInfo = new TitoInfo();
        metadataFetch = new MetadataFetch(restTemplate);
        fullFetch = new FullFetch(restTemplate, workerConfiguration, runPoller, workerInfo, metadataFetch) ;
    }

    @Test
    void testFullFetch() {
        fullFetch.runFullFetch();
    }
}