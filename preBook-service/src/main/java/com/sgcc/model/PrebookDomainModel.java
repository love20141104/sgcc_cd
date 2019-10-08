package com.sgcc.model;

import com.example.Utils;
import com.example.constant.PrebookStartTimeConstants;
import com.google.common.base.Strings;
import com.sgcc.dao.PreBookDao;

import com.sgcc.dao.ServiceHallDao;
import com.sgcc.dtomodel.prebook.PrebookDTO;
import com.sgcc.dtomodel.prebook.PrebookStartTimeDTO;
import com.sgcc.dtomodel.prebook.ServiceHallPrebookStatusDTO;
import com.sgcc.utils.DateUtils;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@NoArgsConstructor
@Data
public class PrebookDomainModel {
    List<PrebookDTO> prebookDTOS = new ArrayList<>();
    List<PreBookDao> preBookDaos = new ArrayList<>();
    PrebookDTO prebookDTO;
    PreBookDao preBookDao;

    /**
     * 根据营业厅id和预约的日期构造PrebookDomainModel 或 根据用户的openId构造
     *
     * @param preBookDaos
     */
    public PrebookDomainModel(List<PreBookDao> preBookDaos) {
        this.preBookDaos = preBookDaos;
    }

    /**
     * 根据preBookDaos构造preBookDTOs
     */
    public void buildPrebookDTOS(List<ServiceHallDao> serviceHallDaos) {
        this.prebookDTOS = new ArrayList<>();
        for (PreBookDao preBookDao : this.preBookDaos) {
            for (ServiceHallDao serviceHallDao : serviceHallDaos) {
                if (serviceHallDao.getServiceHallId().equals(preBookDao.getServiceHallId())) {
                    prebookDTOS.add(new PrebookDTO(
                            preBookDao.getUserId()
                            , preBookDao.getServiceHallId()
                            , preBookDao.getPrebookDate()
                            , preBookDao.getPrebookStartTime()
                            , preBookDao.getPrebookCode()
                            , preBookDao.getContact()
                            , preBookDao.getContactTel()
                            , preBookDao.getSubmitDate()
                    ).buildServiceHallName(serviceHallDao.getServiceHallName()));
                    break;
                }
            }
            Collections.sort(this.prebookDTOS);
        }
    }
    /**
     * 用户提交预约信息时，构造PrebookDomainModel
     *
     * @param prebookDTO
     */
    public PrebookDomainModel(PrebookDTO prebookDTO) {
        this.prebookDTO = prebookDTO;
    }

    /**
     * 将PrebookDTO转换成PreBookDao
     */
    public void buildPrebookDao() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //TODO 编码规则
        if(Strings.isNullOrEmpty(this.prebookDTO.getPrebookCode())){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            Random ra =new Random();
            String s = "Y" + formatter.format(new Date()) + (ra.nextInt(100000)|+1);
            this.prebookDTO.setPrebookCode(s);
        }

        this.prebookDTO.setSubmitDate(simpleDateFormat2.format(new Date()));
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
     * 清洗营业厅预约信息,返回营业厅的预约状态
     */
    public ServiceHallPrebookStatusDTO getServiceHallPrebookStatus(String serviceHallId, String prebookDate) {
        ServiceHallPrebookStatusDTO serviceHallPrebookStatusDTO = new ServiceHallPrebookStatusDTO(serviceHallId, prebookDate);
        this.prebookDTOS.forEach(dto -> {
            PrebookStartTimeDTO prebookStartTimeDTO = new PrebookStartTimeDTO(dto.getPrebookStartTime());
            //更新营业厅预约信息
            serviceHallPrebookStatusDTO.buildPrebookStartTimeDTOS(prebookStartTimeDTO);
        });
        //补全没有预约信息的时段
        Map<String,PrebookStartTimeDTO> map = serviceHallPrebookStatusDTO.buildNullPrebookStartTimeDTOS();
        ServiceHallPrebookStatusDTO serviceHallPrebookStatusDTO1 = new ServiceHallPrebookStatusDTO(sortPrebookStartTime(map,prebookDate));

        return serviceHallPrebookStatusDTO1;

    }

    /**
     * 在线预约时间段排序
     */
    public Map<String,PrebookStartTimeDTO> sortPrebookStartTime(Map<String,PrebookStartTimeDTO> map,String prebookDate){
        Map<String,PrebookStartTimeDTO> map2 = new LinkedHashMap<>();
        List<String> timeList = PrebookStartTimeConstants.TIME_LIST;
        for (String str : timeList) {
            String maxTime = prebookDate+" "+str.substring(str.length()-5,str.length())+":00";
            System.out.println("当前时间段最大时间："+maxTime);
            if (Utils.GetCurTime().before(Utils.GetDate(maxTime))){
                for(Map.Entry<String, PrebookStartTimeDTO> entry : map.entrySet()) {
                    if (entry.getKey().equals(str)){
                        map2.put(entry.getKey(),entry.getValue());
                    }

                }
            }

        }
        return map2;
    }





}
