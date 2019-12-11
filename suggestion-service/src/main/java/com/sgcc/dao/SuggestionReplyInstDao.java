package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionReplyInstDao implements Serializable {
    private static final long serialVersionUID = -9059233887061046506L;
    private String id;
    private String suggestion_id;
    private String reply_openid;
    private String check_openid;
}
