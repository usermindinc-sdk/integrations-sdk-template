package com.usermind.usermindsdk.fetch.samplefetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetchSdktemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;

class SampleFetchSdktemplateTest extends TestBase {

    private SampleFetchSdktemplate sampleFetch;

    @Mock
    private MetadataFetchSdktemplate metadataFetch;

    @BeforeEach
    void setUp() {
        sampleFetch = new SampleFetchSdktemplate(restTemplate, metadataFetch) ;
    }

    @Test
    void testStepSize() {
        assertThat(1).isEqualTo(sampleFetch.findStepSize(-4));
        assertThat(1).isEqualTo(sampleFetch.findStepSize(0));
        assertThat(1).isEqualTo(sampleFetch.findStepSize(4));
        assertThat(1).isEqualTo(sampleFetch.findStepSize(22));
        assertThat(2).isEqualTo(sampleFetch.findStepSize(41));
        assertThat(6).isEqualTo(sampleFetch.findStepSize(124));
        assertThat(2291).isEqualTo(sampleFetch.findStepSize(45832));
    }
}
