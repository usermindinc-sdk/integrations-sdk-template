package com.usermind.usermindsdk.fetch.samplefetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.baselib.dataReaders.RunPoller;
import com.usermind.usermindsdk.baselib.dataReaders.WorkerInfo;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.dropwizard.urlhandlers.json.TitoInfo;
import com.usermind.usermindsdk.fetch.fullfetch.FullFetch;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class SampleFetchTest extends TestBase {

    private SampleFetch sampleFetch;

    @Mock
    private WorkerConfiguration workerConfiguration;

    @Mock
    private RunPoller runPoller;

    @Mock
    private WorkerInfo workerInfo;

    @Mock
    private MetadataFetch metadataFetch;

    @BeforeEach
    void setUp() {
        sampleFetch = new SampleFetch(restTemplate, workerConfiguration, runPoller, workerInfo, metadataFetch) ;
    }

    @Test
    void testStepSize() {
        assertEquals(1, sampleFetch.findStepSize(-4));
        assertEquals(1, sampleFetch.findStepSize(0));
        assertEquals(1, sampleFetch.findStepSize(4));
        assertEquals(1, sampleFetch.findStepSize(22));
        assertEquals(2, sampleFetch.findStepSize(41));
        assertEquals(6, sampleFetch.findStepSize(124));
        assertEquals(2291, sampleFetch.findStepSize(45832));
    }
}
