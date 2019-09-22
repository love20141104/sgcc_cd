package com.sgcc.entity.event;

import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.repository.QARedisRepository;
import com.sgcc.repository.QAnswerRepository;
import com.sgcc.repository.QCategoryRedisRepository;
import com.sgcc.repository.QCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

}
