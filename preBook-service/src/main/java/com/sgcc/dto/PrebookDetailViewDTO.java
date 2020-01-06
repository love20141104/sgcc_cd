package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrebookDetailViewDTO implements Serializable {

    private static final long serialVersionUID = -1273986616501853935L;
    private String prebookNo;
    private String businessTypeName;
    private String serviceHallName;
    private String householdNo;

    private String lineupNo;
    private String lineupTime;

    private String contact;
    private String contactTel;
    private String submitDate;
    private Integer status;
    private String rejectReason;
    private String checkerName;

    private String prebookDate;

}
