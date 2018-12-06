package com.usermind.usermindsdk.authentication.credentials;

import com.fasterxml.jackson.databind.JsonNode;
import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.exceptions.InvalidSessionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SessionCredentialContainerSdktemplateTest extends TestBase {

    private SessionCredentialContainerSdktemplate session;

    @BeforeEach
    void setUp() throws Exception {
        session = new SessionCredentialContainerSdktemplate(objectMapper);
    }

    @Test
    void testReadingToken() throws Exception {
        SessionCredentialContainerSdktemplate session = new SessionCredentialContainerSdktemplate(objectMapper);
        String responseToken = loadFileFixtureAsString("token.json");
        JsonNode node = objectMapper.readTree(responseToken);

        session.load(node);
//TODO SDK: Add in valid asserts to check the values.
//        assertThat(session.getAccessToken()).isEqualTo("the token");
//        assertThat(session.getExpiresIn()).isEqualTo(7200);
//        assertThat(session.getErrcode()).isNotNull();
    }

    @Test
    void testReadingFailure() throws Exception {
        SessionCredentialContainerSdktemplate session = new SessionCredentialContainerSdktemplate(objectMapper);
        String responseToken = loadFileFixtureAsString("invalidtoken.json");
        JsonNode node = objectMapper.readTree(responseToken);

        assertThrows(InvalidSessionException.class,
                ()->{
                    session.load(node);
                });
//TODO SDK: Add in valid asserts to check the values.
//        assertThat(session.getAccessToken()).isNull();
//        assertThat(session.getErrcode()).isEqualTo(40013);
//        assertThat(session.getErrmsg()).startsWith("invalid appid");
    }

}
