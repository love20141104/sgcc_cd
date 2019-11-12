package com.sgcc.model;

import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dto.HouseholdInfoDTO;
import com.sgcc.dto.HouseholdInfoDTO_interface;
import com.sgcc.dto.HouseholdInfoListDTO;
import com.sgcc.dto.HouseholdNumsDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class HouseholdModel {
    String openId;
    List<HouseholdInfoDao> householdInfoDaos = new ArrayList<>();
    HouseholdInfoListDTO householdInfoListDTO = new HouseholdInfoListDTO();

    HouseholdInfoDTO_interface householdInfoDTO_interface = new HouseholdInfoDTO_interface();
    HouseholdInfoDao householdInfoDao;

    public HouseholdModel(String openId,List<HouseholdInfoDao> householdInfoDaos) {
        this.openId = openId;
        this.householdInfoDaos = new ArrayList<>(householdInfoDaos);
    }

    public HouseholdModel(HouseholdInfoDTO_interface householdInfoDTO_interface){
        this.householdInfoDTO_interface = householdInfoDTO_interface;
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

    public void interfaceDTO2DAO(String pwd){
        this.householdInfoDao = new HouseholdInfoDao(
                UUID.randomUUID().toString()
                ,this.householdInfoDTO_interface.getHouseholdHouseholder()
                ,this.householdInfoDTO_interface.getHouseholdNumber()
                ,this.householdInfoDTO_interface.getHouseholdAddress()
                ,false
                ,this.householdInfoDTO_interface.getHouseholdType()
                ,pwd
                ,true
        );
    }

    public String[] getNoBindTransform(List<HouseholdNumsDTO> householdNumsDTOS) {
        String[] str = new String[householdNumsDTOS.size()];
        for (int i = 0; i < householdNumsDTOS.size(); i++) {
            str[i] = householdNumsDTOS.get(i).getHouseholdNumber();
        }
        return str;
    }
}
