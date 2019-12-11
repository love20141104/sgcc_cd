package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuggestionReplyMappingDao implements Serializable {
    private static final long serialVersionUID = 4858587519643362723L;
    protected String id;
    protected String suggestion_id;
    protected String reply_content;
    protected String reply_openid;
    protected String reply_date;
    protected String check_openid;
    protected int check_state;
    protected String check_date;
}
