package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.baselib.datareaders.RunPoller;
import com.usermind.usermindsdk.baselib.datareaders.WorkerInfo;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.dropwizard.urlhandlers.json.TitoInfo;
import com.usermind.usermindsdk.fetch.fullfetch.FullFetch;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

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
        fullFetch = new FullFetch(restTemplate, runPoller, metadataFetch) ;
    }

    @Test
    void testFullFetch() {
        fullFetch.runFullFetch();
    }
}