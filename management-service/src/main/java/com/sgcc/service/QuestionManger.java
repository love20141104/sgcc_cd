package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.question.QADTO;
import com.sgcc.dtomodel.question.QAListDTO;
import com.sgcc.dtomodel.question.QuestionCategoryDTO;
import com.sgcc.entity.event.QAEventEntity;
import com.sgcc.entity.query.QAQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.CategoryModel;
import com.sgcc.model.QuestionDomainModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionManger {
    @Autowired
    private QAEventEntity qaEventEntity;
    @Autowired
    private QAQueryEntity qaQueryEntity;
    /**
     * =============================== 常见问题start =======================================
     */

    /**
     * 增加问题分类
     */

    public Result insertQuestionCategory(QuestionCategoryDTO questionCategoryDTO) {
        try {
            List<QuestionCategoryDao> questionCategoryDaos = transform(questionCategoryDTO);
            qaEventEntity.insertQuestionCategory(questionCategoryDaos);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }

    /**
     * 修改问题分类
     */
    public Result updateQuestionCategory(QuestionCategoryDTO questionCategoryDTO) {
        try {
            List<QuestionCategoryDao> questionCategoryDaos = transform(questionCategoryDTO);
            qaEventEntity.updateQuestionCategory(questionCategoryDaos);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }

    }

    /**
     * 数据转换 dto2dao
     *
     * @param questionCategoryDTO
     * @return
     */
    private List<QuestionCategoryDao> transform(QuestionCategoryDTO questionCategoryDTO) {
        CategoryModel categoryModel = new CategoryModel();
        List<QuestionCategoryDTO> questionCategoryDTOS = new ArrayList<QuestionCategoryDTO>() {{
            add(questionCategoryDTO);
        }};
        categoryModel.build(questionCategoryDTOS);
        categoryModel.buildQuestionCategoryDaos();
        return categoryModel.getQuestionCategoryDaos();
    }


    /**
     * 作废问题分类
     */
    public Result deleteQuestionCategory(List<String> categoryIds) {
        try {
            qaEventEntity.deleteQuestionCategory(categoryIds);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }

    /**
     * 查询问题分类
     */
    public Result selectQuestionCategory(
            String categoryId
            , String categoryDesc
    ) {
        try {
            List<QuestionCategoryDao> questionCategoryDaos = qaQueryEntity.selectQuestionCategory(categoryId, categoryDesc);
            CategoryModel categoryModel = new CategoryModel(questionCategoryDaos);
            categoryModel.buildQuestionCategoryDTOS();
            return Result.success(categoryModel.getQuestionCategoryDTOS());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }

    /**
     * 增加QA信息
     */

    public Result insertQA(String categoryId, QADTO qadto) {
        try {
            List<QuestionAnswerDao> questionAnswerDaos = transform(categoryId, qadto);
            qaEventEntity.insertQA(questionAnswerDaos);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }

    /**
     * 修改QA信息
     */
    public Result updateQA(String categoryId, QADTO qadto) {
        try {

            List<QuestionAnswerDao> questionAnswerDaos = transform(categoryId, qadto);
            qaEventEntity.updateQA(questionAnswerDaos);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }

    /**
     * 数据转换 dto2dao
     *
     * @param categoryId
     * @param qadto
     * @return
     */
    private List<QuestionAnswerDao> transform(String categoryId, QADTO qadto) {
        QAListDTO qaListDTO = new QAListDTO(categoryId);
        qaListDTO.getQAdtos().add(qadto);
        QuestionDomainModel questionDomainModel = new QuestionDomainModel();
        questionDomainModel.build(categoryId, qaListDTO);
        questionDomainModel.buildQuestionAnswerDaos();
        return questionDomainModel.getQuestionAnswerDaos();
    }

    /**
     * 作废QA信息
     */
    public Result deleteQA(List<String> ids) {
        try {
            qaEventEntity.deleteQA(ids);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }

    /**
     * =============================== 常见问题end =========================================
     */

}
