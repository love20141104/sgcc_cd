package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SubscribeInfoDTO implements Serializable {

    private static final long serialVersionUID = -7998987379275055415L;

    private Boolean subBill;
    private Boolean subPay;
    private Boolean subArrears;
    private Boolean subCoulometricAnalysis;
    private Boolean subPower;

    public SubscribeInfoDTO(
            Boolean subBill,
            Boolean subPay,
            Boolean subArrears,
            Boolean subCoulometricAnalysis,
            Boolean subPower
    ){
        this.subBill = subBill;
        this.subPay = subPay;
        this.subArrears = subArrears;
        this.subCoulometricAnalysis = subCoulometricAnalysis;
        this.subPower = subPower;

    }

}
