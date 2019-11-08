package com.sgcc.entity.event;

import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dao.SubscribeDao;
import com.sgcc.dao.UserDao;
import com.sgcc.dao.UserHouseholdDao;
import com.sgcc.repository.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class HouseholdEventEntity {

    @Autowired
    private HouseholdRepository householdRepository;

    /**
     * 存用户表，户号表，关系表，订阅信息表
     * @param userOpenId
     * @param householdInfoDao
     * @param userHouseholdDao
     */
    @Transactional
    public void saveHousehold(String userOpenId,
                              HouseholdInfoDao householdInfoDao,
                              UserHouseholdDao userHouseholdDao
    ){
        Boolean aBoolean = householdRepository.ifUserByUserOpenId(userOpenId);
        if(aBoolean){
            Boolean aBoolean1 = householdRepository.ifSubscribeByUserOpenId(userOpenId);
            if(!aBoolean1) {
                SubscribeDao subscribeDao=new SubscribeDao(UUID.randomUUID().toString(),userOpenId,true,true,true,true,true,true);
                householdRepository.insertSubscribe(subscribeDao);
            }
        }else {
            Boolean aBoolean1 = householdRepository.ifSubscribeByUserOpenId(userOpenId);
            if(!aBoolean1) {
                SubscribeDao subscribeDao=new SubscribeDao(UUID.randomUUID().toString(),userOpenId,true,true,true,true,true,true);
                householdRepository.insertSubscribe(subscribeDao);
            }
            String userId = UUID.randomUUID().toString();
            UserDao userDao = new UserDao(userId, userOpenId, null, true);
            householdRepository.insertUser(userDao);
        }
        householdRepository.insertHouseholdInfo(householdInfoDao);
        householdRepository.insertUserHousehold(userHouseholdDao);
    }
    //TODO 删除关系表，户号表
    public void deleteUserHouseHoldAndHouseholdInfo(String householdId,String userOpenId){

    }
    //TODO 4张表中数据作废
    public void unavailableUserHouseHold(String householdId,String userOpenId){

    }
    //TODO  若表中有该用户的作废数据则恢复4张表中数据作
    public void availableUserHouseHold(String householdId,String userOpenId){

    }
    //TODO 修改户号表数据
    public void updateUser(String openId,String householdNum,String pwd){

    }
}
