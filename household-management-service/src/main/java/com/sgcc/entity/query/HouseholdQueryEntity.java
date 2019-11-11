package com.sgcc.entity.query;

import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dao.SubscribeDao;
import com.sgcc.dao.UserDao;
import com.sgcc.dao.UserSubscribeDao;
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
         return householdRepository.getBindList(userOpenId);
     }

    /**
     * 判断该用户是否在b_user表中以及该用户在订阅信息表中是否有记录
     * @return
     */
    public boolean userIsExist(String openId){
        UserDao userDao = householdRepository.selectUserByUserOpenId(openId);
        SubscribeDao subscribeDao = householdRepository.selectSubscribeByUserOpenId(openId);
        if(null != userDao && null != subscribeDao){
            return true;
        }else {
            return false;
        }
    }
    public String getHouseholdInfoByOpenIdAndHouseholdNum(String openId,String householdNum){
       return householdRepository.getHouseholdIdByUserOpenIdAndHouseholdNum(openId,householdNum);
    }
    public SubscribeDao getSubscribeInfo(String openId){
        return householdRepository.selectSubscribeByUserOpenId(openId);
    }
    public List<UserSubscribeDao> getUserSubscribeList(Boolean isAvailable){
        return householdRepository.getUserSubscribeList(isAvailable);
    }
}
