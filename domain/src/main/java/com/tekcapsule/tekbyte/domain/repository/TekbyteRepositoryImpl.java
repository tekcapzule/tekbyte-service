package com.tekcapsule.tekbyte.domain.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Select;
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
    public Tekbyte save(Tekbyte tekbyte) {
        dynamo.save(tekbyte);
        return tekbyte;
    }

    @Override
    public int getAllTekbytesCount() {
        return dynamo.count(Tekbyte.class,new DynamoDBScanExpression().withSelect(Select.COUNT));
    }
}
