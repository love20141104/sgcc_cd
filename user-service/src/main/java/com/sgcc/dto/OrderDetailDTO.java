package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderDetailDTO implements Serializable {

    private static final long serialVersionUID = -9051336100504422500L;

    private String orderNo;

    private String userName;

    private String userNo;

    private String orderAddress;

    private double money;

    private String paymentChannel;  // 缴费渠道

    private String orderSubmitTime;


    public OrderDetailDTO(String orderNo, String userName, String userNo,
                          String orderAddress, double money, String paymentChannel, String orderSubmitTime) {
        this.orderNo = orderNo;
        this.userName = userName;
        this.userNo = userNo;
        this.orderAddress = orderAddress;
        this.money = money;
        this.paymentChannel = paymentChannel;
        this.orderSubmitTime = orderSubmitTime;
    }
}
