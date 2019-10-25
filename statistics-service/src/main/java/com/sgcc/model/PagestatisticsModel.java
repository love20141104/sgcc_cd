package com.sgcc.model;

import com.sgcc.dao.PageStatisticsDao;
import com.sgcc.dto.PageStatisticsDto;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.UUID;

public class PagestatisticsModel {
    public static PageStatisticsDao dtotodao(PageStatisticsDto pageStatisticsDto){
        PageStatisticsDao pageStatisticsDao = new PageStatisticsDao();
        BeanUtils.copyProperties(pageStatisticsDto,pageStatisticsDao);
        pageStatisticsDao.setId(UUID.randomUUID().toString());
        pageStatisticsDao.setVisitDate(new Date());
        return pageStatisticsDao;
    }
}
