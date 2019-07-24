package com.usermind.usermindsdk.authentication;

import static org.mockito.Mockito.*;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.authentication.credentials.ConnectionDataSdktemplate;
import com.usermind.usermindsdk.authentication.credentials.SessionCredentialContainerSdktemplate;
import com.usermind.usermindsdk.authentication.credentials.SessionCredentialManagerSdktemplate;
import com.usermind.usermindsdk.authentication.exceptions.ConnectionException;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import com.usermind.usermindsdk.authentication.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthenticationServiceSdktemplateTest extends TestBase {

    private AuthenticationServiceSdktemplate authenticationService;
    private MockRestServiceServer mockServer;

    @Mock
    SessionCredentialManagerSdktemplate sessionCredentialManager;

    @Mock
    SessionCredentialContainerSdktemplate sessionCredentialContainer;


    @BeforeEach
    void setUp() throws Exception {
        mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
        authenticationService = new AuthenticationServiceSdktemplate(restTemplate, objectMapper, sessionCredentialManager);

        when(sessionCredentialManager.validate(any(ConnectionDataSdktemplate.class))).thenReturn(sessionCredentialContainer);
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

        Assertions.assertThrows(InvalidCredentialsException.class, () -> {
            authenticationService.validate(TestClassFactory.getCredentialContainerSdktemplate());
        });

        mockServer.verify();
    }

    @Test
    void connectionExceptionTest() throws Exception {

        mockServer.expect(requestTo(CoreMatchers.startsWith(AuthenticationServiceSdktemplate.AUTH_CHECKING_PATH)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        Assertions.assertThrows(ConnectionException.class, () -> {
            authenticationService.validate(TestClassFactory.getCredentialContainerSdktemplate());
        });
        mockServer.verify();
    }
}
