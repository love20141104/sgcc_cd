package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreBookHouseholdDao implements Serializable {
    private static final long serialVersionUID = 7012839126627054415L;
    private String id;
    private String jobId;
    private String houseHoldName;
    private String houseHoldNumber;

}
