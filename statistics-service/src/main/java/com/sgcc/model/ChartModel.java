package com.sgcc.model;

import com.sgcc.dto.PaymentTimesChartDTO;
import com.sgcc.dto.PaymentTimesDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChartModel {

    private PaymentTimesChartDTO paymentTimesChartDTO;


    public void queryPaymentTimesChartTransform(PaymentTimesDTO paymentTimesDTO,
                                                List<PaymentTimesDTO> paymentTimesDTOS) {

        this.paymentTimesChartDTO = new PaymentTimesChartDTO(paymentTimesDTO.getTotal(),paymentTimesDTOS);
    }



}
