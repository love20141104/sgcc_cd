package com.sgcc.entity.query;

import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dto.SubscribeInfoDTO;
import com.sgcc.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    /**
     * 获取用户绑定的户号
     */
     public List<HouseholdInfoDao> getBindList(String userOpenId){
         //TODO
         return null;
     }

    /**
     * 判断该用户是否在b_user表中以及该用户在订阅信息表中是否有记录
     * @return
     */
    public boolean userIsExist(String openId){
        //TODO
        return true;
    }

    /**
     * 获取该用户的订阅信息并返回
     */
    public SubscribeInfoDTO getSubscribeInfo(String openId){
        //TODO
        return null;
    }

}
