package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeDao {
    private String subId;
    private String userId;
    private Boolean subBill;
    private Boolean subPay;
    private Boolean subArrears;
    private Boolean subCoulometricAnalysis;
    private Boolean subPower;
    private Boolean isAvailable;

}
