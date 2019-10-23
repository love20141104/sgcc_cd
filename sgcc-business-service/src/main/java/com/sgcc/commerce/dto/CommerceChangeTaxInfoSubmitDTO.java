package com.sgcc.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class CommerceChangeTaxInfoSubmitDTO implements Serializable {
    private static final long serialVersionUID = 3859551880595498765L;
    String user_open_id;
    String new_install_company_name;
    String new_install_address;
    String new_install_license_img;
    String propertyRight_img1;
    String propertyRight_img2;
    String propertyRight_img3;
    String propertyRight_img4;
    String propertyRight_img5;
    String propertyRight_img6;
    String new_install_name;
    String cq_idcard_positive_img;
    String cq_idcard_back_img;
    String new_install_idcard;
    String new_install_telphone;
    String new_install_invoice;
    String invoice_company;
    String invoice_number;
    String invoice_bank;
    String invoice_bank_account;
    String invoice_regist_addr;
    String invoice_phone;
    Date invoice_date;
    String invoice_img;
    String new_install_apply_person;
    String new_install_transactor;
    String sq_idcard_positive_img;
    String sq_idcard_back_img;
    String new_install_transactor_idcard;
    String new_install_transactor_tel;
}