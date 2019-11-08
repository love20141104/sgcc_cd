package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdInfoDTO implements Serializable {
    private static final long serialVersionUID = -6301556894234627123L;

    private String householdHouseholder;
    private String householdNumber;
    private String householdAddress;
    private Boolean householdDefault;
    private String householdType;

}
