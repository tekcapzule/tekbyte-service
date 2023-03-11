package com.tekcapsule.tekbyte.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.tekbyte.domain.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class CreateInput {
    private String title;
    private String topicCode;
    private String category;
    private String summary;
    private String description;
    private String imageUrl;
    private List<String> aliases;
    private GoldenCircle goldenCircle;
    private List<Event> timeline;
    private List<Concept> keyConcepts;
    private List<Application> applications;
    private List<Trend> currentTrends;
    private List<Challenge> challenges;
    private String didYouKnow;
    private String wayForward;
}