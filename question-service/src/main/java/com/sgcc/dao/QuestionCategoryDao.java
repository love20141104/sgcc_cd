package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 常见问题类别
 */
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class QuestionCategoryDao {

    private String id;

    // 问题分类id
    private String categoryId;

    // 问题分类描述
    private String categoryDesc;

    // 排序
    private Integer categoryOrder;

    // 是否可用
    private Boolean categoryAvailable;

}
