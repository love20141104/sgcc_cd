package com.sgcc.Service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.HotQuestionDao;
import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionAnswerDetailDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dto.QuestionAnswerDetailDTO;
import com.sgcc.dto.QuestionAnwserListDTO;
import com.sgcc.dtomodel.question.*;
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
        List<String> categoryIds = qaQueryEntity.getHotQuuestion();
        CategoryModel categoryModel = new CategoryModel(questionCategoryDaos,categoryIds);
        categoryModel.buildQuestionCategoryDTOS();
        categoryModel.buildHotQuestionCategoryDTOS();
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

    /**
     * =============================== 常见问题start =======================================
     */

    /**
     * 增加问题分类
     */

    public Result insertQuestionCategory(CategrateInsertDTO categrateInsertDTO) {
        try {
            //参数判断
            if(Strings.isNullOrEmpty(categrateInsertDTO.getCategoryDesc())||Strings.isNullOrEmpty(categrateInsertDTO.getCategoryDetail())){
                throw new RuntimeException("参数为空");
            }
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.buildDao(categrateInsertDTO);
            List<QuestionCategoryDao> questionCategoryDaos = categoryModel.getQuestionCategoryDaos();
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
            CategoryModel categoryModel = new CategoryModel();
            List<QuestionCategoryDTO> questionCategoryDTOS = new ArrayList<QuestionCategoryDTO>() {{
                add(questionCategoryDTO);
            }};
            categoryModel.build(questionCategoryDTOS);
            categoryModel.buildQuestionCategoryDaos();

            List<QuestionCategoryDao> questionCategoryDaos = categoryModel.getQuestionCategoryDaos();
            qaEventEntity.updateQuestionCategory(questionCategoryDaos);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }

    }

//    /**
//     * 数据转换 dto2dao
//     *
//     * @param questionCategoryDTO
//     * @return
//     */
//    private List<QuestionCategoryDao> transform(QuestionCategoryDTO questionCategoryDTO) {
//        CategoryModel categoryModel = new CategoryModel();
//        List<QuestionCategoryDTO> questionCategoryDTOS = new ArrayList<QuestionCategoryDTO>() {{
//            add(questionCategoryDTO);
//        }};
//        categoryModel.build(questionCategoryDTOS);
//        categoryModel.buildQuestionCategoryDaos();
//        return categoryModel.getQuestionCategoryDaos();
//    }


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
    public Result selectQuestionCategory(String categoryId, String categoryDesc ,boolean available) {
        try {
            List<QuestionCategoryDao> questionCategoryDaos = qaQueryEntity.selectQuestionCategory(categoryId, categoryDesc,available);
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
     * 查询QA信息
     */
    public Result selectQADTO(String category_name, String question_desc,String answer) {
        try {
            List<QAnswerDTO> questionAnswerDaos = qaQueryEntity.getAvailableQAList(category_name,question_desc,answer);
            return Result.success(questionAnswerDaos);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }

    /**
     * 查询所有问题列表
     * @param keyword
     * @return
     */
    public Result getAllQuestionAnwsersList(String keyword) {

        try {
            List<QuestionAnswerDao> questionAnswerDaos = qaQueryEntity.findAllQAnswer(keyword);
            List<QuestionCategoryDao> questionCategoryDaos =  qaQueryEntity.findQCategorys();
            QuestionDomainModel model = new QuestionDomainModel();
            List<QuestionAnwserListDTO> dtos = model.getAllQuestionAnwsersListTrans(questionAnswerDaos,questionCategoryDaos);
            if (dtos.size() > 0)
                return Result.success(dtos);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 查询所有问题信息
     * @param id
     * @return
     */
    public Result getAllQuestionAnwsersDetail(String id) {
        if (Strings.isNullOrEmpty(id))
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        try {
            List<QuestionAnswerDetailDao> questionAnswerDetailDaos = qaQueryEntity.findAllQAnswerDetail(id);
            QuestionDomainModel model = new QuestionDomainModel();
            List<QuestionAnswerDetailDTO> detailDTOS=model.QuestionAnwsersDetailTrans(questionAnswerDetailDaos);
            if (detailDTOS.size() > 0)
                return Result.success(detailDTOS);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * =============================== 常见问题end =========================================
     */
}
