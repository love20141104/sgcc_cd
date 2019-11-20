package com.sgcc.entity;

import com.sgcc.dao.RepairProgressDao;
import com.sgcc.repository.RepairProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepairProgressEntity {
    @Autowired
    private RepairProgressRepository repairProgressRepository;

    public void saveRepairProgress(RepairProgressDao progressDao){
        //如果保存的该进度为抢修中 ，且数据库有该工单抢修中状态则覆盖

        //如果保存的该进度为抢修完成 ，则工单状态改为已完成

    }
    public void updateRepairProgress(RepairProgressDao progressDao){

    }
    public void deleteRepairProgress(List<String> ids){

    }
    public List<RepairProgressDao> selectRepairProgress(String jobId){
        return null;
    }
}
