package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dao.PayResultDao;
import com.sgcc.dto.PayQueryDTO;
import com.sgcc.dto.PayQueryStatisticsDTO;
import com.sgcc.dto.PayResultSubmitDTO;
import com.sgcc.entity.PayResultEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.UserDomainModel;
import com.sgcc.sgccenum.DatetypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WechatPayResultService {

    @Autowired
    private PayResultEntity payResultEntity;


    public Result findPayResultByYearOrMonth(PayQueryDTO payQueryDTO){

        if (payQueryDTO == null)
            return Result.failure(TopErrorCode.ZERO_OBJ);

        List<PayQueryStatisticsDTO> payQueryStatisticsDTOS = null;
        try {
           if (DatetypeEnum.MONTH.name().equals(payQueryDTO.getDateUnit())){
               payQueryStatisticsDTOS = payResultEntity.findPayResultByMonth();

           }else if (DatetypeEnum.YEAR.name().equals(payQueryDTO.getDateUnit())){
               payQueryStatisticsDTOS = payResultEntity.findPayResultByYear();
           }else {
               return Result.failure(TopErrorCode.ZERO_OBJ);
           }

            UserDomainModel userDomainModel = new UserDomainModel();
            userDomainModel.findResultStatisticsByMonth(
                    payQueryStatisticsDTOS,payQueryDTO.getStartDate());

            if (userDomainModel.getPayStatisticsDTOS().size() > 0){
                return Result.success(userDomainModel.getPayStatisticsDTOS());
            }else {
                return Result.failure(TopErrorCode.NO_DATAS);
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
            UserDomainModel userDomainModel = new UserDomainModel(payResultDaos);
            userDomainModel.findTransform();

            if (userDomainModel.getPayResultViewDTOS().size() > 0){
                return Result.success(userDomainModel.getPayResultViewDTOS());
            }else {
                return Result.failure(TopErrorCode.NO_DATAS);
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

            UserDomainModel userDomainModel = new UserDomainModel(payResultSubmitDTO);
            userDomainModel.insertTransform();
            int count = payResultEntity.insertPayResult(userDomainModel.getPayResultDao());
            if (count > 0){
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
