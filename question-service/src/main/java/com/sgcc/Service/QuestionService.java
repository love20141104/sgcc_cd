package com.sgcc.Service;

import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.question.QAListDTO;
import com.sgcc.dtomodel.question.QuestionCategoryDTO;
import com.sgcc.entity.event.QAEventEntity;
import com.sgcc.entity.query.QAQueryEntity;
import com.sgcc.model.CategoryModel;
import com.sgcc.model.QuestionDomainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QAQueryEntity qaQueryEntity;
    @Autowired
    private QAEventEntity qaEventEntity;


    /**
     * 将mysql中的常见问题信息全部存入redis
     */
    public void initCategory(){
//        try{
//            qaEventEntity.innitQuestionCategory();
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new RuntimeException("常见问题初始化失败");
//        }
    }

    /**
     * 将mysql中的常见问题信息全部存入redis
     */
    public void initQuestion(){
//        try{
//            qaEventEntity.innitQuestionAnswer();
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new RuntimeException("常见问题初始化失败");
//        }
    }


    /**
     * 查询问题分类列表
     *
     * @return
     */
    public List<QuestionCategoryDTO> getQuestionCategory() {
        List<QuestionCategoryDao> questionCategoryDaos = qaQueryEntity.getRedisCategoryList();
        //如果redis中没取到,从mysql中查出并存入redis
        if(null == questionCategoryDaos || questionCategoryDaos.size() == 0 || null == questionCategoryDaos.get(0)){
            qaEventEntity.innitQuestionCategory();
            questionCategoryDaos = qaQueryEntity.getRedisCategoryList();
        }
        CategoryModel categoryModel = new CategoryModel(questionCategoryDaos);
        categoryModel.buildQuestionCategoryDTOS();
        return categoryModel.getQuestionCategoryDTOS();
    }

    /**
     * 根据问题分类id查询QA
     *
     * @param categoryId
     * @return
     */
    public QAListDTO getQAList(String categoryId) {
        List<QuestionAnswerDao> questionAnswerDaos = qaQueryEntity.getRedisQAList(categoryId);
        //如果redis中没取到,从mysql中查出并存入redis
        if(null == questionAnswerDaos || questionAnswerDaos.size() == 0 || null == questionAnswerDaos.get(0)){
            qaEventEntity.innitQuestionAnswer(categoryId);
            questionAnswerDaos = qaQueryEntity.getRedisQAList(categoryId);
        }
        QuestionDomainModel questionDomainModel = new QuestionDomainModel(categoryId, questionAnswerDaos);
        questionDomainModel.buildQAListDTO();
        return questionDomainModel.getQaListDTO();
    }
}
