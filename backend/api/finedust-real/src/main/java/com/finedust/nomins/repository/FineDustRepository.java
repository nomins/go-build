package com.finedust.nomins.repository;

import com.finedust.nomins.domain.FineDust;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FineDustRepository extends MongoRepository<FineDust, String>{
}
