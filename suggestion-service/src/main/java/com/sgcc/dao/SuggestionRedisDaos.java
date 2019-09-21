package com.sgcc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuggestionRedisDaos implements Serializable {

    private static final long serialVersionUID = 8635300115640683189L;

    private List<SuggestionRedisDao> suggestionRedisDaoList;
}
