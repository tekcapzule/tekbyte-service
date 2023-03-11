package com.tekcapsule.tekbyte.domain.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekcapsule.tekbyte.domain.model.Concept;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConceptConverter implements DynamoDBTypeConverter<List<Map<String, AttributeValue>>, List<Concept>> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<Map<String, AttributeValue>> convert(List<Concept> concepts) {
        List<Map<String, AttributeValue>> conceptMaps = new ArrayList<>();
        for (Concept concept : concepts) {
            Map<String, AttributeValue> conceptMap = new HashMap<>();
            try {
                conceptMap.put("event", new AttributeValue(mapper.writeValueAsString(concept)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            conceptMaps.add(conceptMap);
        }
        return conceptMaps;
    }

    @Override
    public List<Concept> unconvert(List<Map<String, AttributeValue>> conceptMaps) {
        List<Concept> concepts = new ArrayList<>();
        for (Map<String, AttributeValue> conceptMap : conceptMaps) {
            Concept concept = null;
            try {
                concept = mapper.readValue((DataInput) conceptMap.get("event"), Concept.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            concepts.add(concept);
        }
        return concepts;
    }
}

