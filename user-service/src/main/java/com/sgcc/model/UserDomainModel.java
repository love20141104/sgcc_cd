package com.sgcc.model;

import com.example.Utils;
import com.sgcc.dao.PayResultDao;
import com.sgcc.dto.PayQueryStatisticsDTO;
import com.sgcc.dto.PayResultSubmitDTO;
import com.sgcc.dto.PayResultViewDTO;
import com.sgcc.dto.PayStatisticsDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
public class UserDomainModel {

    private PayStatisticsDTO payStatisticsDTO;
    private PayResultSubmitDTO payResultSubmitDTO;
    private PayResultDao payResultDao;
    private List<PayResultDao> payResultDaos = new ArrayList<>();
    private List<PayResultSubmitDTO> payResultSubmitDTOS = new ArrayList<>();
    private List<PayStatisticsDTO> payStatisticsDTOS = new ArrayList<>();

    private List<PayResultViewDTO> payResultViewDTOS = new ArrayList<>();

    public UserDomainModel(PayResultSubmitDTO payResultSubmitDTO) {
        this.payResultSubmitDTO = payResultSubmitDTO;
    }

    public UserDomainModel(PayResultDao payResultDao) {
        this.payResultDao = payResultDao;
    }

    public UserDomainModel(List<PayResultDao> payResultDaos) {
        this.payResultDaos = payResultDaos;
    }



    public void findResultStatisticsByMonth(PayQueryStatisticsDTO dto,String StartDate,String dateUnit){
            this.payStatisticsDTO = new PayStatisticsDTO(
                    StartDate,
                    dateUnit,
                    dto.getPayTotal(),
                    dto.getPaySum(),
                    null
            );
    }

    public void findResultStatisticsByYear(PayQueryStatisticsDTO payQueryStatisticsDTO,
                                           List<PayQueryStatisticsDTO> payQueryStatisticsDTOS,
            String StartDate,String dateUnit){
        payQueryStatisticsDTOS.forEach(dto -> {
            this.payStatisticsDTOS.add(new PayStatisticsDTO(
                    StartDate,
                    dateUnit,
                    dto.getPayTotal(),
                    dto.getPaySum(),
                    null
            ));
        });

        this.payStatisticsDTO = new PayStatisticsDTO(
                StartDate,
                dateUnit,
                payQueryStatisticsDTO.getPayTotal(),
                payQueryStatisticsDTO.getPaySum(),
                this.getPayStatisticsDTOS()
        );

    }



    /**
     * 新增支付结果dto转dao
     */
    public void insertTransform(){
        String id = UUID.randomUUID().toString();
        this.payResultDao = new PayResultDao(
                id,
                id,
                this.payResultSubmitDTO.getOrderNo(),
                this.payResultSubmitDTO.getUserNo(),
                this.payResultSubmitDTO.getOpenId(),
                this.payResultSubmitDTO.getMoney(),
                "微信",
                Utils.GetCurTime()
        );


    }

    /**
     * 查询支付结果dao转dto
     */
    public void findTransform(){
        this.payResultDaos.forEach(payResultDao->{
            this.payResultViewDTOS.add(new PayResultViewDTO(
                    payResultDao.getPayId(),
                    payResultDao.getOrderNo(),
                    payResultDao.getUserNo(),
                    payResultDao.getOpenId(),
                    payResultDao.getMoney(),
                    payResultDao.getPaymentChannel(),
                    Utils.GetTime(payResultDao.getOrderSubmitTime())
            ));
        });
    }



}
