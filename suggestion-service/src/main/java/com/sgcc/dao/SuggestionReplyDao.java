package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionReplyDao implements Serializable {
    private static final long serialVersionUID = -5647553181331473773L;
    private String id;
    private String suggestion_id;
    private String reply_content;
    private String reply_date;
}
