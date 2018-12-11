package com.usermind.usermindsdk.fetch;


import com.usermind.usermindsdk.fetch.FetchSetupSdktemplate;
import com.usermind.usermindsdk.metadata.EntityInformationSdktemplate;
import com.usermind.usermindsdk.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FetchSetupSdktemplateTest extends TestBase {
    private FetchSetupSdktemplate fetchSetup;

    @BeforeEach
    void setUp() throws Exception {
        fetchSetup = new FetchSetupSdktemplate(restTemplate, objectMapper, new EntityInformationSdktemplate());
    }


}
