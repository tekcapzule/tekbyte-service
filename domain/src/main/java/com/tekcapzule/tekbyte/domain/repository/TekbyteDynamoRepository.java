package com.tekcapzule.tekbyte.domain.repository;

import com.tekcapzule.core.domain.CrudRepository;
import com.tekcapzule.tekbyte.domain.model.Tekbyte;

import java.util.List;

public interface TekbyteDynamoRepository extends CrudRepository<Tekbyte, String> {

    int getAllTekbytesCount();
    List<Tekbyte> findAllByTopicCode(String topicCode);


}
