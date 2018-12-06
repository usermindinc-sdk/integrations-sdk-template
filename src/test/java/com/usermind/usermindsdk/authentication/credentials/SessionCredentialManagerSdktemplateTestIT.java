package com.usermind.usermindsdk.authentication.credentials;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.authentication.credentials.SessionCredentialContainerSdktemplate;
import com.usermind.usermindsdk.authentication.credentials.SessionCredentialManagerSdktemplate;
import com.usermind.usermindsdk.exceptions.InvalidSessionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SessionCredentialManagerSdktemplateTestIT extends TestBase {

    private SessionCredentialManagerSdktemplate sessionCredentials;


    @BeforeEach
    void setUp() throws Exception {
        sessionCredentials = new SessionCredentialManagerSdktemplate(restTemplate, objectMapper);
    }

    @Test
    void testSuccess() throws Exception {

        SessionCredentialContainerSdktemplate session = sessionCredentials.getSession(TestClassFactory.getCredentialContainerSdktemplate());
//TODO SDK: Add in valid asserts to check the values.
//        assertThat(session.getAccessToken()).isNotEmpty();
    }

    @Test
    void testFailure() throws Exception {
        Assertions.assertThrows(InvalidSessionException.class, () -> {
            sessionCredentials.getSession(TestClassFactory.getInvalidCredentialContainerSdktemplate());
        });
    }

    @Test
    void testRepeats() throws Exception {
        SessionCredentialContainerSdktemplate sessionA = sessionCredentials.getSession(TestClassFactory.getCredentialContainerSdktemplate());
        SessionCredentialContainerSdktemplate sessionB = sessionCredentials.getSession(TestClassFactory.getCredentialContainerSdktemplate());

         assertThat(sessionA == sessionB).isTrue().withFailMessage("The cached session was not returned on a second call.");
    }
}
