package com.nook.TownRegistry.repository;


import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.model.citizen.Citizen;
import com.nook.TownRegistry.model.town.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class TownQueryImpl implements TownQuery {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TownQueryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Town updateTown(Town town) {
        Query query = new Query(where("townId").is(town.getTownId()));
        Update update = new Update();
        update.set("store", town.getStore());
        update.set("numberOfResidents", town.getNumberOfResidents());
        update.set("museum", town.getMuseum());
        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), Town.class);
    }

    @Override
    public DeleteResult removeTown(String townId) {
        Query query = new Query(where("townId").is(townId));
        return mongoTemplate.remove(query, Town.class);
    }
}
