package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.baselib.datareaders.RunPoller;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class FullFetchTest extends TestBase {

    private FullFetch fullFetch;

    private MockRestServiceServer mockServer;

    @Mock
    private RunPoller runPoller;

    @Mock
    private MetadataFetch metadataFetch;

    @BeforeEach
    void setUp() throws IOException {
        fullFetch = new FullFetch(restTemplate, runPoller, metadataFetch);

        //eventsString = loadFileFixtureAsString("events.json");
        mockServer = MockRestServiceServer.bindTo(restTemplate).ignoreExpectOrder(true).build();

    }

    @Test
    void serializingEvents() throws IOException {
        String eventsString = loadFileFixtureAsString("events.json");
        Events events = objectMapper.readValue(eventsString, Events.class);
        assertThat(events.getData().size()).isGreaterThan(5);

        String secondString = objectMapper.writeValueAsString(events);
        assertThat(secondString).isNotEmpty();
    }

    @Test
    void serializingRegistrations() throws IOException {
        String eventsString = loadFileFixtureAsString("registrations.json");
        Registrations registrations = objectMapper.readValue(eventsString, Registrations.class);
        assertThat(registrations.getData().size()).isEqualTo(2);

        String secondString = objectMapper.writeValueAsString(registrations);
        assertThat(secondString).isNotEmpty();
    }

    @Test
    void serializingEmptyRegistrations() throws IOException {
        String eventsString = loadFileFixtureAsString("emptyRegistrations.json");
        Registrations registrations = objectMapper.readValue(eventsString, Registrations.class);
        assertThat(registrations.getData().size()).isEqualTo(0);

        String secondString = objectMapper.writeValueAsString(registrations);
        assertThat(secondString).isNotEmpty();
    }

    @Test
    void testFullFetch() throws IOException, NoSuchMethodException {
        String eventStr = loadFileFixtureAsString("Events.json");
        Events events = objectMapper.readValue(eventStr, Events.class);
        when(metadataFetch.runMetadataFetch(anyString(), anyString())).thenReturn(events);

//        mockServer.expect(requestTo(CoreMatchers.equalTo("https://api.tito.io/v2/ragi-test/events")))
//                .andExpect(method(HttpMethod.GET))
//                .andRespond(withSuccess(eventsString, MediaType.APPLICATION_JSON));
//
//
        fullFetch.performFullFetch();
    }
}
