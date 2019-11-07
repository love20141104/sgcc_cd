package com.sgcc.entity;

import com.example.Utils;
import com.sgcc.dao.PageStatisticsDao;
import com.sgcc.dto.HotPageDto;
import com.sgcc.dto.PageStatistcsDateDto;
import com.sgcc.dto.PageStatistcsMonthDto;
import com.sgcc.repository.PageStatisticsRepository;
import com.sgcc.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PageStatisticsEntity {
    @Autowired
    private PageStatisticsRepository pageStatisticsRepository;

    public void savePageStatistics(PageStatisticsDao pageStatisticsDao){
        pageStatisticsRepository.savePageStatistics(pageStatisticsDao);
    }
    public List<PageStatistcsDateDto> getPageStatistcsMonthDtoList(){

        return pageStatisticsRepository.getPageStatisticsCountMonth();
    }

    public PageStatistcsMonthDto getPageStatistcsDayDtoList(){
        return pageStatisticsRepository.getPageStatisticsCountDay();
    }

    public List<HotPageDto> hotPageDtoList(){
        return pageStatisticsRepository.hotPageDtoList();
    }
}
