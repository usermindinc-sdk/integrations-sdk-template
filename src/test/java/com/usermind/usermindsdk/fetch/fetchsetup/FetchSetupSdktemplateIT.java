package com.usermind.usermindsdk.fetch.fetchsetup;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.fetch.fetchoperations.ExtractDataFromSdktemplateResponse;
import com.usermind.usermindsdk.fetch.fetchoperations.ExtractedData;
import com.usermind.usermindsdk.fetch.fullfetch.FetchData;
import com.usermind.usermindsdk.fetch.fullfetch.FullFetchDriver;
import com.usermind.usermindsdk.fetch.incrementalfetch.IncrementalFetchDriver;
import com.usermind.usermindsdk.metadata.EntityInformation;
import com.usermind.usermindsdk.metadata.EntityInformationSdktemplate;
import com.usermind.usermindsdk.metadata.MetadataFetchDriver;
import com.usermind.usermindsdk.fetch.samplefetch.SampleFetchDriver;
import com.usermind.usermindsdk.fetch.timefetch.TimeLimitedFetchDriver;
import com.usermind.usermindsdk.normalization.Normalizer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FetchSetupSdktemplateIT extends TestBase {

    private FetchSetupSdktemplate fetchSetup;
    private final EntityInformation entityInformation = new EntityInformationSdktemplate();

    @Disabled
    @Test
    void testSetupCall() throws Exception {
        FetchSetupSdktemplate fetchSetupSdktemplate = ctx.getBean(FetchSetupSdktemplate.class);
        FetchSetupData fetchData = fetchSetupSdktemplate.performFullFetchSetup(TestClassFactory.getCredentialContainerSdktemplate(), TestClassFactory.getEntitySet().stream().findAny().get());
        assertThat(fetchData.getFetchSetupWebRequests().isEmpty()).isFalse();
    }

    @Disabled
    @Test
    void testFullFetch() throws Exception {
        FullFetchDriver fullFetchSdktemplate = ctx.getBean(FullFetchDriver.class);
        FetchData fetchData = fullFetchSdktemplate.runFullFetch(TestClassFactory.getCredentialContainerSdktemplate(), TestClassFactory.getEntitySet());

        checkResults(fetchData, true, false);
    }

    @Disabled
    @Test
    void testIncrementalFetch() throws Exception {
        IncrementalFetchDriver incrementalFetchSdktemplate = ctx.getBean(IncrementalFetchDriver.class);
        FetchData fetchData = incrementalFetchSdktemplate.runIncrementalFetch(TestClassFactory.getCredentialContainerSdktemplate(), TestClassFactory.getEntitySet(), "2019-04-24T00:00:00Z");

        //Incremental fetch will sometimes fetch data, but if run a second time there might not be new data.
        //So don't test the extraction except to make sure it doesn't throw an exception
        checkResults(fetchData, false, false);
    }

    @Disabled
    @Test
    void testSampleFetch() throws Exception {
        SampleFetchDriver sampleFetchSdktemplate = ctx.getBean(SampleFetchDriver.class);
        FetchData fetchData = sampleFetchSdktemplate.runSampleFetch(TestClassFactory.getCredentialContainerSdktemplate(), 10, TestClassFactory.getEntitySet());

        checkResults(fetchData, true, false);
    }

    @Disabled
    @Test
    void testTimeLimitedFetch() throws Exception {
        TimeLimitedFetchDriver timeLimitedFetchSdktemplate = ctx.getBean(TimeLimitedFetchDriver.class);
        FetchData fetchData = timeLimitedFetchSdktemplate.runTimeLimitedFetch(TestClassFactory.getCredentialContainerSdktemplate(), "2019-03-10T00:00:00Z", "2019-04-15T00:00:00Z", TestClassFactory.getEntitySet());

        //Time limited fetch may or may not get data depending on the times chosen.
        //So don't test the extraction except to make sure it doesn't throw an exception
        checkResults(fetchData, false, false);
    }

    @Disabled
    @Test
    void testMetadataFetch() throws Exception{
        MetadataFetchDriver metadataFetchDriver = ctx.getBean(MetadataFetchDriver.class);
        FetchData fetchData = metadataFetchDriver.runMetadataFetch(TestClassFactory.getCredentialContainerSdktemplate());

        checkResults(fetchData, true, true);
    }

    private void checkResults(FetchData fetchData, boolean checkExtraction, boolean metadata) {
        //If the factory returns nothing then this test isn't helpful.
        assertThat(TestClassFactory.getEntitySet()).isNotEmpty();
        //It should have run through the entire process and gotten actual data back
        if (metadata) {
            assertThat(fetchData.getAllEntities()).containsOnlyElementsOf(Arrays.asList("metadata"));
        } else {
            assertThat(fetchData.getAllEntities()).containsOnlyElementsOf(TestClassFactory.getEntitySet());
        }


        Map<String, String> allResults = fetchData.getAllByName();
        ExtractDataFromSdktemplateResponse extractor = ctx.getBean(ExtractDataFromSdktemplateResponse.class);
        Normalizer normalizer = new Normalizer(new EntityInformationSdktemplate(), objectMapper);

        if (checkExtraction) {
            allResults.entrySet().stream().
                    forEach(e -> {
                        ExtractedData extracted = extractor.extractData(TestClassFactory.getCredentialContainerSdktemplate(), e.getKey(),
                                e.getValue(), metadata, "");
                        //make sure we extracted data!
                        assertThat(extracted.getExtractedDataItems().isEmpty()).isFalse();

                        //Does it normalize OK?
                        String flattenedData = null;
                        try {
                            flattenedData = normalizer.normalizeData("Entity", extracted.getExtractedDataItems().get(0));
                        } catch (Exception e1) {
                            assertThat(false).withFailMessage("Normalization threw an error! " + e1.getMessage());
                        }
                        assertThat(flattenedData).isNotEmpty();
                    });
        }
    }


}
