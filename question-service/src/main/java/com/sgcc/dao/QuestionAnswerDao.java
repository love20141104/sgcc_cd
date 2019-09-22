package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * 常见问题
 */
@Wither
@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("question_answer")
public class QuestionAnswerDao {

    // 问题id
    @Id
    private String id;
    @Indexed
    // 问题类别id
    private String categoryId;

    // 问题描述
    private String questionDesc;

    // 问题回答
    private String answer;
    @Indexed
    // 是否可用
    private Boolean categoryAvailable;

}
