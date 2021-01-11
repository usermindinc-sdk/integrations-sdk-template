package com.usermind.usermindsdk.authentication;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateSessionManager;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthenticationServiceSdktemplateTest extends TestBase {

    private AuthenticationServiceSdktemplate authenticationService;
    //MockRestServiceServer allows you to catch and mock restTemplate calls, meaning you can
    //unit test web APIs without actually going out to the web.
    private MockRestServiceServer mockServer;

    @Mock
    SdktemplateSessionManager sessionCredentialManager;

    @BeforeEach
    void setUp() throws Exception {
        mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
        authenticationService = new AuthenticationServiceSdktemplate(restTemplate, objectMapper, sessionCredentialManager);
    }

    @Test
    void basicTest() throws Exception {

        String accessTokenResponse = loadFileFixtureAsString("credentials/token.json");
        mockServer.expect(requestTo(CoreMatchers.startsWith(AuthenticationServiceSdktemplate.AUTH_CHECKING_PATH)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(accessTokenResponse, MediaType.APPLICATION_JSON));

        authenticationService.validate(TestClassFactory.getCredentialContainerSdktemplate());
        mockServer.verify();
    }

    @Test
    void invalidCredentialsTest() throws Exception {
        mockServer.expect(requestTo(CoreMatchers.startsWith(AuthenticationServiceSdktemplate.AUTH_CHECKING_PATH)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withBadRequest());

        assertThat(authenticationService
                .validate(TestClassFactory.
                        getCredentialContainerSdktemplate()).getStatus()).isEqualTo(Status.CREDENTIALS_FAILURE);

        mockServer.verify();
    }

    @Test
    void connectionExceptionTest() throws Exception {
        mockServer.expect(requestTo(CoreMatchers.startsWith(AuthenticationServiceSdktemplate.AUTH_CHECKING_PATH)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        assertThat(authenticationService
                .validate(TestClassFactory.
                        getCredentialContainerSdktemplate()).getStatus()).isEqualTo(Status.CONNECTION_FAILURE);
        mockServer.verify();
    }
}
