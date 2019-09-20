package com.sgcc.entity.event;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SuggestionQueryEntity {
    @Autowired
    private SuggestionRepository suggestionRepository;

    public SuggestionDao findAllByUserId(String userId )
    {
        return suggestionRepository.findAllByUserId(userId);
    }
}
