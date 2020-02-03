package com.sgcc.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@ExcelTarget(value = "HouseHoldDTO")
@NoArgsConstructor
@AllArgsConstructor
public class HouseHoldDTO implements Serializable {

    private static final long serialVersionUID = -676415209789413402L;
    @Excel(name = "户名",orderNum = "0", width = 30)
    private String householdName;
    @Excel(name = "户号",orderNum = "1", width = 30)
    private String householdNumber;
    @Excel(name = "是否是批量号",orderNum = "2", width = 30,replace = {"是_true","否_false"})
    private Boolean isBatchNumber;

}
