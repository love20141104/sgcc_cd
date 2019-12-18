package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionReplyCheckDTO implements Serializable {
    private static final long serialVersionUID = -8646488398287880411L;
    protected String suggestionId;
    protected String checkOpenid;
    protected int checkState;
    protected String checkReject;
}
