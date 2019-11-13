package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.PayResultDao;
import com.sgcc.dto.*;
import com.sgcc.entity.query.PayResultEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.UserModel;
import com.sgcc.sgccenum.DatetypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatPayResultService {

    @Autowired
    private WeChatService weChatService;
    @Autowired
    private HouseholdService householdService;

    @Autowired
    private PayResultEntity payResultEntity;

    /**
     * 缴费结果统计
     * @param payQueryDTO
     * @return
     */
    public Result findPayResultByYearOrMonth(PayQueryDTO payQueryDTO){

        if (Strings.isNullOrEmpty(payQueryDTO.getStartDate()) || Strings.isNullOrEmpty(payQueryDTO.getDateUnit()))
            return Result.failure(TopErrorCode.ZERO_OBJ);

        List<PayQueryStatisticsDTO> payQueryStatisticsDTOS = null;
        PayQueryStatisticsDTO payQueryStatisticsDTO = null;
        UserModel userModel = new UserModel();
        try {
            // 判断是年份或月份
           if (DatetypeEnum.MONTH.name().equals(payQueryDTO.getDateUnit())){
               // 根据当前时间做最近30天缴费统计
               payQueryStatisticsDTO = payResultEntity.findPayResultByMonth(payQueryDTO.getStartDate());
               userModel.findResultStatisticsByMonth(payQueryStatisticsDTO,payQueryDTO.getStartDate(),
                       payQueryDTO.getDateUnit());

               if (userModel.getPayStatisticsDTO() != null){
                   return Result.success(userModel.getPayStatisticsDTO());
               }else {
                   return Result.failure(TopErrorCode.NO_DATAS);
               }
           }else if (DatetypeEnum.YEAR.name().equals(payQueryDTO.getDateUnit())){
               // 做当前年缴费统计和每个月统计
               payQueryStatisticsDTO = payResultEntity.findPayResultByCurrentYear(payQueryDTO.getStartDate());
               payQueryStatisticsDTOS = payResultEntity.findPayResultByCurrentMonth(payQueryDTO.getStartDate());

               userModel.findResultStatisticsByYear(payQueryStatisticsDTO,payQueryStatisticsDTOS,
                       payQueryDTO.getStartDate(),
                       payQueryDTO.getDateUnit());

               if (userModel.getPayStatisticsDTO() != null){
                   return Result.success(userModel.getPayStatisticsDTO());
               }else {
                   return Result.failure(TopErrorCode.NO_DATAS);
               }

           }else {
               return Result.failure(TopErrorCode.ZERO_OBJ);
           }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }


    }


    /**
     * 查询所有缴费成功结果
     * @return
     */
    public Result findPayResult(){

        try {
            List<PayResultDao> payResultDaos = payResultEntity.findPayResult();
            UserModel userModel = new UserModel(payResultDaos);
            userModel.findTransform();

            if (userModel.getPayResultViewDTOS().size() > 0){
                return Result.success(userModel.getPayResultViewDTOS());
            }else {
                return Result.failure(TopErrorCode.NO_DATAS);
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }


    }


    public Result findRecentlyTransform(String openId){
        if (Strings.isNullOrEmpty(openId))
            return Result.failure("openId为空");

        try {
            List<PayResultDao> payResultDaos = payResultEntity.findPayResult();
            PayResultViewsDTO payResultViewsDTO = payResultEntity.findMoneyByRecently(openId);

            if (payResultDaos != null && payResultViewsDTO != null){
                UserModel userModel = new UserModel(payResultDaos);
                userModel.findRecentlyTransform(payResultViewsDTO.getTotal());
                return Result.success(userModel.findRecentlyTransform(payResultViewsDTO.getTotal()));
            }else {
                return Result.failure("没有查询到数据");
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }



    /**
     * 新增缴费成功结果
     * @param payResultSubmitDTO
     * @return
     */
    public Result insertPayResult(PayResultSubmitDTO payResultSubmitDTO){
        if (payResultSubmitDTO == null)
            return Result.failure(TopErrorCode.NULL_OBJ);

        try {

            UserModel userModel = new UserModel(payResultSubmitDTO);
            userModel.insertTransform();
            int count = payResultEntity.insertPayResult(userModel.getPayResultDao());
            if (count > 0){
                Result result = householdService.getSubscribeInfo(userModel.getPayResultDao().getOpenId());
                if(null != result.getData()){
                    SubscribeInfoDTO subscribeInfoDTO = (SubscribeInfoDTO)result.getData();
                    if(subscribeInfoDTO == null || subscribeInfoDTO.getSubPay()){
                        weChatService.sendRechargeSuccessTempMsg(userModel.getPayResultDao().getOpenId(),userModel.getPayResultDao().getUserNo(),userModel.getPayResultDao().getMoney());
                    }
                }
                return Result.success("新增支付结果成功！");
            }else {
                return Result.failure(TopErrorCode.SAVE_OBJ_ERR);
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.INVALID_PARAMS);
        }


    }



}
