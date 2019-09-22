package com.sgcc.model;

import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.question.QADTO;
import com.sgcc.dtomodel.question.QAListDTO;
import com.sgcc.dtomodel.question.QuestionCategoryDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class QuestionDomainModel {

    private String categoryId;
    private List<QuestionAnswerDao> questionAnswerDaos = new ArrayList<>();
    private QAListDTO qaListDTO;

    public QuestionDomainModel(String categoryId, List<QuestionAnswerDao> questionAnswerDaos){
        this.categoryId = categoryId;
        this.questionAnswerDaos = new ArrayList<>(questionAnswerDaos);
    }

    public void buildQAListDTO(){
        this.qaListDTO = new QAListDTO(this.categoryId);
        this.questionAnswerDaos.forEach(dao ->{
            this.qaListDTO.getQAdtos().add(
                    new QADTO(
                            dao.getId()
                            ,dao.getQuestionDesc()
                            ,dao.getAnswer()
                            ,dao.getCategoryAvailable()
                    )
            );
        });
    }
}
