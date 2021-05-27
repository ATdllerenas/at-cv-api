package com.dllerenasg.challenge.atcvapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.dllerenasg.challenge.atcvapi.model.cv.CV;

@Repository
public interface CVRepository extends MongoRepository<CV, String>{

}
