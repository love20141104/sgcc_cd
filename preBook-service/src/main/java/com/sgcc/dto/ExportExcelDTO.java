package com.sgcc.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.sgcc.dao.PreBookHouseholdDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
@ExcelTarget(value = "ExportExcelDTO")
@NoArgsConstructor
@AllArgsConstructor
public class ExportExcelDTO implements Serializable {
    private static final long serialVersionUID = 7723666291147177187L;
    @Excel(name = "工单号",orderNum = "0",width = 20,isImportField = "true_ticket",needMerge = true)
    private String prebookNo;

    @Excel(name = "办理业务",orderNum = "1",isImportField = "true_ticket",needMerge = true)
    private String businessTypeName;

    @Excel(name = "营业厅网点",orderNum = "2",isImportField = "true_ticket",needMerge = true)
    private String serviceHallName;

    @Excel(name = "电费年月",orderNum = "3",needMerge = true)
    private String ticketMonth;

    @Excel(name = "预约号",orderNum = "4",needMerge = true)
    private String lineupNo;

    @Excel(name = "预约时间",orderNum = "5",width = 20,needMerge = true)
    private String prebookDate;

    @Excel(name = "联系人",orderNum = "6",needMerge = true)
    private String contact;

    @Excel(name = "联系方式",orderNum = "7",needMerge = true)
    private String contactTel;

    @Excel(name = "提交时间",orderNum = "8",width = 20,databaseFormat = "yyyy-MM-dd HH:mm:ss", format = "yyyy-MM-dd HH:mm:ss",needMerge = true)
    private Date submitDate;

    @Excel(name = "预约状态",orderNum = "9",replace={"审核中_1","已受理_2","驳回_3"},needMerge = true)
    private Integer status;

    @Excel(name = "审核人",orderNum = "10",needMerge = true)
    private String checker;

    @Excel(name = "审核时间",orderNum = "11",width = 20,databaseFormat = "yyyy-MM-dd HH:mm:ss", format = "yyyy-MM-dd HH:mm:ss",needMerge = true)
    private Date checkDate;

    @Excel(name = "驳回原因",orderNum = "12", isWrap = true,needMerge = true)
    private String rejectReason;

    @Excel(name = "是否打印",orderNum = "13", replace = {"是_true","否_false"},needMerge = true)
    private Boolean isPrinted;

    @Excel(name = "是否在黑名单",orderNum = "14",replace = {"是_true","否_false"},needMerge = true)
    private Boolean isBlackList;

//    @Excel(name = "是否已取消预约",orderNum = "15",replace = {"是_true","否_false"},needMerge = true)
//    private Boolean isCancel;

//    @Excel(name = "取消预约时间",orderNum = "16",width = 20,databaseFormat = "yyyy-MM-dd HH:mm:ss", format = "yyyy-MM-dd HH:mm:ss",needMerge = true)
//    private Date cancelDate;

    @Excel(name = "工单完成状态",orderNum = "17",replace = {"未取票_0","已取票_1"},needMerge = true)
    private Integer ticketStatus;

    @ExcelCollection(name = "户号信息", orderNum = "18")
    private List<HouseHoldDTO> dtos;
}
