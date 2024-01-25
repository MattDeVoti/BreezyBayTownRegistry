package com.nook.TownRegistry.model.citizen;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Employee extends Citizen{
    String placeOfEmployment;
    String jobTitle;
    String jobDescription;
}
