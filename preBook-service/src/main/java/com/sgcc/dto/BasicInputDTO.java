package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInputDTO implements Serializable {

    private static final long serialVersionUID = -7688530321312953104L;

    private String appId;       // 渠道编号
    private InputDataDTO data;
    private String signature;   // 签名
}
