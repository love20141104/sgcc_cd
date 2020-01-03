package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckerEditDTO implements Serializable {

    private static final long serialVersionUID = 613483990344268477L;
    private String id;
    private String checkerName;
    private String checkerTel;
    private String userOpenId;
//    private String serviceHallId;
//    private String serviceHallName;

}
