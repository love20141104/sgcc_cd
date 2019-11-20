package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoticeAndJobDao {
    private String id;

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
    private Date submitDate;
}
