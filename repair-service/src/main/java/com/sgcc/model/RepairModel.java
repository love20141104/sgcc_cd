package com.sgcc.model;

import com.example.Utils;
import com.sgcc.dao.RepairDao;
import com.sgcc.dto.OrderDTO;
import com.sgcc.dto.RepairEditDTO;
import com.sgcc.dto.RepairSubmitDTO;
import com.sgcc.dto.RepairViewDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class RepairModel {

    private String repairId;

    private RepairDao repairDao;

    private OrderDTO orderDTO;

    private RepairSubmitDTO repairSubmitDTO;

    private RepairViewDTO repairViewDTO;

    private RepairEditDTO repairEditDTO;

    private List<OrderDTO> orderDTOS = new ArrayList<>();

    private List<RepairDao> repairDaos = new ArrayList<>();

    private List<RepairViewDTO> repairViewDTOS = new ArrayList<>();

    public RepairModel(RepairDao repairDao) {
        this.repairDao = repairDao;
    }

    public RepairModel(List<RepairDao> repairDaos) {
        this.repairDaos = repairDaos;
    }


    public void queryByOpenIdTransform(List<RepairDao> repairDaos){
        String progress = "抢修中";
        repairDaos.forEach(dao->{
            this.orderDTO = new OrderDTO();
            orderDTO.setOrderNo(dao.getRepairId());
            orderDTO.setAddress(dao.getRepairAddr());
            orderDTO.setApplyDate(Utils.GetTime(dao.getSubmitDate()));
            orderDTO.setProgress(progress);
            orderDTO.setUserName(dao.getRepairContact());
            orderDTO.setUserNo(dao.getOpenId());
            this.orderDTOS.add(orderDTO);
        });
    }



    public void updateRepairTransform(RepairEditDTO dto){
        this.repairDao = new RepairDao(
                null,
                dto.getRepairId(),
                dto.getOpenId(),
                dto.getRepairContent(),
                dto.getRepairContact(),
                dto.getRepairTel(),
                dto.getRepairAddr(),
                dto.getRepairImg1(),
                dto.getRepairImg2(),
                dto.getRepairImg3(),
                null
        );

    }


    public void addRepairTransform(RepairSubmitDTO dto){
        String id = UUID.randomUUID().toString();
        this.repairDao = new RepairDao(
                id,
                id,
                dto.getOpenId(),
                dto.getRepairContent(),
                dto.getRepairContact(),
                dto.getRepairTel(),
                dto.getRepairAddr(),
                dto.getRepairImg1(),
                dto.getRepairImg2(),
                dto.getRepairImg3(),
                new Date()
        );

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
