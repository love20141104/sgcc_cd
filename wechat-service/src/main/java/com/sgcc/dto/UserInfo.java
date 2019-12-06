package com.sgcc.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private String subscribe;
    private String openid;
    private String nickname;
    private String sex;
    private String language;
    private String city;
    private String province;
    private String country;
    private String headimgurl;
    private String subscribe_time;
    private String unionid;
    private String remark;
    private String groupid;
    private String tagid_list;
    private String subscribe_scene;
    private String qr_scene;
    private String qr_scene_str;
}
