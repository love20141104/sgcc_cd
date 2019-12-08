package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeDao implements Serializable {

    private static final long serialVersionUID = -2935298822473178655L;

    private String id;
    private String user_open_id;
    private String nick_name;
    private int sex;
    private String city;
    private String head_img_url;
    private Integer is_sub_bill;
    private Integer is_sub_pay;
    private Integer is_sub_notice_pay;
    private Integer is_sub_analysis;

}
