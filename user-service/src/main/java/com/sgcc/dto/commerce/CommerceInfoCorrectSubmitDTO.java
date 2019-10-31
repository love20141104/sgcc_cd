package com.sgcc.dto.commerce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommerceInfoCorrectSubmitDTO implements Serializable {

    private static final long serialVersionUID = 8249195525925420313L;

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




}
