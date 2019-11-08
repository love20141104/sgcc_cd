package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 模拟接口查询得到的户号信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseholdInfoDTO_interface implements Serializable {
    private static final long serialVersionUID = 4167384023285128091L;

    private String householdHouseholder;
    private String householdNumber;
    private String householdAddress;
    private String householdType;
}
