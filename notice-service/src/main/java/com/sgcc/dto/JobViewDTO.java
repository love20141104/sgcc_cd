package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobViewDTO implements Serializable {

    private static final long serialVersionUID = 2861606968819330199L;

    private String noticeId;

    private String noticeDistrict;

    private String typeName;    // 停电类型

    private String range;       // 抢修范围

    private String noticeDate;  // 停电时间

    private String jobId;
    private String jobNo;
    private String userOpenId;
    private String jobStatus;
    private String jobRepairPersonnel;
    private String jobReason;
    private String submitDate;



}
