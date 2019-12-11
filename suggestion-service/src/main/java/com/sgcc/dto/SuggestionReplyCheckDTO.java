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
    protected String suggestion_id;
    protected String reply_openid;
    protected String reply_content;
    protected String check_openid;
    protected int check_state;
}
