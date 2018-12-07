package com.usermind.usermindsdk.fetch.fetchsetup;


import static org.assertj.core.api.Assertions.*;

import com.usermind.usermindsdk.metadata.EntityInformationSdktemplate;
import org.junit.jupiter.api.Test;
import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
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
