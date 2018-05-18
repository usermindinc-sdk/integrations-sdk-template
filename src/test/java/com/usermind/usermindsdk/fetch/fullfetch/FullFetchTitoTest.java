package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.TestClassFactory;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import com.usermind.usermindsdk.fetch.metadata.MetadataFetchTito;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ExtendWith(MockitoExtension.class)
class FullFetchTitoTest extends TestBase {

    private FullFetchTito fullFetch;

    private MockRestServiceServer mockServer;

    @Mock
    private MetadataFetchTito metadataFetch;// = mock(MetadataFetch.class);

    @BeforeEach
    void setUp() throws IOException {
        fullFetch = new FullFetchTito(restTemplate, metadataFetch);

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
        String eventStr = loadFileFixtureAsString("TwoEvents.json");
        String firstReg = loadFileFixtureAsString("FirstRegistrations.json");
        String secondReg = loadFileFixtureAsString("SecondRegistrations.json");
        Events events = objectMapper.readValue(eventStr, Events.class);
//        when(metadataFetch.runMetadataFetch(anyString(), anyString())).thenReturn(events);
        when(metadataFetch.performMetadataFetch(ArgumentMatchers.eq(TestClassFactory.getTitoCredentialString()))).thenReturn(events);

        mockServer.expect(requestTo(CoreMatchers.equalTo("https://api.tito.io/v2/ragi-test/2016-edition/registrations")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(firstReg, MediaType.APPLICATION_JSON));

        mockServer.expect(requestTo(CoreMatchers.equalTo("https://api.tito.io/v2/ragi-test/2017-edition/registrations")))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(firstReg, MediaType.APPLICATION_JSON));

        fullFetch.performFullFetch(TestClassFactory.getTitoCredentialString());

        mockServer.verify();
    }
}
