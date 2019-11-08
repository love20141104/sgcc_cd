package com.sgcc.entity.query;

import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HouseholdQueryEntity {
    @Autowired
    private HouseholdRepository householdRepository;

    /**
     * 判断用户是否绑定超5个户号
     * @param userOpenId
     * @return
     */
    public Boolean userExceed5(String userOpenId){
        return householdRepository.userExceed5(userOpenId);
    }

    /**
     * 判断户号是否绑定超5个用户
     * @param householdNumber
     * @return
     */
    public Boolean householdExceed5(String householdNumber){
        return householdRepository.householdExceed5(householdNumber);
    }


}
