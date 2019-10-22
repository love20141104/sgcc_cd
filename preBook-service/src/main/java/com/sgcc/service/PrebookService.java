package com.sgcc.service;

import com.example.Utils;
import com.example.constant.PrebookStartTimeConstants;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.PreBookDao;
import com.sgcc.dao.ServiceHallDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.dtomodel.prebook.ServiceHallPrebookStatusDTO;
import com.sgcc.entity.ServiceHallEntity;
import com.sgcc.entity.event.PrebookEventEntity;
import com.sgcc.entity.query.PrebookQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.PrebookDomainModel;
import com.sgcc.producer.PrebookProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PrebookService {

    @Autowired
    private PrebookEventEntity prebookEventEntity;
    @Autowired
    private PrebookQueryEntity prebookQueryEntity;
    @Autowired
    private PrebookProducer prebookProducer;
    @Autowired
    private ServiceHallEntity serviceHallEntity;


    /**
     * 根据用户id查询所有的预约信息
     *
     * @param openId
     * @return
     */
    public Result getPrebookInfosByUser(String openId) {
        //参数检查 start
        if (Strings.isNullOrEmpty(openId)) {
            return Result.failure(TopErrorCode.GENERAL_ERR, "openId为空");
        }
        //参数检查 end
        List<PrebookDTO> prebookDTOS = this.getPrebookInfos(openId);
        return Result.success(prebookDTOS);
    }

    /**
     * 根据用户id查询所有的预约信息
     *
     * @param openId
     * @return
     */
    private List<PrebookDTO> getPrebookInfos(String openId) {
        //根据用户openid查询该用户近3天的所有预约信息
        List<PreBookDao> preBookDaos = prebookQueryEntity.findAllByUserId(openId);
        //根据preBookDaos构造PrebookDomainModel
        PrebookDomainModel prebookModel = new PrebookDomainModel(preBookDaos);


        List<ServiceHallDao> serviceHallDaos = serviceHallEntity.findHallList();

        //根据preBookDaos构造preBookDTOs
        prebookModel.buildPrebookDTOS(serviceHallDaos);
        //返回prebookDTOS
        return prebookModel.getPrebookDTOS();
    }

    /**
     * 用户提交在线预约
     *
     * @param prebookDTO
     * @param openId
     * @return
     */
    public Result submitPrebookInfo(PrebookDTO prebookDTO, String openId) {
        try {
            //参数检查 start
            if (Strings.isNullOrEmpty(prebookDTO.getServiceHallId())) {
                throw new RuntimeException("serviceHallId为空");
            }
            if (!prebookDTO.getUserId().equals(openId)) {
                throw new RuntimeException("openId:" + openId + ",userId:" + prebookDTO.getUserId() + ",两者不匹配！！");
            }
            if (!PrebookStartTimeConstants.TIME_LIST.contains(prebookDTO.getPrebookStartTime())) {
                throw new RuntimeException("预约时间段错误");
            }
            if (Strings.isNullOrEmpty(prebookDTO.getContact()) || Strings.isNullOrEmpty(prebookDTO.getContactTel())) {
                throw new RuntimeException("联系人相关信息为空");
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

            System.out.println("service:threadID : " + Thread.currentThread().getId());
            //构造PrebookDomainModel
            PrebookDomainModel prebookDomainModel = new PrebookDomainModel(prebookDTO);
            //dto转dao
            prebookDomainModel.buildPrebookDao();
            //用户提交在线预约
            prebookDTO = this.submitPrebookInfo(prebookDomainModel);
            //获取提交后的预约信息列表
            List<PrebookDTO> prebookDTOS = this.getPrebookInfos(openId);

            if (null != prebookDTO) {
                if (!Strings.isNullOrEmpty(prebookDTO.getPrebookCode())) {
                    //该用户预约成功
                    return Result.success(prebookDTOS);
                } else {
                    //预约号为空,该用户预约达到最大次数,或提交过相同预约信息
                    return Result.failure(TopErrorCode.EXCEEDING_LIMIT, prebookDTOS);
                }
            } else {
                //该时段预约已满
                return Result.failure(TopErrorCode.PREBOOK_FULL, prebookDTOS);
            }
        } catch (Exception e) {
            List<PrebookDTO> prebookDTOS = this.getPrebookInfos(openId);
            //未知错误导致的预约失败
            return Result.failure(TopErrorCode.PARAMETER_ERR, prebookDTOS);
        }

    }

    /**
     * 用户提交在线预约
     *
     * @param prebookDomainModel
     * @return
     */
    private PrebookDTO submitPrebookInfo(PrebookDomainModel prebookDomainModel) {
        //用户当天的预约次数是否超过限制
        if (prebookQueryEntity
                .findAllByUserIdAndPrebookDate
                        (
                                //用户openid
                                prebookDomainModel
                                        .getPrebookDTO().getUserId()
                                //预约日期
                                , prebookDomainModel
                                        .getPrebookDTO().getPrebookDate()
                                //预约时间段
                                , prebookDomainModel
                                        .getPrebookDTO().getPrebookStartTime()
                        )
                ) {
            //预约次数超过限制,将预约号置空
            prebookDomainModel.getPrebookDTO().setPrebookCode(null);
            return prebookDomainModel.getPrebookDTO();
        }
        //判断此营业厅该时段预约次数是否超过限制
        if (prebookQueryEntity.findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(
                prebookDomainModel.getPrebookDTO().getServiceHallId()
                , prebookDomainModel.getPrebookDTO().getPrebookDate()
                , prebookDomainModel.getPrebookDTO().getPrebookStartTime())) {
            return null;
        } else {
            synchronized (this) {

                System.out.println("PrebookDomainModel:threadID : " + Thread.currentThread().getId());
                //再次判断此营业厅该时段预约次数是否超过限制
                if (prebookQueryEntity.findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(
                        prebookDomainModel.getPrebookDTO().getServiceHallId()
                        , prebookDomainModel.getPrebookDTO().getPrebookDate()
                        , prebookDomainModel.getPrebookDTO().getPrebookStartTime())) {
                    return null;
                } else {
                    try {
                        //存入redis
                        prebookEventEntity.cacheSubmitPreBookDao(prebookDomainModel.getPreBookDao());
                        //发MQ 持久化
                        prebookProducer.prebookMQ(prebookDomainModel.getPreBookDao());//TODO
                        //返回预约信息
                        return prebookDomainModel.getPrebookDTO();
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.print("预约信息提交失败！！");
                        return null;
                    }

                }
            }
        }
    }

    /**
     * 根据营业亭id查询营业厅预约状态
     *
     * @param serviceHallId
     */
    public Result getPrebookInfosByServiceHall(String serviceHallId, String prebookDate) {
        //参数检查 start
        try {
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            prebookDate = format1.format(format1.parse(prebookDate.trim()));
            if (Strings.isNullOrEmpty(serviceHallId)) {
                throw new RuntimeException("serviceHallId为空");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR, "参数格式不正确");
        }
        //参数检查 end

        List<PreBookDao> preBookDaos = new ArrayList<>(
                //查询该营业厅指定日期的全部预约信息
                prebookQueryEntity.getPrebookInfosByServiceHall(serviceHallId, prebookDate)
        );
        //根据营业厅id和预约的日期构造PrebookDomainModel
        PrebookDomainModel prebookModel = new PrebookDomainModel(preBookDaos);

        List<ServiceHallDao> serviceHallDaos = serviceHallEntity.findHallList();

        //根据preBookDaos构造preBookDTOs
        prebookModel.buildPrebookDTOS(serviceHallDaos);
        //清洗营业厅预约信息,返回营业厅的预约状态
        ServiceHallPrebookStatusDTO serviceHallPrebookStatus = prebookModel.getServiceHallPrebookStatus(serviceHallId, prebookDate);
        return Result.success(serviceHallPrebookStatus);

    }

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
            PreBookDao preBookDao = prebookEventEntity.updatePrebook(prebookDTO);
            if (null != preBookDao) {
                System.out.println("id:" + preBookDao.getId() + " 的预约信息已修改");
            } else {
                System.out.println("修改失败");
                throw new RuntimeException("修改失败");
            }

            //如果redis中存在则更新
            if(null != prebookQueryEntity.findByIdInRedis(preBookDao.getId())){
                prebookEventEntity.cacheSubmitPreBookDao(preBookDao);
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
            //如果redis中存在则删除
            if(null != prebookQueryEntity.findByIdInRedis(id)){
                prebookEventEntity.deleteInRedis(id);
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
     * @param prebookCodes
     * @return
     */
    public Result deletePrebookDTOs(List<String> prebookCodes) {

        try {
            //参数检查 start
            if (prebookCodes.size() < 0)  {
                throw new RuntimeException("prebookCode为空");
            }
            //参数检查 end
            List<String> ids = prebookEventEntity.deletePrebooks(prebookCodes);
            if (ids.size() > 0) {
                System.out.println("id:" + ids + " 的预约信息已删除");
            } else {
                System.out.println("删除失败");
                throw new RuntimeException("删除失败");
            }
            //如果redis中存在则删除
            if(prebookQueryEntity.findByIdsInRedis(ids).size() > 0){
                for (String id : ids){
                    prebookEventEntity.deleteInRedis(id);
                }
            }
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
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
            if(!Strings.isNullOrEmpty(prebook_date_start)){
                prebook_date_start = format1.format(format1.parse(prebook_date_start.trim()));
            }
            if(!Strings.isNullOrEmpty(prebook_date_end)){
                prebook_date_end = format1.format(format1.parse(prebook_date_end.trim()));
            }

            List<PreBookDao> preBookDaos = prebookQueryEntity.getPrebook(user_open_id, service_hall_id,
                    prebook_code, prebook_date_start, prebook_date_end);

            //根据preBookDaos构造PrebookDomainModel
            PrebookDomainModel prebookModel = new PrebookDomainModel(preBookDaos);


            List<ServiceHallDao> serviceHallDaos = serviceHallEntity.findHallList();

            //根据preBookDaos构造preBookDTOs
            prebookModel.buildPrebookDTOS(serviceHallDaos);

            return Result.success(prebookModel.getPrebookDTOS());
        } catch (ParseException e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }


    }

    /**
     * ================================= 预约信息end =======================================
     */
}
