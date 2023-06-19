package com.tekcapsule.tekbyte.domain.command;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tekcapsule.core.domain.Command;
import com.tekcapsule.tekbyte.domain.model.Category;
import com.tekcapsule.tekbyte.domain.model.PrizingModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class UpdateCommand extends Command {
    private String code;
    private String name;
    private String summary;
    private Category category;
    private String description;
    private String imageUrl;
    private List<String> aliases;
    private int recommendations;
    private PrizingModel prizingModel;
    private boolean promoted;
    private boolean featured;
}
