package com.sgcc.dao;

import com.sgcc.dtomodel.question.QuestionCategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * 常见问题类别
 */
@Data
@AllArgsConstructor //生成全参数构造函数
@NoArgsConstructor  //生成无参构造函数
@RedisHash("question_category")
public class QuestionCategoryDao {
    @Id
    private String id;
    @Indexed
    // 问题分类id
    private String categoryId;

    // 问题分类描述
    private String categoryDesc;

    // 排序
    private Integer categoryOrder;
    @Indexed
    // 是否可用
    private Boolean categoryAvailable;

    public QuestionCategoryDTO build(){
        return new QuestionCategoryDTO(
                this.categoryId
                ,this.categoryDesc
                ,this.categoryOrder
                ,this.categoryAvailable
        );
    }

}
