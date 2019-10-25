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

    private String id;

    private CommerceIncreaseCapacityDao commerceIncreaseCapacityDao;

    private CommerceIncreaseCapacitySubmitDTO commerceIncreaseCapacitySubmitDTO;

    private CommerceIncreaseCapacityUpdateDTO commerceIncreaseCapacityUpdateDTO;

    private List<CommerceIncreaseCapacityDao> commerceIncreaseCapacityDaos = new ArrayList<>();

    private List<CommerceIncreaseCapacitySubmitDTO> commerceIncreaseCapacitySubmitDTOS = new ArrayList<>();

    private List<CommerceIncreaseCapacityDetailDTO> detailDTOS = new ArrayList<>();

    public CommerceModel(String openId, CommerceIncreaseCapacitySubmitDTO commerceIncreaseCapacitySubmitDTO) {
        this.openId = openId;
        this.commerceIncreaseCapacitySubmitDTO = commerceIncreaseCapacitySubmitDTO;
    }

    public CommerceModel(String openId, List<CommerceIncreaseCapacityDao> commerceIncreaseCapacityDaos) {
        this.openId = openId;
        this.commerceIncreaseCapacityDaos = commerceIncreaseCapacityDaos;
    }

    public CommerceModel(CommerceIncreaseCapacityUpdateDTO commerceIncreaseCapacityUpdateDTO ,String id) {
        this.commerceIncreaseCapacityUpdateDTO = commerceIncreaseCapacityUpdateDTO;
        this.id = id;
    }

    public void queryIncreaseCapacityAllTransform(){
        this.commerceIncreaseCapacityDaos.forEach(commerceIncreaseCapacityDao->{
            this.detailDTOS.add(new CommerceIncreaseCapacityDetailDTO(
                    commerceIncreaseCapacityDao.getId(),
                    commerceIncreaseCapacityDao.getOpenId(),
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
                    commerceIncreaseCapacityDao.getInvoiceImg(),
                    commerceIncreaseCapacityDao.getSubmitDate()
            ));
        });

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
                this.commerceIncreaseCapacitySubmitDTO.getIn_company_name(),
                this.commerceIncreaseCapacitySubmitDTO.getIn_current_capacity(),
                this.commerceIncreaseCapacitySubmitDTO.getIn_name(),
                this.commerceIncreaseCapacitySubmitDTO.getIn_idcard(),
                this.commerceIncreaseCapacitySubmitDTO.getIn_telphone(),
                this.commerceIncreaseCapacitySubmitDTO.getIn_license_img(),
                this.commerceIncreaseCapacitySubmitDTO.getIn_apply_person(),
                this.commerceIncreaseCapacitySubmitDTO.getIn_transactor(),
                this.commerceIncreaseCapacitySubmitDTO.getIn_transactor_idcard(),
                this.commerceIncreaseCapacitySubmitDTO.getIn_invoice(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoice_number(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoice_bank(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoice_bank_account(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoice_regist_addr(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoice_phone(),
                Utils.GetDate(this.commerceIncreaseCapacitySubmitDTO.getInvoice_date()),
                this.commerceIncreaseCapacitySubmitDTO.getPropertyRight_img1(),
                this.commerceIncreaseCapacitySubmitDTO.getPropertyRight_img2(),
                this.commerceIncreaseCapacitySubmitDTO.getPropertyRight_img3(),
                this.commerceIncreaseCapacitySubmitDTO.getPropertyRight_img4(),
                this.commerceIncreaseCapacitySubmitDTO.getPropertyRight_img5(),
                this.commerceIncreaseCapacitySubmitDTO.getPropertyRight_img6(),
                this.commerceIncreaseCapacitySubmitDTO.getCq_idcard_positive_img(),
                this.commerceIncreaseCapacitySubmitDTO.getCq_idcard_back_img(),
                this.commerceIncreaseCapacitySubmitDTO.getSq_idcard_positive_img(),
                this.commerceIncreaseCapacitySubmitDTO.getSq_idcard_back_img(),
                this.commerceIncreaseCapacitySubmitDTO.getInvoice_img(),
                new Date()
        );

    }


    public void updateIncreaseCapacityTransform(){
        this.commerceIncreaseCapacityDao = new CommerceIncreaseCapacityDao(
                this.id,
                null,
                this.commerceIncreaseCapacityUpdateDTO.getIn_company_name(),
                this.commerceIncreaseCapacityUpdateDTO.getIn_current_capacity(),
                this.commerceIncreaseCapacityUpdateDTO.getIn_name(),
                this.commerceIncreaseCapacityUpdateDTO.getIn_idcard(),
                this.commerceIncreaseCapacityUpdateDTO.getIn_telphone(),
                this.commerceIncreaseCapacityUpdateDTO.getIn_license_img(),
                this.commerceIncreaseCapacityUpdateDTO.getIn_apply_person(),
                this.commerceIncreaseCapacityUpdateDTO.getIn_transactor(),
                this.commerceIncreaseCapacityUpdateDTO.getIn_transactor_idcard(),
                this.commerceIncreaseCapacityUpdateDTO.getIn_invoice(),
                this.commerceIncreaseCapacityUpdateDTO.getInvoice_number(),
                this.commerceIncreaseCapacityUpdateDTO.getInvoice_bank(),
                this.commerceIncreaseCapacityUpdateDTO.getInvoice_bank_account(),
                this.commerceIncreaseCapacityUpdateDTO.getInvoice_regist_addr(),
                this.commerceIncreaseCapacityUpdateDTO.getInvoice_phone(),
                null,
                this.commerceIncreaseCapacityUpdateDTO.getPropertyRight_img1(),
                this.commerceIncreaseCapacityUpdateDTO.getPropertyRight_img2(),
                this.commerceIncreaseCapacityUpdateDTO.getPropertyRight_img3(),
                this.commerceIncreaseCapacityUpdateDTO.getPropertyRight_img4(),
                this.commerceIncreaseCapacityUpdateDTO.getPropertyRight_img5(),
                this.commerceIncreaseCapacityUpdateDTO.getPropertyRight_img6(),
                this.commerceIncreaseCapacityUpdateDTO.getCq_idcard_positive_img(),
                this.commerceIncreaseCapacityUpdateDTO.getCq_idcard_back_img(),
                this.commerceIncreaseCapacityUpdateDTO.getSq_idcard_positive_img(),
                this.commerceIncreaseCapacityUpdateDTO.getSq_idcard_back_img(),
                this.commerceIncreaseCapacityUpdateDTO.getInvoice_img(),
                null
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
