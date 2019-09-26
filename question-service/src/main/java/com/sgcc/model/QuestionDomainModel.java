package com.sgcc.model;

import com.google.common.base.Strings;
import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.question.QADTO;
import com.sgcc.dtomodel.question.QAListDTO;
import com.sgcc.dtomodel.question.QuestionCategoryDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public void build(String categoryId, QAListDTO qaListDTO){
        this.categoryId = categoryId;
        this.qaListDTO = qaListDTO;
    }

    public void buildQuestionAnswerDaos(){
        this.qaListDTO.getQAdtos().forEach(qadto -> {
            questionAnswerDaos = new ArrayList<QuestionAnswerDao>(){{
                add(new QuestionAnswerDao(
                        Strings.isNullOrEmpty(qadto.getId())?UUID.randomUUID().toString():qadto.getId()
                        ,categoryId
                        ,qadto.getQuestionDesc()
                        ,qadto.getAnswer()
                        ,qadto.getCategoryAvailable()
                ));
            }};
        });
    }

}
