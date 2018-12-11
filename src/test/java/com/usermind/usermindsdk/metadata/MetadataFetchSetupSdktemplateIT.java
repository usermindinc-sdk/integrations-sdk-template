package com.usermind.usermindsdk.metadata;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class MetadataFetchSetupSdktemplateIT extends TestBase {

    @Disabled
    @Test
    void testMetadataFetch() throws Exception{

        //This integration test exercizes most of your metadata fetching code.
        MetadataFetchDriver metadataFetchDriver = ctx.getBean(MetadataFetchDriver.class);
        List<MetadataRecords> records = metadataFetchDriver.runMetadataFetch(TestClassFactory.getCredentialContainerSdktemplate());
        assertThat(records.size()).isNotZero();
    }

}
