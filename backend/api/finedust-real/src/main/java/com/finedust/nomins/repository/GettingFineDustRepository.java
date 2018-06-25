package com.finedust.nomins.repository;

import com.finedust.nomins.domain.FineDust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


@Repository
public class GettingFineDustRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public FineDust getFineDustByCity(String cityName){
        Query query = new Query();
        query.addCriteria(Criteria.where("region").is(cityName).and("date").is(getCurrentTime()));
        return mongoTemplate.findOne(query, FineDust.class);
    }

    private String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH", Locale.KOREA);
        return sdf.format(date)+":00";
    }
}
