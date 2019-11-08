package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdInfoListDTO implements Serializable {
    private static final long serialVersionUID = 4577570675650894130L;

    String openId;
    List<HouseholdInfoDTO> householdInfoDTOS = new ArrayList<>();
}
