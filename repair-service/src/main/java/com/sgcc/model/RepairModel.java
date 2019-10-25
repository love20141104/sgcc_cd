package com.sgcc.model;

import com.sgcc.dao.RepairDao;
import com.sgcc.dto.RepairEditDTO;
import com.sgcc.dto.RepairSubmitDTO;
import com.sgcc.dto.RepairViewDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class RepairModel {

    private String repairId;

    private RepairDao repairDao;

    private RepairSubmitDTO repairSubmitDTO;

    private RepairViewDTO repairViewDTO;

    private RepairEditDTO repairEditDTO;

    private List<RepairDao> repairDaos = new ArrayList<>();

    private List<RepairViewDTO> repairViewDTOS = new ArrayList<>();

    public RepairModel(RepairDao repairDao) {
        this.repairDao = repairDao;
    }

    public RepairModel(List<RepairDao> repairDaos) {
        this.repairDaos = repairDaos;
    }



    public void queryByIdTransform(){
        this.repairViewDTO = new RepairViewDTO(
                this.repairDao.getId(),
                this.repairDao.getRepairId(),
                this.repairDao.getOpenId(),
                this.repairDao.getRepairContent(),
                this.repairDao.getRepairContact(),
                this.repairDao.getRepairTel(),
                this.repairDao.getRepairAddr(),
                this.repairDao.getRepairImg1(),
                this.repairDao.getRepairImg2(),
                this.repairDao.getRepairImg3(),
                this.repairDao.getSubmitDate()
        );
    }


    public void queryAllTransform(){
        this.repairDaos.forEach(dao -> {
            this.repairViewDTOS.add(new RepairViewDTO(
                    dao.getId(),
                    dao.getRepairId(),
                    dao.getOpenId(),
                    dao.getRepairContent(),
                    dao.getRepairContact(),
                    dao.getRepairTel(),
                    dao.getRepairAddr(),
                    dao.getRepairImg1(),
                    dao.getRepairImg2(),
                    dao.getRepairImg3(),
                    dao.getSubmitDate()
            ));
        });
    }




}
