package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SubscribeInfoDTO implements Serializable {

    private static final long serialVersionUID = -7998987379275055415L;

    private Boolean subBill = true;
    private Boolean subPay = true;
    private Boolean subArrears = true;
    private Boolean subCoulometricAnalysis = true;
    private Boolean subPower = true;

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
