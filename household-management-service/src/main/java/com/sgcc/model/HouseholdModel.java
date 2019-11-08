package com.sgcc.model;

import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dto.HouseholdInfoDTO;
import com.sgcc.dto.HouseholdInfoListDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class HouseholdModel {
    String openId;
    List<HouseholdInfoDao> householdInfoDaos = new ArrayList<>();
    HouseholdInfoListDTO householdInfoListDTO = new HouseholdInfoListDTO();

    public HouseholdModel(String openId,List<HouseholdInfoDao> householdInfoDaos) {
        this.openId = openId;
        this.householdInfoDaos = new ArrayList<>(householdInfoDaos);
    }

    public void daos2dto(){
        this.householdInfoDaos.forEach(dao->{
            householdInfoListDTO.getHouseholdInfoDTOS().add(
                    new HouseholdInfoDTO(
                            dao.getHouseholdHouseholder()
                            ,dao.getHouseholdNumber()
                            ,dao.getHouseholdAddress()
                            ,dao.getHouseholdDefault()
                            ,dao.getHouseholdType()
                    )
            );
        });
        this.householdInfoListDTO.setOpenId(this.openId);
    }
}
