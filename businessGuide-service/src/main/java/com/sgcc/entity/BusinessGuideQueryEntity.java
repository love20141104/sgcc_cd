package com.sgcc.entity;

import com.sgcc.repository.BusinessCategoryRepository;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessGuideQueryEntity {
    @Autowired
    private BusinessCategoryRepository businessCategoryRepository;

    public List<String> selectHotBusinessCategory() {
        List<String> ids = businessCategoryRepository.selectHotBusinessCategory();
        return ids;
    }

}
