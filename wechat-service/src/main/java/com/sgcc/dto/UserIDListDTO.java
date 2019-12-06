package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserIDListDTO implements Serializable {

    private static final long serialVersionUID = -7510413521484202348L;

    private Integer total;

    private Integer count;

    private WxOpenIdInfoDTO data;

    private String next_openid;

}
