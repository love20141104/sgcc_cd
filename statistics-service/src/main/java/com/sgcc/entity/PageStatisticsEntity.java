package com.sgcc.entity;

import com.example.Utils;
import com.sgcc.dao.PageStatisticsDao;
import com.sgcc.dto.PageStatistcsDateDto;
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
    public List<PageStatistcsDateDto> getPageStatistcsWeekDtoList(){
        List<PageStatistcsDateDto> pageStatistcsDateDtos =new ArrayList<>();
        for (int i = 1; i <=7 ; i++) {
            String begin= Utils.GetTime(DateUtil.getnweekFirst(i));
            String end= Utils.GetTime(DateUtil.getnweekLast(i));
            PageStatistcsDateDto pageStatisticsCount = pageStatisticsRepository.getPageStatisticsCount(begin, end);
            pageStatisticsCount.setDate(DateUtil.getnMonthFirst(i));
            pageStatistcsDateDtos.add(pageStatisticsCount);
        }
        return pageStatistcsDateDtos;
    }
    public List<PageStatistcsDateDto> getPageStatistcsDayDtoList(){
        List<PageStatistcsDateDto> pageStatistcsDateDtos =new ArrayList<>();
        for (int i = 1; i <=31 ; i++) {
            if(null!=DateUtil.getndayFirst(i)) {
                String begin = Utils.GetTime(DateUtil.getndayFirst(i));
                String end = Utils.GetTime(DateUtil.getndayLast(i));
                PageStatistcsDateDto pageStatisticsCount = pageStatisticsRepository.getPageStatisticsCount(begin, end);
                pageStatisticsCount.setDate(DateUtil.getnMonthFirst(i));
                pageStatistcsDateDtos.add(pageStatisticsCount);
            }
        }
        return pageStatistcsDateDtos;
    }
    public List<PageStatistcsDateDto> getPageStatistcsHourDtoList(){
        List<PageStatistcsDateDto> pageStatistcsDateDtos =new ArrayList<>();
        for (int i = 0; i <=23 ; i++) {
            String begin= Utils.GetTime(DateUtil.getnHourFirst(i));
            String end= Utils.GetTime(DateUtil.getnHourLast(i));
            PageStatistcsDateDto pageStatisticsCount = pageStatisticsRepository.getPageStatisticsCount(begin, end);
            pageStatisticsCount.setDate(DateUtil.getnMonthFirst(i));
            pageStatistcsDateDtos.add(pageStatisticsCount);
        }
        return pageStatistcsDateDtos;
    }
}
