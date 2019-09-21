package com.sgcc.service;

import com.example.Utils;
import com.example.constant.PrebookStartTimeConstants;
import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.PreBookDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.dtomodel.prebook.ServiceHallPrebookStatusDTO;
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
public class ProbookService {

    @Autowired
    private PrebookEventEntity prebookEventEntity;
    @Autowired
    private PrebookQueryEntity prebookQueryEntity;
    @Autowired
    private PrebookProducer prebookProducer;
    /**
     * 根据用户id查询所有的预约信息
     * @param openId
     * @return
     */
    public Result getPrebookInfosByUser(String openId) {
        //参数检查 start
         if(Strings.isNullOrEmpty(openId)){
             return Result.failure(TopErrorCode.GENERAL_ERR,"openId为空");
         }
        //参数检查 end
        List<PreBookDao> preBookDaos = prebookQueryEntity.findAllByUserId(openId);
        PrebookDomainModel prebookModel = new PrebookDomainModel(preBookDaos);
        List<PrebookDTO> prebookDTOS = prebookModel.getPrebookDTOS();
        return Result.success(prebookDTOS);
    }

    /**
     * 用户提交在线预约
     * @param prebookDTO
     * @param openId
     * @return
     */
    public Result submitPrebookInfo(PrebookDTO prebookDTO, String openId) {
        try{
            //参数检查 start
            if(Strings.isNullOrEmpty(prebookDTO.getServiceHallId())){
                throw new RuntimeException("serviceHallId为空");
            }if (!prebookDTO.getUserId().equals(openId)){
                throw new RuntimeException("openId:"+openId+",userId:"+prebookDTO.getUserId()+",两者不匹配！！");
            }if(!PrebookStartTimeConstants.TIME_LIST.contains(prebookDTO.getPrebookStartTime())){
                throw new RuntimeException("预约时间段错误");
            }if(Strings.isNullOrEmpty(prebookDTO.getContact()) ||Strings.isNullOrEmpty(prebookDTO.getContactTel())){
                throw new RuntimeException("联系人相关信息为空");
            }
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            format1.parse(prebookDTO.getPrebookDate());
            //参数检查 end

            System.out.println("service:threadID : "+Thread.currentThread().getId());
            PrebookDomainModel prebookDomainModel = new PrebookDomainModel(prebookDTO);
            //用户提交在线预约
            prebookDTO = this.submitPrebookInfo(prebookDomainModel);
            if(null != prebookDTO){
                if(!Strings.isNullOrEmpty(prebookDTO.getPrebookCode())){
                    return Result.success(prebookDTO);
                }else {
                    return Result.failure(TopErrorCode.GENERAL_ERR,"超过最大预约次数");
                }
            }else {
                return Result.failure(TopErrorCode.GENERAL_ERR,"该时间预约已满");
            }
        }catch (ParseException e){
            return Result.failure(TopErrorCode.GENERAL_ERR,"参数错误 ");
        }

    }

    /**
     * 用户提交在线预约
     * @param prebookDomainModel
     * @return
     */
    private PrebookDTO submitPrebookInfo(PrebookDomainModel prebookDomainModel){
        if(prebookQueryEntity.findAllByUserIdAndPrebookDate(prebookDomainModel.getPrebookDTO().getUserId(),prebookDomainModel.getPrebookDTO().getPrebookDate())){
            prebookDomainModel.getPrebookDTO().setPrebookCode(null);
            return prebookDomainModel.getPrebookDTO();
        }
        if (prebookQueryEntity.findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(
                prebookDomainModel.getPrebookDTO().getServiceHallId()
                , prebookDomainModel.getPrebookDTO().getPrebookDate()
                , prebookDomainModel.getPrebookDTO().getPrebookStartTime())){
            return null;
        } else {
            synchronized (this) {
                System.out.println("PrebookDomainModel:threadID : " + Thread.currentThread().getId());
                //取出数量
                if (prebookQueryEntity.findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(
                        prebookDomainModel.getPrebookDTO().getServiceHallId()
                        , prebookDomainModel.getPrebookDTO().getPrebookDate()
                        , prebookDomainModel.getPrebookDTO().getPrebookStartTime())) {
                    return null;
                } else {
                    try {
                        //存入redis
                        prebookEventEntity.cacheSubmitPreBookDao(prebookDomainModel.getPreBookDao());//TODO
                        //TODO 发MQ 持久化
                        prebookProducer.prebookMQ(prebookDomainModel.getPreBookDao());//TODO
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
     * @param serviceHallId
     */
    public Result getPrebookInfosByServiceHall(String serviceHallId, String prebookDate) {
        //参数检查
        try{
            DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            format1.parse(prebookDate);
            if(Strings.isNullOrEmpty(serviceHallId)){
                throw new RuntimeException("serviceHallId为空");
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR,"参数格式不正确");
        }

        List<PreBookDao> preBookDaos = new ArrayList<>(prebookQueryEntity.getPrebookInfosByServiceHall(serviceHallId,prebookDate));
        //根据营业厅id和预约的日期构造PrebookDomainModel
        PrebookDomainModel prebookModel = new PrebookDomainModel(preBookDaos);
        //清洗营业厅预约信息,返回营业厅的预约状态
        ServiceHallPrebookStatusDTO serviceHallPrebookStatus = prebookModel.getServiceHallPrebookStatus(serviceHallId,prebookDate);

        return Result.success(serviceHallPrebookStatus);

    }
}
