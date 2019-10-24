package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiStatisticsQueryDto implements Serializable {
    private static final long serialVersionUID = -2720159918106116259L;


    //接口地址
    private String apiUrl;


    //调用次数
    private Integer callNum;
}
