package com.sgcc.commerce.Model;

import com.example.Utils;
import com.sgcc.commerce.dao.CommerceChangeTaxInfoDao;
import com.sgcc.commerce.dao.CommerceIncreaseCapacityDao;
import com.sgcc.commerce.dao.CommerceNewDao;
import com.sgcc.commerce.dto.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
public class CommerceModel {

    private String openId;

    private CommerceIncreaseCapacityDao commerceIncreaseCapacityDao;

    private CommerceIncreaseCapacitySubmitDTO commerceIncreaseCapacitySubmitDTO;

    private List<CommerceIncreaseCapacityDao> commerceIncreaseCapacityDaos = new ArrayList<>();

    private List<CommerceIncreaseCapacitySubmitDTO> commerceIncreaseCapacitySubmitDTOS = new ArrayList<>();

    public CommerceModel(String openId, CommerceIncreaseCapacitySubmitDTO commerceIncreaseCapacitySubmitDTO) {
        this.openId = openId;
        this.commerceIncreaseCapacitySubmitDTO = commerceIncreaseCapacitySubmitDTO;
    }

    public CommerceModel(String openId, List<CommerceIncreaseCapacityDao> commerceIncreaseCapacityDaos) {
        this.openId = openId;
        this.commerceIncreaseCapacityDaos = commerceIncreaseCapacityDaos;
    }

    public void queryIncreaseCapacityByGeTransform(){
        this.commerceIncreaseCapacityDaos.forEach(commerceIncreaseCapacityDao->{
            this.commerceIncreaseCapacitySubmitDTOS.add(new CommerceIncreaseCapacitySubmitDTO(
                    commerceIncreaseCapacityDao.getCompanyName(),
                    commerceIncreaseCapacityDao.getCurrentCapacity(),
                    commerceIncreaseCapacityDao.getName(),
                    commerceIncreaseCapacityDao.getIdcard(),
                    commerceIncreaseCapacityDao.getContactTel(),
                    commerceIncreaseCapacityDao.getLicenseImg(),
                    commerceIncreaseCapacityDao.getAplicant(),
                    commerceIncreaseCapacityDao.getTransactor(),
                    commerceIncreaseCapacityDao.getTransactorIdcard(),
                    commerceIncreaseCapacityDao.getInvoiceFlag(),
                    commerceIncreaseCapacityDao.getInvoiceNum(),
                    commerceIncreaseCapacityDao.getInvoiceBank(),
                    commerceIncreaseCapacityDao.getInvoiceBankAccount(),
                    commerceIncreaseCapacityDao.getInvoiceRegistAddr(),
                    commerceIncreaseCapacityDao.getInvoiceContactTel(),
                    Utils.GetTime(commerceIncreaseCapacityDao.getInvoiceDate()),
                    commerceIncreaseCapacityDao.getSecuritiesImg1(),
                    commerceIncreaseCapacityDao.getSecuritiesImg2(),
                    commerceIncreaseCapacityDao.getSecuritiesImg3(),
                    commerceIncreaseCapacityDao.getSecuritiesImg4(),
                    commerceIncreaseCapacityDao.getSecuritiesImg5(),
                    commerceIncreaseCapacityDao.getSecuritiesImg6(),
                    commerceIncreaseCapacityDao.getCqIdcardPositiveImg(),
                    commerceIncreaseCapacityDao.getCqIdcardBackImg(),
                    commerceIncreaseCapacityDao.getSqIdcardPositiveImg(),
                    commerceIncreaseCapacityDao.getSqIdcardBackImg(),
                    commerceIncreaseCapacityDao.getInvoiceImg()
            ));
        });

    }



