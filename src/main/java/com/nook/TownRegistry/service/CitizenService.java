package com.nook.TownRegistry.service;

import com.nook.TownRegistry.exception.BadRequestException;
import com.nook.TownRegistry.model.citizen.Citizen;
import com.nook.TownRegistry.model.citizen.Resident;
import com.nook.TownRegistry.model.citizen.ResidentResponse;
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
    private final ModelMapper modelMapper;

    public ResidentResponse create(String townId, String citizenId, Resident request) {
        validation(townId, citizenId, request);
        //TODO: validate no duplicate

    }

    public ResidentResponse delete(String townId, String citizenId) {
        validation(townId, citizenId);

    }

    public ResidentResponse get(String townId, String citizenId) {
        validation(townId, citizenId);
        List<Citizen> citizen = citizenRepository.findByCitizenId(citizenId);
        return modelMapper.map(citizen, ResidentResponse.class);
    }

    public ResidentResponse update(String townId, String citizenId, Resident request) {
        validation(townId, citizenId, request);

    }

    public void validation(String townId, String citizenId, Resident request){
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
