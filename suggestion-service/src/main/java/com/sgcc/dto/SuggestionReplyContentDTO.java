package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionReplyContentDTO implements Serializable {
    private static final long serialVersionUID = -5376697363179861096L;
    protected String suggestion_id;
    protected String reply_openid;
    protected String reply_content;
}
