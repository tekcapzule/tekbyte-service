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
public class UpdateCommand extends Command {
    private String tekByteId;
    private String title;
    private String summary;
    private String description;
    private String imageUrl;
    private String illustrationUrl;
    private List<String> aliases;
    private GoldenCircle goldenCircle;
    private List<Event> events;
    private List<Concept> keyConcepts;
    private List<Application> applications;
    private String didYouKnow;
    private String wayForward;
    private int recommendations;
    private PrizingModel prizingModel;
    private boolean promoted;
    private boolean featured;
    private List<Event> timeline;

}
