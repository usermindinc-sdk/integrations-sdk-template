package com.usermind.usermindsdk.fetch.fetchsetup;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.fetch.fetchoperations.ExtractDataFromSdktemplateResponse;
import com.usermind.usermindsdk.fetch.fullfetch.FetchData;
import com.usermind.usermindsdk.fetch.fullfetch.FullFetchDriver;
import com.usermind.usermindsdk.fetch.metadatafetch.EntityInformation;
import com.usermind.usermindsdk.fetch.metadatafetch.EntityInformationSdktemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FetchSetupSdktemplateIT extends TestBase {

    private FetchSetupSdktemplate fetchSetup;
    private final EntityInformation entityInformation = new EntityInformationSdktemplate();

    @BeforeEach
    void setUp() throws IOException {
        fetchSetup = new FetchSetupSdktemplate(restTemplate, objectMapper);
    }

    //This test works fine, but makes actual calls which will expire when the credentials do
    @Disabled
    @Test
    void basicTest() throws Exception {

        FetchSetupData fetchData = fetchSetup.performFullFetchSetup(TestClassFactory.getCredentialContainerSdktemplate(), new ArrayList<>(), new ArrayList<>());
        assertThat(fetchData.getFetchSetupWebRequests().isEmpty()).isFalse();
    }

    @Test
    void runFullFetch() throws Exception {
        FullFetchDriver driver = ctx.getBean(FullFetchDriver.class);
        FetchData fetchData = driver.runFullFetch(TestClassFactory.getCredentialContainerSdktemplate(), new ArrayList<>(), new ArrayList<>());
        assertThat(fetchData.getWebRequests().isEmpty()).isFalse();

        ExtractDataFromSdktemplateResponse extractor = ctx.getBean(ExtractDataFromSdktemplateResponse.class);
        fetchData.getWebRequests().entrySet().stream().
                forEach(e -> {
                    extractor.extractData(TestClassFactory.getCredentialContainerSdktemplate(), e.getKey().getEntity(),
                            e.getValue(), new ArrayList<>(), new ArrayList<>(), false);
                });

        return;
    }


}
