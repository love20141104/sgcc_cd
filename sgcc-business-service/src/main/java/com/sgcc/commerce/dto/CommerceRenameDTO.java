package com.sgcc.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
@Data
public class CommerceRenameDTO implements Serializable {

    private static final long serialVersionUID = -7684290543431166635L;
    private String id;
}
