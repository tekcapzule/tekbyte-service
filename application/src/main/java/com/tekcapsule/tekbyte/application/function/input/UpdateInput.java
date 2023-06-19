package com.tekcapsule.tekbyte.application.function.input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.tekbyte.domain.model.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class UpdateInput {
    private String code;
    private String title;
    private String summary;
    private String description;
    private String imageUrl;
    private List<String> aliases;
    private GoldenCircle goldenCircle;
    private List<TekbyteProperty> timeLines;
    private List<Concept> keyConcepts;
    private List<Application> applications;
    private String didYouKnow;
    private String wayForward;
    private String illustrationUrl;
    private int recommendations;
    private PrizingModel prizingModel;
    private boolean promoted;
    private boolean featured;
    private List<Event> timeline;
}
