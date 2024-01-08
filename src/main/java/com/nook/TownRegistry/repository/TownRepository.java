package com.nook.TownRegistry.repository;

import com.nook.TownRegistry.model.town.Town;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TownRepository extends MongoRepository<Town, String>, TownQuery {
    List<Town> findByTownId(String citizenId);
    List<Town> findByName(String name);
}
