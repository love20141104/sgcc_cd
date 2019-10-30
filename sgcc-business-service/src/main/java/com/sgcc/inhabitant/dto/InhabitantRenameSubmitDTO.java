package com.sgcc.inhabitant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor  //生成无参构造函数
@AllArgsConstructor
public class InhabitantRenameSubmitDTO implements Serializable {
    private static final long serialVersionUID = 8259578234317479309L;
//    private String infoId;

    private String houseId;          // 户号信息id

    private String openId;

    private Boolean change;         // 产权是否变更

    private String houseName;

    private String idCard;

    private String contactTel;

    private String idCardPositiveImg;

    private String idCardBackImg;

    private String applicant;       // 申请人身份

    private String transactorName;  // 经办人姓名

    private String sqArttorneyImg;

    private String sqIdCardPositiveImg;

    private String sqIdCardBackImg;

    private String transactorIdCard;

    private String transactorTel;



}
