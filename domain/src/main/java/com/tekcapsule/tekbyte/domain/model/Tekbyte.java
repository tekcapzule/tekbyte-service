package com.tekcapsule.tekbyte.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tekbyte extends BaseDomainEntity implements AggregateRoot {
    @DynamoDBHashKey(attributeName="tekByteId")
    private String tekByteId;
    @DynamoDBAttribute(attributeName="title")
    private String title;
    @DynamoDBAttribute(attributeName="topicCode")
    private String topicCode;
    @DynamoDBAttribute(attributeName = "summary")
    private String summary;
    @DynamoDBAttribute(attributeName = "description")
    private String description;
    @DynamoDBAttribute(attributeName = "imageUrl")
    private String imageUrl;
    @DynamoDBAttribute(attributeName = "illustrationUrl")
    private String illustrationUrl;
    @DynamoDBAttribute(attributeName = "aliases")
    private List<String> aliases;
    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConvertedEnum
    private Status status;
    @DynamoDBAttribute(attributeName="goldenCircle")
    private GoldenCircle goldenCircle;
    @DynamoDBAttribute(attributeName="keyConcepts")
    private List<Concept> keyConcepts;
    @DynamoDBAttribute(attributeName="timeline")
    private List<Event> timeline;
    @DynamoDBAttribute(attributeName="applications")
    private List<Application> applications;
    @DynamoDBAttribute(attributeName="didYouKnow")
    private String didYouKnow;
    @DynamoDBAttribute(attributeName="wayForward")
    private String wayForward;
    @DynamoDBAttribute(attributeName = "recommendations")
    private int recommendations;
    @DynamoDBAttribute(attributeName = "prizingModel")
    @DynamoDBTypeConvertedEnum
    private PrizingModel prizingModel;
    @DynamoDBAttribute(attributeName = "promoted")
    private boolean promoted;
    @DynamoDBAttribute(attributeName = "featured")
    private boolean featured;


}