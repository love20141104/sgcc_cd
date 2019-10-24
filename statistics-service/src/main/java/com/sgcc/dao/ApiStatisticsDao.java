package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApiStatisticsDao implements Serializable {
    private static final long serialVersionUID = -2720156418106643259L;

    private String id;

    //接口地址
    private String apiUrl;

    //
    private String userOpenId;

    //调用时间
    private Date visitDate;

    //客户端ip
    private String clientIp;


}
