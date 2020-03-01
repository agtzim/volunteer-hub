package com.agtzim.volunteerhub.util;

import com.agtzim.volunteerhub.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ParticipantsSerializer extends JsonSerializer<Set<User>> {

    @Override
    public void serialize(Set<User> participants, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {

        List<Long> participantIds = new ArrayList<>();
        participants.forEach(p ->{
            System.out.println(p.getUserId());
            participantIds.add(p.getUserId());
        });

        jsonGenerator.writeObject(participantIds);
    }
}
