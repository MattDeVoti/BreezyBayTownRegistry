package com.nook.TownRegistry.repository;

import com.nook.TownRegistry.model.citizen.Citizen;
import com.nook.TownRegistry.model.citizen.citizenEnums.CitizenType;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class CitizenRepositoryImpl implements CitizenRepository{

    private final MongoTemplate mongoTemplate;

    @Autowired
    public CitizenRepositoryImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Citizen> findByCitizenId(String citizenId){

    }

//    @Override
//    public ContainerEntity setPendingRecording(String containerId, String studioMeetingId, ContainerType containerType) {
//        Query query = new Query(where("_id").is(containerId));
//        Update update = new Update();
//        update.set("studioMeetingId", new ObjectId(studioMeetingId));
//        update.set("containerRecordingState", com.vzw.studio.common.model.ActionState.PENDING_START);
//        update.set("type", containerType);
//        return mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true), ContainerEntity.class);
//    }

    @Override
    public List<Citizen> findByName(String name){

    }

    @Override
    public List<Citizen> findAllOfCitizenType(CitizenType citizenType){

    }
}
