package com.sgcc.model;

import com.example.constant.PrebookStartTimeConstants;
import com.sgcc.dao.PreBookDao;

import com.sgcc.dto.PrebookInfoSaveDTO;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.dtomodel.prebook.PrebookStartTimeDTO;
import com.sgcc.dtomodel.prebook.ServiceHallPrebookStatusDTO;
import com.sgcc.entity.event.PrebookEventEntity;
import com.sgcc.entity.query.PrebookQueryEntity;
import com.sgcc.producer.PrebookProducer;
import com.sgcc.repository.PrebookRedisRepository;
import com.sgcc.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class PrebookDomainModel  {
//    @Autowired
//    private PrebookQueryEntity prebookQueryEntity;// = new PrebookQueryEntity();
//    @Autowired
//    private PrebookEventEntity prebookEventEntity;// = new PrebookEventEntity();
//    @Autowired
//    private PrebookProducer prebookProducer;// = new PrebookProducer();

    List<PrebookDTO> prebookDTOS = new ArrayList<>();
    List<PreBookDao> preBookDaos = new ArrayList<>();
    PrebookDTO prebookDTO;
    PreBookDao preBookDao;

    /**
     * 根据营业厅id和预约的日期构造PrebookDomainModel 或 根据用户的openId构造
     * @param preBookDaos
     */
    public PrebookDomainModel(List<PreBookDao> preBookDaos) {
        this.prebookDTOS = null;//todo
        this.preBookDaos = preBookDaos;
        this.prebookDTOS = new ArrayList<PrebookDTO>() {{
            preBookDaos.forEach(preBookDao -> {
                add(new PrebookDTO(
                        preBookDao.getUserId()
                        , preBookDao.getServiceHallId()
                        , preBookDao.getPrebookDate()
                        , preBookDao.getPrebookStartTime()
                        , preBookDao.getPrebookCode()
                        , preBookDao.getContact()
                        , preBookDao.getContactTel()
                        , preBookDao.getSubmitDate()
                ));
            });
        }};

    }

//    public PrebookDomainModel dto2dao(List<PrebookDTO> prebookDTOS) {
//        this.prebookDTOS = prebookDTOS;
//
//        this.preBookDaos = new ArrayList<PreBookDao>() {{
//            prebookDTOS.forEach(prebookDTO -> {
//                //TODO 编码规则
//                prebookDTO.setPrebookCode(UUID.randomUUID().toString());
//                add(
//                        new PreBookDao(
//                                DateUtils.getSeconds() + (DateUtils.daysBetweenTwoDate(new Date(), prebookDTO.getPrebookDate()) * 24 * 3600)
//                                , UUID.randomUUID().toString()
//                                , prebookDTO.getUserId()
//                                , prebookDTO.getServiceHallId()
//                                , prebookDTO.getPrebookDate()
//                                , prebookDTO.getPrebookStartTime()
//                                , prebookDTO.getPrebookCode()
//                                , prebookDTO.getContact()
//                                , prebookDTO.getContactTel()
//                                , prebookDTO.getSubmitDate()
//                        )
//
//                );
//            });
//
//        }};
//        return this;
//    }

//    /**
//     * 查询
//     * @param preBookDao
//     */
//    public PrebookDomainModel(PreBookDao preBookDao){
//        this.preBookDao = preBookDao;
//        this.prebookDTO = new PrebookDTO(
//                preBookDao.getUserId()
//                , preBookDao.getServiceHallId()
//                , preBookDao.getPrebookDate()
//                , preBookDao.getPrebookStartTime()
//                , preBookDao.getPrebookCode()
//                , preBookDao.getContact()
//                , preBookDao.getContactTel()
//                , preBookDao.getSubmitDate()
//        );
//    }

    /**
     * 用户提交预约信息时，将PrebookDTO转换成PreBookDao
     * @param prebookDTO
     */
    public PrebookDomainModel(PrebookDTO prebookDTO) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        prebookDTO.setPrebookCode(UUID.randomUUID().toString());
        prebookDTO.setSubmitDate(new Date());
        this.prebookDTO = prebookDTO;
        this.preBookDao = new PreBookDao(
                DateUtils.getSeconds() + (DateUtils.daysBetweenTwoDate(new Date(), simpleDateFormat.parse(prebookDTO.getPrebookDate())) * 24 * 3600)
                , UUID.randomUUID().toString()
                , prebookDTO.getUserId()
                , prebookDTO.getServiceHallId()
                , prebookDTO.getPrebookDate()
                , prebookDTO.getPrebookStartTime()
                , prebookDTO.getPrebookCode()
                , prebookDTO.getContact()
                , prebookDTO.getContactTel()
                , prebookDTO.getSubmitDate()
        );
    }




    /**
     * 用户提交在线预约
     * @return
     */
//    public PrebookDTO submitPrebookInfo() {
//        //check 预约次数
//        if(prebookQueryEntity.findAllByUserIdAndPrebookDate(prebookDTO.getUserId(),prebookDTO.getPrebookDate())){
//            prebookDTO.setPrebookCode(null);
//            return prebookDTO;
//        }
//        if (prebookQueryEntity.findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(
//                prebookDTO.getServiceHallId()
//                , prebookDTO.getPrebookDate()
//                , prebookDTO.getPrebookStartTime())){
//            return null;
//        } else {
//            synchronized (this) {
//                System.out.println("PrebookDomainModel:threadID : " + Thread.currentThread().getId());
//                //取出数量
//                if (prebookQueryEntity.findAllByServiceHallIdAndPrebookDateAndPrebookStartTime(
//                        prebookDTO.getServiceHallId()
//                        , prebookDTO.getPrebookDate()
//                        , prebookDTO.getPrebookStartTime())) {
//                    return null;
//                } else {
//                    try {
//                        //存入redis
//                        prebookEventEntity.cacheSubmitPreBookDao(preBookDao);//TODO
//                        //TODO 发MQ 持久化
//                        prebookProducer.prebookMQ(preBookDao);//TODO
//                        return prebookDTO;
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        System.out.print("预约信息提交失败！！");
//                        return null;
//                    }
//
//                }
//            }
//        }
//
//
//    }


    /**
     * 清洗营业厅预约信息,返回营业厅的预约状态
     */
    public ServiceHallPrebookStatusDTO getServiceHallPrebookStatus(String serviceHallId,String prebookDate) {
        ServiceHallPrebookStatusDTO serviceHallPrebookStatusDTO = new ServiceHallPrebookStatusDTO(serviceHallId,prebookDate);
        this.prebookDTOS.forEach(dto ->{
            PrebookStartTimeDTO prebookStartTimeDTO = new PrebookStartTimeDTO(dto.getPrebookStartTime());
            //更新营业厅预约信息
            serviceHallPrebookStatusDTO.buildPprebookStartTimeDTOS(prebookStartTimeDTO);
        });
        //补全没有预约信息的时段
        serviceHallPrebookStatusDTO.buildNullPprebookStartTimeDTOS();
        return serviceHallPrebookStatusDTO;

    }
}
