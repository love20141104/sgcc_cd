package com.sgcc.entity.query;

import com.sgcc.dao.RepairDao;
import com.sgcc.repository.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepairQueryEntity {

    @Autowired
    private RepairRepository repairRepository;


    public List<RepairDao> findRepairOrderAll(){
        return repairRepository.findRepairOrderAll();
    }

    public RepairDao findRepairOrderById(String repairId){
        return repairRepository.findRepairOrderById(repairId).get(0);
    }



}
