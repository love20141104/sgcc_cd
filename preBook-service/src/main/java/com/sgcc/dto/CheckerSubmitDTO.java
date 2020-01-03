package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckerSubmitDTO implements Serializable {

    private static final long serialVersionUID = 613483990344268477L;
    private String checkerName;
    private String checkerTel;
    private String userOpenId;
    private String serviceHallId;
    private String serviceHallName;

}
