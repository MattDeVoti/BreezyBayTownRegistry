package com.nook.TownRegistry.repository;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.model.citizen.Citizen;
import com.nook.TownRegistry.model.citizen.citizenEnums.CitizenType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CitizenQuery {

    Citizen updateCitizen(Citizen citizen);
    DeleteResult removeCitizen(String citizenId);


}
