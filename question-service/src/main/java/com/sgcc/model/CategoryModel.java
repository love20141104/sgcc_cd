package com.sgcc.model;

import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.question.QuestionCategoryDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class CategoryModel {
    private List<QuestionCategoryDTO> questionCategoryDTOS = new ArrayList<>();
    private List<QuestionCategoryDao> questionCategoryDaos = new ArrayList<>();



    public CategoryModel(List<QuestionCategoryDao> questionCategoryDaos){
        this.questionCategoryDaos = new ArrayList<>(questionCategoryDaos);
    }

    public void buildQuestionCategoryDTOS(){
        this.questionCategoryDTOS = new ArrayList<QuestionCategoryDTO>(){{
            questionCategoryDaos.forEach(dao ->{
                add(dao.build());
            });
        }};
    }
}
