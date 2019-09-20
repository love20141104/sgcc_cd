package com.sgcc.service;

import com.example.Utils;
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

import java.text.ParseException;
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
    public List<PrebookDTO> getPrebookInfosByUser(String openId) {
        List<PreBookDao> preBookDaos = prebookQueryEntity.findAllByUserId(openId);
        PrebookDomainModel prebookModel = new PrebookDomainModel(preBookDaos);
        List<PrebookDTO> prebookDTOS = prebookModel.getPrebookDTOS();
        return prebookDTOS;
    }

    /**
     * 用户提交在线预约
     * @param prebookDTO
     * @param openId
     * @return
     */
    public Result submitPrebookInfo(PrebookDTO prebookDTO, String openId) {
        try{
            //TODO param check
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
            return Result.failure(TopErrorCode.GENERAL_ERR,"日期格式错误");
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
    public ServiceHallPrebookStatusDTO getPrebookInfosByServiceHall(String serviceHallId, String prebookDate) {

        List<PreBookDao> preBookDaos = new ArrayList<>(prebookQueryEntity.getPrebookInfosByServiceHall(serviceHallId,prebookDate));
        //根据营业厅id和预约的日期构造PrebookDomainModel
        PrebookDomainModel prebookModel = new PrebookDomainModel(preBookDaos);
        //清洗营业厅预约信息,返回营业厅的预约状态
        ServiceHallPrebookStatusDTO serviceHallPrebookStatus = prebookModel.getServiceHallPrebookStatus(serviceHallId,prebookDate);




        return serviceHallPrebookStatus;
    }
}
