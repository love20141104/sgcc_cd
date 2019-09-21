package com.sgcc.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuggestionUpdateDTO implements Serializable{
    private static final long serialVersionUID = -4581669884064441516L;
    private String suggestionId;
    private String userId;
    private String replyContent;
    private Date replyDate;
}