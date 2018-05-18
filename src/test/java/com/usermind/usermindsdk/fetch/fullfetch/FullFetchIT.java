package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetchTito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FullFetchIT extends TestBase {

    private FullFetchTito fullFetch;
    private MetadataFetchTito metadataFetch;

    @BeforeEach
    void setUp() {
        metadataFetch = new MetadataFetchTito(restTemplate);
        fullFetch = new FullFetchTito(restTemplate, metadataFetch) ;
    }

    @Test
    void testFullFetch() throws NoSuchMethodException {
        fullFetch.performFullFetch(TestClassFactory.getTitoCredentialString());
    }
}