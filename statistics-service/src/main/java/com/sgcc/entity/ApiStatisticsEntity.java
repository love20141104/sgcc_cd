package com.sgcc.entity;

import com.example.Utils;
import com.sgcc.dao.ApiStatisticsDao;
import com.sgcc.dto.ApiStatistcsMonthDto;
import com.sgcc.dto.ApiStatisticsDto;
import com.sgcc.dto.ApiStatisticsQueryDto;
import com.sgcc.repository.ApiStatisticsRepository;
import com.sgcc.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApiStatisticsEntity {
    @Autowired
    private ApiStatisticsRepository apiStatisticsRepository;

    public void saveApiStatistics(ApiStatisticsDao apiStatisticsDao){
        apiStatisticsRepository.saveApiStatistics(apiStatisticsDao);
    }
    public List<ApiStatisticsQueryDto> getApiStatisticsQuery(String visit_date_begin, String visit_date_end){
        return  apiStatisticsRepository.getApiStatisticsQuery(visit_date_begin,visit_date_end);
    }
    public List<ApiStatisticsDto> getApiStatistics(String visit_date_begin, String visit_date_end){
        return  apiStatisticsRepository.getApiStatistics(visit_date_begin,visit_date_end);
    }

    public Integer getApiStatisticsOpenIdNum(String visit_date_begin, String visit_date_end){
        return apiStatisticsRepository.getApiStatisticsOpenIdNum(visit_date_begin, visit_date_end);
    }
    public List<ApiStatistcsMonthDto> getApiStatistcsMonthDtoList(){
        List<ApiStatistcsMonthDto> apiStatistcsMonthDtos=new ArrayList<>();
        for (int i = 0; i <=11 ; i++) {

            String begin= Utils.GetTime(DateUtil.getnMonthFirst(i));
            String end= Utils.GetTime(DateUtil.getnMonthLast(i));
            ApiStatistcsMonthDto apiStatistcsMonthDto = apiStatisticsRepository.getApiStatisticsCount(begin, end);
            apiStatistcsMonthDto.setMonth(DateUtil.getnMonthFirst(i));
            apiStatistcsMonthDtos.add(apiStatistcsMonthDto);
        }
        return apiStatistcsMonthDtos;
    }
}
