package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobAndRepairProgressDao {
    private String jobRepairPersonnel;
    private String jobReason;

    private String id;
    private String userOpenId;
    private String jobId;
    private String progressStatus;
    private Date progressDate;
    private String progressImg1;
    private String progressImg2;
    private String progressImg3;
}
