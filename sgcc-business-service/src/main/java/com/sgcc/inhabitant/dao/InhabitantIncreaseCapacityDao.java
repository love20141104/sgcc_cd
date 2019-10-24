package com.sgcc.inhabitant.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor  //生成无参构造函数
public class InhabitantIncreaseCapacityDao implements Serializable {
    private static final long serialVersionUID = 5576275212099527076L;
    private String id;
    private String orderNo;         // 订单号
    private String openId;
    private Double currentCapacity;
    private String name;
    private String idcard;
    private String contactTel;

    private String aplicant;        // 申请人
    private String transactor;      // 代办人
    private String transactorIdcard;

//    private String securitiesImg1;   // 产权证明
//    private String securitiesImg2;
//    private String securitiesImg3;
//    private String securitiesImg4;   // 产权证明
//    private String securitiesImg5;
//    private String securitiesImg6;
    private String cqIdcardPositiveImg;     // 产权人
    private String cqIdcardBackImg;
    private String sqIdcardPositiveImg;     // 代办人
    private String sqIdcardBackImg;

    private Date submitDate;

    public InhabitantIncreaseCapacityDao(String id, String orderNo,String openId, Double currentCapacity, String name,
                                         String idcard, String contactTel, String aplicant,
                                         String transactor, String transactorIdcard,
                                         String cqIdcardPositiveImg, String cqIdcardBackImg, String sqIdcardPositiveImg,
                                         String sqIdcardBackImg, Date submitDate) {
        this.id = id;
        this.orderNo=orderNo;
        this.openId = openId;
        this.currentCapacity = currentCapacity;
        this.name = name;
        this.idcard = idcard;
        this.contactTel = contactTel;
        this.aplicant = aplicant;
        this.transactor = transactor;
        this.transactorIdcard = transactorIdcard;
        this.cqIdcardPositiveImg = cqIdcardPositiveImg;
        this.cqIdcardBackImg = cqIdcardBackImg;
        this.sqIdcardPositiveImg = sqIdcardPositiveImg;
        this.sqIdcardBackImg = sqIdcardBackImg;
        this.submitDate = submitDate;
    }
}
