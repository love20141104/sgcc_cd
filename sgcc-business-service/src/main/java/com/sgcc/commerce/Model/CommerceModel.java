package com.sgcc.commerce.Model;

import com.example.Utils;
import com.sgcc.commerce.dao.CommerceChangeTaxInfoDao;
import com.sgcc.commerce.dao.CommerceIncreaseCapacityDao;
import com.sgcc.commerce.dao.CommerceNewDao;
import com.sgcc.commerce.dao.CommerceRenameDao;
import com.sgcc.commerce.dto.*;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
import com.sgcc.inhabitant.dto.InhabitantNewDTO;
import com.sgcc.inhabitant.dto.InhabitantSubmitDTO;
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

    private CommerceIncreaseCapacityDTO commerceIncreaseCapacityDTO;

    private List<CommerceIncreaseCapacityDao> commerceIncreaseCapacityDaos = new ArrayList<>();

    private List<CommerceIncreaseCapacityDTO> commerceIncreaseCapacityDTOS = new ArrayList<>();

    public CommerceModel(String openId, CommerceIncreaseCapacityDTO commerceIncreaseCapacityDTO) {
        this.openId = openId;
        this.commerceIncreaseCapacityDTO = commerceIncreaseCapacityDTO;
    }

    public CommerceModel(String openId, List<CommerceIncreaseCapacityDao> commerceIncreaseCapacityDaos) {
        this.openId = openId;
        this.commerceIncreaseCapacityDaos = commerceIncreaseCapacityDaos;
    }

    public void queryIncreaseCapacityByGeTransform(){
        this.commerceIncreaseCapacityDaos.forEach(commerceIncreaseCapacityDao->{
            this.commerceIncreaseCapacityDTOS.add(new CommerceIncreaseCapacityDTO(
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
                this.commerceIncreaseCapacityDTO.getCompanyName(),
                this.commerceIncreaseCapacityDTO.getCurrentCapacity(),
                this.commerceIncreaseCapacityDTO.getName(),
                this.commerceIncreaseCapacityDTO.getIdcard(),
                this.commerceIncreaseCapacityDTO.getContactTel(),
                this.commerceIncreaseCapacityDTO.getLicenseImg(),
                this.commerceIncreaseCapacityDTO.getAplicant(),
                this.commerceIncreaseCapacityDTO.getTransactor(),
                this.commerceIncreaseCapacityDTO.getTransactorIdcard(),
                this.commerceIncreaseCapacityDTO.getInvoiceFlag(),
                this.commerceIncreaseCapacityDTO.getInvoiceNum(),
                this.commerceIncreaseCapacityDTO.getInvoiceBank(),
                this.commerceIncreaseCapacityDTO.getInvoiceBankAccount(),
                this.commerceIncreaseCapacityDTO.getInvoiceRegistAddr(),
                this.commerceIncreaseCapacityDTO.getInvoiceContactTel(),
                Utils.GetDate(this.commerceIncreaseCapacityDTO.getInvoiceDate()),
                this.commerceIncreaseCapacityDTO.getSecuritiesImg1(),
                this.commerceIncreaseCapacityDTO.getSecuritiesImg2(),
                this.commerceIncreaseCapacityDTO.getSecuritiesImg3(),
                this.commerceIncreaseCapacityDTO.getSecuritiesImg4(),
                this.commerceIncreaseCapacityDTO.getSecuritiesImg5(),
                this.commerceIncreaseCapacityDTO.getSecuritiesImg6(),
                this.commerceIncreaseCapacityDTO.getCqIdcardPositiveImg(),
                this.commerceIncreaseCapacityDTO.getCqIdcardBackImg(),
                this.commerceIncreaseCapacityDTO.getSqIdcardPositiveImg(),
                this.commerceIncreaseCapacityDTO.getSqIdcardBackImg(),
                this.commerceIncreaseCapacityDTO.getInvoiceImg(),
                new Date()
        );

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
