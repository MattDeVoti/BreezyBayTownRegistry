package com.nook.TownRegistry.model.citizen;

import com.nook.TownRegistry.model.citizen.citizenEnums.Gender;
import com.nook.TownRegistry.model.citizen.citizenEnums.Species;
import com.nook.TownRegistry.model.songs.Song;

public class ResidentResponse {
    private String name;
    private Gender gender;
    private Species species;
    private String birthday;
    private boolean ownsVacationHome;
    private String personality;
    private Song favoriteSong;
}
