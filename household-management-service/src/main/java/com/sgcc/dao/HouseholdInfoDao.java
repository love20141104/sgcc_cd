package com.sgcc.dao;

import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HouseholdInfoDao {
    private String householdId;
    private String householdHouseholder ="";
    private String householdNumber;
    private String householdAddress="";
    private Boolean householdDefault;
    private String householdType ="";
    private String householdPassword="";
    private Boolean isAvailable;

    public HouseholdInfoDao(String householdId, String householdHouseholder, String householdNumber, String householdAddress, Boolean householdDefault, String householdType, String householdPassword, Boolean isAvailable) {
        this.householdId = householdId;
        this.householdHouseholder = Strings.isNullOrEmpty(householdHouseholder)?"":householdHouseholder;
        this.householdNumber = householdNumber;
        this.householdAddress = Strings.isNullOrEmpty(householdAddress)?"":householdAddress;
        this.householdDefault = householdDefault;
        this.householdType = Strings.isNullOrEmpty(householdType)?"":householdType;
        this.householdPassword = householdPassword;
        this.isAvailable = isAvailable;
    }
}
