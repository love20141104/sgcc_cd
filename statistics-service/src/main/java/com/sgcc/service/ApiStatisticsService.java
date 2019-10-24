package com.sgcc.service;

import com.example.Utils;
import com.example.result.Result;
import com.sgcc.dao.ApiStatisticsDao;
import com.sgcc.dto.ApiStatistcsMonthDto;
import com.sgcc.dto.ApiStatisticsActiveDto;
import com.sgcc.dto.ApiStatisticsQueryDto;
import com.sgcc.entity.ApiStatisticsEntity;
import com.sgcc.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class ApiStatisticsService {
    @Autowired
    private ApiStatisticsEntity apiStatisticsEntity;

    public void saveApiStatistics(ApiStatisticsDao apiStatisticsDao){
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

    public Result getApiStatistcsMonthDtoList(){
        return  Result.success(apiStatisticsEntity.getApiStatistcsMonthDtoList());
    }


}
