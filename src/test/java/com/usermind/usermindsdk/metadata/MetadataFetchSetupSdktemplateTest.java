package com.usermind.usermindsdk.metadata;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.fetch.fetchsetup.FetchSetupData;
import com.usermind.usermindsdk.fetch.fetchsetup.FetchSetupSdktemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

@ExtendWith(MockitoExtension.class)
class MetadataFetchSetupSdktemplateTest extends TestBase {

    private MetadataFetchSetupSdktemplate metadataFetchSetup;

    @BeforeEach
    void setUp() throws Exception {
        metadataFetchSetup = new MetadataFetchSetupSdktemplate(restTemplate, objectMapper, new EntityInformationSdktemplate());
    }

    @Test
    void testMeta() throws Exception {
        FetchSetupData setupData = metadataFetchSetup.performMetadataFetchSetup(TestClassFactory.getCredentialContainerSdktemplate());
        assertThat(setupData.getFetchSetupWriteRequests().size()).isEqualTo(2);
    }

}
