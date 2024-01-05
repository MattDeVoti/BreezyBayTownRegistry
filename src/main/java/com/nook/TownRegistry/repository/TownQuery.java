package com.nook.TownRegistry.repository;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.model.town.Town;

public interface TownQuery {
    Town updateTown(Town town);
    DeleteResult removeTown(String townId);
}
