package com.sgcc.entity;

import com.sgcc.dao.PayResultDao;
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

}
