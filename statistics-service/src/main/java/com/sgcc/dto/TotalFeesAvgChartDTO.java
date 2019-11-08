package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalFeesAvgChartDTO implements Serializable {

    private static final long serialVersionUID = 1055183495134210005L;


    private double total;

    private double thisWeekTotal;

    private double ratio;

    private double average;



}
