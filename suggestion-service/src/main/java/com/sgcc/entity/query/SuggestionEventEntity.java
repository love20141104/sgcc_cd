package com.sgcc.entity.query;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDao;
import com.sgcc.repository.SuggestionRedisRepository;
import com.sgcc.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class SuggestionEventEntity {
    @Autowired
    private SuggestionRepository suggestionRepository;
    @Autowired
    private SuggestionRedisRepository suggestionRedisRepository;

    public void Save( SuggestionDao dao ){
        List<SuggestionDao> daos = new ArrayList<>();
        daos.add(dao);
        suggestionRepository.saveAll(daos);
    }

    public SuggestionDao Update(String reply_user_id , String reply_content, Date reply_date, String suggestion_id ){
        return suggestionRepository.update(reply_user_id,reply_content,reply_date,suggestion_id);
    }

    public void SaveAll( List<SuggestionRedisDao> daos )
    {
        suggestionRedisRepository.saveAll(daos);
    }

    public void Save( SuggestionRedisDao dao )
    {
        suggestionRedisRepository.save(dao);
    }
}
