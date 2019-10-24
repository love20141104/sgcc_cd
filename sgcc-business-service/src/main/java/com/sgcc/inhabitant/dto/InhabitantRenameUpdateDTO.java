package com.sgcc.inhabitant.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor  //生成无参构造函数
public class InhabitantRenameUpdateDTO implements Serializable {
    private static final long serialVersionUID = 8259578234317479309L;

    private String houseId;          // 户号信息id

    private Boolean change;         // 产权是否变更

    private String houseName;

    private String idCard;

    private String contactTel;

    private String idCardPositiveImg;

    private String idCardBackImg;

    public InhabitantRenameUpdateDTO(String houseId, Boolean change, String houseName, String idCard,
                                     String contactTel, String idCardPositiveImg, String idCardBackImg) {
        this.houseId = houseId;
        this.change = change;
        this.houseName = houseName;
        this.idCard = idCard;
        this.contactTel = contactTel;
        this.idCardPositiveImg = idCardPositiveImg;
        this.idCardBackImg = idCardBackImg;
    }
}
