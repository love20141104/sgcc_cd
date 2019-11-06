package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dto.PaymentAmountChartDTO;
import com.sgcc.dto.PaymentTimesChartDTO;
import com.sgcc.dto.PaymentTimesDTO;
import com.sgcc.dto.TotalFeesAvgChartDTO;
import com.sgcc.entity.query.ChartQueryEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.ChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChartService {

    @Autowired
    private ChartQueryEntity chartQueryEntity;


    public Result findPaymentAmountChart(){
        try {
            List<PaymentAmountChartDTO> paymentAmountChartDTOS = chartQueryEntity.findPaymentAmountChart();
            if (paymentAmountChartDTOS.size() > 0){
                return Result.success(paymentAmountChartDTOS);
            }else {
                return Result.failure("查询错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }



    /**
     * 查询十天总支付笔数和最近十天每天支付笔数
     * @return
     */
    public Result findPaymentTimesChart(){
        try {
            PaymentTimesDTO paymentTimesChartDTO = chartQueryEntity.findAllPaymentTimesChart();
            List<PaymentTimesDTO> paymentTimesChartDTOS = chartQueryEntity.findPaymentTimesChart();
            ChartModel chartModel = new ChartModel();
            chartModel.queryPaymentTimesChartTransform(paymentTimesChartDTO,paymentTimesChartDTOS);
            if (chartModel.getPaymentTimesChartDTO()!= null){
                return Result.success(chartModel.getPaymentTimesChartDTO());
            }else {
                return Result.failure("查询错误");
            }

        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }


    /**
     * 查询最近10天缴费总额和日均销售额
     * @return
     */
    public Result findTotalFeesAvgChart(){
        try {
            TotalFeesAvgChartDTO totalFeesAvgChartDTO = chartQueryEntity.findTotalFeesAvgChart();
            if (totalFeesAvgChartDTO != null){
                return Result.success(totalFeesAvgChartDTO);
            }else {
                return Result.failure("查询错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Result.failure(TopErrorCode.GENERAL_ERR);
        }
    }



}
