package com.tekcapsule.tekbyte.domain.repository;

import com.tekcapsule.core.domain.CrudRepository;
import com.tekcapsule.tekbyte.domain.model.Tekbyte;

public interface TekbyteDynamoRepository extends CrudRepository<Tekbyte, String> {
}
