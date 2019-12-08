package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSubscribeDao implements Serializable {

    private static final long serialVersionUID = -3191679164828729329L;
    private String id;
    private String openid;
    private String nickname;
    private Integer sex;
    private String city;
    private String headimgurl;
    private Integer is_sub_bill;
    private Integer is_sub_pay;
    private Integer is_sub_notice_pay;
    private Integer is_sub_analysis;



}
