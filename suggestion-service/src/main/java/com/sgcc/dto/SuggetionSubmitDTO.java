package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuggetionSubmitDTO implements Serializable {
    private static final long serialVersionUID = -2451743385362120087L;
    private String userId;
    private String suggestionContent;
    private Date suggestionContact;
    private Date suggestionTel;
    private String img_1;
    private String img_2;
    private String img_3;

}