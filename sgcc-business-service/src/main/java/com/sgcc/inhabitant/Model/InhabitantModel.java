package com.sgcc.inhabitant.Model;

import com.example.Utils;
import com.sgcc.inhabitant.dao.InhabitantNewDao;
import com.sgcc.inhabitant.dto.InhabitantNewDTO;
import com.sgcc.inhabitant.dto.InhabitantSubmitDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sgcc.commerce.dao.CommerceIncreaseCapacityDao;
import com.sgcc.commerce.dto.CommerceIncreaseCapacityDTO;
import com.sgcc.inhabitant.dao.InhabitantIncreaseCapacityDao;
import com.sgcc.inhabitant.dao.InhabitantRenameDao;
import com.sgcc.inhabitant.dto.InhabitantIncreaseCapacityDTO;
import com.sgcc.inhabitant.dto.InhabitantRenameDTO;
import com.sgcc.inhabitant.dto.InhabitantRenameOrderListDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class InhabitantModel {

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

    private String openId;

    private InhabitantRenameDao inhabitantRenameDao;

    private InhabitantRenameDTO inhabitantRenameDTO;

    private InhabitantIncreaseCapacityDTO inhabitantIncreaseCapacityDTO;

    private InhabitantIncreaseCapacityDao inhabitantIncreaseCapacityDao;

    private CommerceIncreaseCapacityDao commerceIncreaseCapacityDao;

    private CommerceIncreaseCapacityDTO commerceIncreaseCapacityDTO;

    private List<InhabitantRenameOrderListDTO> inhabitantRenameOrderListDTOS;

    private List<InhabitantRenameDao> inhabitantRenameDaos = new ArrayList<>();

    private List<InhabitantRenameDTO> inhabitantRenameDTOS = new ArrayList<>();

    private List<CommerceIncreaseCapacityDao> commerceIncreaseCapacityDaos = new ArrayList<>();

    private List<CommerceIncreaseCapacityDTO> commerceIncreaseCapacityDTOS = new ArrayList<>();

    public InhabitantModel(InhabitantRenameDao inhabitantRenameDao) {
        this.inhabitantRenameDao = inhabitantRenameDao;
    }

    public InhabitantModel(InhabitantRenameDTO inhabitantRenameDTO, String openId) {
        this.inhabitantRenameDTO = inhabitantRenameDTO;
        this.openId = openId;
    }

    public InhabitantModel(List<InhabitantRenameDao> inhabitantRenameDaos) {
        this.inhabitantRenameDaos = inhabitantRenameDaos;
    }


    public InhabitantModel(InhabitantIncreaseCapacityDTO inhabitantIncreaseCapacityDTO,String openId) {
        this.inhabitantIncreaseCapacityDTO = inhabitantIncreaseCapacityDTO;
        this.openId = openId;
    }

    public InhabitantModel(String openId, CommerceIncreaseCapacityDTO commerceIncreaseCapacityDTO) {
        this.openId = openId;
        this.commerceIncreaseCapacityDTO = commerceIncreaseCapacityDTO;
    }

    public InhabitantModel(String openId, List<CommerceIncreaseCapacityDao> commerceIncreaseCapacityDaos) {
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
                    commerceIncreaseCapacityDao.getInvoiceDate(),
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
                id.replace("-",""),
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
                this.commerceIncreaseCapacityDTO.getInvoiceDate(),
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




    /**
     * 新增增容订单dto转dao
     */
    public void insertIncreaseCapacityTransform(){
        String id = UUID.randomUUID().toString();
        this.inhabitantIncreaseCapacityDao = new InhabitantIncreaseCapacityDao(
                id,
                id.replace("-",""),
                this.getOpenId(),
                this.inhabitantIncreaseCapacityDTO.getCurrentCapacity(),
                this.inhabitantIncreaseCapacityDTO.getName(),
                this.inhabitantIncreaseCapacityDTO.getIdcard(),
                this.inhabitantIncreaseCapacityDTO.getContactTel(),
                this.inhabitantIncreaseCapacityDTO.getAplicant(),
                this.inhabitantIncreaseCapacityDTO.getTransactor(),
                this.inhabitantIncreaseCapacityDTO.getTransactorIdcard(),
                this.inhabitantIncreaseCapacityDTO.getCqIdcardPositiveImg(),
                this.inhabitantIncreaseCapacityDTO.getCqIdcardBackImg(),
                this.inhabitantIncreaseCapacityDTO.getSqIdcardPositiveImg(),
                this.inhabitantIncreaseCapacityDTO.getSqIdcardBackImg(),
                new Date()
        );

    }



    /**
     * 新增更名过户订单dto转dao
     */
    public void insertRenameTransform(){
        String id = UUID.randomUUID().toString();
        this.inhabitantRenameDao = new InhabitantRenameDao(
                id,
                id,
                this.inhabitantRenameDTO.getHouseId(),
                this.getOpenId(),
                this.inhabitantRenameDTO.getChange(),
                this.inhabitantRenameDTO.getHouseName(),
                this.inhabitantRenameDTO.getIdCard(),
                this.inhabitantRenameDTO.getContactTel(),
                this.inhabitantRenameDTO.getIdCardPositiveImg(),
                this.inhabitantRenameDTO.getIdCardBackImg(),
                new Date()
        );

    }

    /**
     * 查询更名过户订单daos转dtos
     */
    public void queryRenameTransform(){
        this.inhabitantRenameDaos.forEach(inhabitantRenameDao->{
            this.inhabitantRenameDTOS.add(new InhabitantRenameDTO(
                    inhabitantRenameDao.getInfoId(),
                    inhabitantRenameDao.getHouseId(),
                    inhabitantRenameDao.getChange(),
                    inhabitantRenameDao.getHouseName(),
                    inhabitantRenameDao.getIdCard(),
                    inhabitantRenameDao.getContactTel(),
                    inhabitantRenameDao.getIdCardPositiveImg(),
                    inhabitantRenameDao.getIdCardBackImg(),
                    inhabitantRenameDao.getSubmitDate()
            ));
        });

    }


    /**
     * 查询更名过户订单列表daos转dtos
     */





}
