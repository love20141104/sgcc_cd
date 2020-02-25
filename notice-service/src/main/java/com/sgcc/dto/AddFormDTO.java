package com.sgcc.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ExcelTarget(value = "AddFormDTO")
public class AddFormDTO implements Serializable {

    private static final long serialVersionUID = 2219396935452655443L;

    @Excel(name = "停电区域",orderNum = "1")
    private String noticeDistrict;

    @Excel(name = "停电类型",orderNum = "0")
    private String typeName;

    @Excel(name = "停电时间",orderNum = "2")
    private String noticeDate;

    @Excel(name = "停电范围",orderNum = "3")
    private String range;

}
