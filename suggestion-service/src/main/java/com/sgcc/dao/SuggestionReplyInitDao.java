package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionReplyInitDao implements Serializable {
    private static final long serialVersionUID = -8613479560653504903L;
    protected String id;
    protected String suggestion_id;
    protected String reply_openid;
    protected String check_openid;
}
