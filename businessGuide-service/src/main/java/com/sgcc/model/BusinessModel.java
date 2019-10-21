package com.sgcc.model;

import com.sgcc.dao.BusinessCategoryDao;
import com.sgcc.dao.BusinessGuideDao;
import com.sgcc.dao.BusinessGuideRedisDao;
import com.sgcc.dto.BusinessCategoryDto;
import com.sgcc.dto.BusinessGuideDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BusinessModel {
    public static List<BusinessCategoryDao> dtoTodaoBC(List<BusinessCategoryDto> businessCategoryDtos){
        List<BusinessCategoryDao> collect = businessCategoryDtos.stream().map(businessCategoryDto -> {
            BusinessCategoryDao businessCategoryDao = new BusinessCategoryDao();
            BeanUtils.copyProperties(businessCategoryDto, businessCategoryDao);
            return businessCategoryDao;
        }).collect(Collectors.toList());
        return collect;
    }
    public static List<BusinessCategoryDto> daoTodtoBC(List<BusinessCategoryDao> businessCategoryDaos){
        List<BusinessCategoryDto> collect = businessCategoryDaos.stream().map(businessCategoryDao -> {
            BusinessCategoryDto businessCategoryDto = new BusinessCategoryDto();
            BeanUtils.copyProperties( businessCategoryDao,businessCategoryDto);
            return businessCategoryDto;
        }).collect(Collectors.toList());
        return collect;
    }
    public static List<BusinessGuideRedisDao> dtoTodaoBG(List<BusinessGuideDto> businessGuideDtos){
        List<BusinessGuideRedisDao> collect = businessGuideDtos.stream().map(businessGuideDto -> {
            BusinessGuideRedisDao businessGuideDao = new BusinessGuideRedisDao();
            BeanUtils.copyProperties(businessGuideDto, businessGuideDao);
            return businessGuideDao;
        }).collect(Collectors.toList());
        return collect;
    }
    public static List<BusinessGuideDto> redisdaoTodtoBG(List<BusinessGuideRedisDao> businessGuideDaos){
        List<BusinessGuideDto> collect = businessGuideDaos.stream().map(businessGuideDao -> {
            BusinessGuideDto businessGuideDto = new BusinessGuideDto();
            BeanUtils.copyProperties( businessGuideDao,businessGuideDto);
            return businessGuideDto;
        }).collect(Collectors.toList());
        return collect;
    }
    public static List<BusinessGuideRedisDao> daooToredisdaoBG(List<BusinessGuideDao> businessGuideDaos){
        List<BusinessGuideRedisDao> collect = businessGuideDaos.stream().map(businessGuideDao -> {
            BusinessGuideRedisDao BusinessGuideRedisDao = new BusinessGuideRedisDao();
            BeanUtils.copyProperties(businessGuideDao, BusinessGuideRedisDao);
            return BusinessGuideRedisDao;
        }).collect(Collectors.toList());
        return collect;
    }
    public static List<BusinessGuideDto> daoTodtoBG(List<BusinessGuideDao> businessGuideDaos){
        List<BusinessGuideDto> collect = businessGuideDaos.stream().map(businessGuideDao -> {
            BusinessGuideDto businessGuideDto = new BusinessGuideDto();
            BeanUtils.copyProperties( businessGuideDao,businessGuideDto);
            return businessGuideDto;
        }).collect(Collectors.toList());
        return collect;
    }
    public static BusinessGuideDao dtotodaoBG(BusinessGuideDto businessGuideDto){
        BusinessGuideDao businessGuideDao = new BusinessGuideDao();
        BeanUtils.copyProperties(businessGuideDto,businessGuideDao);
        return businessGuideDao;
    }
    public static BusinessGuideRedisDao dtotoredisdaoBG(BusinessGuideDto businessGuideDto){
        BusinessGuideRedisDao businessGuideDao = new BusinessGuideRedisDao();
        BeanUtils.copyProperties(businessGuideDto,businessGuideDao);
        return businessGuideDao;
    }

}
