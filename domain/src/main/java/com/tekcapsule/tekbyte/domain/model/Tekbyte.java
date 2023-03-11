package com.tekcapsule.tekbyte.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.AggregateRoot;
import com.tekcapsule.core.domain.BaseDomainEntity;
import lombok.*;

import java.util.List;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@DynamoDBTable(tableName = "Tekbyte")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tekbyte extends BaseDomainEntity implements AggregateRoot {
    @DynamoDBHashKey(attributeName="id")
    private String id;
    @DynamoDBAttribute(attributeName="name")
    private String name;
    @DynamoDBAttribute(attributeName="topicCode")
    private String topicCode;
    @DynamoDBAttribute(attributeName="category")
    private String category;
    @DynamoDBAttribute(attributeName="code")
    private String code;
    @DynamoDBAttribute(attributeName = "summary")
    private String summary;
    @DynamoDBAttribute(attributeName = "description")
    private String description;
    @DynamoDBAttribute(attributeName = "imageUrl")
    private String imageUrl;
    @DynamoDBAttribute(attributeName = "aliases")
    private List<String> aliases;
    @DynamoDBAttribute(attributeName="status")
    private String status;
    @DynamoDBAttribute(attributeName="goldenCircle")
    private GoldenCircle goldenCircle;
    @DynamoDBAttribute(attributeName="keyConcepts")
    private List<Concept> keyConcepts;
    @DynamoDBAttribute(attributeName="timeline")
    private List<Event> timeline;
    @DynamoDBAttribute(attributeName="applications")
    private List<Application> applications;
    @DynamoDBAttribute(attributeName="currentTrends")
    private List<Trend> currentTrends;
    @DynamoDBAttribute(attributeName="challenges")
    private List<Challenge> challenges;
    @DynamoDBAttribute(attributeName="didYouKnow")
    private String didYouKnow;
    @DynamoDBAttribute(attributeName="wayForward")
    private String wayForward;

}