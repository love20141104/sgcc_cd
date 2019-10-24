package com.sgcc.inhabitant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor  //生成无参构造函数
public class InhabitantRenameDetailDTO implements Serializable {
    private static final long serialVersionUID = 8259578234317479309L;

    private String orderNo;          // 工单编号

    private String userNo;

    private String userName;

    private String address;

    private String progress;

    private String houseId;          // 户号信息id

    private Boolean change;         // 产权是否变更

    private String houseName;

    private String idCard;

    private String contactTel;

    private String idCardPositiveImg;

    private String idCardBackImg;

    private Date submitDate;

}
