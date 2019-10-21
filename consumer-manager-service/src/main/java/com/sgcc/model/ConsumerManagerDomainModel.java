package com.sgcc.model;

import com.sgcc.dao.ConsumerManagerDao;
import com.sgcc.dto.ConsumerManagerDTO;
import com.sgcc.dto.ConsumerManagerInsertDTO;
import com.sgcc.dto.ConsumerManagerGroupDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@Data
public class ConsumerManagerDomainModel {
    //新增客户经理时用DTO
    private ConsumerManagerInsertDTO consumerManagerInsertDTO;

    //客户经理信息DAO
    private ConsumerManagerDao consumerManagerDao;

    //返回单个客户经理信息时用DTO
    private ConsumerManagerDTO consumerManagerDTO;

    private List<ConsumerManagerDao> consumerManagerDaos = new ArrayList<>();

    //返回全部客户经理信息时用DTO
    private ConsumerManagerGroupDTO consumerManagerGroupDTO;


    public ConsumerManagerDomainModel(ConsumerManagerInsertDTO consumerManagerInsertDTO){
        this.consumerManagerInsertDTO = consumerManagerInsertDTO;
    }

    public ConsumerManagerDomainModel(ConsumerManagerDTO consumerManagerDTO) {
        this.consumerManagerDTO = consumerManagerDTO;
    }

    public ConsumerManagerDomainModel(List<ConsumerManagerDao> consumerManagerDaos) {
        this.consumerManagerDaos = consumerManagerDaos;
    }

    public ConsumerManagerDomainModel(ConsumerManagerDao consumerManagerDao) {
        this.consumerManagerDao = consumerManagerDao;
    }


    /**
     * 查询所有客户经理信息时daos转dto
     */
    public void selectAllTransform(){
        List<ConsumerManagerDTO> consumerManagerDTOList = new ArrayList<>();
        this.consumerManagerDaos.forEach(consumerManagerDao1 -> {
            consumerManagerDTOList.add(
              new ConsumerManagerDTO(
                      consumerManagerDao1.getConsumerManagerId()
                      ,consumerManagerDao1.getConsumerManagerName()
                      ,consumerManagerDao1.getConsumerManagerTel()
                      ,consumerManagerDao1.getConsumerManagerServiceArea()
                      ,consumerManagerDao1.getConsumerManagerAdministrativeRegion()
                      ,consumerManagerDao1.getConsumerManagerDuty()
                      ,consumerManagerDao1.getConsumerManagerWorkTime()
                      ,consumerManagerDao1.getConsumerManagerEmergencyTel()
                      ,consumerManagerDao1.getConsumerManagerWorkUnit()
                      ,consumerManagerDao1.getConsumerManagerCategory()
                      ,consumerManagerDao1.getConsumerManagerImg()
              )
            );
        });
        Map<String,List<ConsumerManagerDTO>> hmap = new HashMap<>();
        consumerManagerDTOList.forEach(consumerManagerDTO -> {
            if( !hmap.containsKey(consumerManagerDTO.getConsumerManagerAdministrativeRegion()) )
            {
                hmap.put(consumerManagerDTO.getConsumerManagerAdministrativeRegion(),new ArrayList<ConsumerManagerDTO>());
            }
            List<ConsumerManagerDTO> list = hmap.get(consumerManagerDTO.getConsumerManagerAdministrativeRegion());
            if( list == null )
                list = new ArrayList<>();
            list.add(consumerManagerDTO);
        });
        //排序
        //Collections.sort(consumerManagerDTOList);
        this.consumerManagerGroupDTO = new ConsumerManagerGroupDTO(hmap);
    }

    /**
     * 新增客户经理信息时dto转dao
     */
    public void insertTransform(){
        String id = UUID.randomUUID().toString();
        this.consumerManagerDao = new ConsumerManagerDao(
                id
                ,id
                ,this.consumerManagerInsertDTO.getConsumerManagerName()
                ,this.consumerManagerInsertDTO.getConsumerManagerTel()
                ,this.consumerManagerInsertDTO.getConsumerManagerServiceArea()
                ,this.consumerManagerInsertDTO.getConsumerManagerAdministrativeRegion()
                ,this.consumerManagerInsertDTO.getConsumerManagerDuty()
                ,this.consumerManagerInsertDTO.getConsumerManagerWorkTime()
                ,this.consumerManagerInsertDTO.getConsumerManagerEmergencyTel()
                ,this.consumerManagerInsertDTO.getConsumerManagerWorkUnit()
                ,this.consumerManagerInsertDTO.getConsumerManagerCategory()
                ,this.consumerManagerInsertDTO.getConsumerManagerImg()
        );
    }


    /**
     * 修改客户经理信息时dto转dao
     */
    public void updateTransform(){
        this.consumerManagerDao = new ConsumerManagerDao(
                this.consumerManagerDTO.getConsumerManagerId()
                ,this.consumerManagerDTO.getConsumerManagerId()
                ,this.consumerManagerDTO.getConsumerManagerName()
                ,this.consumerManagerDTO.getConsumerManagerTel()
                ,this.consumerManagerDTO.getConsumerManagerServiceArea()
                ,this.consumerManagerDTO.getConsumerManagerAdministrativeRegion()
                ,this.consumerManagerDTO.getConsumerManagerDuty()
                ,this.consumerManagerDTO.getConsumerManagerWorkTime()
                ,this.consumerManagerDTO.getConsumerManagerEmergencyTel()
                ,this.consumerManagerDTO.getConsumerManagerWorkUnit()
                ,this.consumerManagerDTO.getConsumerManagerCategory()
                ,this.consumerManagerDTO.getConsumerManagerImg()
        );
    }

    /**
     * 查询单个客户经理信息，将dao转dto
     */
    public void selectTransform(){
        this.consumerManagerDTO = new ConsumerManagerDTO(
                this.consumerManagerDao.getConsumerManagerId()
                ,this.consumerManagerDao.getConsumerManagerName()
                ,this.consumerManagerDao.getConsumerManagerTel()
                ,this.consumerManagerDao.getConsumerManagerServiceArea()
                ,this.consumerManagerDao.getConsumerManagerAdministrativeRegion()
                ,this.consumerManagerDao.getConsumerManagerDuty()
                ,this.consumerManagerDao.getConsumerManagerWorkTime()
                ,this.consumerManagerDao.getConsumerManagerEmergencyTel()
                ,this.consumerManagerDao.getConsumerManagerWorkUnit()
                ,this.consumerManagerDao.getConsumerManagerCategory()
                ,this.consumerManagerDao.getConsumerManagerImg()
        );
    }


}
