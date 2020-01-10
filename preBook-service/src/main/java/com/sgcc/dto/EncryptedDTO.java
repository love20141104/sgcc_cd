package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncryptedDTO implements Serializable {

    private static final long serialVersionUID = 7830774012375059662L;
    private String appId;       // 渠道编号
    private String data;
    private String signature;   // 签名
}
