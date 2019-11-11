package com.sgcc.dao;

import com.sgcc.dao.SubscribeDao;
import com.sgcc.dao.UserDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubscribeDao {
    private String userId;
    private String userOpenId;
    private String userTel;

    private String subId;
    private Boolean subBill;
    private Boolean subPay;
    private Boolean subArrears;
    private Boolean subCoulometricAnalysis;
    private Boolean subPower;
    private Boolean isAvailable;
}
