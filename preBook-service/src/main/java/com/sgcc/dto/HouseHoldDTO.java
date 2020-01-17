package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseHoldDTO implements Serializable {

    private static final long serialVersionUID = -676415209789413402L;
    private String householdName;
    private String householdNumber;

}
