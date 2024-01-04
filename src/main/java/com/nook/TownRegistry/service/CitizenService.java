package com.nook.TownRegistry.service;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.exception.BadRequestException;
import com.nook.TownRegistry.model.citizen.Citizen;
import com.nook.TownRegistry.model.citizen.Resident;
import com.nook.TownRegistry.model.citizen.CitizenResponse;
import com.nook.TownRegistry.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CitizenService {

    private final CitizenRepository citizenRepository;
    private final ModelMapper modelMapper;

    public CitizenResponse create(String townId, String citizenId, Citizen request) {
        Citizen citizen = validation(townId, citizenId, request);
        citizen = citizenRepository.save(citizen);
        log.debug("Creating citizen {} with citizenId {}", citizen.getName(), citizen.getCitizenId());
        return modelMapper.map(citizen, CitizenResponse.class);
    }

    public DeleteResult delete(String townId, String citizenId) {
        validation(townId, citizenId);
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
        citizen = citizenRepository.updateCitizen(request);
        log.debug("Updating citizen {} with citizenId {}", citizen.getName(), citizen.getCitizenId());
        return modelMapper.map(citizen, CitizenResponse.class);
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
