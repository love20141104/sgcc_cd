package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionCheckDao implements Serializable {
    private static final long serialVersionUID = 5603164235104925713L;
    private String id;
    private String suggestion_id;
    private int check_state;
    private String check_date;
}
