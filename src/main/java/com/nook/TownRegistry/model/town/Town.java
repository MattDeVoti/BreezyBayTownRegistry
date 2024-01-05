package com.nook.TownRegistry.model.town;

import com.nook.TownRegistry.model.museum.Museum;
import com.nook.TownRegistry.model.town.townEnums.GeneralStore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@Document(collection = "town")
public class Town {
    @Id
    String townId;

    String name;
    GeneralStore store;
    int numberOfResidents; //TODO Increment or decriment this number as residents are added or removed

    Museum museum;
}
