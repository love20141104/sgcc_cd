package com.sgcc.model;

import com.sgcc.dao.HouseholdInfoDao;
import com.sgcc.dao.SubscribeDao;
import com.sgcc.dto.HouseholdInfoDTO;
import com.sgcc.dto.SubscribeInfoDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscribeModel {
    SubscribeInfoDTO subscribeInfoDTO;
    SubscribeDao subscribeDao;

    public SubscribeModel(SubscribeDao subscribeDao) {
        this.subscribeDao = subscribeDao;
    }

    public void selectTransform() {
        this.subscribeInfoDTO = new SubscribeInfoDTO(
                this.subscribeDao.getSubBill()
                , this.subscribeDao.getSubPay()
                , this.subscribeDao.getSubArrears()
                , this.subscribeDao.getSubCoulometricAnalysis()
                , this.subscribeDao.getSubPower()
        );

    }
}
