package com.nook.TownRegistry.service;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.exception.BadRequestException;
import com.nook.TownRegistry.model.citizen.Citizen;
import com.nook.TownRegistry.model.citizen.CitizenResponse;
import com.nook.TownRegistry.model.citizen.citizenEnums.CitizenType;
import com.nook.TownRegistry.repository.CitizenQuery;
import com.nook.TownRegistry.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CitizenService {

    private final CitizenRepository citizenRepository;
    private final TownService townService;
    private final ModelMapper modelMapper;

    // Handles the creation of a new citizen (can be a resident or an employee)
    public CitizenResponse create(String townId, String citizenId, Citizen request) {
        Citizen citizen = validation(townId, citizenId, request);

        // Saves new citizen to MongoDB
        citizen = citizenRepository.save(citizen);

        // Makes sure to increase the town's resident count by 1 when a new resident is created for this town
        // Employees are not considered residents and so we skip this step if the citizen type is an employee
        if(request.getCitizenType().equals(CitizenType.RESIDENT)) {
            townService.updateResidentCount(townId, 1);
        }

        log.debug("Creating citizen {} with citizenId {}", citizen.getName(), citizen.getCitizenId());
        return modelMapper.map(citizen, CitizenResponse.class);
    }

    public DeleteResult delete(String townId, String citizenId) {
        validation(townId, citizenId);

        // Makes sure to decrease the town's resident count by 1 when a new resident is removed from this town
        // Employees are not considered residents and so we skip this step if the citizen type is an employee
        if(citizenRepository.findByCitizenId(citizenId).get(0).getCitizenType().equals(CitizenType.RESIDENT)){
            townService.updateResidentCount(townId, -1);
        }

        DeleteResult result = citizenRepository.removeCitizen(citizenId);
        log.debug("Citizen deleted successfully : {}", result.wasAcknowledged());
        return result;
    }

    public CitizenResponse get(String townId, String citizenId) {
        validation(townId, citizenId);
        List<Citizen> citizen = citizenRepository.findByCitizenId(citizenId);
        return modelMapper.map(citizen.get(0), CitizenResponse.class);
    }

    public CitizenResponse update(String townId, String citizenId, Citizen request) {
        Citizen citizen = validation(townId, citizenId, request);

        // Makes sure to increase the new town's resident count by 1 and decrease the old town's resident count
        // by 1 when a new resident is updated and moved from one town to another
        // Employees are not considered residents and so we skip this step if the citizen type is an employee
        if(request.getCitizenType().equals(CitizenType.RESIDENT)) {
            updateCitizenCountOnMove(townId, citizenId);
        }

        citizen = citizenRepository.updateCitizen(request);
        log.debug("Updating citizen {} with citizenId {}", citizen.getName(), citizen.getCitizenId());
        return modelMapper.map(citizen, CitizenResponse.class);
    }

    public void updateCitizenCountOnMove(String townId, String citizenId){
        String oldTownId = citizenRepository.findByCitizenId(citizenId).get(0).getTownId();

        if(!townId.equals(oldTownId)){
            townService.updateResidentCount(townId, 1);
            townService.updateResidentCount(oldTownId, -1);
        }
    }

    // Retrieves a list of all residents or employees in a given town based on citizen type (resident or employee)
    public List<CitizenResponse> findAllOfCitizenType(CitizenType citizenType, String townId){
        List<Citizen> citizens = citizenRepository.findAllOfCitizenType(citizenType, townId);
        List<CitizenResponse> citizenResponses = citizens
                .stream()
                .map(citizen -> modelMapper.map(citizen, CitizenResponse.class))
                .collect(Collectors.toList());
        return citizenResponses;
    }

    public Citizen validation(String townId, String citizenId, Citizen request){
        if(townId.isBlank()){
            throw new BadRequestException("townId cannot be null");
        }
        if(citizenId.isBlank()){
            throw new BadRequestException("residentId cannot be null");
        }
        if(request == null){
            throw new BadRequestException("request cannot be null");
        }
        request.setTownId(townId);
        request.setCitizenId(citizenId);
        return request;
    }

    public void validation(String townId, String citizenId){
        if(townId.isBlank()){
            throw new BadRequestException("townId cannot be null");
        }
        if(citizenId.isBlank()){
            throw new BadRequestException("residentId cannot be null");
        }
    }
}
