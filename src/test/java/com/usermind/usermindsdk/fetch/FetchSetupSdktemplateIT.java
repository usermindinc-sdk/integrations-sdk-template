package com.usermind.usermindsdk.fetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.TestUtils;
import com.usermind.usermindsdk.fetch.FetchSetupSdktemplate;
import com.usermind.usermindsdk.fetch.structures.FetchSetupData;
import com.usermind.usermindsdk.fetch.ExtractDataFromSdktemplateResponse;
import com.usermind.usermindsdk.fetch.structures.ExtractedData;
import com.usermind.usermindsdk.fetch.structures.FetchData;
import com.usermind.usermindsdk.fetch.drivers.FullFetchDriver;
import com.usermind.usermindsdk.fetch.drivers.IncrementalFetchDriver;
import com.usermind.usermindsdk.metadata.EntityInformation;
import com.usermind.usermindsdk.metadata.EntityInformationSdktemplate;
import com.usermind.usermindsdk.metadata.MetadataFetchDriver;
import com.usermind.usermindsdk.fetch.drivers.SampleFetchDriver;
import com.usermind.usermindsdk.fetch.drivers.TimeLimitedFetchDriver;
import com.usermind.usermindsdk.metadata.MetadataRecords;
import com.usermind.usermindsdk.normalization.Normalizer;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
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

    private void checkResults(FetchData fetchData, boolean checkExtraction, boolean metadata) {
        //If the factory returns nothing then this test isn't helpful.
        assertThat(TestClassFactory.getEntitySet()).isNotEmpty();
        //It should have run through the entire process and gotten actual data back
        if (metadata) {
            assertThat(fetchData.getAllEntities()).containsOnlyElementsOf(Arrays.asList("metadata"));
        } else {
            assertThat(fetchData.getAllEntities()).containsOnlyElementsOf(TestClassFactory.getEntitySet());
        }

        Map<String, String> rideAlongs = fetchData.getRideAlongData();
        Map<String, String> allResults = fetchData.getAllByName();

        ExtractDataFromSdktemplateResponse extractor = ctx.getBean(ExtractDataFromSdktemplateResponse.class);
        Normalizer normalizer = new Normalizer(new EntityInformationSdktemplate(), objectMapper);

        if (checkExtraction) {
            allResults.entrySet().stream().
                    forEach(e -> {
                        BufferedReader bufferedReader = TestUtils.stringToBufferedReader( e.getValue());
                        StringWriter stringWriter = new StringWriter();
                        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);

                        ExtractedData extracted = extractor.extractData(TestClassFactory.getCredentialContainerSdktemplate(), e.getKey(),
                                bufferedReader, bufferedWriter, "");

                        //make sure we extracted data!
                        try {
                            bufferedWriter.close();
                            stringWriter.close();
                            String results = stringWriter.toString();
                            assertThat(results.isEmpty()).isFalse();

                            BufferedReader bufferedResults = TestUtils.stringToBufferedReader( results);

                            //Does it normalize OK?
                            String flattenedData = TestUtils.normalize(e.getKey(), results, normalizer);
                            assertThat(flattenedData).isNotEmpty();
                        } catch (IOException e1) {
                            Fail.fail("Threw an exception trying to flush the buffered writer", e);
                        }
                    });
        }
    }


}
