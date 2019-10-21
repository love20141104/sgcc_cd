package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class ElectricityTypeDTO implements Serializable {

    private static final long serialVersionUID = -111616434198120547L;

    private String name;

    private double totalNum;    // 合计电量

    private double totalSum;    // 金额


}
