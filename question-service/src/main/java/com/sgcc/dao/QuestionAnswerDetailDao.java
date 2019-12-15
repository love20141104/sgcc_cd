package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

/**
 * 常见问题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerDetailDao implements Serializable {
    private static final long serialVersionUID = -5305322832633688504L;

    // 问题id

    private String id;

    // 问题类别id
    private String categoryId;

    private String categoryName;

    // 问题描述
    private String questionDesc;

    // 问题回答
    private String answer;

    // 是否可用
    private Boolean categoryAvailable;

}
