package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dao.PageStatisticsDao;
import com.sgcc.dto.PageStatisticsDto;
import com.sgcc.entity.PageStatisticsEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.PagestatisticsModel;
import com.sgcc.sgccenum.DatetypeEnum;
import com.sgcc.utils.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageStatisticsService {
    @Autowired
    private PageStatisticsEntity pageStatisticsEntity;

    public Result addPageStatistics(PageStatisticsDto pageStatisticsDto){
        PageStatisticsDao dtotodao = PagestatisticsModel.dtotodao(pageStatisticsDto);
        dtotodao.setClientIp(IPUtil.getIpAddress());
        pageStatisticsEntity.savePageStatistics(dtotodao);
        return Result.success();
    }
    //
    public Result getPageStatistcsMonth() {
            return Result.success(pageStatisticsEntity.getPageStatistcsMonthDtoList());
    }

    public Result getPageStatistcsDay() {
            return Result.success(pageStatisticsEntity.getPageStatistcsDayDtoList());
    }
    public Result getHotPage(){
        return Result.success(pageStatisticsEntity.hotPageDtoList());
    }
}
