package com.tekcapsule.tekbyte.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class TekbyteProperty {
    @DynamoDBAttribute(attributeName ="title")
    private String title;
    @DynamoDBAttribute(attributeName ="description")
    private String description;
}
