package com.sgcc.service;

import com.example.Utils;
import com.example.result.Result;
import com.sgcc.dao.ApiDescDao;
import com.sgcc.dao.ApiStatisticsDao;
import com.sgcc.dto.ApiStatisticsActiveDto;
import com.sgcc.entity.ApiStatisticsEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.repository.ApiDescRepository;
import com.sgcc.sgccenum.DatetypeEnum;
import com.sgcc.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ApiStatisticsService {
    @Autowired
    private ApiStatisticsEntity apiStatisticsEntity;
    @Autowired
    private ApiDescRepository apiDescRepository;

    public void saveApiStatistics(ApiStatisticsDao apiStatisticsDao){
        ApiDescDao apiDesc = apiDescRepository.getApiDesc(apiStatisticsDao.getRequestURI(), apiStatisticsDao.getRequestMethod());
        if(null!=apiDesc) {
            apiStatisticsDao.setApiUrlDesc(apiDesc.getRequestDesc());
        }
        apiStatisticsEntity.saveApiStatistics(apiStatisticsDao);
    }
    public Result getApiStatisticsQuery(String visit_date_begin, String visit_date_end){
        return  Result.success(apiStatisticsEntity.getApiStatisticsQuery(visit_date_begin,visit_date_end));
    }

    public Result getApiStatistics(String visit_date_begin, String visit_date_end) {
        return  Result.success(apiStatisticsEntity.getApiStatistics(visit_date_begin,visit_date_end));
    }

    public Result getApiStatisticsOpenIdNum() {
        ApiStatisticsActiveDto apiStatisticsActiveDto = new ApiStatisticsActiveDto();
        Integer todayOpenIdNum = apiStatisticsEntity.getApiStatisticsOpenIdNum(Utils.GetTime(DateUtil.getToday0()), Utils.GetTime(DateUtil.getToday24()));
        Integer yesterdayOpenIdNum = apiStatisticsEntity.getApiStatisticsOpenIdNum(Utils.GetTime(DateUtil.getYesterday0()), Utils.GetTime(DateUtil.getYesterday24()));
        Integer lastweekOpenIdNum = apiStatisticsEntity.getApiStatisticsOpenIdNum(Utils.GetTime(DateUtil.getLastweek0()), Utils.GetTime(DateUtil.getLastweek24()));
        apiStatisticsActiveDto.setUsernum(todayOpenIdNum);
        apiStatisticsActiveDto.setQoq(divide(todayOpenIdNum,yesterdayOpenIdNum));
        apiStatisticsActiveDto.setYoy(divide(todayOpenIdNum,lastweekOpenIdNum));
        return  Result.success(apiStatisticsActiveDto);
    }


    public Double divide(Integer i1,Integer i2){
        BigDecimal bi1 = new BigDecimal(i1.toString());
        BigDecimal bi2 = new BigDecimal(i2.toString());
        BigDecimal divide = bi1.divide(bi2, 4, RoundingMode.HALF_UP);
        return divide.doubleValue();
    }

    public Result getApiStatistcsDateDtoList(String tpye){
        if(DatetypeEnum.MONTH.name().equalsIgnoreCase(tpye)){
            return  Result.success(apiStatisticsEntity.getApiStatistcsMonthDtoList());
        }
        if(DatetypeEnum.WEEK.name().equalsIgnoreCase(tpye)){
            return  Result.success(apiStatisticsEntity.getApiStatistcsWeekDtoList());
        }
        if(DatetypeEnum.DAY.name().equalsIgnoreCase(tpye)){
            return  Result.success(apiStatisticsEntity.getApiStatistcsDayDtoList());
        }
        if(DatetypeEnum.HOUR.name().equalsIgnoreCase(tpye)){
            return  Result.success(apiStatisticsEntity.getApiStatistcsHourDtoList());
        }else {
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }

    }


}
