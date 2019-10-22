package com.sgcc.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class CommerceChangeTaxInfoDTO implements Serializable {
    private static final long serialVersionUID = -1118931362688312140L;
    private String id;
}
