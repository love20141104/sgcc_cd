package com.sgcc.service;


import com.example.result.Result;
import com.sgcc.entity.event.HouseholdEventEntity;
import com.sgcc.entity.query.HouseholdQueryEntity;
import com.sgcc.exception.TopErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HouseholdService {
    @Autowired
    private HouseholdEventEntity householdEventEntity;
    @Autowired
    private HouseholdQueryEntity householdQueryEntity;

    /**
     * 用户绑定户号
     */
    public Result bindHousehold() {
        //TODO 判断该用户绑定户号数量是否超过5个
        if(true){
            return Result.failure(TopErrorCode.HOUSEHOLD_BIND_NUM_EXCEED);
        }
        //TODO 判断该户号是否已被5个用户绑定
        else if(true){
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
    public Result removeBind() {

        try{
            //TODO 删除关系表，户号表

            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }
    /**
     * 用户取消关注
     */
    public Result cancelFocusWechat(){

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
    public Result focusWechat(){
        try{
            //TODO  若表中有该用户的作废数据则恢复4张表中数据作
            return Result.success();
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }

    }

    /**
     * 数据库中记录的密码失效时，提示用户输入新密码、
     */
    public Result changePWD(String openId,String householdNum,String pwd){
        //TODO 调用接口验证新密码是否正确
        if(true){
            try{
                //TODO 修改户号表数据

            }catch (Exception e){
                e.printStackTrace();
                Result.failure(TopErrorCode.GENERAL_ERR);
            }
        }else {
            return Result.failure(TopErrorCode.HOUSEHOLD_PWD_ERR);
        }


        return null;
    }
    /**
     * 查询用户消息订阅状态
     */

    /**
     * 用户取消消息订阅
     */

    /**
     * 用户恢复消息订阅
     */

}
