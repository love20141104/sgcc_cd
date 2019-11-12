package com.sgcc.service;


import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dao.SubscribeDao;
import com.sgcc.dao.UserSubscribeDao;
import com.sgcc.des.DesUtil;
import com.sgcc.dto.HouseholdInfoDTO_interface;
import com.sgcc.dto.HouseholdNumsDTO;
import com.sgcc.dto.SubscribeInfoDTO;
import com.sgcc.dto.UserSubscribeDTO;
import com.sgcc.entity.event.HouseholdEventEntity;
import com.sgcc.entity.query.HouseholdQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.HouseholdModel;
import com.sgcc.model.SubscribeModel;
import com.sgcc.model.UserSubscribeModel;
import com.sgcc.sgccenum.SubscribeCateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HouseholdService {
    @Autowired
    private HouseholdEventEntity householdEventEntity;
    @Autowired
    private HouseholdQueryEntity householdQueryEntity;


/*
*
     * 用户绑定户号

*/

    public Result bindHousehold(String openId, String householdNum,String pwd) {
        //todo 解密householdNum和pwd
        try {
            householdNum=DesUtil.decrypt(householdNum);
            pwd=DesUtil.decrypt(pwd);
        }catch (Exception e){
            return Result.failure(TopErrorCode.DECRYPTION_FAILED);
        }
        if(householdNum.length()!=10){
            return Result.failure(TopErrorCode.HOUSEHOLD_NUM_ERR);
        }

        //判断该户号是否被该用户绑定过
        if(null!=householdQueryEntity.getHouseholdInfoByOpenIdAndHouseholdNum(openId,householdNum)){
            return Result.failure(TopErrorCode.HOUSEHOLD_BIND_REPEAT);
        }
        // 判断该用户绑定户号数量是否超过5个
            if(householdQueryEntity.userExceed5(openId)){
            return Result.failure(TopErrorCode.HOUSEHOLD_BIND_NUM_EXCEED);
        }
        // 判断该户号是否已被5个用户绑定
        else if(householdQueryEntity.householdExceed5(householdNum)){
            return Result.failure(TopErrorCode.USER_BIND_NUM_EXCEED);
        }else {
            //TODO 根据户号密码调用接口验证信息是否正确
            if(true){
                // TODO 获取用户信息
                HouseholdInfoDTO_interface householdInfoDTO_interface = new HouseholdInfoDTO_interface();
                householdInfoDTO_interface.setHouseholdNumber(householdNum);

                try{
                    HouseholdModel householdModel = new HouseholdModel(householdInfoDTO_interface);
                    householdModel.interfaceDTO2DAO(pwd);
                    // 存用户表，户号表，关系表，订阅信息表
                    householdEventEntity.saveHousehold(
                            openId
                            ,householdModel.getHouseholdInfoDao()
                            );
                    //返回成功
                    return Result.success();
                }catch (Exception e){
                    e.printStackTrace();
                    return Result.failure(TopErrorCode.GENERAL_ERR);
                }
            }else {
                //户号密码错误
                return Result.failure(TopErrorCode.HOUSEHOLD_PWD_ERR);
            }
        }
    }

/*
*
     * 用户解邦户号

*/

    public Result removeBind(String openId, String householdId) {

        try{
            // 删除关系表，户号表
            householdEventEntity.deleteUserHouseHoldAndHouseholdInfo(householdId,openId);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }
/*

*
     * 用户获取绑定户号列表

*/

    public Result getBindList(String openId){
        try{
            // 获取用户绑定的户号
            List<HouseholdInfoDao> householdInfoDaos =householdQueryEntity.getBindList(openId);
            //数据清洗
            HouseholdModel householdModel = new HouseholdModel(openId,householdInfoDaos);
            householdModel.daos2dto();
            //返回结果
            return Result.success(householdModel.getHouseholdInfoListDTO());
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

/**
     * 设置默认户号
     */

    public Result setDefaultHouseholdNum(String opneId,String householdId){
        try{
            // 更新户号表，设置默认
            householdEventEntity.setDefaultHouseholdNum(opneId,householdId);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

/*
*
     * 用户取消关注

*/

    public Result cancelFocusWechat(String openId){

        try{
            // 4张表中数据作废
            householdEventEntity.unavailableUserHouseHold(openId);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }
/**
     * 用户关注公众号
     */

    public Result focusWechat(String openId){
        try{
            // 若表中有该用户的作废数据则恢复4张表中数据
            householdEventEntity.availableUserHouseHold(openId);
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }
/*

*
     * 数据库中记录的密码失效时，提示用户输入新密码修改密码

*/

    public Result changePWD(String openId,String householdNum,String pwd){
        try {
            householdNum=DesUtil.decrypt(householdNum);
        }catch (Exception e){
            return Result.failure(TopErrorCode.DECRYPTION_FAILED);
        }
        //TODO 调用接口验证新密码是否正确
        if(true){
            try{
                // 修改户号表数据
                householdEventEntity.updateUser(openId,householdNum,pwd);
                return Result.success();
            }catch (Exception e){
                e.printStackTrace();
                return Result.failure(TopErrorCode.GENERAL_ERR);
            }
        }else {
            return Result.failure(TopErrorCode.HOUSEHOLD_PWD_ERR);
        }
    }
/*
*
     * 查询用户消息订阅状态

*/

    public Result getSubscribeInfo(String openId){
        // 判断该用户是否在b_user表中以及该用户在订阅信息表中是否有记录
        if(householdQueryEntity.userIsExist(openId)){
            // 获取该用户的订阅信息
            SubscribeDao subscribeDao = householdQueryEntity.getSubscribeInfo(openId);
            //数据清洗
            SubscribeModel subscribeModel = new SubscribeModel(subscribeDao);
            subscribeModel.selectTransform();
            return Result.success(subscribeModel.getSubscribeInfoDTO());
        }else {
            try{
                return Result.failure(TopErrorCode.NONE_HOUSEHOLD_INFO);
            }catch (Exception e){
                e.printStackTrace();
                return Result.failure(TopErrorCode.GENERAL_ERR);
            }

        }
    }

/*
*
     * 用户修改消息订阅状态

*/


    public Result updateSubscribe(String openId, SubscribeCateEnum subscribeCateEnum, boolean isSubscribe){
        try{
            // 判断该用户是否在b_user表中以及该用户在订阅信息表中是否有记录
            if(householdQueryEntity.userIsExist(openId)){
                // 修改订阅信息
                householdEventEntity.updateSubscribe(openId,subscribeCateEnum,isSubscribe);
                return Result.success();
            }else {
                return Result.failure(TopErrorCode.NONE_HOUSEHOLD_INFO);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 查询UserSubscribe
     * @param isAvailable
     * @return
     */
    public Result getUserSubscribeList(Boolean isAvailable){
        List<UserSubscribeDao> userSubscribeList = householdQueryEntity.getUserSubscribeList(isAvailable);
        UserSubscribeModel userSubscribeModel = new UserSubscribeModel(userSubscribeList);
        return Result.success(userSubscribeModel.getUserSubscribeDTOList());
    }

    /**
     * 查询缴费记录中其他非绑定用户户号
     * @return
     */
    public Result getNoBindList(String openId) {
        if (Strings.isNullOrEmpty(openId))
            return Result.failure("openId为空");
        try {
            List<HouseholdNumsDTO> householdNumsDTOS = householdQueryEntity.getNoBindList(openId);
            HouseholdModel model = new HouseholdModel();
            String[] str = model.getNoBindTransform(householdNumsDTOS);
            return Result.success(str);
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    public Result updateUserSubscribe(UserSubscribeDTO userSubscribeDTO) {
        UserSubscribeModel userSubscribeModel = new UserSubscribeModel(userSubscribeDTO);
        householdEventEntity.updateUserSubscribe(userSubscribeModel.getUserSubscribeDao());

        return Result.success();
    }
    /**
     * 加密
     * @param pwd
     * @return
     */
    public String encrypt(String pwd){
        try {
            return DesUtil.encrypt(pwd);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     * @param s
     * @return
     */
    public String decrypt(String s){
        try {
            return DesUtil.decrypt(s);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
