package com.sgcc.model;

import com.example.Utils;
import com.sgcc.Enum.JobEnum;
import com.sgcc.Enum.RepairProgressEnum;
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
               JobEnum.UNFINISHED,
               newJobSubmitDTO.getJobRepairPersonnel(),
               newJobSubmitDTO.getJobReason(),
               Utils.GetCurTime()
        );
    }


    public void updateJobStatusTransform(String jobId,JobEnum jobEnum) {
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
