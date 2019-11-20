package com.sgcc.entity;

import com.sgcc.dao.RepairProgressDao;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepairProgressEntity {
    public void saveRepairProgress(RepairProgressDao progressDao){
        //如果保存的该进度为抢修中 ，且数据库有该工单抢修中状态则覆盖
        //

    }
    public void updateRepairProgress(RepairProgressDao progressDao){

    }
    public void deleteRepairProgress(List<String> ids){

    }
    public List<RepairProgressDao> selectRepairProgress(String jobId){
        return null;
    }
}
