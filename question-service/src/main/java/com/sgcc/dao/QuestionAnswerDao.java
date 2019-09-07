package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 常见问题
 */
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
public class QuestionAnswerDao {

    // 问题id
    private String id;

    // 问题类别id
    private String categoryId;

    // 问题描述
    private String questionDesc;

    // 问题回答
    private String answer;

    // 是否可用
    private Boolean categoryAvailable;

}
