package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessRecordDao {
    //业务类型
    private String businessCategory;
    //办理次数
    private int applications;
}
