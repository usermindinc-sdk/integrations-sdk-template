package com.usermind.usermindsdk.fetch;

import com.usermind.tracking.TrackingLog;
import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.testlib.sdk.SDKTestUtils;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import com.usermind.usermindsdk.fetch.structures.FetchSetupData;
import com.usermind.usermindsdk.fetch.structures.ExtractedData;
import com.usermind.usermindsdk.fetch.structures.FetchData;
import com.usermind.usermindsdk.fetch.drivers.FullFetchDriver;
import com.usermind.usermindsdk.fetch.drivers.IncrementalFetchDriver;
import com.usermind.usermindsdk.metadata.EntityInformation;
import com.usermind.usermindsdk.metadata.EntityInformationSdktemplate;
import com.usermind.usermindsdk.fetch.drivers.SampleFetchDriver;
import com.usermind.usermindsdk.fetch.drivers.TimeLimitedFetchDriver;
import com.usermind.usermindsdk.normalization.Normalizer;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FetchSetupSdktemplateIT extends TestBase {

    private FetchSetupSdktemplate fetchSetup;
    private final EntityInformation entityInformation = new EntityInformationSdktemplate();
    private final SdktemplateConnectionData connectionData = TestClassFactory.getCredentialContainerSdktemplate();

    private TrackingLog trackingLog = TrackingLog.builder().build();

    @Test
    void testSetupCall() throws Exception {
        FetchSetupSdktemplate fetchSetupSdktemplate = ctx.getBean(FetchSetupSdktemplate.class);
        FetchSetupData fetchData = fetchSetupSdktemplate.performFullFetchSetup(connectionData,
                connectionData.getEntities().stream().map(e->e.getEntityName()).collect(Collectors.toSet()) .stream().findAny().get());
        assertThat(fetchData.getFetchSetupWebRequests().isEmpty()).isFalse();
    }

    @Test
    void testFullFetch() throws Exception {
        FullFetchDriver fullFetchSdktemplate = ctx.getBean(FullFetchDriver.class);
        FetchData fetchData = fullFetchSdktemplate.runFullFetch(connectionData,
                connectionData.getEntities().stream().map(e->e.getEntityName()).collect(Collectors.toSet()));

        checkResults(fetchData, true, false, true);
    }

    @Test
    void testIncrementalFetch() throws Exception {
        IncrementalFetchDriver incrementalFetchSdktemplate = ctx.getBean(IncrementalFetchDriver.class);
        FetchData fetchData = incrementalFetchSdktemplate.runIncrementalFetch(connectionData,
                connectionData.getEntities().stream().map(e->e.getEntityName()).collect(Collectors.toSet()), "2019-04-24T00:00:00Z");

        //Incremental fetch will sometimes fetch data, but if run a second time there might not be new data.
        //So don't test the extraction except to make sure it doesn't throw an exception
        checkResults(fetchData, true, false, true);
    }

    @Test
    void testSampleFetch() throws Exception {
        SampleFetchDriver sampleFetchSdktemplate = ctx.getBean(SampleFetchDriver.class);
        FetchData fetchData = sampleFetchSdktemplate.runSampleFetch(connectionData, 10,
                connectionData.getEntities().stream().map(e->e.getEntityName()).collect(Collectors.toSet()));

        checkResults(fetchData, true, false, true);
    }

    @Test
    void testTimeLimitedFetch() throws Exception {
        TimeLimitedFetchDriver timeLimitedFetchSdktemplate = ctx.getBean(TimeLimitedFetchDriver.class);
        FetchData fetchData = timeLimitedFetchSdktemplate.runTimeLimitedFetch(connectionData,
                "2019-03-10T00:00:00Z", "2019-04-15T00:00:00Z",
                connectionData.getEntities().stream().map(e->e.getEntityName()).collect(Collectors.toSet()));

        //Time limited fetch may or may not get data depending on the times chosen.
        //So don't test the extraction except to make sure it doesn't throw an exception
        checkResults(fetchData, false, false, true);
    }

    private void checkResults(FetchData fetchData, boolean checkExtraction, boolean metadata, boolean checkSavePoint) {
        //If the factory returns nothing then this test isn't helpful.
        assertThat(connectionData.getEntities().stream().map(e->e.getEntityName()).collect(Collectors.toSet())).isNotEmpty();
        //It should have run through the entire process and gotten actual data back
        if (metadata) {
            assertThat(fetchData.getAllEntities()).isSubsetOf(Arrays.asList("metadata"));
        } else {
            assertThat(fetchData.getAllEntities()).isSubsetOf(connectionData.getEntities().stream().map(e->e.getEntityName()).collect(Collectors.toSet()));
        }

        Map<String, String> rideAlongs = fetchData.getRideAlongData();
        Map<String, String> allResults = fetchData.getAllByName();

        ExtractDataFromSdktemplateResponse extractor = ctx.getBean(ExtractDataFromSdktemplateResponse.class);
        Normalizer normalizer = new Normalizer(new EntityInformationSdktemplate(), objectMapper);

        if (checkExtraction) {
            allResults.entrySet().stream().
                    forEach(e -> {
                        BufferedReader bufferedReader = SDKTestUtils.stringToBufferedReader( e.getValue());
                        StringWriter stringWriter = new StringWriter();
                        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
                        StringWriter stringWriterForInvalidRecords = new StringWriter();
                        BufferedWriter bufferedWriterInvalidRecords = new BufferedWriter(stringWriterForInvalidRecords);

                        ExtractedData extracted = extractor.extractData(connectionData, e.getKey(),
                                bufferedReader, bufferedWriter, bufferedWriterInvalidRecords, trackingLog, "");

                        //An entity savepoint should be present so that incremental fetches will work.
                        //This means making sure the test entity is set up correctly with a field to use for this.
                        //If this isn't applicable then set checkSavePoint to false, but needs to work in order for incremental fetches to work.
                        if (checkSavePoint) {
                            assertThat(extracted.getEntitySavepoint()).withFailMessage("No entity save point was set. Make sure your connection object in TestClassFactory defines a field for it!").isNotBlank();
                        }
                        //make sure we extracted data!
                        try {
                            bufferedWriter.close();
                            stringWriter.close();
                            String results = stringWriter.toString();
                            assertThat(results.isEmpty()).isFalse();

                            BufferedReader bufferedResults = SDKTestUtils.stringToBufferedReader( results);

                            //Does it normalize OK?
                            String flattenedData = SDKTestUtils.normalize(e.getKey(), results, normalizer);
                            assertThat(flattenedData).isNotEmpty();
                        } catch (IOException e1) {
                            Fail.fail("Threw an exception trying to flush the buffered writer", e);
                        }
                    });
        }
    }


}
