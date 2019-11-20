package com.sgcc.entity;


import com.sgcc.dao.RepairProgressDao;
import com.sgcc.repository.JobRepository;
import com.sgcc.repository.RepairProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepairProgressEntity {
    @Autowired
    private RepairProgressRepository repairProgressRepository;
    @Autowired
    private JobRepository jobRepository;

    /**
     * 保存抢修进度
     * @param progressDao
     */
    @Transactional
    public void saveRepairProgress(RepairProgressDao progressDao){
        //如果保存的该进度为抢修中 ，且数据库有该工单抢修中状态则覆盖
        if(progressDao.getProgressStatus().equalsIgnoreCase("抢修中")){
            List<RepairProgressDao> repairProgressDaos = repairProgressRepository.selectRepairProgressList(progressDao.getJobId());
            repairProgressDaos.forEach(repairProgressDao->{
                if(repairProgressDao.getProgressStatus().equalsIgnoreCase("抢修中")){
                    List<String> ids=new ArrayList<>();
                    ids.add(repairProgressDao.getId());
                    repairProgressRepository.deleteRepairProgress(ids);
                }
            });
        }
        //如果保存的该进度为抢修完成 ，则工单状态改为已完成
        if(progressDao.getProgressStatus().equalsIgnoreCase("已完成")){
            jobRepository.updatejobStatus(progressDao.getJobId(), "已完成");
        }
        repairProgressRepository.insertRepairProgress(progressDao);

    }
    public void updateRepairProgress(RepairProgressDao progressDao){

    }
    public void deleteRepairProgress(List<String> ids){

    }

    /**
     * 通过停电公告id查询抢修进度列表
     * @param noticeId
     * @return
     */
    public List<RepairProgressDao> selectRepairProgressListByNoticeId(String noticeId){
        return repairProgressRepository.selectRepairProgressListByNoticeId(noticeId);
    }
}
