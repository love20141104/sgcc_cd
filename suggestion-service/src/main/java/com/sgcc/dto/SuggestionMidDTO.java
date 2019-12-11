package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionMidDTO implements Serializable {
    private static final long serialVersionUID = -5200672813599596691L;
    protected String suggestion_id;
    protected String user_location;
}
