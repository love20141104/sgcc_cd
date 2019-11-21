package com.sgcc.model;

import com.example.Utils;
import com.sgcc.dao.JobAndRepairProgressDao;
import com.sgcc.dao.JobDao;
import com.sgcc.dao.NoticeAndJobDao;
import com.sgcc.dao.RepairProgressDao;
import com.sgcc.dto.JobViewDTO;
import com.sgcc.dto.NewJobSubmitDTO;
import com.sgcc.dto.RepairProgressSubmitDTO;
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

    private List<JobViewDTO> jobViewDTOS = new ArrayList<>();


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


    public void selectRepairProgressTransform(List<JobAndRepairProgressDao> jobAndRepairProgressDaos) {

        jobAndRepairProgressDaos.forEach(dao->{
            this.repairProgressViewDTOS.add(new RepairProgressViewDTO(
                    dao.getProgressStatus(),
                    Utils.GetTime(dao.getProgressDate()),
                    dao.getProgressStatus().equals("抢修中") ? dao.getJobRepairPersonnel():"",
                    dao.getProgressStatus().equals("抢修中") ? dao.getJobReason():"",
                    dao.getProgressImg1(),
                    dao.getProgressImg2(),
                    dao.getProgressImg3()
            ));
        });

    }

    public void addProgressTransform(String openId, RepairProgressSubmitDTO repairProgressSubmitDTO) {
        String id = UUID.randomUUID().toString();
        this.repairProgressDao = new RepairProgressDao(
                id,
                openId,
                null,
                repairProgressSubmitDTO.getProgressStatus(),
                Utils.GetCurTime(),
                repairProgressSubmitDTO.getProgressImg1(),
                repairProgressSubmitDTO.getProgressImg2(),
                repairProgressSubmitDTO.getProgressImg3()
        );

    }


    public void selectJobsTransform(List<NoticeAndJobDao> noticeAndJobDaos) {
        noticeAndJobDaos.forEach(dao->{
            this.jobViewDTOS.add(new JobViewDTO(
                    dao.getNoticeId(),
                    dao.getNoticeDistrict(),
                    dao.getTypeName(),
                    dao.getRange(),
                    dao.getNoticeDate(),
                    dao.getJobId(),
                    dao.getJobNo(),
                    dao.getUserOpenId(),
                    dao.getJobStatus(),
                    dao.getJobRepairPersonnel(),
                    dao.getJobReason(),
                    Utils.GetTime(dao.getSubmitDate())
            ));
        });


    }




}
