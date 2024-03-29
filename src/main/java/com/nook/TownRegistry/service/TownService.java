package com.nook.TownRegistry.service;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.exception.BadRequestException;
import com.nook.TownRegistry.model.town.Town;
import com.nook.TownRegistry.repository.TownRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TownService {

    private final TownRepository townRepository;

    public Town create(String townId, Town request){
        Town town = validation(townId, request);

        // Saves new town to MongoDB
        town = townRepository.save(town);

        log.debug("Creating citizen {} with citizenId {}", town.getName(), town.getTownId());
        return town;
    }

    public DeleteResult delete(String townId) {
        validation(townId);

        // Removes town from MongoDB
        DeleteResult result = townRepository.removeTown(townId);

        log.debug("Town deleted successfully : {}", result.wasAcknowledged());
        return result;
    }

    public Town get(String townId) {
        validation(townId);
        List<Town> town = townRepository.findByTownId(townId);
        return town.get(0);
    }

    public Town update(String townId, Town request) {
        validation(townId, request);
        Town town = townRepository.updateTown(request);
        log.debug("Updating town {} with townId {}", town.getName(), town.getTownId());
        return town;
    }

    // Typically called when a resident is created, destroyed or updated
    public void updateResidentCount(String townId, int i){
        Town town = get(townId);
        town.setNumberOfResidents((town.getNumberOfResidents()+i));
        update(townId, town);
    }

    public Town validation(String townId, Town request){
        if(townId.isBlank()){
            throw new BadRequestException("townId cannot be null");
        }
        if(request == null){
            throw new BadRequestException("request cannot be null");
        }
        request.setTownId(townId);
        return request;
    }

    public void validation(String townId){
        if(townId.isBlank()){
            throw new BadRequestException("townId cannot be null");
        }
    }
}
