package com.usermind.usermindsdk.dropwizard.urlhandlers;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.authentication.Authenticator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticatorURLHandlerTest extends TestBase {

    private AuthenticatorURLHandler authenticatorURLHandler;

    private String validJson = "    {\n" +
            "  \"connectionData\": {\n" +
            "    \"encrypted\": {\n" +
            "      \"credentials\": {\n" +
            "        \"clientId\": \"ragi-test\",\n" +
            "        \"clientSecret\": \"nM_bPyV4sfbVBz8Po28g\"\n" +
            "      },\n" +
            "      \"endpoints\": {\n" +
            "        \"api\": \"unused\",\n" +
            "        \"identity\": \"unused\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
    @Mock
    private Authenticator authenticator;

    @BeforeEach
    void setUp() {
        authenticatorURLHandler = new AuthenticatorURLHandler(authenticator, objectMapper);
    }

    @Test
    void testDeserialization() throws IOException {
            authenticatorURLHandler.authenticate(validJson);
    }
}