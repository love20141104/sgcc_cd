package com.sgcc.dto.commerce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommerceInfoCorrectQueryDTO implements Serializable {

    private static final long serialVersionUID = -1929063571471500160L;

    private String correctId;

    private String userOpenId;

    private String houseId;

    private String correctName;

    private String correctIdcard;

    private String correctTel;

    private String correctIdcardPositiveImg;

    private String correctIdcardBackImg;

    private String correctLicenseImg;

    private String correctNewName;

    private String correctNewAddress;

    private String correctNewTel;

    private String propertyRightImg1;

    private String propertyRightImg2;

    private String propertyRightImg3;

    private String propertyRightImg4;

    private String propertyRightImg5;

    private String propertyRightImg6;

    private Date correctSubmitDate;





}
