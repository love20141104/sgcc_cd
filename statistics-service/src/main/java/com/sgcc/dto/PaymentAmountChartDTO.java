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
public class PaymentAmountChartDTO implements Serializable {

    private static final long serialVersionUID = -6915785460682474843L;
    private double total;

    private String date;

}
