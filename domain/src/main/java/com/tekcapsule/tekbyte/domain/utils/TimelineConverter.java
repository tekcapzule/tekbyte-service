package com.tekcapsule.tekbyte.domain.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekcapsule.tekbyte.domain.model.Event;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TimelineConverter implements DynamoDBTypeConverter<List<Map<String, AttributeValue>>, List<Event>> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<Map<String, AttributeValue>> convert(List<Event> events) {
        List<Map<String, AttributeValue>> timeLineMaps = new ArrayList<>();
        for (Event event : events) {
            Map<String, AttributeValue> timeLineMap = new HashMap<>();
            try {
                timeLineMap.put("event", new AttributeValue(mapper.writeValueAsString(event)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            timeLineMaps.add(timeLineMap);
        }
        return timeLineMaps;
    }

    @Override
    public List<Event> unconvert(List<Map<String, AttributeValue>> timeLineMaps) {
        List<Event> events = new ArrayList<>();
        for (Map<String, AttributeValue> applicationMap : timeLineMaps) {
            Event event = null;
            try {
                event = mapper.readValue((DataInput) applicationMap.get("event"), Event.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            events.add(event);
        }
        return events;
    }
}

