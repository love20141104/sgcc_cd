package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdNumsDTO implements Serializable {

    private static final long serialVersionUID = 6086496107718644892L;

    private String householdNumber;


}
