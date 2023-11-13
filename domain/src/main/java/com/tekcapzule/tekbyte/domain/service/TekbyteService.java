package com.tekcapzule.tekbyte.domain.service;

import com.tekcapzule.tekbyte.domain.command.*;
import com.tekcapzule.tekbyte.domain.command.*;
import com.tekcapzule.tekbyte.domain.model.Tekbyte;

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
