package com.sgcc.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class PayResultDao implements Serializable {

    private static final long serialVersionUID = -670041112509084678L;

    private String id;

    private String payId;

    private String orderNo;

    private String userName;

    private String userNo;

    private String openId;

    private String orderAddress;

    private double money;

    private String paymentChannel;  // 缴费渠道

    private String orderSubmitTime;

    public PayResultDao(String id, String payId, String orderNo, String userName,
                        String userNo, String openId, String orderAddress, double money,
                        String paymentChannel, String orderSubmitTime) {
        this.id = id;
        this.payId=payId;
        this.orderNo = orderNo;
        this.userName = userName;
        this.userNo = userNo;
        this.openId = openId;
        this.orderAddress = orderAddress;
        this.money = money;
        this.paymentChannel = paymentChannel;
        this.orderSubmitTime = orderSubmitTime;
    }
}
