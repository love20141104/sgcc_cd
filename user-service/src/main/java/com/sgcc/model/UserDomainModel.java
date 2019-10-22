package com.sgcc.model;

import com.sgcc.dao.PayResultDao;
import com.sgcc.dto.PayResultDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
public class UserDomainModel {

    private PayResultDTO payResultDTO;
    private PayResultDao payResultDao;
    private List<PayResultDao> payResultDaos = new ArrayList<>();
    private List<PayResultDTO> payResultDTOS = new ArrayList<>();


    public UserDomainModel(PayResultDTO payResultDTO) {
        this.payResultDTO = payResultDTO;
    }

    public UserDomainModel(PayResultDao payResultDao) {
        this.payResultDao = payResultDao;
    }

    public UserDomainModel(List<PayResultDao> payResultDaos) {
        this.payResultDaos = payResultDaos;
    }

    /**
     * 新增支付结果dto转dao
     */
    public void insertTransform(){
        String id = UUID.randomUUID().toString();
        this.payResultDao = new PayResultDao(
                id,
                id,
                this.payResultDTO.getOrderNo(),
                this.payResultDTO.getUserName(),
                this.payResultDTO.getUserNo(),
                this.payResultDTO.getOpenId(),
                this.payResultDTO.getOrderAddress(),
                this.payResultDTO.getMoney(),
                this.payResultDTO.getPaymentChannel(),
                this.payResultDTO.getOrderSubmitTime()
        );


    }

    /**
     * 查询支付结果dao转dto
     */
    public void findTransform(){
        this.payResultDaos.forEach(payResultDao->{
            this.payResultDTOS.add(new PayResultDTO(
                    payResultDao.getOrderNo(),
                    payResultDao.getUserName(),
                    payResultDao.getUserNo(),
                    payResultDao.getOpenId(),
                    payResultDao.getOrderAddress(),
                    payResultDao.getMoney(),
                    payResultDao.getPaymentChannel(),
                    payResultDao.getOrderSubmitTime()
            ));
        });


    }



}
