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
public class HallInfoDTO implements Serializable {


    private static final long serialVersionUID = -3227110170570116547L;
    private String servivceHallId;
    private String serviceHallName;

}
