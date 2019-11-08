package com.sgcc.service;


import com.example.result.Result;
import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dto.SubscribeInfoDTO;
import com.sgcc.entity.event.HouseholdEventEntity;
import com.sgcc.entity.query.HouseholdQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.HouseholdModel;
import com.sgcc.sgccenum.SubscribeCateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class HouseholdService {
    @Autowired
    private HouseholdEventEntity householdEventEntity;
    @Autowired
    private HouseholdQueryEntity householdQueryEntity;

    /**
     * 用户绑定户号
     */
    public Result bindHousehold(String openId, String householdNum,String pwd) {
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
                Object o = new Object();
                try{
                    // TODO 存用户表，户号表，关系表，订阅信息表
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

    /**
     * 用户解邦户号
     */
    public Result removeBind(String openId, String householdNum) {

        try{
            //TODO 删除关系表，户号表

            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 用户获取绑定户号列表
     */
    public Result getBindList(String openId){
        try{
            //TODO 获取用户绑定的户号
            List<HouseholdInfoDao> householdInfoDaos = new ArrayList<>();
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
    public Result setDefaultHouseholdNum(String opneId,String householdNum){
        try{
            //TODO 更新户号表，设置默认

            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }

    /**
     * 用户取消关注
     */
    public Result cancelFocusWechat(String opneId){

        try{
            //TODO 4张表中数据作废

            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }
    /**
     * 用户关注公众号
     */
    public Result focusWechat(String opneId){
        try{
            //TODO  若表中有该用户的作废数据则恢复4张表中数据作
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 数据库中记录的密码失效时，提示用户输入新密码修改密码
     */
    public Result changePWD(String openId,String householdNum,String pwd){
        //TODO 调用接口验证新密码是否正确
        if(true){
            try{
                //TODO 修改户号表数据

                return Result.success();
            }catch (Exception e){
                e.printStackTrace();
                return Result.failure(TopErrorCode.GENERAL_ERR);
            }
        }else {
            return Result.failure(TopErrorCode.HOUSEHOLD_PWD_ERR);
        }
    }
    /**
     * 查询用户消息订阅状态
     */
    public Result getSubscribeInfo(String openId){
        //TODO 判断该用户是否在b_user表中以及该用户在订阅信息表中是否有记录
        if(true){
            //TODO 获取该用户的订阅信息并返回
            return Result.success();
        }else {
            try{
                //TODO 将用户信息和订阅信息存入

                //返回订阅信息
                return Result.success(new SubscribeInfoDTO());
            }catch (Exception e){
                e.printStackTrace();
                return Result.failure(TopErrorCode.GENERAL_ERR);
            }

        }
    }

    /**
     * 用户修改消息订阅状态
     */

    public Result ypdateSubscribe(String openId, SubscribeCateEnum subscribeCateEnum, boolean isSubscribe){
        try{
            //TODO 修改订阅信息
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }




}
