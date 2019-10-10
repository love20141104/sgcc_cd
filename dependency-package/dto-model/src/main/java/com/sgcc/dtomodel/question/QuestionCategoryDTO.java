package com.sgcc.dtomodel.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionCategoryDTO implements Serializable ,Comparable<QuestionCategoryDTO>{
    private static final long serialVersionUID = -5867765633296987012L;
    // 问题分类id
    private String categoryId;

    // 问题分类描述
    private String categoryDesc;

    // 排序
    private Integer categoryOrder;

    // 是否可用
    private Boolean categoryAvailable;

    private String categoryDetail;

    @Override
    public int compareTo(QuestionCategoryDTO questionCategoryDTO) {
        return this.categoryOrder.compareTo(questionCategoryDTO.categoryOrder);
    }
}
