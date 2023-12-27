package com.nook.TownRegistry.controller;

import com.nook.TownRegistry.model.citizen.Resident;
import com.nook.TownRegistry.model.citizen.ResidentResponse;
import com.nook.TownRegistry.service.CitizenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nook/{townId}/registry/citizen")
public class CitizenController {

    private final CitizenService citizenService;

    @RequestMapping(method = RequestMethod.POST, value="createResident/{citizenId}")
    //@Operation(summary = "Create new resident", description = "Add a new resident to the registry")
    public ResidentResponse createResident(@PathVariable String townId, @PathVariable String citizenId, @RequestBody Resident request){
        return citizenService.create(townId, citizenId, request);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="deleteResident")
    public void deleteResident(@PathVariable String townId, @PathVariable String citizenId){
        citizenService.delete(townId, citizenId);
    }

    @RequestMapping(method = RequestMethod.GET, value="getResident/{citizenId}")
    public ResidentResponse getResident(@PathVariable String townId, @PathVariable String citizenId){
        return citizenService.get(townId, citizenId);
    }

    @RequestMapping(method = RequestMethod.PUT, value="updateResident/{citizenId}")
    public ResidentResponse updateResident(@PathVariable String townId, @PathVariable String citizenId, @RequestBody Resident request){
        return citizenService.update(townId, citizenId, request);
    }
}
