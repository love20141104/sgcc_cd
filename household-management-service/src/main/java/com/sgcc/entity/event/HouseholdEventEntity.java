package com.sgcc.entity.event;

import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class HouseholdEventEntity {

    @Autowired
    private HouseholdRepository householdRepository;
    /**
     * 保存HouseholdInfo
     * @param householdInfoDao
     */
    @Transactional
    public void insertHouseholdInfo(HouseholdInfoDao householdInfoDao){
        householdRepository.insertHouseholdInfo(householdInfoDao);
    }
}
