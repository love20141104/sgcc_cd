package com.sgcc.entity.query;

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
public class QAQueryEntity {
    @Autowired
    private QAnswerRepository qAnswerRepository;
    @Autowired
    private QARedisRepository qaRedisRepository;
    @Autowired
    private QCategoryRedisRepository qCategoryRedisRepository;
    @Autowired
    private QCategoryRepository qCategoryRepository;

    /**
     * 从mysql中查询可用的问题分类列表
     * @return
     */
    public List<QuestionCategoryDao> getAvailableCategoryList(){
        return qCategoryRepository.findAllQCategory();
    }

    /**
     * 查询所有问题回答
     */
    public List<QuestionAnswerDao> getAvailableQAList(){
        return qAnswerRepository.findAllQAnswer();
    }

    /**
     * 从redis中查询问题分类列表
     * @return
     */
    public List<QuestionCategoryDao> getRedisCategoryList(){
        return qCategoryRedisRepository.findAllByCategoryAvailable(true);
    }

    /**
     * 根据问题分类id从redis中查询问题回答分类列表
     * @return
     */
    public List<QuestionAnswerDao> getRedisQAList(String categoryId){
        return qaRedisRepository.findAllByCategoryIdAndAndCategoryAvailable(categoryId,true);
    }

    /**
     * 查询问题分类
     * @param categoryId
     * @param categoryDesc
     */
    public List<QuestionCategoryDao> selectQuestionCategory(String categoryId, String categoryDesc) {
        return qCategoryRepository.selectQuestionCategory(categoryId,categoryDesc);
    }


}

