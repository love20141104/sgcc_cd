package com.sgcc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@ApiModel(description = "税票预约提交内容")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrebookInfoSubmitDTO implements Serializable {
    @ApiModelProperty(hidden=true)
    @JsonIgnore
    private static final long serialVersionUID = -4050132859628546270L;

    @ApiModelProperty(value = "公众号用户标识",name = "userOpenId",example = "ownrvsmAtCVxZezBovJSO8SY1Srk")
    private String userOpenId;

    @ApiModelProperty(value = "取票年月",name = "ticketMonth",dataType = "List")
    private List<String> ticketMonth;

    @ApiModelProperty(value = "业务类型id",name = "businessTypeId",example = "123")
    private String businessTypeId;

    @ApiModelProperty(value = "业务类型名称",name = "businessTypeName",example = "税票预约")
    private String businessTypeName;

    @ApiModelProperty(value = "营业厅id",name = "serviceHallId",example = "123456")
    private String serviceHallId;

    @ApiModelProperty(value = "营业厅名称",name = "serviceHallName",example = "高新芳草营业厅")
    private String serviceHallName;

    @ApiModelProperty(value = "户号信息",name = "householdNos",dataType = "List")
    private List<HouseHoldDTO> householdNos;

    @ApiModelProperty(value = "联系人",name = "contact",example = "张三")
    private String contact;

    @ApiModelProperty(value = "联系电话",name = "contactTel",example = "18582106276")
    private String contactTel;

    @ApiModelProperty(value = "预约取票年月日",name = "day",example = "2020-02-04")
    private String day;

    @ApiModelProperty(value = "预约取票时间段",name = "prebookDate",example = "10:30~11:30")
    private String prebookDate;


}
