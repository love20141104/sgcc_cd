package com.sgcc.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoViewDTO implements Serializable {
    private static final long serialVersionUID = 2982259712037565402L;
    private String openid;
    private String nickname;
    private String sex;
    private String city;
    private String headimgurl;
}
