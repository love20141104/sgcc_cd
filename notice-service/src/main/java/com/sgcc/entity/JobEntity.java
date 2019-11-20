package com.sgcc.entity;

import com.sgcc.Enum.JobEnum;
import com.sgcc.Enum.RepairProgressEnum;
import com.sgcc.dao.JobDao;
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
    @Transactional
    public void saveJob(JobDao jobDao){
        //同时保存抢修进度，状态为已受理
        jobRepository.insertJob(jobDao);
        RepairProgressDao repairProgressDao = new RepairProgressDao(
                UUID.randomUUID().toString()
                ,jobDao.getUserOpenId()
                ,jobDao.getJobId()
                , RepairProgressEnum.valueOf("ACCEPTED")
                ,new Date()
                ,""
                ,""
                ,""
        );
        repairProgressRepository.insertRepairProgress(repairProgressDao);
    }
    public void updatejobStatus(JobDao jobDao){

    }
    public void deleteJob(List<String> ids){

    }
    public List<RepairProgressDao> selectJob(){
        return null;
    }
}
