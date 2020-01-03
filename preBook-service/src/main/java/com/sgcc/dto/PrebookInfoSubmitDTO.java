package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrebookInfoSubmitDTO implements Serializable {

    private static final long serialVersionUID = -4050132859628546270L;
//    private String id;
    private String userOpenId;
    private String businessTypeId;
    private String businessTypeName;
    private String serviceHallId;
    private String serviceHallName;
    private String householdNo;
//    private String lineupNo;
//    private String lineupTime;

    private String contact;
    private String contactTel;
//    private String submitDate;
//    private String status;

    private String prebookDate;


}
