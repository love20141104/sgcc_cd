package com.sgcc.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor  //生成无参构造函数
public class CommerceIncreaseCapacityDetailDTO implements Serializable {
    private static final long serialVersionUID = -4788799188392273837L;
//    private String id;
//    private String openId;
//    private String companyName;
//    private Double currentCapacity;
//    private String name;
//    private String idcard;
//    private String contactTel;
//    private String licenseImg;      // 营业执照
//    private String aplicant;        // 申请人
//    private String transactor;      // 代办人
//    private String transactorIdcard;
//    private Boolean invoiceFlag;        // 是否开发票
//    private String invoiceNum;        // 纳税人识别号
//    private String invoiceBank;        // 开户银行
//    private String invoiceBankAccount;        // 银行账号
//    private String invoiceRegistAddr;        // 注册地址
//    private String invoiceContactTel;        // 联系电话
//    private Date invoiceDate;        // 开征起始日期
//    private String securitiesImg1;   // 产权证明
//    private String securitiesImg2;
//    private String securitiesImg3;
//    private String securitiesImg4;   // 产权证明
//    private String securitiesImg5;
//    private String securitiesImg6;
//    private String cqIdcardPositiveImg;     // 法人
//    private String cqIdcardBackImg;
//    private String sqIdcardPositiveImg;     // 代办人
//    private String sqIdcardBackImg;
//    private String invoiceImg;              // 一般纳税人资格证明
//    private Date submitDate;



    private String id;
    private String user_open_id;
    private String in_company_name;
    private Double in_current_capacity;
    private String in_name;
    private String in_idcard;
    private String in_telphone;
    private String in_license_img;      // 营业执照
    private String in_apply_person;        // 申请人
    private String in_transactor;      // 代办人
    private String in_transactor_idcard;
    private Boolean in_invoice;        // 是否开发票
    private String invoice_number;        // 纳税人识别号
    private String invoice_bank;        // 开户银行
    private String invoice_bank_account;        // 银行账号
    private String invoice_regist_addr;        // 注册地址
    private String invoice_phone;        // 联系电话
    private String invoice_date;        // 开征起始日期
    private String propertyRight_img1;
    private String propertyRight_img2;
    private String propertyRight_img3;
    private String propertyRight_img4;
    private String propertyRight_img5;
    private String propertyRight_img6;
    private String cq_idcard_positive_img;     // 法人
    private String cq_idcard_back_img;
    private String sq_idcard_positive_img;
    private String sq_idcard_back_img;
    private String invoice_img;              // 一般纳税人资格证明
    private String in_submit_date;
    private String in_transactor_tel;
    private String sq_attorney_img;

    public CommerceIncreaseCapacityDetailDTO(String id, String user_open_id, String in_company_name,
                                             Double in_current_capacity, String in_name, String in_idcard,
                                             String in_telphone, String in_license_img, String in_apply_person,
                                             String in_transactor, String in_transactor_idcard, Boolean in_invoice,
                                             String invoice_number, String invoice_bank, String invoice_bank_account,
                                             String invoice_regist_addr, String invoice_phone, String invoice_date,
                                             String propertyRight_img1, String propertyRight_img2,
                                             String propertyRight_img3, String propertyRight_img4,
                                             String propertyRight_img5, String propertyRight_img6,
                                             String cq_idcard_positive_img, String cq_idcard_back_img,
                                             String sq_idcard_positive_img, String sq_idcard_back_img,
                                             String invoice_img, String in_submit_date,String in_transactor_tel,String sq_attorney_img) {
        this.id = id;
        this.user_open_id = user_open_id;
        this.in_company_name = in_company_name;
        this.in_current_capacity = in_current_capacity;
        this.in_name = in_name;
        this.in_idcard = in_idcard;
        this.in_telphone = in_telphone;
        this.in_license_img = in_license_img;
        this.in_apply_person = in_apply_person;
        this.in_transactor = in_transactor;
        this.in_transactor_idcard = in_transactor_idcard;
        this.in_invoice = in_invoice;
        this.invoice_number = invoice_number;
        this.invoice_bank = invoice_bank;
        this.invoice_bank_account = invoice_bank_account;
        this.invoice_regist_addr = invoice_regist_addr;
        this.invoice_phone = invoice_phone;
        this.invoice_date = invoice_date;
        this.propertyRight_img1 = propertyRight_img1;
        this.propertyRight_img2 = propertyRight_img2;
        this.propertyRight_img3 = propertyRight_img3;
        this.propertyRight_img4 = propertyRight_img4;
        this.propertyRight_img5 = propertyRight_img5;
        this.propertyRight_img6 = propertyRight_img6;
        this.cq_idcard_positive_img = cq_idcard_positive_img;
        this.cq_idcard_back_img = cq_idcard_back_img;
        this.sq_idcard_positive_img = sq_idcard_positive_img;
        this.sq_idcard_back_img = sq_idcard_back_img;
        this.invoice_img = invoice_img;
        this.in_submit_date = in_submit_date;
        this.in_transactor_tel = in_transactor_tel;
        this.sq_attorney_img = sq_attorney_img;
    }
}
