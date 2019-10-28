package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
public class PayResultSubmitDTO implements Serializable {

    private static final long serialVersionUID = -670041112509084678L;

    private String orderNo;     // 订单号

//    private String userName;

    private String userNo;      // 户号

    private String openId;      // 提交者

//    private String orderAddress;

    private double money;       // 金额

//    private String paymentChannel;  // 缴费渠道

//    private String orderSubmitTime;

    public PayResultSubmitDTO(String orderNo, String userNo, String openId,
                              double money) {
        this.orderNo = orderNo;
        this.userNo = userNo;
        this.openId = openId;
        this.money = money;
    }
}
