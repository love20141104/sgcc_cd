package com.sgcc.entity.event;

import com.google.common.base.Strings;
import com.sgcc.constant.SubscribeConstant;
import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dao.SubscribeDao;
import com.sgcc.dao.UserDao;
import com.sgcc.dao.UserHouseholdDao;
import com.sgcc.repository.HouseholdRepository;
import com.sgcc.sgccenum.SubscribeCateEnum;
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
     */
    @Transactional
    public void saveHousehold(String userOpenId,
                              HouseholdInfoDao householdInfoDao
    ){  //通过userOpenId查询数据库是否有该用户
        UserDao userDao1 = householdRepository.selectUserByUserOpenId(userOpenId);
        //有user
        if(null!=userDao1){
            //通过userOpenId查询数据库是否有该Subscribe
            SubscribeDao subscribeDao = householdRepository.selectSubscribeByUserOpenId(userOpenId);
            //没有Subscribe
            if(null==subscribeDao) {
                //保存Subscribe
                 subscribeDao=new SubscribeDao(UUID.randomUUID().toString(),userDao1.getUserId(),true,true,true,true,true,true);
                householdRepository.insertSubscribe(subscribeDao);
            }
            //没有user
        }else {
             userDao1=new UserDao();
            //通过userOpenId查询数据库是否有该Subscribe
            SubscribeDao subscribeDao = householdRepository.selectSubscribeByUserOpenId(userOpenId);
            //没有Subscribe
            if(null==subscribeDao) {
                String uuid = UUID.randomUUID().toString();
                userDao1.setUserId(uuid);
                 subscribeDao=new SubscribeDao(UUID.randomUUID().toString(),uuid,true,true,true,true,true,true);
                householdRepository.insertSubscribe(subscribeDao);
                //保存user
                UserDao userDao = new UserDao(uuid, userOpenId, null, true);
                householdRepository.insertUser(userDao);
            }else {
            //保存user
                UserDao userDao = new UserDao(subscribeDao.getUserId(), userOpenId, null, true);
                householdRepository.insertUser(userDao);
            }
        }
        //todo 判断该用户否是第一次绑定户号
        //是 默认户号true
        //不是 默认户号flase
        householdRepository.insertHouseholdInfo(householdInfoDao);
        //如果householdInfoDao没有主键
        if(Strings.isNullOrEmpty(householdInfoDao.getHouseholdId())){
            householdInfoDao.setHouseholdId(UUID.randomUUID().toString());
        }
        //保存userHouseholdDao关系表
        UserHouseholdDao userHouseholdDao = new UserHouseholdDao(UUID.randomUUID().toString(), userDao1.getUserId(), householdInfoDao.getHouseholdId(), true);
        householdRepository.insertUserHousehold(userHouseholdDao);
    }
    //TODO 解绑删除关系表，户号表
    public void deleteUserHouseHoldAndHouseholdInfo(String householdNumber,String userOpenId){
        householdRepository.deleteUserHouseHoldAndHouseholdInfo(householdNumber,userOpenId);
    }
    //TODO 4张表中数据作废
    public void unavailableUserHouseHold(String userOpenId){

    }
    //TODO  若表中有该用户的作废数据则恢复4张表中数据作
    public void availableUserHouseHold(String userOpenId){

    }
    //TODO 修改户号表数据
    public void updateUser(String openId,String householdNum,String pwd){

    }

    //TODO 更新户号表，设置默认
    public void setDefaultHouseholdNum(String opneId,String householdNum){

    }

    //TODO 修改订阅信息
    public void updateSubscribe(String openId, SubscribeCateEnum subscribeCateEnum, boolean isSubscribe){
        //数据库列名
        SubscribeConstant.SUBSCRIBE_CATEGORY.get(subscribeCateEnum.name());
    }

}
