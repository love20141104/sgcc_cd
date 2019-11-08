package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseholdInfoDao {
    private String householdId;
    private String householdHouseholder;
    private String householdNumber;
    private String householdAddress;
    private Boolean householdDefault;
    private String householdType;
    private String householdPassword;
    private Boolean isAvailable;
}
