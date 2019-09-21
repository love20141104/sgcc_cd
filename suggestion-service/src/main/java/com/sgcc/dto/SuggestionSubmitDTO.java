package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuggestionSubmitDTO implements Serializable {
    private static final long serialVersionUID = -2451743385362120087L;
    private String userId;
    private String suggestionContent;
    private String suggestionContact;
    private String suggestionTel;
    private String media_1;
    private String media_2;
    private String media_3;

}
