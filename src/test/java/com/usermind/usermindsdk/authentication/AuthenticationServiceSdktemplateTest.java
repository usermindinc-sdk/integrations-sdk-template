package com.usermind.usermindsdk.authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.authentication.exceptions.ConnectionException;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import com.usermind.usermindsdk.authentication.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceSdktemplateTest extends TestBase {

    private AuthenticationServiceSdktemplate authenticationService;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() throws IOException {
        mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
        authenticationService = new AuthenticationServiceSdktemplate(restTemplate, objectMapper);
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
