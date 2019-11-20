package com.sgcc.model;

import com.example.Utils;
import com.sgcc.dao.JobDao;
import com.sgcc.dao.RepairProgressDao;
import com.sgcc.dto.NewJobSubmitDTO;
import com.sgcc.dto.RepairProgressViewDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class JobModel {

    private JobDao jobDao;

    private RepairProgressDao repairProgressDao;

    private List<RepairProgressViewDTO> repairProgressViewDTOS = new ArrayList<>();

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


    public void selectRepairProgressTransform(List<RepairProgressDao> repairProgressDaos) {

        repairProgressDaos.forEach(dao->{
            this.repairProgressViewDTOS.add(new RepairProgressViewDTO(
                    dao.getProgressStatus(),
                    Utils.GetTime(dao.getProgressDate()),
                    dao.getProgressImg1(),
                    dao.getProgressImg2(),
                    dao.getProgressImg3()
            ));
        });

    }
}
