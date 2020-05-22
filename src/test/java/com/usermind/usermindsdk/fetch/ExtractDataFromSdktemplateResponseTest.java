package com.usermind.usermindsdk.fetch;

import com.usermind.tracking.TrackingLog;
import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.TestUtils;
import com.usermind.usermindsdk.fetch.structures.ExtractedData;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ExtractDataFromSdktemplateResponseTest extends TestBase {

    private ExtractDataFromSdktemplateResponse extractor;

    private TrackingLog trackingLog = TrackingLog.builder().build();

    @BeforeEach
    void setUp() throws Exception {
        extractor = new ExtractDataFromSdktemplateResponse(objectMapper);
    }


    @Test
    void testBasicEntity() throws Exception {
        //"entity" should be the entity name
       testBasic("entity");
    }

    void testBasic(String entityName) throws Exception {
        String entityString = loadFileFixtureAsString(entityName + ".json");

        BufferedReader bufferedReader = TestUtils.stringToBufferedReader(entityString);
        StringWriter stringWriter = new StringWriter();
        BufferedWriter bufferedWriter = new BufferedWriter(stringWriter);
        StringWriter stringWriterForInvalidRecords = new StringWriter();
        BufferedWriter bufferedWriterInvalidRecords = new BufferedWriter(stringWriterForInvalidRecords);

        ExtractedData results = extractor.extractData(TestClassFactory.getCredentialContainerSdktemplate(), entityName, bufferedReader, bufferedWriter, bufferedWriterInvalidRecords, trackingLog, "");
        //Write some less generic tests! If your response has three records in it, for example, this should show three records.
        //Maybe check a few values.
        bufferedWriter.flush();
        String output = stringWriter.toString();
        assertThat(output.isEmpty()).isFalse();

        //Some calls have lots of data and a link to get a next page with more data. Make sure that if there is a next page in the data on disk, that it's detected. Or not,
        //as appropriate.
        assertThat(results.getNextPage().getFetchSetupWebRequests().size()).isEqualTo(0);
        assertThat(results.getNextPage().getFetchSetupWriteRequests().size()).isEqualTo(0);
    }

}
