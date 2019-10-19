package com.sgcc.dtomodel.question;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class CategrateInsertDTO implements Serializable {
    private static final long serialVersionUID = -2953112611047220577L;

    // 问题分类描述
    private String categoryDesc;
    // 问题分类详细描述
    private String categoryDetail;
}
