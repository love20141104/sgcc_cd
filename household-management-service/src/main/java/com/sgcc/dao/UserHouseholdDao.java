package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserHouseholdDao {
    private String id;
    private String userId;
    private String householdId;
    private String isAvailable;
}
