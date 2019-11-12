package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdInfoDTO implements Serializable {

    private static final long serialVersionUID = -1963020315539773459L;

    private String householder;

    private String householdNumber;

    private String householdAddress;


}
