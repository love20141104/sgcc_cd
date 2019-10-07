package com.sgcc.entity.event;

import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.repository.QARedisRepository;
import com.sgcc.repository.QAnswersRepository;
import com.sgcc.repository.QCategoryRedisRepository;
import com.sgcc.repository.QCategorysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QAEventEntity {
    @Autowired
    private QAnswersRepository qAnswersRepository;
    @Autowired
    private QARedisRepository qaRedisRepository;
    @Autowired
    private QCategoryRedisRepository qCategoryRedisRepository;
    @Autowired
    private QCategorysRepository qCategorysRepository;



    public void innitQuestionCategory(){
        List<QuestionCategoryDao> questionCategoryDaos = qCategorysRepository.findAllQCategory();
        qCategoryRedisRepository.saveAll(questionCategoryDaos);
    }

    public void innitQuestionAnswer(){
        List<QuestionAnswerDao> questionCategoryDaos = qAnswersRepository.findAllQAnswer();
        qaRedisRepository.saveAll(questionCategoryDaos);
    }

    public void innitQuestionAnswer(String categoryId){
        List<QuestionAnswerDao> questionCategoryDaos = qAnswersRepository.findQAnswerByCategoryId(categoryId);
        qaRedisRepository.saveAll(questionCategoryDaos);
    }

    /**
     * 管理系统新增分类
     * @param questionCategoryDaos
     */
    public void insertQuestionCategory(List<QuestionCategoryDao> questionCategoryDaos){

        qCategorysRepository.addQCategory(questionCategoryDaos);
    }

    /**
     * 管理系统修改分类
     * @param questionCategoryDaos
     */
    public void updateQuestionCategory(List<QuestionCategoryDao> questionCategoryDaos) {
        qCategorysRepository.updateQCategory(questionCategoryDaos);

    }

    /**
     * 管理系统作废问题分类
     * @param categoryIds
     */
    public void deleteQuestionCategory(List<String> categoryIds) {
        qCategorysRepository.delQCategory(categoryIds);
    }

    /**
     * 管理工具新增问题信息
     */
    public void insertQA(List<QuestionAnswerDao> questionAnswerDaos){
        qAnswersRepository.addQAnswer(questionAnswerDaos);
    }

    /**
     * 管理工具修改问题信息
     */
    public void updateQA(List<QuestionAnswerDao> questionAnswerDaos){
        qAnswersRepository.updateQAnswer(questionAnswerDaos);
    }


    /**
     * 管理工具作废问题信息
     * @param ids
     */
    public void deleteQA(List<String> ids) {
        qAnswersRepository.delQAnswer(ids);
    }
}
