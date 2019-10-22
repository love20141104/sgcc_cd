package com.sgcc.commerce.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class CommerceChangeTaxInfoDao implements Serializable {
    private static final long serialVersionUID = 7915805112297299845L;
    private String id;
}
