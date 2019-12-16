package com.sgcc.entity;

import com.google.common.base.Strings;
import com.sgcc.dao.BusinessGuideBriefDao;
import com.sgcc.dao.BusinessGuideRedisDao;
import com.sgcc.model.BusinessModel;
import com.sgcc.repository.BGRedisRepository;
import com.sgcc.repository.BusinessCategoryRepository;
import com.sgcc.repository.BusinessGuideRepository;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BusinessGuideQueryEntity {
    @Autowired
    private BusinessCategoryRepository businessCategoryRepository;

    @Autowired
    private BGRedisRepository bgRedisRepository;
    @Autowired
    private BusinessGuideRepository businessGuideRepository;

    public List<String> selectHotBusinessCategory() {
        List<String> ids = businessCategoryRepository.selectHotBusinessCategory();
        return ids;
    }

    public List<BusinessGuideRedisDao> findByCidOrTitle(String cid, String title) {
        if (!Strings.isNullOrEmpty(cid)){
            return bgRedisRepository.findAllByCategoryId(cid);
        }
        if (!Strings.isNullOrEmpty(title)){
            return bgRedisRepository.findAllByTitle(title);
        }else {
            List<BusinessGuideBriefDao> businessGuideBriefDaos = businessGuideRepository.selectBusinessGuideBrief(cid, title);
            return BusinessModel.briefDaoToRedisdaoBG(businessGuideBriefDaos);
        }
    }
}
