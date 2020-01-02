package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrebookInfoDao implements Serializable {

    private static final long serialVersionUID = -6211857993819466752L;

    private String id;
    private String userOpenId;
    private String businessTypeId;
    private String businessTypeName;
    private String serviceHallId;
    private String serviceHallName;
    private String householdNo;

    private String lineupNo;
    private Date lineupTime;

    private String contact;
    private String contactTel;
    private Date submitDate;
    private Integer status;
    private String rejectReason;
    private String checkerId;

}
