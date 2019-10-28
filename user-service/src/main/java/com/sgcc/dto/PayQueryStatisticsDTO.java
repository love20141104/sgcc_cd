package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PayQueryStatisticsDTO implements Serializable {

    private static final long serialVersionUID = 5968338848265502740L;
    private int PayTotal; //支付笔数
    private Double PaySum; // 支付总额

}
