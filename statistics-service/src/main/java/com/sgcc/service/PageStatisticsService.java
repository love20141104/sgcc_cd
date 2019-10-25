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
    public Result getPageStatistcsDateDtoList(String tpye)

    {
        if (DatetypeEnum.MONTH.name().equalsIgnoreCase(tpye)) {
            return Result.success(pageStatisticsEntity.getPageStatistcsMonthDtoList());
        }
        if (DatetypeEnum.WEEK.name().equalsIgnoreCase(tpye)) {
            return Result.success(pageStatisticsEntity.getPageStatistcsWeekDtoList());
        }
        if (DatetypeEnum.DAY.name().equalsIgnoreCase(tpye)) {
            return Result.success(pageStatisticsEntity.getPageStatistcsDayDtoList());
        }
        if (DatetypeEnum.HOUR.name().equalsIgnoreCase(tpye)) {
            return Result.success(pageStatisticsEntity.getPageStatistcsHourDtoList());
        } else {
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }
}
