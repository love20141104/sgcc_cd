package com.sgcc.entity.event;

import com.sgcc.dao.*;
import com.sgcc.repository.ReplierAndCheckerRepository;
import com.sgcc.repository.SuggestionRedisRepository;
import com.sgcc.repository.SuggestionReplyRepository;
import com.sgcc.repository.SuggestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SuggestionEventEntity {
    @Autowired
    private SuggestionsRepository suggestionsRepository;
    @Autowired
    private SuggestionRedisRepository suggestionRedisRepository;
    @Autowired
    private ReplierAndCheckerRepository replierAndCheckerRepository;
    @Autowired
    private SuggestionReplyRepository suggestionReplyRepository;

    public void Save( SuggestionDao dao ){
        List<SuggestionDao> daos = new ArrayList<>();
        daos.add(dao);
        suggestionsRepository.saveAll(daos);
    }

    public SuggestionDao Update(String reply_user_id , String reply_content, Date reply_date, String suggestion_id ){
        return suggestionsRepository.update(reply_user_id,reply_content,reply_date,suggestion_id);
    }
    public SuggestionDao Update( SuggestionDao dao ){
        return suggestionsRepository.update(dao);
    }

    public void SaveAll( List<SuggestionDao> daos )
    {
        suggestionsRepository.saveAll(daos);
    }

    public void CacheAll( List<SuggestionRedisDao> daos )
    {
        suggestionRedisRepository.saveAll(daos);
    }

    public void Cache( SuggestionRedisDao dao )
    {
        suggestionRedisRepository.save(dao);
    }

    public void DeleteSuggestions(List<String> suggestionIds )
    {
        suggestionsRepository.deleteAll(suggestionIds);
    }

    public void FlushSuggestions(List<String> suggestionIds )
    {
        Iterable<SuggestionRedisDao> daos = suggestionRedisRepository.findAllById(suggestionIds);
        suggestionRedisRepository.deleteAll(daos);
    }

    public void save( ReplierAndCheckerDao dao )
    {
        replierAndCheckerRepository.save(dao);
    }
    public void update( ReplierAndCheckerDao dao )
    {
        replierAndCheckerRepository.update(dao);
    }

    public void InitReplyID(SuggestionReplyInitDao dao )
    {
        suggestionReplyRepository.save(dao);
    }
    public void ContentReply (SuggestionReplyDao dao )
    {
        suggestionReplyRepository.update(dao);
    }
    public void CheckReply (SuggestionCheckDao dao )
    {
        suggestionReplyRepository.update(dao);
    }
}
