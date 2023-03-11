package com.tekcapsule.tekbyte.domain.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekcapsule.tekbyte.domain.model.TekbyteProperty;

import java.util.List;

public class EventsConverter implements DynamoDBTypeConverter<String, List<TekbyteProperty>> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convert(List<TekbyteProperty> events) {
        try {
            return objectMapper.writeValueAsString(events);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert List<Event> to String.", e);
        }
    }

    @Override
    public List<TekbyteProperty> unconvert(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<TekbyteProperty>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert String to List<Event>.", e);
        }
    }
}
