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
public class InputDataDTO implements Serializable {

    private static final long serialVersionUID = -1458370997644552791L;

    private String serviceCode;
    private String appId;
    private String deviceId;
    private Map<String,String> data = new LinkedHashMap<>();

}
