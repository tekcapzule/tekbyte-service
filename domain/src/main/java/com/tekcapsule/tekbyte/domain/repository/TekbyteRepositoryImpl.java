package com.tekcapsule.tekbyte.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.tekcapsule.tekbyte.domain.model.Tekbyte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class TekbyteRepositoryImpl implements TekbyteDynamoRepository {

    private DynamoDBMapper dynamo;

    @Autowired
    public TekbyteRepositoryImpl(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public List<Tekbyte> findAll() {

        return dynamo.scan(Tekbyte.class,new DynamoDBScanExpression());
    }

    @Override
    public Tekbyte findBy(String code) {
        return dynamo.load(Tekbyte.class, code);
    }

    @Override
    public List<Tekbyte> findAllByTopicCode(String topicCode) {

        HashMap<String, AttributeValue> expAttributes = new HashMap<>();
        expAttributes.put(":topicCode", new AttributeValue().withS(topicCode));

        HashMap<String, String> expNames = new HashMap<>();
        expNames.put("#topicCode", "topicCode");

        DynamoDBQueryExpression<Tekbyte> queryExpression = new DynamoDBQueryExpression<Tekbyte>()
                .withConsistentRead(false)
                .withKeyConditionExpression("begins_with(#topicCode, :topicCode)")
                .withExpressionAttributeValues(expAttributes)
                .withExpressionAttributeNames(expNames);

        return dynamo.query(Tekbyte.class, queryExpression);

    }

    @Override
    public List<Tekbyte> findAllByCategory(String category) {

        HashMap<String, AttributeValue> expAttributes = new HashMap<>();
        expAttributes.put(":category", new AttributeValue().withS(category));

        HashMap<String, String> expNames = new HashMap<>();
        expNames.put("#category", "category");

        DynamoDBQueryExpression<Tekbyte> queryExpression = new DynamoDBQueryExpression<Tekbyte>()
                .withConsistentRead(false)
                .withKeyConditionExpression("contains(#category, :category)")
                .withExpressionAttributeValues(expAttributes)
                .withExpressionAttributeNames(expNames);

        return dynamo.query(Tekbyte.class, queryExpression);

    }

    @Override
    public Tekbyte save(Tekbyte tekbyte) {
        dynamo.save(tekbyte);
        return tekbyte;
    }
}
