package com.sgcc.entity.query;

import com.sgcc.dao.PayResultDao;
import com.sgcc.dto.PayQueryStatisticsDTO;
import com.sgcc.repository.PayResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PayResultEntity {

    @Autowired
    private PayResultRepository payResultRepository;

    public int insertPayResult(PayResultDao payResultDao){
        return payResultRepository.insertPayResult(payResultDao);
    }

    public List<PayResultDao> findPayResult(){
        return payResultRepository.findPayResult();
    }


    public PayQueryStatisticsDTO findPayResultByMonth(String date){
        return payResultRepository.findPayResultByMonth(date);
    }


    public List<PayQueryStatisticsDTO> findPayResultByYear(){
        return payResultRepository.findPayResultByYear();
    }


    public List<PayQueryStatisticsDTO> findPayResultByCurrentMonth(String date){
        return payResultRepository.findPayResultByCurrentMonth(date);
    }


    public PayQueryStatisticsDTO findPayResultByCurrentYear(String date){
        return payResultRepository.findPayResultByCurrentYear(date);
    }


}
