package com.sgcc.entity.event;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuggestionQueryEntity {
    @Autowired
    private SuggestionRepository suggestionRepository;

    public List<SuggestionDao> GetAllSuggestions(String userId )
    {
        return suggestionRepository.findAllByUserID(userId);
    }
    public SuggestionDao GetSuggestion(String suggestionId )
    {
        return suggestionRepository.findBySuggestionId(suggestionId);
    }
}
