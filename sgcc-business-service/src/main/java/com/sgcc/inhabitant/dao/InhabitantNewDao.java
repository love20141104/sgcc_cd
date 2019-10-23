package com.sgcc.inhabitant.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class InhabitantNewDao implements Serializable {
    private static final long serialVersionUID = -7166299354706325648L;
    String id;
    String user_open_id;
    String new_install_district;
    String new_install_address;
    String new_install_capacity;
    String propertyRight_img1;
    String propertyRight_img2;
    String propertyRight_img3;
    String propertyRight_img4;
    String propertyRight_img5;
    String propertyRight_img6;
    String cq_idcard_positive_img;
    String cq_idcard_back_img;
    String new_install_name;
    String new_install_idcard;
    String new_install_telphone;
    String new_install_apply_person;
    String new_install_transactor;
    String sq_idcard_positive_img;
    String sq_idcard_back_img;
    String new_install_transactor_idcard;
    String new_install_transactor_tel;
    Date submit_date;
}
