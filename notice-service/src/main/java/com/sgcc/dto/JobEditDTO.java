package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class JobEditDTO {

    private String noticeId;
    private String jobStatus;
    private String jobRepairPersonnel;
    private String jobReason;

}
