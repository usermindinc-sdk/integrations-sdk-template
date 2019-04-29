package com.usermind.usermindsdk.registration;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.dropwizard.WorkerConfiguration;
import com.usermind.usermindsdk.workerinformation.SdktemplateInfo;
import com.usermind.usermindsdk.workerinformation.WorkerInfo;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
class RegistrationSdktemplateTest extends TestBase {

    private Registrar registrar;
    private WorkerInfo workerInfo = new SdktemplateInfo();

    public static final String firstResponse = "{\n" +
            "  \"results\" : [ {\n" +
            "    \"id\" : \"32615f79-5d23-4576-94c5-97062256ddaa\",\n" +
            "    \"created_at\" : \"2018-08-01T11:44:08.435\",\n" +
            "    \"updated_at\" : \"2018-09-13T23:17:50.37\",\n" +
            "    \"integration_type\" : \"cornerstone\",\n" +
            "    \"configuration\" : {\"writer\":{\"type\":\"S3\"}},\n" +
            "    \"current_stable_integration_id\" : \"9fda1759-d800-4dea-983b-58e8d0c99028\",\n" +
            "    \"visibility\" : null\n" +
            "  } ],\n" +
            "  \"per_page\" : 499\n" +
            "}";

    public static final String thirdResponse = "{\n" +
            "  \"results\" : [ {\n" +
            "    \"id\" : \"9fda1759-d800-4dea-983b-58e8d0c99028\",\n" +
            "    \"created_at\" : \"2018-08-01T11:44:43.799\",\n" +
            "    \"updated_at\" : \"2018-09-13T22:14:36.096\",\n" +
            "    \"integration_type_id\" : \"32615f79-5d23-4576-94c5-97062256ddaa\",\n" +
            "    \"configuration\" : {\"actions\":{},\"driver\":{\"actionMetadataEnabled\":false},\"onboarding\":{\"allowedFetchFrequencies\":[\"24hrs\",\"12hrs\",\"6hrs\",\"3hrs\",\"1hrs\"],\"auth\":{\"helpText\":\"Cornerstone\",\"keys\":{\"apiId\":{\"displayName\":\"API Id\",\"hidden\":false},\"apiSecret\":{\"displayName\":\"API Secret\",\"hidden\":false},\"domain\":{\"displayName\":\"Domain\",\"hidden\":false},\"username\":{\"displayName\":\"User Name\",\"hidden\":false}},\"type\":\"keyValue\"},\"category\":\"Cornerstone\",\"disconnectable\":true,\"displayName\":\"Cornerstone\",\"icons\":{\"error\":\"//s.usermind.com/images/channels/corner-stone-error-4a79f0ab.svg\",\"off\":\"//s.usermind.com/images/channels/corner-stone-off-d4f50dba.svg\",\"on\":\"//s.usermind.com/images/channels/corner-stone-on-d5cef542.svg\"},\"legacy\":false,\"maxConnections\":1}},\n" +
            "    \"version\" : \"1.0\"\n" +
            "  } ],\n" +
            "  \"per_page\" : 499\n" +
            "}";

    private MockRestServiceServer mockServer;

    @Mock
    WorkerConfiguration configuration;

    @BeforeEach
    void setUp() throws Exception {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
        registrar = new Registrar(restTemplate, objectMapper, configuration, workerInfo);
        return;
    }


    @Test
    void basicTest() throws Exception {
        when(configuration.getIntegrationRestApiUrl()).thenReturn("https://stage-atc-integrations.usermind.com");

        mockServer.expect(requestTo(CoreMatchers.startsWith("https://stage-atc-integrations.usermind.com/v1/integration_types")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(firstResponse, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo(CoreMatchers.startsWith("https://stage-atc-integrations.usermind.com/v1/integration_types")))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo(CoreMatchers.startsWith("https://stage-atc-integrations.usermind.com/v1/integrations")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(thirdResponse, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo(CoreMatchers.startsWith("https://stage-atc-integrations.usermind.com/v1/integrations")))
                .andExpect(method(HttpMethod.PUT))
                .andRespond(withSuccess("{}", MediaType.APPLICATION_JSON));

        assertThat("9fda1759-d800-4dea-983b-58e8d0c99028").isEqualTo(registrar.register());
        mockServer.verify();
    }
}
