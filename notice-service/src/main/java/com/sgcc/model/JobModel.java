package com.sgcc.model;

import com.example.Utils;
import com.sgcc.dao.JobDao;
import com.sgcc.dao.RepairProgressDao;
import com.sgcc.dto.NewJobSubmitDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class JobModel {

    private JobDao jobDao;

    private RepairProgressDao repairProgressDao;

    public void addJobTransform(String openId, NewJobSubmitDTO newJobSubmitDTO) {
        String id = UUID.randomUUID().toString();
        this.jobDao = new JobDao(
               id,
               id.replace("-",""),
               openId,
               newJobSubmitDTO.getNoticeId(),
               "未完成",
               newJobSubmitDTO.getJobRepairPersonnel(),
               newJobSubmitDTO.getJobReason(),
               Utils.GetCurTime()
        );
    }


    public void updateJobStatusTransform(String jobId,String jobEnum) {
        this.jobDao = new JobDao(
                jobId,
                null,
                null,
                null,
                jobEnum,
                null,
                null,
                null
        );
    }




}
