package com.agtzim.volunteerhub.util;

import com.agtzim.volunteerhub.model.Event;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EventSerializer extends JsonSerializer<Set<Event>> {

    @Override
    public void serialize(Set<Event> events, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        List<Long> eventIds = new ArrayList<>();
        events.forEach(e -> {
            eventIds.add(e.getEventId());
        });
        jsonGenerator.writeObject(eventIds);
    }
}
