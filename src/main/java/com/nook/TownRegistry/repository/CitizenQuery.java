package com.nook.TownRegistry.repository;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.model.citizen.Citizen;
import com.nook.TownRegistry.model.citizen.citizenEnums.CitizenType;

import java.util.List;

public interface CitizenQuery {

    Citizen updateCitizen(Citizen citizen);
    DeleteResult removeCitizen(String citizenId);
    List<Citizen> findAllOfCitizenType(CitizenType citizenType, String townId);


}
