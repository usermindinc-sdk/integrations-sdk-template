package com.usermind.usermindsdk.actions;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import com.usermind.usermindsdk.exceptions.SDKActionConfigurationFailedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class SdktemplateCreateEntityActionTest extends TestBase {

    private SdktemplateCreateEntityAction actionHandler;
    private MockRestServiceServer mockServer;

    public static final String SUCCESS_BODY = "{\n" +
            "  \"operation\": \"add\",\n" +
            "  \"requestDate\": \"2019-07-19T18:53:04.554Z\",\n" +
            "  \"schema\": \"email\",\n" +
            "  \"nbValidIdentifiers\": 2,\n" +
            "  \"nbInvalidIdentifiers\": 0,\n" +
            "  \"sampleInvalidIdentifiers\": []\n" +
            "}";


    @BeforeEach
    void setUp() throws Exception {
        mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
        actionHandler = new SdktemplateCreateEntityAction();
    }

    private Map<String, SdktemplateCreateEntityInput> getInput() {
        SdktemplateCreateEntityInput inputData = new SdktemplateCreateEntityInput();
        inputData.setField("keyOne", "valueOne");
        inputData.setField("keyTwo", "valueTwo");
        inputData.setField("keyThree", "valueThree");

        Map<String, SdktemplateCreateEntityInput> inputMap = new HashMap<>();
        inputMap.put("key", inputData);
        return inputMap;
    }

    @Disabled
    @Test
    void testValid() throws Exception {
        String entityName = "Products";
        SdktemplateConnectionData connectionData = TestClassFactory.getCredentialContainerSdktemplate();

        //TODO - add a test here to verify that something happened
        /*
        Example test:

        mockServer.expect(requestTo( "https://something.com"))
                .andExpect(method(HttpMethod.PATCH))
                .andRespond(withSuccess(SUCCESS_BODY, MediaType.APPLICATION_JSON));
*/
        actionHandler.runAction(connectionData, entityName, getInput());

//        mockServer.verify();

    }

    @Test
    void testInvalid() throws Exception {
        String entityName = "Products";
        SdktemplateConnectionData sdktemplateConnectionData = TestClassFactory.getCredentialContainerSdktemplate();

        mockServer.expect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.UNAUTHORIZED));

        actionHandler.runAction(sdktemplateConnectionData, entityName, getInput());

        mockServer.verify();
    }

    @Test
    void testNoEntity() throws Exception {
        String entityName = "NotPresent";
        SdktemplateConnectionData credentialContainer = TestClassFactory.getCredentialContainerSdktemplate();
        Map<String, SdktemplateCreateEntityInput> gbqInput = getInput();

        Exception exception = assertThrows(SDKActionConfigurationFailedException.class,
                ()->{
                    actionHandler.runAction(credentialContainer, entityName, gbqInput);

                });
    }


}
