package com.sgcc.entity.query;

import com.sgcc.dao.ReplierAndCheckerDao;
import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDao;
import com.sgcc.dao.SuggestionReplyInstDao;
import com.sgcc.repository.ReplierAndCheckerRepository;
import com.sgcc.repository.SuggestionRedisRepository;
import com.sgcc.repository.SuggestionReplyRepository;
import com.sgcc.repository.SuggestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuggestionQueryEntity {
    @Autowired
    private SuggestionsRepository suggestionsRepository;
    @Autowired
    private SuggestionRedisRepository suggestionRedisRepository;
    @Autowired
    private ReplierAndCheckerRepository replierAndCheckerRepository;
    @Autowired
    private SuggestionReplyRepository suggestionReplyRepository;

    public SuggestionReplyInstDao getInstReply(String sgstId )
    {
        return suggestionReplyRepository.getInst( sgstId );
    }

    public List<SuggestionRedisDao> GetAllsuggestions(String userId)
    {
        return suggestionRedisRepository.findAllByUserId(userId);
    }


    public List<SuggestionDao> GetAllSuggestions(String userId )
    {
        return suggestionsRepository.findAllByUserID(userId);
    }

    public SuggestionRedisDao GetRedisSuggestion(String suggestionId )
    {
        return suggestionRedisRepository.findBySuggestionId(suggestionId);
    }

    public SuggestionDao GetSuggestion(String suggestionId )
    {
        return suggestionsRepository.findBySuggestionId(suggestionId);
    }
    public List<SuggestionDao> findAll()
    {
        return suggestionsRepository.findAll();
    }
    public SuggestionDao findById(String id)
    {
        return suggestionsRepository.findById(id);
    }
    public List<SuggestionDao> GetSuggestions()
    {
        return suggestionsRepository.findAll();
    }


    public List<ReplierAndCheckerDao> findAllByRegion(String Region){
        return replierAndCheckerRepository.findAllByRegion(Region);
    }

    public List<ReplierAndCheckerDao> GetAll(){
        return replierAndCheckerRepository.findAll();
    }

}
