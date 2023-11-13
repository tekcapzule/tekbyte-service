package com.tekcapzule.tekbyte.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapzule.core.domain.Command;
import com.tekcapzule.tekbyte.domain.model.*;
import com.tekcapzule.tekbyte.domain.model.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class CreateCommand extends Command {
    private String title;
    private String summary;
    private String topicCode;
    private String description;
    private String imageUrl;

    private String illustartionUrl;
    private List<String> aliases;
    private GoldenCircle goldenCircle;
    private List<Event> timeline;
    private List<Concept> keyConcepts;
    private List<Application> applications;
    private String didYouKnow;
    private String wayForward;
    private int recommendations;
    private PrizingModel prizingModel;
    private boolean promoted;
    private boolean featured;
}
