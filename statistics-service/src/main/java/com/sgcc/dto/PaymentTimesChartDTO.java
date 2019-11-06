package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTimesChartDTO implements Serializable {

    private static final long serialVersionUID = 1055183495134210005L;


    private double total;

    private List<PaymentTimesDTO> paymentTimesDTOS = new ArrayList<>();



}
