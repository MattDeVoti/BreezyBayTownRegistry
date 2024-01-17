package com.nook.TownRegistry.model.citizen;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nook.TownRegistry.model.citizen.citizenEnums.Gender;
import com.nook.TownRegistry.model.citizen.citizenEnums.Species;
import com.nook.TownRegistry.model.songs.Song;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CitizenResponse {
    private String name;
    private Gender gender;
    private Species species;
    private String birthday;
    private boolean vacationHomeOwner;
    private String personality;
    private Song favoriteSong;
    String placeOfEmployment;
    String jobTitle;
    String jobDescription;
}
