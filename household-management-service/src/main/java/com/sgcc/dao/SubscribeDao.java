package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

    public SubscribeDao(
            String subId
            , String userId
            , Boolean subBill
            , Boolean subPay
            , Boolean subArrears
            , Boolean subCoulometricAnalysis
            , Boolean subPower
            , Boolean isAvailable
    ) {
        this.subId = subId;
        this.userId = userId;
        this.subBill = subBill;
        this.subPay = subPay;
        this.subArrears = subArrears;
        this.subCoulometricAnalysis = subCoulometricAnalysis;
        this.subPower = subPower;
        this.isAvailable = isAvailable;
    }

    public SubscribeDao(
            String subId
            , String userId
    ) {
        this.subId = subId;
        this.userId = userId;
        this.subBill = true;
        this.subPay = true;
        this.subArrears = true;
        this.subCoulometricAnalysis = true;
        this.subPower = true;
        this.isAvailable = true;
    }
}
