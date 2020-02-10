package com.usermind.usermindsdk.authentication.credentials;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.usermind.usermindsdk.TestBase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SdktemplateSessionInformationTest extends TestBase {

    public static final String  BASIC_INPUT = "{\n" +
            "  \"Validations\" : null,\n" +
            "  \"status\" : 201,\n" +
            "  \"timeStamp\" : \"2018-07-25T21:07:17+0000\",\n" +
            "  \"createdRecords\" : 1,\n" +
            "  \"data\" : [ {\n" +
            "    \"access_token\" : \"1smu27drq7ezq\",\n" +
            "    \"expires_in\" : \"2018-07-25T22:07:17+0000\"\n" +
            "  } ]\n" +
            "}";


    @Test
    void testBasicParsing() throws Exception {

        JsonNode jsonNode = objectMapper.readTree(BASIC_INPUT);
        SdktemplateSession sessionInformation = new SdktemplateSession();
        sessionInformation = (SdktemplateSession)sessionInformation.getInstance(jsonNode.get("data").get(0));

        assertThat(sessionInformation.getAccessToken()).isEqualTo("1smu27drq7ezq");
        assertThat(sessionInformation.getExpiresIn()).isEqualTo(OffsetDateTime.parse("2018-07-25T22:07:17.000+00:00"));
    }

    @Test
    void testDeserialization() throws Exception {
        //Mostly checking that all the Jackson annotations are correct
        //If they aren't then testBasicParsing will fail, this is just a little more direct of a test, mostly left
        //here from fixing the Jackson annotations and wanting as direct an example as I could get
        JsonNode jsonNode = objectMapper.readTree(BASIC_INPUT);
        SdktemplateSession session = objectMapper.treeToValue(jsonNode.get("data").get(0), SdktemplateSession.class);
        assertThat(session.getAccessToken().equals("1smu27drq7ezq"));
    }

    @Test
    void testNoData() throws Exception {
        String input = "{\n" +
                "  \"Validations\" : null,\n" +
                "  \"status\" : 201,\n" +
                "  \"timeStamp\" : \"2018-07-25T21:07:17+0000\"\n" +
                "}";

        JsonNode jsonNode = objectMapper.readTree(input);

        assertThrows(MismatchedInputException.class, () -> {
            SdktemplateSession session = new SdktemplateSession();
            session.getInstance(jsonNode);
        });
    }
}




