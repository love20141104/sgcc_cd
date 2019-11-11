package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscribeDTO {

    private String userOpenId;
    private String userTel;

    private Boolean subBill;
    private Boolean subPay;
    private Boolean subArrears;
    private Boolean subCoulometricAnalysis;
    private Boolean subPower;
}
