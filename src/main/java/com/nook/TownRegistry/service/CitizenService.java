package com.nook.TownRegistry.service;

import com.mongodb.client.result.DeleteResult;
import com.nook.TownRegistry.exception.BadRequestException;
import com.nook.TownRegistry.model.citizen.Citizen;
import com.nook.TownRegistry.model.citizen.Resident;
import com.nook.TownRegistry.model.citizen.ResidentResponse;
import com.nook.TownRegistry.repository.CitizenQuery;
import com.nook.TownRegistry.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CitizenService {

    private final CitizenRepository citizenRepository;
    private final CitizenQuery citizenQuery;
    private final ModelMapper modelMapper;

    public ResidentResponse create(String townId, String citizenId, Resident request) {
        Citizen citizen = validation(townId, citizenId, request);
        citizen = citizenRepository.save(citizen);
        log.debug("Creating citizen {} with citizenId {}", citizen.getName(), citizen.getCitizenId());
        return modelMapper.map(citizen, ResidentResponse.class);
    }

    public void delete(String townId, String citizenId) {
        validation(townId, citizenId);
        DeleteResult result = citizenQuery.removeCitizen(citizenId);
        log.debug("Citizen deleted successfully : {}", result.wasAcknowledged());
    }

    public ResidentResponse get(String townId, String citizenId) {
        validation(townId, citizenId);
        List<Citizen> citizen = citizenRepository.findByCitizenId(citizenId);
        return modelMapper.map(citizen, ResidentResponse.class);
    }

    public ResidentResponse update(String townId, String citizenId, Resident request) {
        Citizen citizen = validation(townId, citizenId, request);
        citizen = citizenQuery.updateCitizen(request);
        log.debug("Updating citizen {} with citizenId {}", citizen.getName(), citizen.getCitizenId());
        return modelMapper.map(citizen, ResidentResponse.class);
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
