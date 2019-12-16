package com.sgcc.model;

import com.google.common.base.Strings;
import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionAnswerDetailDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dto.QuestionAnswerDTO;
import com.sgcc.dto.QuestionAnswerDetailDTO;
import com.sgcc.dto.QuestionAnwserListDTO;
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


    public List<QuestionAnwserListDTO> getAllQuestionAnwsersListTrans(List<QuestionAnswerDao> questionAnswerDaos,
                                               List<QuestionCategoryDao> questionCategoryDaos) {
        List<QuestionAnwserListDTO> questionAnwserListDTOS = new ArrayList<>();

        for (int i = 0; i < questionCategoryDaos.size(); i++) {
            List<QuestionAnswerDTO> list = new ArrayList<>();
            for (int j = 0; j < questionAnswerDaos.size(); j++) {
                if (questionCategoryDaos.get(i).getCategoryId().equals(questionAnswerDaos.get(j).getCategoryId())){
                    list.add(new QuestionAnswerDTO(
                            questionAnswerDaos.get(j).getId(),
                            questionAnswerDaos.get(j).getQuestionDesc()
                    ));
                }
            }
            questionAnwserListDTOS.add(new QuestionAnwserListDTO(questionCategoryDaos.get(i).getCategoryDesc(),
                    questionCategoryDaos.get(i).getId(),list));
        }
        return questionAnwserListDTOS;
    }


    public List<QuestionAnswerDetailDTO> QuestionAnwsersDetailTrans(List<QuestionAnswerDetailDao> questionAnswerDetailDaos) {
        List<QuestionAnswerDetailDTO> questionAnswerDetailDTOS = new ArrayList<>();
        questionAnswerDetailDaos.forEach(dao->{
            questionAnswerDetailDTOS.add(new QuestionAnswerDetailDTO(
                    dao.getCategoryName(),
                    dao.getQuestionDesc(),
                    dao.getAnswer()
            ));
        });
        return questionAnswerDetailDTOS;
    }


}
