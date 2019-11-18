package com.sgcc.entity.query;

import com.sgcc.dao.HotQuestionDao;
import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.question.QAnswerDTO;
import com.sgcc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QAQueryEntity {
    @Autowired
    private QAnswersRepository qAnswersRepository;
    @Autowired
    private QARedisRepository qaRedisRepository;
    @Autowired
    private QCategoryRedisRepository qCategoryRedisRepository;
    @Autowired
    private QCategorysRepository qCategorysRepository;
    @Autowired
    private HotCategoryRepositry hotCategoryRepositry;

    /**
     * 从mysql中查询可用的问题分类列表
     * @return
     */
    public List<QuestionCategoryDao> getAvailableCategoryList(){
        return qCategorysRepository.findAllQCategory();
    }

    /**
     * 查询所有问题回答
     */
    public List<QAnswerDTO> getAvailableQAList(String category_name, String question_desc, String answer){
        return qAnswersRepository.findQAnswerList(category_name,question_desc,answer);
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
    public List<QuestionCategoryDao> selectQuestionCategory(String categoryId, String categoryDesc,boolean available) {
        return qCategorysRepository.selectQuestionCategory(categoryId,categoryDesc,available);
    }


    /**
     * 获取各个问题分类的使用次数
     * @return
     */
    public List<String> getHotQuuestion(){
        List<String> categoryIds = hotCategoryRepositry.getHotQuuestion();
        return categoryIds;
    }
}

