package com.sgcc.entity.event;

import com.google.common.base.Strings;
import com.sgcc.constant.SubscribeConstant;
import com.sgcc.dao.*;
import com.sgcc.entity.query.HouseholdQueryEntity;
import com.sgcc.repository.HouseholdRepository;
import com.sgcc.sgccenum.SubscribeCateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
public class HouseholdEventEntity {

    @Autowired
    private HouseholdRepository householdRepository;
    @Autowired
    private HouseholdQueryEntity householdQueryEntity;

    /**
     * 存用户表，户号表，关系表，订阅信息表
     *
     * @param userOpenId
     * @param householdInfoDao
     */
    @Transactional
    public void saveHousehold(String userOpenId,
                              HouseholdInfoDao householdInfoDao
    ) {
        UserDao userDao = householdRepository.selectUserByUserOpenId(userOpenId);
        if (null == userDao) {
            userDao = new UserDao(UUID.randomUUID().toString(), userOpenId, null, true);
            householdRepository.insertUser(userDao);

            SubscribeDao subscribeDao = new SubscribeDao(UUID.randomUUID().toString(), userDao.getUserId());
            householdRepository.insertSubscribe(subscribeDao);
        }
        List<HouseholdInfoDao> householdInfoDaos = householdQueryEntity.getBindList(userOpenId);
        if(null == householdInfoDaos || householdInfoDaos.size() == 0){
            householdInfoDao.setHouseholdDefault(true);
        }
        householdRepository.insertHouseholdInfo(householdInfoDao);
        //保存userHouseholdDao关系表
        UserHouseholdDao userHouseholdDao = new UserHouseholdDao(UUID.randomUUID().toString(), userDao.getUserId(), householdInfoDao.getHouseholdId(), true);
        householdRepository.insertUserHousehold(userHouseholdDao);
    }

    //TODO 解绑删除关系表，户号表
    public void deleteUserHouseHoldAndHouseholdInfo(String householdNumber, String userOpenId) {

        //如果解绑的是默认户号并且该用户还有绑定的户号则需要设置一个默认账户
        HouseholdInfoDao householdInfo = householdRepository.getHouseholdInfo(userOpenId, householdNumber);
        householdRepository.deleteUserHouseHoldAndHouseholdInfo(householdNumber, userOpenId);
        if(householdInfo.getHouseholdDefault()){
            List<HouseholdInfoDao> bindList = householdRepository.getBindList(userOpenId);
            if(null!=bindList&&bindList.size()>0){
                HouseholdInfoDao householdInfoDao = bindList.get(0);
                String householdNumber1 = householdInfoDao.getHouseholdNumber();
                householdRepository.setDefaultHouseholdNum(userOpenId,householdNumber1);
            }
        }
    }

    //TODO 4张表中数据作废
    public void unavailableUserHouseHold(String userOpenId) {
        householdRepository.unavailableUserHouseHold(userOpenId,false);
    }

    //TODO  若表中有该用户的作废数据则恢复4张表中数据作
    public void availableUserHouseHold(String userOpenId) {
        householdRepository.unavailableUserHouseHold(userOpenId,true);
    }

    //TODO 修改户号表数据
    public void updateUser(String openId, String householdNum, String pwd) {
        householdRepository.updatePwd(openId,householdNum,pwd);
    }

    //TODO 更新户号表，设置默认
    public void setDefaultHouseholdNum(String openId, String householdNum) {
        householdRepository.setDefaultHouseholdNum(openId,householdNum);
    }

    //TODO 修改订阅信息
    public void updateSubscribe(String openId, SubscribeCateEnum subscribeCateEnum, boolean isSubscribe) {

        String columnName = SubscribeConstant.SUBSCRIBE_CATEGORY.get(subscribeCateEnum.name());

        householdRepository.updateSubscribe(
                openId
                //数据库列名
                ,subscribeCateEnum.name()
                ,isSubscribe
        );
    }


    public void updateUserSubscribe(UserSubscribeDao userSubscribeDao) {
        householdRepository.updateUserSubscribe(userSubscribeDao);
    }
}
