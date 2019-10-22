package com.sgcc.commerce.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class CommerceIncreaseCapacityDao implements Serializable {
    private static final long serialVersionUID = -4788799188392273837L;
    private String id;
}
