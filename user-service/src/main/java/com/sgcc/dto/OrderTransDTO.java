package com.sgcc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor  //生成无参构造函数
public class OrderTransDTO implements Serializable {

    private static final long serialVersionUID = 539600049308458883L;

    private String userNo;

    private Integer typeId;     // 缴费渠道

    private String dateTime;

    public OrderTransDTO(String userNo, int typeId, String dateTime) {
        this.userNo = userNo;
        this.typeId = typeId;
        this.dateTime = dateTime;
    }
}
