package com.sgcc.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuggestionDetailDTO implements Serializable {
    private static final long serialVersionUID = 1484570791579038642L;
    private String id;
    private String suggestionId;
    private String userId;
    private String suggestionContent;
    private String suggestionContact;
    private String suggestionTel;
    private String submitDate;
    private String media_1;
    private String img_1;
    private String media_2;
    private String img_2;
    private String media_3;
    private String img_3;
    private Boolean check_state;
    private String replyContent;
    private String replyDate;
}
