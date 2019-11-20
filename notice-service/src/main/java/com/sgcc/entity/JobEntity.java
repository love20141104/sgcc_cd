package com.sgcc.entity;

import com.sgcc.dao.JobDao;
import com.sgcc.dao.RepairProgressDao;
import com.sgcc.repository.JobRepository;
import com.sgcc.repository.RepairProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobEntity {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private RepairProgressRepository repairProgressRepository;

    public void saveJob(JobDao jobDao){
        //同时保存抢修进度，状态为已受理
        jobRepository.insertJob(jobDao);
        RepairProgressDao repairProgressDao = new RepairProgressDao();
        repairProgressRepository.insertRepairProgress(repairProgressDao);
    }
    public void updatejobStatus(String  jobId,String jobStatus){

    }
    public void deleteJob(List<String> ids){

    }
    public List<RepairProgressDao> selectJob(){
        return null;
    }
}
