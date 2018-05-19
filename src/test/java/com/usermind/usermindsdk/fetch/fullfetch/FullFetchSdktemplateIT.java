package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetchSdktemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FullFetchSdktemplateIT extends TestBase {

    private FullFetchSdktemplate fullFetch;
    private MetadataFetchSdktemplate metadataFetch;

    @BeforeEach
    void setUp() {
        metadataFetch = new MetadataFetchSdktemplate(restTemplate);
        fullFetch = new FullFetchSdktemplate(restTemplate, metadataFetch) ;
    }

    @Test
    void testFullFetch() throws NoSuchMethodException {
        fullFetch.performFullFetch(TestClassFactory.getSdktemplateCredentialString());
    }
}