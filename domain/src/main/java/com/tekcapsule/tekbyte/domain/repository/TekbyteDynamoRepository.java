package com.tekcapsule.tekbyte.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.tekbyte.domain.model.Tekbyte;

import java.util.List;

public interface TekbyteDynamoRepository extends CrudRepository<Tekbyte, String> {

    int getAllTekbytesCount();
    List<Tekbyte> findAllByTopicCode(String topicCode);


}
