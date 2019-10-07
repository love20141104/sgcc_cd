package com.sgcc.dtomodel.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QAnswerDTO implements Serializable {

    private static final long serialVersionUID = -6662442831552356854L;

    private String id;

    private String categoryId;

    private String categoryName;
    // 问题描述
    private String questionDesc;
    // 问题回答
    private String answer;
    // 是否可用
    private Boolean categoryAvailable;
}
