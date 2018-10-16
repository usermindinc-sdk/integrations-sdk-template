package com.usermind.usermindsdk.fetch.fetchsetup;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class FetchSetupSdktemplateTest extends TestBase {
    private FetchSetupSdktemplate fetchSetup;

    @BeforeEach
    void setUp() throws Exception {
        fetchSetup = new FetchSetupSdktemplate(restTemplate, objectMapper);
    }

    @Test
    void testMeta() throws Exception {
        FetchSetupData setupData = fetchSetup.performMetadataFetchSetup(TestClassFactory.getCredentialContainerSdktemplate());
        assertThat(setupData.getFetchSetupWriteRequests().size()).isEqualTo(2);
    }
}
