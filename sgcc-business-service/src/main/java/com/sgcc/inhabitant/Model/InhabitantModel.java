package com.sgcc.inhabitant.Model;

import com.example.Utils;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
import com.sgcc.inhabitant.dto.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sgcc.inhabitant.dao.InhabitantIncreaseCapacityDao;
import com.sgcc.inhabitant.dao.InhabitantRenameDao;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class InhabitantModel {

    private String openId;

    private InhabitantRenameDao inhabitantRenameDao;

    private List<InhabitantRenameDetailDTO> inhabitantRenameDetailDTOs = new ArrayList<>();

    private InhabitantRenameSubmitDTO inhabitantRenameSubmitDTO;

    private InhabitantIncreaseCapacityDTO inhabitantIncreaseCapacityDTO;

    private InhabitantIncreaseCapacityDao inhabitantIncreaseCapacityDao;

    private List<InhabitantRenameOrderListDTO> inhabitantRenameOrderListDTOS =new ArrayList<>();

    private List<InhabitantRenameDao> inhabitantRenameDaos = new ArrayList<>();

    private List<InhabitantRenameSubmitDTO> inhabitantRenameSubmitDTOS = new ArrayList<>();

    public InhabitantModel(InhabitantRenameSubmitDTO inhabitantRenameSubmitDTO, String openId) {
        this.inhabitantRenameSubmitDTO = inhabitantRenameSubmitDTO;
        this.openId = openId;
    }

    public InhabitantModel(List<InhabitantRenameDao> inhabitantRenameDaos) {
        this.inhabitantRenameDaos = inhabitantRenameDaos;
    }

    public InhabitantNewDTO InhabitantNewDao2DTO(InhabitantNewDao dao )
    {
        InhabitantNewDTO dto = new InhabitantNewDTO();
        BeanUtils.copyProperties( dao,dto);
        return dto;
    }
    public InhabitantNewDao InhabitantNewDTO2Dao( InhabitantNewDTO dto )
    {
        InhabitantNewDao dao = new InhabitantNewDao();
        BeanUtils.copyProperties( dto,dao );
        return dao;
    }
    public InhabitantNewDao InhabitantSubmitDTO2Dao( InhabitantSubmitDTO dto )
    {
        InhabitantNewDao dao = new InhabitantNewDao();
        BeanUtils.copyProperties( dto,dao );
        dao.setId(UUID.randomUUID().toString());
        dao.setSubmit_date(Utils.GetCurTime());
        return dao;
    }
    public List<InhabitantNewDTO> InhabitantNewDaos2Dtos(List<InhabitantNewDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<InhabitantNewDTO> dtos = new ArrayList<>();
        for (InhabitantNewDao dao:daos )
            dtos.add(InhabitantNewDao2DTO(dao));

        return dtos;
    }














    /**
     * 新增更名过户订单dto转dao
     */
    public void insertRenameTransform(){
        String id = UUID.randomUUID().toString();
        this.inhabitantRenameDao = new InhabitantRenameDao(
                id,
                id,
                this.inhabitantRenameSubmitDTO.getHouseId(),
                this.getOpenId(),
                this.inhabitantRenameSubmitDTO.getChange(),
                this.inhabitantRenameSubmitDTO.getHouseName(),
                this.inhabitantRenameSubmitDTO.getIdCard(),
                this.inhabitantRenameSubmitDTO.getContactTel(),
                this.inhabitantRenameSubmitDTO.getIdCardPositiveImg(),
                this.inhabitantRenameSubmitDTO.getIdCardBackImg(),
                new Date()
        );

    }


    public void queryRenameAllTransform(){
        this.inhabitantRenameDaos.forEach(dao -> {
            this.inhabitantRenameDetailDTOs.add(new InhabitantRenameDetailDTO(
                    dao.getId(),
                    dao.getInfoId(),
                    dao.getHouseId(),
                    dao.getOpenId(),
                    dao.getChange(),
                    dao.getHouseName(),
                    dao.getIdCard(),
                    dao.getContactTel(),
                    dao.getIdCardPositiveImg(),
                    dao.getIdCardBackImg(),
                    dao.getSubmitDate()
            ));
        });

    }


    /**
     * 查询更名过户订单daos转dtos
     */
    public void queryRenameByIdTransform(){
        this.inhabitantRenameDaos.forEach(inhabitantRenameDao->{
            this.inhabitantRenameSubmitDTOS.add(new InhabitantRenameSubmitDTO(
                    inhabitantRenameDao.getHouseId(),
                    inhabitantRenameDao.getChange(),
                    inhabitantRenameDao.getHouseName(),
                    inhabitantRenameDao.getIdCard(),
                    inhabitantRenameDao.getContactTel(),
                    inhabitantRenameDao.getIdCardPositiveImg(),
                    inhabitantRenameDao.getIdCardBackImg()
            ));
        });

    }


    /**
     * 修改更名过户订单列表dto转dao
     */
    public InhabitantRenameDao updateRenameTransform(InhabitantRenameUpdateDTO dto,String infoId){

        InhabitantRenameDao dao = new InhabitantRenameDao();
        BeanUtils.copyProperties(dto,dao);
        dao.setInfoId(infoId);
        return dao;
    }




}
