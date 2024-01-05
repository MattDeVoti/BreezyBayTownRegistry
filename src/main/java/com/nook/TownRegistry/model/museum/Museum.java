package com.nook.TownRegistry.model.museum;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class Museum {
    List<String> artPieces = new ArrayList<>();
    List<String> bugs = new ArrayList<>();
    List<String> fish = new ArrayList<>();
    List<String> fossils = new ArrayList<>();
}
