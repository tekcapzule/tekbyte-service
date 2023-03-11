package com.tekcapsule.tekbyte.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.tekbyte.domain.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateCommand extends Command {
    private String name;
    private String summary;
    private String topicCode;
    private String category;
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
