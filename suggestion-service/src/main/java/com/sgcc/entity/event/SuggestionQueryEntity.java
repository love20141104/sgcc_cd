package com.sgcc.entity.event;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDao;
import com.sgcc.repository.SuggestionRedisRepository;
import com.sgcc.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuggestionQueryEntity {
    @Autowired
    private SuggestionRepository suggestionRepository;
    @Autowired
    private SuggestionRedisRepository suggestionRedisRepository;

    public List<SuggestionRedisDao> GetAllsuggestions(String userId)
    {
        return suggestionRedisRepository.findAllByUserId(userId);
    }

    public List<SuggestionDao> GetAllSuggestions(String userId )
    {
        return suggestionRepository.findAllByUserID(userId);
    }

    public SuggestionRedisDao GetRedisSuggestion(String suggestionId )
    {
        return suggestionRedisRepository.findBySuggestionId(suggestionId);
    }

    public SuggestionDao GetSuggestion(String suggestionId )
    {
        return suggestionRepository.findBySuggestionId(suggestionId);
    }

}
