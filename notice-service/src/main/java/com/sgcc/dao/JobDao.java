package com.sgcc.dao;

import com.sgcc.Enum.JobEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDao {
    private String jobId;
    private String jobNo;
    private String userOpenId;
    private String noticeId;
    private JobEnum jobStatus;
    private String jobRepairPersonnel;
    private String jobReason;
    private Date submitDate;
}

