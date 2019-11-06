package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dao.ReadingQuantityStatistcsDao;
import com.sgcc.entity.ReadingQuantityEntity;
import com.sgcc.model.ReadingQuantityModel;
import com.sgcc.sgccenum.DateRangeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingQuantityService {
    @Autowired
    private ReadingQuantityEntity readingQuantityEntity;

    public Result getReadingQuantityStatistcs(DateRangeEnum dateRangeEnum){
        List<ReadingQuantityStatistcsDao> readingQuantityStatistcsDaos = readingQuantityEntity.getReadingQuantityStatistcsDTOs(dateRangeEnum);
        ReadingQuantityModel readingQuantityModel = new ReadingQuantityModel(readingQuantityStatistcsDaos);
        readingQuantityModel.daos2dtos();
        readingQuantityModel.viewDTOTransform();
        return Result.success(readingQuantityModel.getReadingQuantityStatistcsViewDTO());
    }
}
