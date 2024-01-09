package com.nook.TownRegistry.repository;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.model.citizen.Citizen;
import com.nook.TownRegistry.model.citizen.citizenEnums.CitizenType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class CitizenQueryImpl implements CitizenQuery {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CitizenQueryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Citizen updateCitizen(Citizen citizen){
        Query query = new Query(where("citizenId").is(citizen.getCitizenId()));
        Update update = new Update();
        update.set("townId", citizen.getTownId());
        update.set("vacationHomeOwner", citizen.isVacationHomeOwner());
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Citizen.class);
    }
    @Override
    public DeleteResult removeCitizen(String citizenId){
        Query query = new Query(where("citizenId").is(citizenId));
        return mongoTemplate.remove(query, Citizen.class);
    }

    @Override
    public List<Citizen> findAllOfCitizenType(CitizenType citizenType, String townId) {
        Query query = new Query(new Criteria().andOperator(
                where("townId").is(townId),
                where("citizenType").is(citizenType)
        ));
        return mongoTemplate.find(query, Citizen.class);

    }
}
