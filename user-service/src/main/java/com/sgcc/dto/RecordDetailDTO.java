package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor  //生成无参构造函数
public class RecordDetailDTO implements Serializable {

    private Integer typeId;

    private String typeName;

    private double paymentAmount;   // 缴费金额

    private String paymentTime;     //缴费时间

    public RecordDetailDTO(Integer typeId, String typeName, double paymentAmount, String paymentTime) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.paymentAmount = paymentAmount;
        this.paymentTime = paymentTime;
    }
}
