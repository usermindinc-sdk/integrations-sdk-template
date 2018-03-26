package com.usermind.usermindsdk.fetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullFetchIT extends TestBase {

    private FullFetch fullFetch;
    private WorkerConfiguration workerConfiguration;

    @BeforeEach
    void setUp() {
        workerConfiguration = new WorkerConfiguration();
        fullFetch = new FullFetch(restTemplate, workerConfiguration) ;
    }

    @Test
    void testFullFetch() {
        fullFetch.runFullFetch();
    }
}