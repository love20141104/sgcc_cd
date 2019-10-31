package com.sgcc.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor  //生成无参构造函数
@AllArgsConstructor
public class CommerceIncreaseCapacityUpdateDTO implements Serializable {
    private static final long serialVersionUID = -2768189117712127898L;
    private String id;
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
    private String invoice_img;              // 一般纳税人
    private String in_transactor_tel;
    private String sq_attorney_img;


}
