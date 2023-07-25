package com.tekcapsule.tekbyte.domain.service;

import com.tekcapsule.tekbyte.domain.command.*;
import com.tekcapsule.tekbyte.domain.model.Tekbyte;

import java.util.List;


public interface TekbyteService {

    void create(CreateCommand createCommand);

    void update(UpdateCommand updateCommand);

    void disable(DisableCommand disableCommand);
    List<Tekbyte> findByTopic( String topicName);

    List<Tekbyte> findAll();

    Tekbyte findBy(String code);

    int getAllTekbytesCount();
    void recommend(RecommendCommand recommendCommand);
    void approve(ApproveCommand approveCommand);

}
