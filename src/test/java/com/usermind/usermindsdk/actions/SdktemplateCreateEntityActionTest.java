package com.usermind.usermindsdk.actions;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.actions.actionreturn.ActionResults;
import com.usermind.usermindsdk.authentication.credentials.SdktemplateConnectionData;
import com.usermind.usermindsdk.exceptions.SDKActionConfigurationFailedException;
import org.junit.jupiter.api.BeforeEach;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

/*
    TODO: Make sure to change the names of this and all the other classes in this folder according
        to the type of Action you are performing instead of the generic CreateEntity
 */


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

        // TODO: verify each input data that is created
        // make test cases seeing no exceptions being thrown
        // as well as test cases that do not pass verification and exception is thrown
        inputData.verify();

        Map<String, SdktemplateCreateEntityInput> inputMap = new HashMap<>();
        inputMap.put("key", inputData);
        return inputMap;
    }

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
        ActionResults results = actionHandler.runAction(connectionData, entityName, getInput());
        mockServer.verify();
        assertThat(results.getFailures()).isEmpty();
        assertThat(results.getSuccesses()).hasSize(1);
    }

    @Test
    void testInvalid() throws Exception {
        String entityName = "Products";
        SdktemplateConnectionData sdktemplateConnectionData = TestClassFactory.getCredentialContainerSdktemplate();

        mockServer.expect(method(HttpMethod.PATCH))
                .andRespond(withStatus(HttpStatus.UNAUTHORIZED));

        ActionResults results = actionHandler.runAction(sdktemplateConnectionData, entityName, getInput());

        mockServer.verify();
        assertThat(results.getFailures()).hasSize(1);
        assertThat(results.getFailureResult("key").getErrorMessage()).contains("Unauthorized");
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
