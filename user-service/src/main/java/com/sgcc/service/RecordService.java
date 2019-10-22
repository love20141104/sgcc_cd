package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dto.*;
import com.sgcc.exception.TopErrorCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordService {

    /**
     * 查询缴费记录
     * @param userNo
     * @return
     */
    public Result queryRecordInfo(String userNo) {

        RecordDetailDTO recordDetailDTO = new RecordDetailDTO(1,"营业厅",100.00,"2019.08.02 15:30:10");
        RecordDetailDTO recordDetailDTO2 = new RecordDetailDTO(2,"微信",100.00,"2019.08.23 10:30:00");
        List<RecordDetailDTO> recordTypeDTOS = new ArrayList<>();
        recordTypeDTOS.add(recordDetailDTO);
        recordTypeDTOS.add(recordDetailDTO2);


        RecordDetailDTO recordDetailDTO3 = new RecordDetailDTO(1,"营业厅",100.00,"2019.08.02 15:20:23");
        RecordDetailDTO recordDetailDTO4 = new RecordDetailDTO(2,"微信",100.00,"2019.08.23 13:30:22");
        RecordDetailDTO recordDetailDTO5 = new RecordDetailDTO(3,"支付宝",100.00,"2019.08.23 12:00:00");
        List<RecordDetailDTO> recordTypeDTOS2 = new ArrayList<>();
        recordTypeDTOS2.add(recordDetailDTO3);
        recordTypeDTOS2.add(recordDetailDTO4);
        recordTypeDTOS2.add(recordDetailDTO5);



        List<PaymentChannelDTO> paymentChannelDTOS = new ArrayList<>();
        PaymentChannelDTO paymentChannelDTO1 = new PaymentChannelDTO("微信支付",200d);
        PaymentChannelDTO paymentChannelDTO2 = new PaymentChannelDTO("支付宝支付",200d);
        PaymentChannelDTO paymentChannelDTO3 = new PaymentChannelDTO("营业厅",30d);
        paymentChannelDTOS.add(paymentChannelDTO1);
        paymentChannelDTOS.add(paymentChannelDTO2);
        paymentChannelDTOS.add(paymentChannelDTO3);

        RecordTypeDTO recordTypeDTO1 = new RecordTypeDTO("2019-10",500d,recordTypeDTOS);
        RecordTypeDTO recordTypeDTO2 = new RecordTypeDTO("2019-09",400d,recordTypeDTOS);
        RecordTypeDTO recordTypeDTO3 = new RecordTypeDTO("2019-08",300d,recordTypeDTOS);
        RecordTypeDTO recordTypeDTO4 = new RecordTypeDTO("2019-07",250d,recordTypeDTOS);
        RecordTypeDTO recordTypeDTO5 = new RecordTypeDTO("2019-06",350d,recordTypeDTOS);
        RecordTypeDTO recordTypeDTO6 = new RecordTypeDTO("2019-05",100d,recordTypeDTOS2);



        List<RecordTypeDTO> recordDTOs = new ArrayList<>();
        recordDTOs.add(recordTypeDTO1);
        recordDTOs.add(recordTypeDTO2);
        recordDTOs.add(recordTypeDTO3);
        recordDTOs.add(recordTypeDTO4);
        recordDTOs.add(recordTypeDTO5);
        recordDTOs.add(recordTypeDTO6);

        RecordViewDTO recordViewDTO = new RecordViewDTO(paymentChannelDTOS,recordDTOs);


        return Result.success(recordViewDTO);
    }

    /**
     * 订单详情信息
     * @param orderTransDTO
     * @return
     */
    public Result queryOrderInfo(OrderTransDTO orderTransDTO){
        if (Strings.isNullOrEmpty(orderTransDTO.getUserNo())  &&
                Strings.isNullOrEmpty(orderTransDTO.getDateTime())&&
                orderTransDTO.getTypeId() != null){

            OrderDetailDTO orderDetailDTO = new OrderDetailDTO("051454522581","刘德华",
                    orderTransDTO.getUserNo(),"高新区天府五街美年广场A座1144号",100d,
                    "微信",orderTransDTO.getDateTime());
            return Result.success(orderDetailDTO);
        }else {
            return Result.failure(TopErrorCode.NO_DATAS);
        }

    }



}
