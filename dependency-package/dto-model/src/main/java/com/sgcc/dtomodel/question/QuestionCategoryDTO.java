package com.sgcc.dtomodel.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class QuestionCategoryDTO implements Serializable ,Comparable<QuestionCategoryDTO>{
    private static final long serialVersionUID = -5867765633296987012L;
    // 问题分类id
    private String categoryId;

    // 问题分类描述
    private String categoryDesc;

    // 排序
    private Integer categoryOrder = 0;

    // 是否可用
    private Boolean categoryAvailable;

    private String categoryDetail;

    public QuestionCategoryDTO(String categoryId
            , String categoryDesc
            , Integer categoryOrder
            , Boolean categoryAvailable
            , String categoryDetail) {
        this.categoryId = categoryId;
        this.categoryDesc = categoryDesc;
        this.categoryOrder = categoryOrder;
        this.categoryAvailable = categoryAvailable;
        this.categoryDetail = categoryDetail;
        this.isHot =false;
    }

    private boolean isHot = false;

    @Override
    public int compareTo(QuestionCategoryDTO questionCategoryDTO) {
        if(this.categoryOrder.compareTo(questionCategoryDTO.categoryOrder) == 0){
            return this.categoryDesc.compareTo(questionCategoryDTO.categoryDesc);
        }
        return this.categoryOrder.compareTo(questionCategoryDTO.categoryOrder);
    }
}
