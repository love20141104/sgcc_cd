package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionReplyInitDTO implements Serializable {
    private static final long serialVersionUID = 6631698679882113992L;
    protected String suggestion_id;
    protected String reply_openid;
    protected String check_openid;
}
