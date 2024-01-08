package com.nook.TownRegistry.controller;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.model.citizen.Employee;
import com.nook.TownRegistry.model.citizen.Resident;
import com.nook.TownRegistry.model.citizen.CitizenResponse;
import com.nook.TownRegistry.service.CitizenService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/nook/{townId}/registry/citizen")
public class CitizenController {

    private final CitizenService citizenService;

    @RequestMapping(method = RequestMethod.POST, value="createResident/{citizenId}")
    @Operation(summary = "Create new resident", description = "Add a new resident to the registry")
    public CitizenResponse createResident(@PathVariable String townId, @PathVariable String citizenId, @RequestBody Resident request){
        return citizenService.create(townId, citizenId, request);
    }

    @RequestMapping(method = RequestMethod.POST, value="createEmployee/{citizenId}")
    @Operation(summary = "Create new Employee", description = "Add a new employee to the registry")
    public CitizenResponse createEmployee(@PathVariable String townId, @PathVariable String citizenId, @RequestBody Employee request){
        return citizenService.create(townId, citizenId, request);
    }

    @RequestMapping(method = RequestMethod.DELETE, value="deleteCitizen/{citizenId}")
    @Operation(summary = "Remove citizen", description = "Removes a citizen from the registry by citizenId")
    public DeleteResult deleteCitizen(@PathVariable String townId, @PathVariable String citizenId){
        return citizenService.delete(townId, citizenId);
    }

    @RequestMapping(method = RequestMethod.GET, value="getCitizen/{citizenId}")
    @Operation(summary = "Get info on resident", description = "Get information about an esisting resident by citizenId")
    public CitizenResponse getCitizen(@PathVariable String townId, @PathVariable String citizenId){
        return citizenService.get(townId, citizenId);
    }

    @RequestMapping(method = RequestMethod.PUT, value="updateResident/{citizenId}")
    @Operation(summary = "Update existing resident", description = "Updates a resident already in the registry by citizenId")
    public CitizenResponse updateResident(@PathVariable String townId, @PathVariable String citizenId, @RequestBody Resident request){
        return citizenService.update(townId, citizenId, request);
    }

    @RequestMapping(method = RequestMethod.PUT, value="updateEmployee/{citizenId}")
    @Operation(summary = "Update existing Employee", description = "Updates an employee already in the registry by citizenId")
    public CitizenResponse updateEmployee(@PathVariable String townId, @PathVariable String citizenId, @RequestBody Employee request){
        return citizenService.update(townId, citizenId, request);
    }
}
