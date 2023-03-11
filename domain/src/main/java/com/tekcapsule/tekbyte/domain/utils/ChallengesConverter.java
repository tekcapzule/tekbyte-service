package com.tekcapsule.tekbyte.domain.utils;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tekcapsule.tekbyte.domain.model.Challenge;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChallengesConverter implements DynamoDBTypeConverter<List<Map<String, AttributeValue>>, List<Challenge>> {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public List<Map<String, AttributeValue>> convert(List<Challenge> challenges) {
        List<Map<String, AttributeValue>> challengeMaps = new ArrayList<>();
        for (Challenge challenge : challenges) {
            Map<String, AttributeValue> challengeMap = new HashMap<>();
            try {
                challengeMap.put("event", new AttributeValue(mapper.writeValueAsString(challenge)));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            challengeMaps.add(challengeMap);
        }
        return challengeMaps;
    }

    @Override
    public List<Challenge> unconvert(List<Map<String, AttributeValue>> challengeMaps) {
        List<Challenge> challenges = new ArrayList<>();
        for (Map<String, AttributeValue> challengeMap : challengeMaps) {
            Challenge challenge = null;
            try {
                challenge = mapper.readValue((DataInput) challengeMap.get("event"), Challenge.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            challenges.add(challenge);
        }
        return challenges;
    }
}

