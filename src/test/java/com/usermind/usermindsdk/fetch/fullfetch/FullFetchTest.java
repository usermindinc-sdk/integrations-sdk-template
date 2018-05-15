package com.usermind.usermindsdk.fetch.fullfetch;

import com.usermind.usermindsdk.TestBase;
import com.usermind.usermindsdk.fetch.json.events.Events;
import com.usermind.usermindsdk.fetch.json.registrations.Registrations;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class FullFetchTest extends TestBase {

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
}
