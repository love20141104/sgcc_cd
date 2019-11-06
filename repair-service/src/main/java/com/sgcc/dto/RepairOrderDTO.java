package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RepairOrderDTO implements Serializable {

    private static final long serialVersionUID = 6861434901748318598L;

    private String orderNo;

    private String applyDate;

    private String userNo;

    private String userName;

    private String address;

    private String progress;

    public RepairOrderDTO(String orderNo, String applyDate, String userNo, String userName, String address,
                          String progress) {
        this.orderNo = orderNo;
        this.applyDate = applyDate;
        this.userNo = userNo;
        this.userName = userName;
        this.address = address;
        this.progress = progress;
    }
}