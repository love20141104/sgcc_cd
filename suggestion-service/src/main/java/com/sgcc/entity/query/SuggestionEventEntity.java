package com.sgcc.entity.query;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class SuggestionEventEntity {
    @Autowired
    private SuggestionRepository suggestionRepository;

    public void Save(SuggestionDao dao ){
        suggestionRepository.save(dao);
    }


}
