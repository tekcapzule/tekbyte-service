package com.tekcapsule.tekbyte.domain.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekcapsule.tekbyte.domain.model.Application;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationConverter implements DynamoDBTypeConverter<List<Map<String, AttributeValue>>, List<Application>> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<Map<String, AttributeValue>> convert(List<Application> applications) {
        List<Map<String, AttributeValue>> applicationMaps = new ArrayList<>();
        for (Application application : applications) {
            Map<String, AttributeValue> applicationMap = new HashMap<>();
            try {
                applicationMap.put("event", new AttributeValue(mapper.writeValueAsString(application)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            applicationMaps.add(applicationMap);
        }
        return applicationMaps;
    }

    @Override
    public List<Application> unconvert(List<Map<String, AttributeValue>> applicationMaps) {
        List<Application> applications = new ArrayList<>();
        for (Map<String, AttributeValue> applicationMap : applicationMaps) {
            Application application = null;
            try {
                application = mapper.readValue((DataInput) applicationMap.get("event"), Application.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            applications.add(application);
        }
        return applications;
    }
}

