package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class MonthlyBillsDTO implements Serializable {

    private static final long serialVersionUID = 1256533682857041118L;

    private String userName;

    private String userNo;

    private int billMonth;

    private double startNum;

    private double endNum;

    private double totalNum;    //实用电量

    private double totalSum;    // 电费合计

    private Map<String,ElectricityTypeDTO> Map = new LinkedHashMap<>();



}
