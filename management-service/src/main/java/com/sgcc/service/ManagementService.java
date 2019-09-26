package com.sgcc.service;

import com.example.constant.PrebookStartTimeConstants;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.QuestionAnswerDao;
import com.sgcc.dao.QuestionCategoryDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.dtomodel.question.QADTO;
import com.sgcc.dtomodel.question.QAListDTO;
import com.sgcc.dtomodel.question.QuestionCategoryDTO;
import com.sgcc.entity.event.PrebookEventEntity;
import com.sgcc.entity.event.QAEventEntity;
import com.sgcc.entity.query.PrebookQueryEntity;
import com.sgcc.entity.query.QAQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.CategoryModel;
import com.sgcc.model.QuestionDomainModel;
import com.sgcc.producer.ManagementProducer;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagementService {

    @Autowired
    private ProbookService probookService;
    @Autowired
    private PrebookEventEntity prebookEventEntity;
    @Autowired
    private PrebookQueryEntity prebookQueryEntity;

    @Autowired
    private QAEventEntity qaEventEntity;
    @Autowired
    private QAQueryEntity qaQueryEntity;




    /**
     *  ================================= 预约信息start =================================
     */

    /**
     * 后台管理系统修改预约信息
     *
     * @param prebookCode
     * @param prebookDTO
     * @return
     */
    public Result updatePrebookDTO(String prebookCode, PrebookDTO prebookDTO) {
        try {
            //参数检查 start
            if (null == prebookDTO) {
                throw new RuntimeException("prebookDTO为空");
            }
            if (Strings.isNullOrEmpty(prebookCode) || Strings.isNullOrEmpty(prebookDTO.getPrebookCode())) {
                throw new RuntimeException("prebookCode为空");
            }
            if (!prebookDTO.getPrebookCode().equals(prebookCode)) {
                throw new RuntimeException("prebookCode:" + prebookCode + ",prebookCode:" + prebookDTO.getPrebookCode() + ",两者不匹配！！");
            }
            if (Strings.isNullOrEmpty(prebookDTO.getServiceHallId())) {
                throw new RuntimeException("serviceHallId为空");
            }
            if (!PrebookStartTimeConstants.TIME_LIST.contains(prebookDTO.getPrebookStartTime())) {
                throw new RuntimeException("预约时间段错误");
            }
            if (Strings.isNullOrEmpty(prebookDTO.getContact()) || Strings.isNullOrEmpty(prebookDTO.getContactTel())) {
                throw new RuntimeException("联系人相关信息为空");
            }
            if (Strings.isNullOrEmpty(prebookDTO.getUserId())) {
                throw new RuntimeException("userid为空");
            }
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            String S = format1.format(format1.parse(prebookDTO.getPrebookDate().trim()));
            prebookDTO.setPrebookDate(S);
            prebookDTO.setPrebookStartTime(prebookDTO.getPrebookStartTime().trim());
            prebookDTO.setContact(prebookDTO.getContact().trim());
            prebookDTO.setUserId(prebookDTO.getUserId().trim());
            prebookDTO.setContactTel(prebookDTO.getContactTel().trim());
            prebookDTO.setServiceHallId(prebookDTO.getServiceHallId().trim());
            //参数检查 end
            //改mysql
            String id = prebookEventEntity.updatePrebook(prebookDTO);
            if (!Strings.isNullOrEmpty(id)) {
                System.out.println("id:" + id + " 的预约信息已修改");
            } else {
                System.out.println("修改失败");
                throw new RuntimeException("修改失败");
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);

        }
    }

    /**
     * 后台管理系统作废预约信息
     *
     * @param prebookCode
     * @return
     */
    public Result deletePrebookDTO(String prebookCode) {

        try {
            //参数检查 start
            if (Strings.isNullOrEmpty(prebookCode)) {
                throw new RuntimeException("prebookCode为空");
            }
            //参数检查 end
            String id = prebookEventEntity.deletePrebook(prebookCode);
            if (!Strings.isNullOrEmpty(id)) {
                System.out.println("id:" + id + " 的预约信息已删除");
            } else {
                System.out.println("删除失败");
                throw new RuntimeException("删除失败");
            }
            return Result.success();
        } catch (Exception e) {
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }

    /**
     * 后台管理系统增加预约信息
     *
     * @param prebookDTO
     * @param openId
     * @return
     */
    public Result insertPrebookDTO(PrebookDTO prebookDTO, String openId) {

        return probookService.submitPrebookInfo(prebookDTO, openId);
    }

    /**
     * 后台管理系统查询预约信息
     *
     * @param user_open_id
     * @param service_hall_id
     * @param prebook_code
     * @param prebook_date_start
     * @param prebook_date_end
     * @return
     */
    public Result selectPrebookDTO(
            String user_open_id
            , String service_hall_id
            , String prebook_code
            , String prebook_date_start
            , String prebook_date_end
    ) {
        try {
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            prebook_date_start = format1.format(format1.parse(prebook_date_start.trim()));
            prebook_date_end = format1.format(format1.parse(prebook_date_end.trim()));
            return Result.success(prebookQueryEntity.getPrebook(user_open_id, service_hall_id, prebook_code, prebook_date_start, prebook_date_end));
        } catch (ParseException e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }


    }
    /**
     * ================================= 预约信息end =======================================
     */

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
