package com.sgcc.model;

import com.google.common.base.Strings;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.question.QuestionCategoryDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class CategoryModel {
    private List<QuestionCategoryDTO> questionCategoryDTOS = new ArrayList<>();
    private List<QuestionCategoryDao> questionCategoryDaos = new ArrayList<>();



    public CategoryModel(List<QuestionCategoryDao> questionCategoryDaos){
        this.questionCategoryDaos = new ArrayList<>(questionCategoryDaos);
    }

    public void buildQuestionCategoryDTOS(){
        this.questionCategoryDTOS = new ArrayList<QuestionCategoryDTO>(){
            private static final long serialVersionUID = -1091276903912351809L;

            {
            questionCategoryDaos.forEach(dao -> add(dao.build()));
        }};
    }

    public void build(List<QuestionCategoryDTO> questionCategoryDTOS){
        this.questionCategoryDTOS = new ArrayList<>(questionCategoryDTOS);
    }

    public void buildQuestionCategoryDaos(){
        this.questionCategoryDaos = new ArrayList<QuestionCategoryDao>(){
            private static final long serialVersionUID = 3689412611824961231L;

            {
            questionCategoryDTOS.forEach(dto-> add(new QuestionCategoryDao(
                    UUID.randomUUID().toString()
                    , Strings.isNullOrEmpty(dto.getCategoryId())?UUID.randomUUID().toString():dto.getCategoryId()
                    ,dto.getCategoryDesc()
                    ,dto.getCategoryOrder()
                    ,dto.getCategoryAvailable()
                    ,dto.getCategoryDetail()
            )));
        }};
    }

}
