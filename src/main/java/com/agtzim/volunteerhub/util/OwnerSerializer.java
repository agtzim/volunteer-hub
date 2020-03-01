package com.agtzim.volunteerhub.util;

import com.agtzim.volunteerhub.model.User;
import com.agtzim.volunteerhub.model.UserDTO;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class OwnerSerializer extends JsonSerializer<User> {

    @Override
    public void serialize(User owner, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        jsonGenerator.writeObject(new UserDTO(
                owner.getUserId(),
                owner.getFirstName(),
                owner.getLastName(),
                owner.getEmail(),
                owner.getCountry())
        );
    }
}
