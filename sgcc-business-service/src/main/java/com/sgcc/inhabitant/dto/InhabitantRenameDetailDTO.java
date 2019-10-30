package com.sgcc.inhabitant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor  //生成无参构造函数
@AllArgsConstructor
public class InhabitantRenameDetailDTO implements Serializable {

    private static final long serialVersionUID = -2581692864245278918L;
    private String id;

    private String infoId;

    private String houseId;          // 户号信息id

    private String userOpenId;

    private Boolean change;         // 产权是否变更

    private String infoName;

    private String infoIdCard;

    private String InfoTelphone;

    private String cqIdCardPositiveImg;

    private String cqIdCardBackImg;

    private String applicant;       // 申请人身份

    private String transactorName;  // 经办人姓名

    private String sqArttorneyImg;

    private String sqIdCardPositiveImg;

    private String sqIdCardBackImg;

    private String transactorIdCard;

    private String transactorTel;

    private String submitDate;

}
