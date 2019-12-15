package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerDTO implements Serializable {

    private static final long serialVersionUID = 3874407420607285968L;

    private String id;

    private String questionDesc;


}
