package com.sgcc.entity.query;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDao;
import com.sgcc.repository.SuggestionRedisRepository;
import com.sgcc.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public void DeleteSuggestions(List<String> suggestionIds )
    {
        suggestionRepository.deleteAll(suggestionIds);
    }

    public void FlushSuggestions(List<String> suggestionIds )
    {
        Iterable<SuggestionRedisDao> daos = suggestionRedisRepository.findAllById(suggestionIds);
        suggestionRedisRepository.deleteAll(daos);
    }

    public void RefreshSuggestion( SuggestionDao dao )
    {
        Optional<SuggestionRedisDao> rdao = suggestionRedisRepository.findById(dao.getId());
        if( rdao != null ){
            rdao.get().setImg_1(dao.getImg_1());
            rdao.get().setImg_2(dao.getImg_2());
            rdao.get().setImg_3(dao.getImg_3());
            suggestionRedisRepository.save( rdao.get() );
        }

    }

}
