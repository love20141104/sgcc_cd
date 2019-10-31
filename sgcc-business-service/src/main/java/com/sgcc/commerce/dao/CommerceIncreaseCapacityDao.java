package com.sgcc.commerce.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor  //生成无参构造函数
@AllArgsConstructor
public class CommerceIncreaseCapacityDao implements Serializable {
    private static final long serialVersionUID = -4788799188392273837L;
    private String id;
    private String openId;
    private String companyName;
    private Double currentCapacity;
    private String name;
    private String idcard;
    private String contactTel;
    private String licenseImg;      // 营业执照
    private String aplicant;        // 申请人
    private String transactor;      // 代办人
    private String transactorIdcard;
    private String transactorTel;
    private Boolean invoiceFlag;        // 是否开发票
    private String invoiceNum;        // 纳税人识别号
    private String invoiceBank;        // 开户银行
    private String invoiceBankAccount;        // 银行账号
    private String invoiceRegistAddr;        // 注册地址
    private String invoiceContactTel;        // 联系电话
    private Date invoiceDate;        // 开征起始日期
    private String securitiesImg1;   // 产权证明
    private String securitiesImg2;
    private String securitiesImg3;
    private String securitiesImg4;   // 产权证明
    private String securitiesImg5;
    private String securitiesImg6;
    private String cqIdcardPositiveImg;     // 法人
    private String cqIdcardBackImg;
    private String sqIdcardPositiveImg;     // 代办人
    private String sqIdcardBackImg;
    private String invoiceImg;              // 一般纳税人资格证明
    private String sqAttorneyImg;           // 申请人委托书
    private Date submitDate;



}
