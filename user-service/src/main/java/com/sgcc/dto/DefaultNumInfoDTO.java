package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultNumInfoDTO implements Serializable {

    private static final long serialVersionUID = 7313151010211637488L;

    private String householder;

    private String householdNumber;

    private String householdAddress;

    private double currentMonthPower;   // 本月电量

    private double currentMonthFees;    // 本月电费

    private double currentPowerBalance; // 电费余额


}
