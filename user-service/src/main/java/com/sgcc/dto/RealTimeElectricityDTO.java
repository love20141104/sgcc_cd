package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RealTimeElectricityDTO implements Serializable {

    private static final long serialVersionUID = 7191850160269342686L;

    private double currentMonthPower;   // 本月电量

    private double currentMonthFees;    // 本月电费

    private double currentPowerBalance; // 电费余额


}
