package com.nook.TownRegistry.controller;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.model.town.Town;
import com.nook.TownRegistry.service.TownService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nook/{townId}/registry/town")
public class TownController {

    private final TownService townService;

    @RequestMapping(method = RequestMethod.POST, value="create")
    @Operation(summary = "Create new town", description = "Add a new town to the registry")
    public Town createTown(@PathVariable String townId, @RequestBody Town request){
        return townService.create(townId, request);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="delete")
    @Operation(summary = "Delete a town", description = "Deletes a town from the registry")
    public DeleteResult deleteTown(@PathVariable String townId){
        return townService.delete(townId);
    }

    @RequestMapping(method = RequestMethod.GET, value="get")
    @Operation(summary = "Get town", description = "Gets a town from the registry")
    public Town getTown(@PathVariable String townId){
        return townService.get(townId);
    }

    @RequestMapping(method = RequestMethod.PUT, value="update")
    @Operation(summary = "Update town", description = "Updates a town in the registry")
    public Town updateTown(@PathVariable String townId, @RequestBody Town request){
        return townService.update(townId, request);
    }

}
