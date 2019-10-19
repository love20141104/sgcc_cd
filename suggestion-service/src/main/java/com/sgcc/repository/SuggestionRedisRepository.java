package com.sgcc.repository;

import com.sgcc.dao.SuggestionRedisDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SuggestionRedisRepository extends CrudRepository<SuggestionRedisDao,String> {
    List<SuggestionRedisDao> findAllByUserId( String userId );
    void deleteAll( List<SuggestionRedisDao> sIds );
    SuggestionRedisDao findBySuggestionId( String suggestionId);
}
