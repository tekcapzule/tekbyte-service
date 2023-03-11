package com.tekcapsule.tekbyte.domain.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@DynamoDBDocument
public class Trend {
    private String title;
    private String description;
}