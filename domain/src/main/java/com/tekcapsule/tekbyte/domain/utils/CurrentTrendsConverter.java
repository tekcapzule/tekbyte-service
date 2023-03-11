package com.tekcapsule.tekbyte.domain.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekcapsule.tekbyte.domain.model.Trend;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrentTrendsConverter implements DynamoDBTypeConverter<List<Map<String, AttributeValue>>, List<Trend>> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<Map<String, AttributeValue>> convert(List<Trend> trends) {
        List<Map<String, AttributeValue>> trendMaps = new ArrayList<>();
        for (Trend trend : trends) {
            Map<String, AttributeValue> trendMap = new HashMap<>();
            try {
                trendMap.put("event", new AttributeValue(mapper.writeValueAsString(trend)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            trendMaps.add(trendMap);
        }
        return trendMaps;
    }

    @Override
    public List<Trend> unconvert(List<Map<String, AttributeValue>> trendMaps) {
        List<Trend> trends = new ArrayList<>();
        for (Map<String, AttributeValue> trendMap : trendMaps) {
            Trend trend = null;
            try {
                trend = mapper.readValue((DataInput) trendMap.get("event"), Trend.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            trends.add(trend);
        }
        return trends;
    }
}

