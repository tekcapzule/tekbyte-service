package com.tekcapzule.tekbyte.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Select;
import com.tekcapzule.tekbyte.domain.model.Tekbyte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Repository
public class TekbyteRepositoryImpl implements TekbyteDynamoRepository {

    private DynamoDBMapper dynamo;
    public static final String ACTIVE_STATUS = "ACTIVE";

    @Autowired
    public TekbyteRepositoryImpl(DynamoDBMapper dynamo) {
        this.dynamo = dynamo;
    }

    @Override
    public List<Tekbyte> findAll() {
        return dynamo.scan(Tekbyte.class,new DynamoDBScanExpression());
    }

    @Override
    public Tekbyte findBy(String tekByteId) {
        return dynamo.load(Tekbyte.class, tekByteId);
    }

    @Override
    public List<Tekbyte> findAllByTopicCode(String topicCode) {

        HashMap<String, AttributeValue> expAttributes = new HashMap<>();
        expAttributes.put(":status", new AttributeValue().withS(ACTIVE_STATUS));
        expAttributes.put(":topicCode", new AttributeValue().withS(topicCode));

        HashMap<String, String> expNames = new HashMap<>();
        expNames.put("#status", "status");
        expNames.put("#topicCode", "topicCode");

        DynamoDBQueryExpression<Tekbyte> queryExpression = new DynamoDBQueryExpression<Tekbyte>()
                .withIndexName("topicGSI").withConsistentRead(false)
                .withKeyConditionExpression("#status = :status and #topicCode = :topicCode")
                .withExpressionAttributeValues(expAttributes)
                .withExpressionAttributeNames(expNames);

        return dynamo.query(Tekbyte.class, queryExpression);

    }

    @Override
    public Tekbyte save(Tekbyte tekbyte) {
        dynamo.save(tekbyte);
        return tekbyte;
    }

    @Override
    public int getAllTekbytesCount() {
        return dynamo.count(Tekbyte.class,new DynamoDBScanExpression().withSelect(Select.COUNT));
    }
}
