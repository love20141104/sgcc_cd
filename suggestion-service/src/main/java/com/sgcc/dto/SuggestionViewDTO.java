package com.sgcc.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuggestionViewDTO implements Serializable{
    private static final long serialVersionUID = -1820156418106116259L;
    private String suggestionId;
    private String suggestionContent;
    private String img_1;
    private String img_2;
    private String img_3;
}
