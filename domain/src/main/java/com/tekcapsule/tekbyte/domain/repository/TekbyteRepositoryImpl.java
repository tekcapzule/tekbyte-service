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
        String projectionExpression = "code,topicCode,category,id,title,summary,description,imageUrl";
        return dynamo.scan(Tekbyte.class,new DynamoDBScanExpression().withProjectionExpression(projectionExpression));
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
        expNames.put("#code", "code");

        DynamoDBQueryExpression<Tekbyte> queryExpression = new DynamoDBQueryExpression<Tekbyte>()
                .withConsistentRead(false)
                .withKeyConditionExpression("begins_with(#code, :topicCode)")
                .withExpressionAttributeValues(expAttributes)
                .withExpressionAttributeNames(expNames);

        return dynamo.query(Tekbyte.class, queryExpression);

    }

    @Override
    public List<Tekbyte> findAllByCategory(String category, String topicCode) {

        HashMap<String, AttributeValue> expAttributes = new HashMap<>();
        expAttributes.put(":category", new AttributeValue().withS(category));
        expAttributes.put(":topicCode", new AttributeValue().withS(topicCode));

        HashMap<String, String> expNames = new HashMap<>();
        expNames.put("#code", "code");

        DynamoDBQueryExpression<Tekbyte> queryExpression = new DynamoDBQueryExpression<Tekbyte>()
                .withConsistentRead(false)
                .withKeyConditionExpression("begins_with(#code, :topicCode) and contains(#code, :category)")
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
