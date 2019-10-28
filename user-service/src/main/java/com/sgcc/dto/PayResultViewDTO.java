package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayResultViewDTO implements Serializable {

    private static final long serialVersionUID = 602193086638185343L;

    private String payId;

    private String orderNo;     // 订单号

    private String userNo;      // 户号

    private String openId;      // 提交者

    private double money;       // 金额

    private String paymentChannel;  // 缴费渠道

    private String orderSubmitTime;


}
