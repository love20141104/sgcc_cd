package com.sgcc.inhabitant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor  //生成无参构造函数
public class InhabitantRenameOrderListDTO implements Serializable {

    private static final long serialVersionUID = 4611029095602232184L;
    private String orderNo;          // 工单编号

    private String userNo;

    private String userName;

    private String address;

    private String progress;

    public InhabitantRenameOrderListDTO(String orderNo, String userNo, String userName,
                                        String address, String progress) {
        this.orderNo = orderNo;
        this.userNo = userNo;
        this.userName = userName;
        this.address = address;
        this.progress = progress;
    }
}
