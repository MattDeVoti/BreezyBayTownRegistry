package com.nook.TownRegistry.model.citizen;

import lombok.Data;

@Data
public class Employee extends Citizen{
    String placeOfEmployment;
    String jobTitle;
    String jobDescription;
}
