package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotDTO implements Serializable {

    private static final long serialVersionUID = 5140013309919127693L;
    private List<String> times;
    private Map<String,Object> timeMap;

}
