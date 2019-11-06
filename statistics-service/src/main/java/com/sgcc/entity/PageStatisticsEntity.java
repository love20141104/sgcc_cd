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
        List<PageStatistcsDateDto> pageStatistcsDateDtos =new ArrayList<>();
        for (int i = 0; i <=11 ; i++) {
            String begin= Utils.GetTime(DateUtil.getnMonthFirst(i));
            String end= Utils.GetTime(DateUtil.getnMonthLast(i));
            PageStatistcsDateDto pageStatisticsCount = pageStatisticsRepository.getPageStatisticsCount(begin, end);
            pageStatisticsCount.setDate(DateUtil.getnMonthFirst(i));
            pageStatistcsDateDtos.add(pageStatisticsCount);
        }
        return pageStatistcsDateDtos;
    }

    public PageStatistcsMonthDto getPageStatistcsDayDtoList(){
        PageStatistcsMonthDto pageStatistcsMonthDto = new PageStatistcsMonthDto();
        List<PageStatistcsDateDto> pageStatistcsDateDtos =new ArrayList<>();
        for (int i = 0; i <=29 ; i++) {
            if(null!=DateUtil.getndayFirst(i)) {
                String begin = Utils.GetTime(DateUtil.getndayFirst(i));
                String end = Utils.GetTime(DateUtil.getndayLast(i));
                PageStatistcsDateDto pageStatisticsCount = pageStatisticsRepository.getPageStatisticsCount(begin, end);
                pageStatisticsCount.setDate(DateUtil.getndayFirst(i));
                pageStatistcsDateDtos.add(pageStatisticsCount);
            }
        }
        String begin = Utils.GetTime(DateUtil.getndayFirst(29));
        String end = Utils.GetTime(DateUtil.getndayLast(0));
        PageStatistcsDateDto pageStatisticsCount = pageStatisticsRepository.getPageStatisticsCount(begin, end);
        pageStatistcsMonthDto.setTotal(pageStatisticsCount.getUrlNum());
        pageStatistcsMonthDto.setPageStatistcsList(pageStatistcsDateDtos);
        return pageStatistcsMonthDto;
    }

    public List<HotPageDto> hotPageDtoList(){
        return pageStatisticsRepository.hotPageDtoList();
    }
}
