package com.sgcc.dto.inhabitant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InhabitantInfoCorrectSubmitDTO implements Serializable {

    private static final long serialVersionUID = 472663863413334230L;

    private String userOpenId;

    private String houseId;

    private String correctName;

    private String correctIdcard;

    private String correctTel;

    private String correctIdcardPositiveImg;

    private String correctIdcardBackImg;

    private String correctNewName;

    private String correctNewAddress;

    private String correctNewTel;

}
