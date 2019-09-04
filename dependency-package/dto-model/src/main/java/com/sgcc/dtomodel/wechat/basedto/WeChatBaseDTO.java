package com.sgcc.dtomodel.wechat.basedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeChatBaseDTO implements Serializable {

    private int errcode;
    private String errmsg;
}
