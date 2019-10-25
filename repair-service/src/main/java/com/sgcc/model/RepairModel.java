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

//    private RepairDao repairDao;
//
//    private RepairSubmitDTO repairSubmitDTO;
//
//    private RepairEditDTO repairEditDTO;
//
//    private List<RepairDao> repairDaos = new ArrayList<>();
//




    public List<RepairViewDTO> queryAllTransform(List<RepairDao> repairDaos){
        List<RepairViewDTO> repairViewDTOS = new ArrayList<>();
        repairDaos.forEach(dao -> {
            RepairViewDTO repairViewDTO = new RepairViewDTO();
            BeanUtils.copyProperties(dao,repairViewDTO);
            repairViewDTOS.add(repairViewDTO);
        });
        return repairViewDTOS;
    }




}
