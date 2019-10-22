package com.sgcc.inhabitant.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class InhabitantRenameDao implements Serializable {
    private static final long serialVersionUID = -4514878156199973011L;
    private String id;
}
