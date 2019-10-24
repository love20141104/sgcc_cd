package com.sgcc.entity.event;

import com.sgcc.dao.RepairDao;
import com.sgcc.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepairEventEntity {

    @Autowired
    private RepairRepository repairRepository;

    public int addRepairOrder(RepairDao dao){
        return repairRepository.addRepairOrder(dao);
    }

    public int updateRepairOrder(RepairDao dao){
        return repairRepository.updateRepairOrder(dao);
    }

    public int delRepairOrder(List<String> ids){
        return repairRepository.delRepairOrder(ids);
    }



}
