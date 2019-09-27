package com.sgcc.dtomodel.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QAListDTO implements Serializable {

    private static final long serialVersionUID = -5668981305865303145L;
    // 问题类别id
    private String categoryId;

    private List<QADTO> QAdtos = new ArrayList<>();

    public QAListDTO(String categoryId){
        this.categoryId = categoryId;
    }
}
