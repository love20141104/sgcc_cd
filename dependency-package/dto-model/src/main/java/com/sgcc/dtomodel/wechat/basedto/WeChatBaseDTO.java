package com.sgcc.dtomodel.wechat.basedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeChatBaseDTO implements Serializable {

    private static final long serialVersionUID = 5843567000663057884L;
    private int errcode;
    private String errmsg;
}
