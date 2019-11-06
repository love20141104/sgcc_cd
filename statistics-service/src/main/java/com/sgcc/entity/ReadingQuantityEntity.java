package com.sgcc.entity;

import com.sgcc.dao.ReadingQuantityStatistcsDao;
import com.sgcc.repository.ReadingQuantityStatistcsRepository;
import com.sgcc.sgccenum.DateRangeEnum;
import com.sgcc.sgccenum.DatetypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReadingQuantityEntity {
    @Autowired
    private ReadingQuantityStatistcsRepository readingQuantityStatistcsRepository;

    public List<ReadingQuantityStatistcsDao> getReadingQuantityStatistcsDTOs(DateRangeEnum dateRangeEnum){

        List<ReadingQuantityStatistcsDao> readingQuantityStatistcsDaos = readingQuantityStatistcsRepository.getReadingQuantityStatistcsDTOs(dateRangeEnum);

        return readingQuantityStatistcsDaos;
    }
}
