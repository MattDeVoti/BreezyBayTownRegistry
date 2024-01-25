package com.nook.TownRegistry.model.citizen;

import com.nook.TownRegistry.model.songs.Song;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Data
@EqualsAndHashCode
public class Resident extends Citizen{
    String personality;
    Song favoriteSong;
}
