package com.sgcc.entity.event;

import com.google.common.base.Strings;
import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.question.QuestionCategoryDTO;
import com.sgcc.model.CategoryModel;
import com.sgcc.repository.QARedisRepository;
import com.sgcc.repository.QAnswerRepository;
import com.sgcc.repository.QCategoryRedisRepository;
import com.sgcc.repository.QCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class QAEventEntity {
    @Autowired
    private QAnswerRepository qAnswerRepository;
    @Autowired
    private QARedisRepository qaRedisRepository;
    @Autowired
    private QCategoryRedisRepository qCategoryRedisRepository;
    @Autowired
    private QCategoryRepository qCategoryRepository;



    public void innitQuestionCategory(){
        List<QuestionCategoryDao> questionCategoryDaos = qCategoryRepository.findAllQCategory();
        qCategoryRedisRepository.saveAll(questionCategoryDaos);
    }

    public void innitQuestionAnswer(){
        List<QuestionAnswerDao> questionCategoryDaos = qAnswerRepository.findAllQAnswer();
        qaRedisRepository.saveAll(questionCategoryDaos);
    }

    public void innitQuestionAnswer(String categoryId){
        List<QuestionAnswerDao> questionCategoryDaos = qAnswerRepository.findQAnswerByCategoryId(categoryId);
        qaRedisRepository.saveAll(questionCategoryDaos);
    }

    /**
     * 管理系统新增分类
     * @param questionCategoryDaos
     */
    public void insertQuestionCategory(List<QuestionCategoryDao> questionCategoryDaos){

        qCategoryRepository.addQCategory(questionCategoryDaos);
    }

    /**
     * 管理系统修改分类
     * @param questionCategoryDaos
     */
    public void updateQuestionCategory(List<QuestionCategoryDao> questionCategoryDaos) {
        qCategoryRepository.updateQCategory(questionCategoryDaos);

    }

    /**
     * 管理系统作废问题分类
     * @param categoryIds
     */
    public void deleteQuestionCategory(List<String> categoryIds) {
        qCategoryRepository.delQCategory(categoryIds);
    }

    /**
     * 管理工具新增问题信息
     */
    public void insertQA(List<QuestionAnswerDao> questionAnswerDaos){
        qAnswerRepository.addQAnswer(questionAnswerDaos);
    }

    /**
     * 管理工具修改问题信息
     */
    public void updateQA(List<QuestionAnswerDao> questionAnswerDaos){
        qAnswerRepository.updateQAnswer(questionAnswerDaos);
    }


    /**
     * 管理工具作废问题信息
     * @param ids
     */
    public void deleteQA(List<String> ids) {
        qAnswerRepository.delQAnswer(ids);
    }
}
