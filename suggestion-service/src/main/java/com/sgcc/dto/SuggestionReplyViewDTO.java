package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionReplyViewDTO implements Serializable {
    private static final long serialVersionUID = -7508596133429178762L;
    private String reply_openid;
    private String reply_name;
    private String reply_content;
    private String reply_date;
}
