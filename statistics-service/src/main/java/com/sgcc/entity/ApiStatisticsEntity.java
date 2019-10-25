package com.sgcc.entity;

import com.example.Utils;
import com.sgcc.dao.ApiStatisticsDao;
import com.sgcc.dto.ApiStatistcsDateDto;
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
    public List<ApiStatistcsDateDto> getApiStatistcsMonthDtoList(){
        List<ApiStatistcsDateDto> apiStatistcsDateDtos =new ArrayList<>();
        for (int i = 0; i <=11 ; i++) {
            String begin= Utils.GetTime(DateUtil.getnMonthFirst(i));
            String end= Utils.GetTime(DateUtil.getnMonthLast(i));
            ApiStatistcsDateDto apiStatistcsDateDto = apiStatisticsRepository.getApiStatisticsCount(begin, end);
            apiStatistcsDateDto.setDate(DateUtil.getnMonthFirst(i));
            apiStatistcsDateDtos.add(apiStatistcsDateDto);
        }
        return apiStatistcsDateDtos;
    }
    public List<ApiStatistcsDateDto> getApiStatistcsHourDtoList(){
        List<ApiStatistcsDateDto> apiStatistcsDateDtos =new ArrayList<>();
        for (int i = 0; i <=23 ; i++) {
            String begin= Utils.GetTime(DateUtil.getnHourFirst(i));
            String end= Utils.GetTime(DateUtil.getnHourLast(i));
            ApiStatistcsDateDto apiStatistcsDateDto = apiStatisticsRepository.getApiStatisticsCount(begin, end);
            apiStatistcsDateDto.setDate(DateUtil.getnMonthFirst(i));
            apiStatistcsDateDtos.add(apiStatistcsDateDto);
        }
        return apiStatistcsDateDtos;
    }
    public List<ApiStatistcsDateDto> getApiStatistcsWeekDtoList(){
        List<ApiStatistcsDateDto> apiStatistcsDateDtos =new ArrayList<>();
        for (int i = 1; i <=7 ; i++) {
            String begin= Utils.GetTime(DateUtil.getnweekFirst(i));
            String end= Utils.GetTime(DateUtil.getnweekLast(i));
            ApiStatistcsDateDto apiStatistcsDateDto = apiStatisticsRepository.getApiStatisticsCount(begin, end);
            apiStatistcsDateDto.setDate(DateUtil.getnMonthFirst(i));
            apiStatistcsDateDtos.add(apiStatistcsDateDto);
        }
        return apiStatistcsDateDtos;
    }
    public List<ApiStatistcsDateDto> getApiStatistcsDayDtoList(){
        List<ApiStatistcsDateDto> apiStatistcsDateDtos =new ArrayList<>();
        for (int i = 1; i <=31 ; i++) {
            if(null!=DateUtil.getndayFirst(i)){
                String begin= Utils.GetTime(DateUtil.getnweekFirst(i));
                String end= Utils.GetTime(DateUtil.getnweekLast(i));
                ApiStatistcsDateDto apiStatistcsDateDto = apiStatisticsRepository.getApiStatisticsCount(begin, end);
                apiStatistcsDateDto.setDate(DateUtil.getnMonthFirst(i));
                apiStatistcsDateDtos.add(apiStatistcsDateDto);
            }
        }
        return apiStatistcsDateDtos;
    }
}
