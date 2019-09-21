package com.sgcc.entity.query;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.repository.SuggestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SuggestionEventEntity {
    @Autowired
    private SuggestionRepository suggestionRepository;

    public void Save(SuggestionDao dao ){
        List<SuggestionDao> daos = new ArrayList<>();
        daos.add(dao);
        suggestionRepository.saveAll(daos);
    }


}
