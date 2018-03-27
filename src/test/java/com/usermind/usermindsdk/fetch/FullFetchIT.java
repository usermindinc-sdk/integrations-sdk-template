package com.usermind.usermindsdk.fetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.baselib.dataReaders.RunPoller;
import com.usermind.usermindsdk.baselib.dataReaders.WorkerInfo;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.dropwizard.urlhandlers.json.TitoInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FullFetchIT extends TestBase {

    private FullFetch fullFetch;
    private WorkerConfiguration workerConfiguration;
    private RunPoller runPoller;
    private WorkerInfo workerInfo;

    @BeforeEach
    void setUp() {
        workerConfiguration = new WorkerConfiguration();
        runPoller = new RunPoller();
        workerInfo = new TitoInfo();
        fullFetch = new FullFetch(restTemplate, workerConfiguration, runPoller, workerInfo) ;
    }

    @Test
    void testFullFetch() {
        fullFetch.runFullFetch();
    }
}