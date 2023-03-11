package com.tekcapsule.tekbyte.domain.service;

import com.tekcapsule.tekbyte.domain.command.CreateCommand;
import com.tekcapsule.tekbyte.domain.command.DisableCommand;
import com.tekcapsule.tekbyte.domain.command.UpdateCommand;
import com.tekcapsule.tekbyte.domain.model.Tekbyte;

import java.util.List;


public interface TekbyteService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);

    List<Tekbyte> findAll();

    Tekbyte findBy(String code);

    List<Tekbyte> findByTopic(String topicCode);

    List<Tekbyte> findByCategory(String category, String topicCode);
}
