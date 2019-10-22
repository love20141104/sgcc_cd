package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor  //生成无参构造函数
public class PaymentChannelDTO implements Serializable {

    private static final long serialVersionUID = -8659676023635259437L;

    private String paymentChannelName;

    private Double totalMoney;

    public PaymentChannelDTO(String paymentChannelName, Double totalMoney) {
        this.paymentChannelName = paymentChannelName;
        this.totalMoney = totalMoney;
    }
}
