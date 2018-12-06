package com.usermind.usermindsdk.authentication.credentials;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.exceptions.InvalidSessionException;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
class SessionCredentialManagerSdktemplateTest extends TestBase {

    private SessionCredentialManagerSdktemplate sessionCredentialManagerSdktemplate;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() throws Exception {
        mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
        sessionCredentialManagerSdktemplate = new SessionCredentialManagerSdktemplate(restTemplate, objectMapper);
    }

    @Test
    void testSuccess() throws Exception {
        String responseToken = loadFileFixtureAsString("token.json");
        mockServer.expect(requestTo(CoreMatchers.startsWith(SessionCredentialManagerSdktemplate.SESSION_URI)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseToken, MediaType.APPLICATION_JSON));

        SessionCredentialContainerSdktemplate session = sessionCredentialManagerSdktemplate.getSession(TestClassFactory.getCredentialContainerSdktemplate());

//TODO SDK: Add in valid asserts to check the values.
//        assertThat(session.getAccessToken()).isEqualTo("13_M5FXwUlfXpOw7CfAzsQjTYunuDUvDpToznrDDPSL5l9Kbn9vvhMu5k9BzuWfBitFzqWOE0dctAnloM89Fjtv5Q2-Nzi_Xq3AdR-xA30FOwuP-tgPmL8bGeQrnDYBROdAGAGWT");
        mockServer.verify();
    }

    @Test
    void testFailure() throws Exception {
        String responseToken = loadFileFixtureAsString("invalidtoken.json");
        mockServer.expect(requestTo(CoreMatchers.startsWith(SessionCredentialManagerSdktemplate.SESSION_URI)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseToken, MediaType.APPLICATION_JSON));

        assertThrows(InvalidSessionException.class, () -> {
            sessionCredentialManagerSdktemplate.getSession(TestClassFactory.getCredentialContainerSdktemplate());
        });

        mockServer.verify();
    }
}
