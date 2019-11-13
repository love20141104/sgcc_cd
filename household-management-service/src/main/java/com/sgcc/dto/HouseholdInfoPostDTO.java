package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdInfoPostDTO implements Serializable {
    private static final long serialVersionUID = -6301556894234627123L;
    private String householdNumber;
    private String pwd;
}
