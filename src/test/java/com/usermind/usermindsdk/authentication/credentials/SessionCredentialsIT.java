package com.usermind.usermindsdk.authentication.credentials;


import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class SessionCredentialsIT extends TestBase {

    private SdktemplateSessionManager sessionCredentials;

    @Disabled
    @Test
    void testGetSessionCredentials() throws Exception {
        sessionCredentials = new SdktemplateSessionManager(restTemplate);
        SdktemplateSession session = sessionCredentials.getSession(TestClassFactory.getCredentialContainerSdktemplate());
        assertThat(session.getAccessToken()).isNotEmpty();
    }
}
