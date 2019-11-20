package com.sgcc.entity;


import com.sgcc.dao.JobDao;
import com.sgcc.dao.NoticeAndJobDao;
import com.sgcc.dao.RepairProgressDao;
import com.sgcc.repository.JobRepository;
import com.sgcc.repository.RepairProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
public class JobEntity {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RepairProgressRepository repairProgressRepository;

    /**
     * 保存工单
     * @param jobDao
     */
    @Transactional
    public void saveJob(JobDao jobDao){
        //同时保存抢修进度，状态为已受理
        jobRepository.insertJob(jobDao);
        RepairProgressDao repairProgressDao = new RepairProgressDao(
                UUID.randomUUID().toString()
                ,jobDao.getUserOpenId()
                ,jobDao.getJobId()
                , "已受理"
                ,new Date()
                ,""
                ,""
                ,""
        );
        repairProgressRepository.insertRepairProgress(repairProgressDao);
    }

    /**
     * 删除工单
     * @param ids
     */
    public void deleteJob(List<String> ids){
        jobRepository.deleteJob( ids);
    }

    /**
     * 获取工单
     * @return List<NoticeAndJobDao>
     */
    public List<NoticeAndJobDao> selectNoticeAndJob(){
        return jobRepository.selectNoticeAndJob();
    }
}
