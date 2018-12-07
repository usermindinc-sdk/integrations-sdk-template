package com.usermind.usermindsdk.fetch.fetchoperations;

import com.fasterxml.jackson.databind.JsonNode;
import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.metadata.EntityInformationSdktemplate;
import com.usermind.usermindsdk.normalization.Normalizer;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@ExtendWith(MockitoExtension.class)
class ExtractDataFromSdktemplateResponseTest extends TestBase {

    private ExtractDataFromSdktemplateResponse extractor;
    private MockRestServiceServer mockServer;

    public static final String URI_CALLED_FOR_AUTHENTICATION = "should be a reference to the java class, not defined here";

    //TODO - replace with actual response
    public static final String accessTokenResponse = "{\n" +
            "\"access_token\": \"13_e4sqat59Qro98zIY-ortv56IX1ciGxF7dvdHCNeaNY2uScpHbZ7jB1tXsP5tRe3sWF14lVV6S3kzXsEW1iDu62tV_v-e0DL-M087LZzPIVPg5PVvW7pCtU9idLcTSGfABADCQ\",\n" +
            "\"expires_in\": 7200\n" +
            "}";


    //TODO - remove these lines and use actual entities
    public static final String ENTITY_TYPE = "REPLACE ME WITH ACTUAL ENTITY TYPE";
    public static final String ANOTHER_ENTITY_TYPE = "REPLACE ME WITH A DIFFERENT ACTUAL ENTITY TYPE";

    @BeforeEach
    void setUp() throws Exception {
        mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
        extractor = new ExtractDataFromSdktemplateResponse(objectMapper);
    }

    @Test
    void testFollowerList() throws Exception {

        mockServer.expect(requestTo(CoreMatchers.startsWith(URI_CALLED_FOR_AUTHENTICATION)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(accessTokenResponse, MediaType.APPLICATION_JSON));

        String firstUserList = "{\"total\":1,\"count\":1,\"data\":{\"openid\":[\"1xhK75iKyXz-Xl0McY3XOeJXqZxo\",\"2xhK75iKyXz-Xl0McY3XOeJXqZxo\"]},\"next_openid\":\"oxhK75iKyXz-Xl0McY3XOeJXqZxo\"}";

        ExtractedData extracted = extractor.extractData(TestClassFactory.getCredentialContainerSdktemplate(), ENTITY_TYPE,
                firstUserList, false, "");

        mockServer.verify();
        assertThat(extracted.getNextPage().getFetchSetupWebRequests().size()).isEqualTo(1);

        //does this normalize correctly?
        Normalizer normalizer = new Normalizer(new EntityInformationSdktemplate(), objectMapper);
        String flattenedData = normalizer.normalizeData(ENTITY_TYPE, extracted.getExtractedDataItems().get(0));
        assertThat(flattenedData).contains("records");
        assertThat(flattenedData).contains("1xhK75iKy");

        return;
    }

    @Test
    void testNoNextLinkFollowerList() throws Exception {

        String secondUserList = "{\"total\": 2,\"count\": 0,\"next_openid\": \"\"}";

//        mockServer.expect(requestTo(CoreMatchers.startsWith(SessionCredentials.SESSION_URI)))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withSuccess(accessTokenResponse, MediaType.APPLICATION_JSON));

        ExtractedData extracted = extractor.extractData(TestClassFactory.getCredentialContainerSdktemplate(), ENTITY_TYPE,
                secondUserList, false, "");

        mockServer.verify();

        assertThat(extracted.getNextPage().getFetchSetupWebRequests().size()).isEqualTo(0);
        return;
    }

    @Test
    void testTemplates() throws Exception {
        String templateList = "{\"template_list\":[{\"template_id\":\"7Hq5pCS2aIZ9Udl2ftm2DOmHGDD0LaquSQW9kDL9wAE\",\"title\":\"Test\",\"primary_industry\":\"\",\"deputy_industry\":\"\",\"content\":\"{{Hello}}\",\"example\":\"\"}]}";

        ExtractedData extracted = extractor.extractData(TestClassFactory.getCredentialContainerSdktemplate(), ANOTHER_ENTITY_TYPE,
                templateList, false, "");

        assertThat(extracted.getNextPage().getFetchSetupWebRequests().size()).isEqualTo(0);

        //does this normalize correctly?
        Normalizer normalizer = new Normalizer(new EntityInformationSdktemplate(), objectMapper);
        String flattenedData = normalizer.normalizeData(ANOTHER_ENTITY_TYPE, extracted.getExtractedDataItems().get(0));

        assertThat(flattenedData).contains("records");
        assertThat(flattenedData).contains("CS2aIZ9Udl2ftm2DO");

        //Did the key get converted to a field named Id?
        assertThat(flattenedData).doesNotContain("template_id");
        JsonNode rootNode = objectMapper.readTree(flattenedData);
        assertThat("7Hq5pCS2aIZ9Udl2ftm2DOmHGDD0LaquSQW9kDL9wAE").isEqualTo(rootNode.findValue("Id").textValue());
        return;
    }

}
