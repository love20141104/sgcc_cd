package com.sgcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuggestionDeleteDTO implements Serializable {
    private static final long serialVersionUID = 2757175721599983663L;
    private List<String> suggestionIds;
}
