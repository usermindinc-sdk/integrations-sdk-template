package com.usermind.usermindsdk.fetch.metadata;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.fetch.json.events.Events;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.equalTo;

class MetadataFetchTest extends TestBase {

    private MetadataFetch metadataFetch;
    private MockRestServiceServer mockServer;

    private String eventsString;

    @BeforeEach
    void setUp() throws IOException {
        eventsString = loadFileFixtureAsString("events.json");
        //mockServer = MockRestServiceServer.createServer(restTemplate);
        mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();
        metadataFetch = new MetadataFetch(restTemplate);
    }

    @Test
    void basicTest() {

        mockServer.expect(requestTo(CoreMatchers.equalTo("https://api.tito.io/v2/ragi-test/events")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(eventsString, MediaType.APPLICATION_JSON));

        Events events = metadataFetch.runMetadataFetch("ragi-test", "nM_bPyV4sfbVBz8Po28g");
        assertThat(events.getData().size()).isGreaterThan(5);

        mockServer.verify();
    }

    @Test
    void testError() {
        mockServer.expect(requestTo(CoreMatchers.equalTo("https://api.tito.io/v2/ragi-test/events")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));

        Events events = metadataFetch.runMetadataFetch("ragi-test", "nM_bPyV4sfbVBz8Po28g");
        assertThat(events.getData().size()).isEqualTo(0);

        mockServer.verify();

    }
}
