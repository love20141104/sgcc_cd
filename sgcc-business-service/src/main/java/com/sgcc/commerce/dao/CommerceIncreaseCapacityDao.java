package com.sgcc.commerce.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor  //生成无参构造函数
public class CommerceIncreaseCapacityDao implements Serializable {
    private static final long serialVersionUID = -4788799188392273837L;
    private String id;
//    private String orderNo;         // 订单号
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

    private Boolean invoiceFlag;        // 是否开发票
    private String invoiceNum;        // 纳税人识别号
    private String invoiceBank;        // 开户银行
    private String invoiceBankAccount;        // 银行账号
    private String invoiceRegistAddr;        // 注册地址
    private String invoiceContactTel;        // 联系电话
    private String invoiceDate;        // 开征起始日期

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
    private Date submitDate;


    public CommerceIncreaseCapacityDao(String id, String openId, String companyName,
                                       Double currentCapacity, String name, String idcard, String contactTel,
                                       String licenseImg, String aplicant, String transactor, String transactorIdcard,
                                       Boolean invoiceFlag, String invoiceNum, String invoiceBank,
                                       String invoiceBankAccount, String invoiceRegistAddr, String invoiceContactTel,
                                       String invoiceDate, String securitiesImg1, String securitiesImg2,
                                       String securitiesImg3, String securitiesImg4, String securitiesImg5,
                                       String securitiesImg6, String cqIdcardPositiveImg, String cqIdcardBackImg,
                                       String sqIdcardPositiveImg, String sqIdcardBackImg, String invoiceImg,
                                       Date submitDate) {
        this.id = id;
//        this.orderNo = orderNo;
        this.openId = openId;
        this.companyName = companyName;
        this.currentCapacity = currentCapacity;
        this.name = name;
        this.idcard = idcard;
        this.contactTel = contactTel;
        this.licenseImg = licenseImg;
        this.aplicant = aplicant;
        this.transactor = transactor;
        this.transactorIdcard = transactorIdcard;
        this.invoiceFlag = invoiceFlag;
        this.invoiceNum = invoiceNum;
        this.invoiceBank = invoiceBank;
        this.invoiceBankAccount = invoiceBankAccount;
        this.invoiceRegistAddr = invoiceRegistAddr;
        this.invoiceContactTel = invoiceContactTel;
        this.invoiceDate = invoiceDate;
        this.securitiesImg1 = securitiesImg1;
        this.securitiesImg2 = securitiesImg2;
        this.securitiesImg3 = securitiesImg3;
        this.securitiesImg4 = securitiesImg4;
        this.securitiesImg5 = securitiesImg5;
        this.securitiesImg6 = securitiesImg6;
        this.cqIdcardPositiveImg = cqIdcardPositiveImg;
        this.cqIdcardBackImg = cqIdcardBackImg;
        this.sqIdcardPositiveImg = sqIdcardPositiveImg;
        this.sqIdcardBackImg = sqIdcardBackImg;
        this.invoiceImg = invoiceImg;
        this.submitDate = submitDate;


    }
}
