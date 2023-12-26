package com.nook.TownRegistry.model.citizen;

import com.nook.TownRegistry.model.citizen.citizenEnums.CitizenType;
import com.nook.TownRegistry.model.citizen.citizenEnums.Gender;
import com.nook.TownRegistry.model.citizen.citizenEnums.Species;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "citizen")
public class Citizen {
    @Id
    String citizenId;

    String name;
    String townId;
    Gender gender;
    Species species;
    String birthday;
    boolean ownsVacationHome;
    CitizenType citizenType;
}
