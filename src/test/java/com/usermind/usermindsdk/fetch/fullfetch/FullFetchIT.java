package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.baselib.datareaders.RunPoller;
import com.usermind.usermindsdk.baselib.datareaders.WorkerInfo;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.dropwizard.urlhandlers.json.TitoInfo;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetchTito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class FullFetchIT extends TestBase {

    private FullFetchTito fullFetch;
    private WorkerConfiguration workerConfiguration;
    private RunPoller runPoller;
    private WorkerInfo workerInfo;
    private MetadataFetchTito metadataFetch;

    @BeforeEach
    void setUp() {
        workerConfiguration = new WorkerConfiguration();
        runPoller = new RunPoller();
        workerInfo = new TitoInfo();
        metadataFetch = new MetadataFetchTito(restTemplate);
        fullFetch = new FullFetchTito(restTemplate, metadataFetch) ;
    }

    @Test
    void testFullFetch() throws NoSuchMethodException {
        fullFetch.performFullFetch("ragi-test", "nM_bPyV4sfbVBz8Po28g");
    }
}