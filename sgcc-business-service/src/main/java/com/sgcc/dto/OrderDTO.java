package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class OrderDTO implements Serializable {

    private static final long serialVersionUID = 6861434901748318598L;

    private String orderNo;

    private String applyDate;

    private String userNo;

    private String userName;

    private String address;

    private String progress;

    private String userType;    // 用户类型

    public OrderDTO(String orderNo, String applyDate, String userNo, String userName, String address,
                    String progress, String userType) {
        this.orderNo = orderNo;
        this.applyDate = applyDate;
        this.userNo = userNo;
        this.userName = userName;
        this.address = address;
        this.progress = progress;
        this.userType = userType;
    }
}
