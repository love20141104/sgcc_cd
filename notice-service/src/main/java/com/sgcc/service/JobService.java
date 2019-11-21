package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;

import com.sgcc.dao.JobAndRepairProgressDao;
import com.sgcc.dao.NoticeAndJobDao;
import com.sgcc.dao.RepairProgressDao;
import com.sgcc.dto.JobEditDTO;
import com.sgcc.dto.NewJobSubmitDTO;
import com.sgcc.dto.RepairProgressSubmitDTO;
import com.sgcc.entity.JobEntity;
import com.sgcc.entity.RepairProgressEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.JobModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobEntity jobEntity;

    @Autowired
    private RepairProgressEntity repairProgressEntity;

    /**
     * 新增工单
     * @param openId
     * @param newJobSubmitDTO
     * @return
     */
    public Result saveJob(String openId, NewJobSubmitDTO newJobSubmitDTO) {
        if (Strings.isNullOrEmpty(openId) || newJobSubmitDTO == null)
            return Result.failure("传入参数为空");
        try {
            JobModel jobModel = new JobModel();
            jobModel.addJobTransform(openId,newJobSubmitDTO);
            jobEntity.saveJob(jobModel.getJobDao());
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 删除工单
     * @param ids
     * @return
     */
    public Result delJob(List<String> ids) {

        if (ids.size() < 1)
            return Result.failure("传入参数为空");
        try {
            jobEntity.deleteJob(ids);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }



    /**
     * 查询工单
     * @return
     */
    public Result selectJobs() {

        try {
            List<NoticeAndJobDao> noticeAndJobDaos = jobEntity.selectNoticeAndJob();
            JobModel model = new JobModel();
            model.selectJobsTransform(noticeAndJobDaos);
            return Result.success(model.getJobViewDTOS());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }


    /**
     * 查询抢修进度
     * @param noticeId
     * @return
     */
    public Result selectProgressByNoticeId(String noticeId) {
        if (Strings.isNullOrEmpty(noticeId))
            return Result.failure("传入参数为空");
        try {
            List<JobAndRepairProgressDao> repairProgressDaos = repairProgressEntity.selectRepairProgressListByNoticeId(noticeId);
            JobModel model = new JobModel();
            model.selectRepairProgressTransform(repairProgressDaos);
            return Result.success(model.getRepairProgressViewDTOS());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 新增进度
     * @param openId
     * @param repairProgressSubmitDTO
     * @return
     */
    public Result addProgress(String openId, RepairProgressSubmitDTO repairProgressSubmitDTO) {
        if (Strings.isNullOrEmpty(openId) || repairProgressSubmitDTO == null)
            return Result.failure("传入参数为空");

        try {
            JobModel model = new JobModel();
            model.addProgressTransform(openId,repairProgressSubmitDTO);
            repairProgressEntity.saveRepairProgress(repairProgressSubmitDTO.getNoticeId(),model.getRepairProgressDao());
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }
}
