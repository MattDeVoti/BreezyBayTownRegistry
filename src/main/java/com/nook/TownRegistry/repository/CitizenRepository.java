package com.nook.TownRegistry.repository;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.model.citizen.Citizen;
import com.nook.TownRegistry.model.citizen.citizenEnums.CitizenType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CitizenRepository extends MongoRepository<Citizen, String> {

    List<Citizen> findByCitizenId(String citizenId);
    List<Citizen> findByName(String name);
    List<Citizen> findAllOfCitizenType(CitizenType citizenType);
    Citizen updateCitizen(Citizen citizen);
    DeleteResult removeCitizen(String citizenId);


}
