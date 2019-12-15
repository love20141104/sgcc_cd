package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnwserListDTO implements Serializable {

    private static final long serialVersionUID = 8053208832818062728L;

    private String categoryName;

    private String categoryId;

    private List<QuestionAnswerDTO> questionAnswerDTOS;


}
