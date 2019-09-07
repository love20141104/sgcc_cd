package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务类型
 */
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class BusinessTypeDao {

    private String id;

    // 业务类型id
    private String businessTypeId;

    // 业务类型
    private String businessType;

    // 排序
    private Integer order;

    // 是否可用
    private Boolean businessTypeAvailable;

}
