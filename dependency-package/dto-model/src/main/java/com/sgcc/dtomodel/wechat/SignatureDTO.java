package com.sgcc.dtomodel.wechat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SignatureDTO implements Serializable {
    private static final long serialVersionUID = -5760408591640273446L;

    private String noncestr;
    private String jsapi_ticket;
    private String timestamp;
    private String URL;
    private String appId;
    private String signature;

}
