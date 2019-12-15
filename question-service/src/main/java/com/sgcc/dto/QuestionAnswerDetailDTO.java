package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 常见问题
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerDetailDTO implements Serializable {


    private static final long serialVersionUID = -4481752428496642081L;
    private String categoryName;

    // 问题描述
    private String questionDesc;

    // 问题回答
    private String answer;


}
