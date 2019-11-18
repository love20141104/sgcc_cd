package com.sgcc.model;

import com.google.common.base.Strings;
import com.sgcc.dao.HotQuestionDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.question.CategrateInsertDTO;
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
    private List<String> hotCategoryIds = new ArrayList<>();


    /**
     *
     * @param questionCategoryDaos
     */
    public CategoryModel(List<QuestionCategoryDao> questionCategoryDaos){
        this.questionCategoryDaos = new ArrayList<>(questionCategoryDaos);
    }


    public CategoryModel(List<QuestionCategoryDao> questionCategoryDaos, List<String> categoryIds){
        this.questionCategoryDaos = new ArrayList<>(questionCategoryDaos);
        this.hotCategoryIds = new ArrayList<>(categoryIds);
    }


    public void buildQuestionCategoryDTOS(){
        this.questionCategoryDTOS = new ArrayList<QuestionCategoryDTO>(){
            private static final long serialVersionUID = -1091276903912351809L;

            {
            questionCategoryDaos.forEach(dao -> add(dao.build()));
        }};
    }

    public void buildHotQuestionCategoryDTOS(){
        this.questionCategoryDTOS.forEach(dto ->{
            if(this.hotCategoryIds.contains(dto.getCategoryId())){
                dto.setHot(true);
            }
        });
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

    public void buildDao(CategrateInsertDTO categrateInsertDTO) {
        String s = UUID.randomUUID().toString();
        this.questionCategoryDaos = new ArrayList<QuestionCategoryDao>(){{
            add(new QuestionCategoryDao(
                    s
                    ,s
                    ,categrateInsertDTO.getCategoryDesc()
                    ,0
                    ,true
                    ,categrateInsertDTO.getCategoryDetail()
            ));
        }};

    }
}