    public void insertIncreaseCapacityByGeTransform(){
        String id = UUID.randomUUID().toString();
        this.commerceIncreaseCapacityDao = new CommerceIncreaseCapacityDao(
                id,
                this.getOpenId(),
                this.commerceIncreaseCapacitySubmitDTO.getCompanyName(),
                this.commerceIncreaseCapacitySubmitDTO.getCurrentCapacity(),
                this.commerceIncreaseCapacitySubmitDTO.getName(),
                this.commerceIncreaseCapacitySubmitDTO.getIdcard(),
                this.commerceIncreaseCapacitySubmitDTO.getContactTel(),
                this.commerceIncreaseCapacitySubmitDTO.getLicenseImg(),
                this.commerceIncreaseCapacitySubmitDTO.getAplicant(),
                this.commerceIncreaseCapacitySubmitDTO.getTransactor(),
                this.commerceIncreaseCapacitySubmitDTO.getTransactorIdcard(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoiceFlag(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoiceNum(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoiceBank(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoiceBankAccount(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoiceRegistAddr(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoiceContactTel(),
                Utils.GetDate(this.commerceIncreaseCapacitySubmitDTO.getInvoiceDate()),
                this.commerceIncreaseCapacitySubmitDTO.getSecuritiesImg1(),
                this.commerceIncreaseCapacitySubmitDTO.getSecuritiesImg2(),
                this.commerceIncreaseCapacitySubmitDTO.getSecuritiesImg3(),
                this.commerceIncreaseCapacitySubmitDTO.getSecuritiesImg4(),
                this.commerceIncreaseCapacitySubmitDTO.getSecuritiesImg5(),
                this.commerceIncreaseCapacitySubmitDTO.getSecuritiesImg6(),
                this.commerceIncreaseCapacitySubmitDTO.getCqIdcardPositiveImg(),
                this.commerceIncreaseCapacitySubmitDTO.getCqIdcardBackImg(),
                this.commerceIncreaseCapacitySubmitDTO.getSqIdcardPositiveImg(),
                this.commerceIncreaseCapacitySubmitDTO.getSqIdcardBackImg(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoiceImg(),
                new Date()
        );

    }


    public CommerceIncreaseCapacityDao updateIncreaseCapacityTransform(CommerceIncreaseCapacityUpdateDTO dto, String id){
        CommerceIncreaseCapacityDao dao = new CommerceIncreaseCapacityDao();
        BeanUtils.copyProperties(dto,dao);
        dao.setId(id);
        return dao;
    }


    // --------------------------------------------------------------------------
    public CommerceNewDTO CommerceNewDao2DTO(CommerceNewDao dao )
    {
        CommerceNewDTO dto = new CommerceNewDTO();
        BeanUtils.copyProperties( dao,dto);
        return dto;
    }


    public CommerceNewDao CommerceNewDTO2Dao( CommerceNewDTO dto )
    {
        CommerceNewDao dao = new CommerceNewDao();
        BeanUtils.copyProperties( dto,dao );
        return dao;
    }


    public CommerceNewDao CommerceNewSubmitDTO2Dao( CommerceNewSubmitDTO dto)
    {
        CommerceNewDao dao = new CommerceNewDao();
        BeanUtils.copyProperties( dto,dao );
        dao.setId(UUID.randomUUID().toString());
        dao.setSubmit_date(Utils.GetCurTime());
        return dao;
    }


    public List<CommerceNewDTO> CommerceNewDaos2Dtos(List<CommerceNewDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<CommerceNewDTO> dtos = new ArrayList<>();
        for (CommerceNewDao dao:daos )
            dtos.add(CommerceNewDao2DTO(dao));

        return dtos;
    }

    // --------------------------------------------------------------------------
    public CommerceChangeTaxInfoDTO CommerceChangeTaxInfoDao2DTO(CommerceChangeTaxInfoDao dao )
    {
        CommerceChangeTaxInfoDTO dto = new CommerceChangeTaxInfoDTO();
        BeanUtils.copyProperties( dao,dto);
        return dto;
    }


    public CommerceChangeTaxInfoDao CommerceChangeTaxInfoDTO2Dao( CommerceChangeTaxInfoDTO dto )
    {
        CommerceChangeTaxInfoDao dao = new CommerceChangeTaxInfoDao();
        BeanUtils.copyProperties( dto,dao );
        return dao;
    }


    public CommerceChangeTaxInfoDao CommerceChangeTaxInfoSubmitDTO2Dao( CommerceChangeTaxInfoSubmitDTO dto)
    {
        CommerceChangeTaxInfoDao dao = new CommerceChangeTaxInfoDao();
        BeanUtils.copyProperties( dto,dao );
        dao.setId(UUID.randomUUID().toString());
        dao.setSubmit_date(Utils.GetCurTime());
        return dao;
    }


    public List<CommerceChangeTaxInfoDTO> CommerceChangeTaxInfoDaos2Dtos(List<CommerceChangeTaxInfoDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<CommerceChangeTaxInfoDTO> dtos = new ArrayList<>();
        for (CommerceChangeTaxInfoDao dao:daos )
            dtos.add(CommerceChangeTaxInfoDao2DTO(dao));

        return dtos;
    }
}
