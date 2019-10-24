package com.sgcc.inhabitant.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor  //生成无参构造函数
public class InhabitantRenameDao implements Serializable {
    private static final long serialVersionUID = -4514878156199973011L;
    private String id;

    private String infoId;

    private String houseId;          // 户号信息id

    private String openId;

    private Boolean change;         // 产权是否变更

    private String houseName;

    private String idCard;

    private String contactTel;

    private String idCardPositiveImg;

    private String idCardBackImg;

    private Date submitDate;

    public InhabitantRenameDao(String id, String infoId, String houseId, String openId,
                               Boolean change, String houseName, String idCard, String contactTel,
                               String idCardPositiveImg, String idCardBackImg, Date submitDate) {
        this.id = id;
        this.infoId = infoId;
        this.houseId = houseId;
        this.openId = openId;
        this.change = change;
        this.houseName = houseName;
        this.idCard = idCard;
        this.contactTel = contactTel;
        this.idCardPositiveImg = idCardPositiveImg;
        this.idCardBackImg = idCardBackImg;
        this.submitDate = submitDate;
    }
}
