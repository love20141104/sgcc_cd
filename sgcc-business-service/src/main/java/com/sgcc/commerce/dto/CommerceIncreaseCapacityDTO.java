package com.sgcc.commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class CommerceIncreaseCapacityDTO implements Serializable {
    private static final long serialVersionUID = -2768189117712127898L;
    private String id;
}
