package com.sgcc.inhabitant.Model;

import com.example.Utils;
import com.sgcc.dto.OrderDTO;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
import com.sgcc.inhabitant.dto.*;
import org.springframework.beans.BeanUtils;

import java.util.*;

import com.sgcc.inhabitant.dao.InhabitantIncreaseCapacityDao;
import com.sgcc.inhabitant.dao.InhabitantRenameDao;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public InhabitantModel(InhabitantRenameSubmitDTO inhabitantRenameSubmitDTO) {
        this.inhabitantRenameSubmitDTO = inhabitantRenameSubmitDTO;
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

        Collections.sort(dtos, new Comparator<InhabitantNewDTO>() {
            @Override
            public int compare(InhabitantNewDTO o1, InhabitantNewDTO o2) {
                if (o1.getSubmit_date().getTime() > o2.getSubmit_date().getTime()) {
                    return -1;
                }
                if (o1.getSubmit_date().getTime() == o2.getSubmit_date().getTime()) {
                    return 0;
                }
                return 1;
            }
        });

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
                this.inhabitantRenameSubmitDTO.getOpenId(),
                this.inhabitantRenameSubmitDTO.getChange(),
                this.inhabitantRenameSubmitDTO.getHouseName(),
                this.inhabitantRenameSubmitDTO.getIdCard(),
                this.inhabitantRenameSubmitDTO.getContactTel(),
                this.inhabitantRenameSubmitDTO.getIdCardPositiveImg(),
                this.inhabitantRenameSubmitDTO.getIdCardBackImg(),
                this.inhabitantRenameSubmitDTO.getApplicant(),
                this.inhabitantRenameSubmitDTO.getTransactorName(),
                this.inhabitantRenameSubmitDTO.getSqArttorneyImg(),
                this.inhabitantRenameSubmitDTO.getSqIdCardPositiveImg(),
                this.inhabitantRenameSubmitDTO.getSqIdCardBackImg(),
                this.inhabitantRenameSubmitDTO.getTransactorIdCard(),
                this.inhabitantRenameSubmitDTO.getTransactorTel(),
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
                    dao.getInfoName(),
                    dao.getInfoIdCard(),
                    dao.getInfoTel(),
                    dao.getCqIdCardPositiveImg(),
                    dao.getCqIdCardBackImg(),
                    dao.getApplicant(),
                    dao.getTransactorName(),
                    dao.getSqArttorneyImg(),
                    dao.getSqIdCardPositiveImg(),
                    dao.getSqIdCardBackImg(),
                    dao.getTransactorIdCard(),
                    dao.getTransactorTel(),
                    Utils.GetTime(dao.getSubmitDate())
            ));
        });

        Collections.sort(this.inhabitantRenameDetailDTOs, new Comparator<InhabitantRenameDetailDTO>() {
            @Override
            public int compare(InhabitantRenameDetailDTO o1, InhabitantRenameDetailDTO o2) {
                if (Utils.GetDate(o1.getSubmitDate()).getTime() > Utils.GetDate(o2.getSubmitDate()).getTime()) {
                    return -1;
                }
                if (Utils.GetDate(o1.getSubmitDate()).getTime() == Utils.GetDate(o2.getSubmitDate()).getTime()) {
                    return 0;
                }
                return 1;
            }
        });


    }


    /**
     * 查询更名过户订单daos转dtos
     */
    public void queryRenameByIdTransform(){
        this.inhabitantRenameDaos.forEach(inhabitantRenameDao->{
            this.inhabitantRenameSubmitDTOS.add(new InhabitantRenameSubmitDTO(
                    inhabitantRenameDao.getHouseId(),
                    inhabitantRenameDao.getOpenId(),
                    inhabitantRenameDao.getChange(),
                    inhabitantRenameDao.getInfoName(),
                    inhabitantRenameDao.getInfoIdCard(),
                    inhabitantRenameDao.getInfoTel(),
                    inhabitantRenameDao.getCqIdCardPositiveImg(),
                    inhabitantRenameDao.getCqIdCardBackImg(),
                    inhabitantRenameDao.getApplicant(),
                    inhabitantRenameDao.getTransactorName(),
                    inhabitantRenameDao.getSqArttorneyImg(),
                    inhabitantRenameDao.getSqIdCardPositiveImg(),
                    inhabitantRenameDao.getSqIdCardBackImg(),
                    inhabitantRenameDao.getTransactorIdCard(),
                    inhabitantRenameDao.getTransactorTel()
            ));
        });

    }


    /**
     * 修改更名过户订单列表dto转dao
     */
    public InhabitantRenameDao updateRenameTransform(InhabitantRenameUpdateDTO dto){

        InhabitantRenameDao dao = new InhabitantRenameDao(
                null,
                dto.getInfoId(),
                dto.getHouseId(),
                dto.getHouseId(),
                dto.getChange(),
                dto.getInfoName(),
                dto.getInfoIdCard(),
                dto.getInfoTelphone(),
                dto.getCqIdCardPositiveImg(),
                dto.getCqIdCardBackImg(),
                dto.getApplicant(),
                dto.getTransactorName(),
                dto.getSqArttorneyImg(),
                dto.getSqIdCardPositiveImg(),
                dto.getSqIdCardBackImg(),
                dto.getTransactorIdCard(),
                dto.getTransactorTel(),
                null
        );
        return dao;
    }




}
