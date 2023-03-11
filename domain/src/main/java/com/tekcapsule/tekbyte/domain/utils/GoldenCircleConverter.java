package com.tekcapsule.tekbyte.domain.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekcapsule.tekbyte.domain.model.GoldenCircle;

public class GoldenCircleConverter implements DynamoDBTypeConverter<String, GoldenCircle> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convert(GoldenCircle object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting object to JSON", e);
        }
    }

    @Override
    public GoldenCircle unconvert(String jsonString) {
        try {
            return mapper.readValue(jsonString, GoldenCircle.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting JSON to object", e);
        }
    }
}
