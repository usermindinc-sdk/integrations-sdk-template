package com.usermind.usermindsdk.fetch;


import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.fetch.FetchSetupSdktemplate;
import com.usermind.usermindsdk.fetch.structures.FetchSetupData;
import com.usermind.usermindsdk.metadata.EntityInformationSdktemplate;
import com.usermind.usermindsdk.TestBase;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
class FetchSetupSdktemplateTest extends TestBase {
    private FetchSetupSdktemplate fetchSetup;

//    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() throws Exception {
        fetchSetup = new FetchSetupSdktemplate(restTemplate, objectMapper, new EntityInformationSdktemplate());
    }

    //A sample unit test to call a fetch setup class that needs to make a rest call of its own in order to get
    //the data to figure out what to fetch.

//    @Test
//    void testFull() throws Exception {
//        String accessTokenResponse = loadFileFixtureAsString("adaccounts.json");
//        mockServer.expect(requestTo(CoreMatchers.startsWith(FetchSetupSdktemplate.AD_ACCOUNTS_URI)))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withSuccess(accessTokenResponse, MediaType.APPLICATION_JSON));
//
//        FetchSetupData setupData = fetchSetup.performFullFetchSetup(TestClassFactory.getCredentialContainerSdktemplate(), "");
//
//        assertThat(setupData.getTaskCount()).isGreaterThan(0);
//        mockServer.verify();
//    }

}
