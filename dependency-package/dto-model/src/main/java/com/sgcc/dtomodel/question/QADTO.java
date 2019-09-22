package com.sgcc.dtomodel.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QADTO implements Serializable {
    private static final long serialVersionUID = -5932623003362487212L;

    private String id;
    // 问题描述
    private String questionDesc;
    // 问题回答
    private String answer;
    // 是否可用
    private Boolean categoryAvailable;
}
