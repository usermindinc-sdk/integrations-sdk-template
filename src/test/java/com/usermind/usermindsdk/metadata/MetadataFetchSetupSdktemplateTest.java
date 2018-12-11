package com.usermind.usermindsdk.metadata;

import static org.assertj.core.api.Assertions.*;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.fetch.structures.FetchSetupData;
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
        //Call the setup
        FetchSetupData setupData = metadataFetchSetup.performMetadataFetchSetup(TestClassFactory.getCredentialContainerSdktemplate());

        //Add some better tests. This just makes sure that requests were actually written.
        assertThat(setupData.getTaskCount() > 0).isTrue();
    }

}
