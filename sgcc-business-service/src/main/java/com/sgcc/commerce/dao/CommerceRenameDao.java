package com.sgcc.commerce.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class CommerceRenameDao implements Serializable {
    private static final long serialVersionUID = -268544149015202872L;
    private String id;
}
