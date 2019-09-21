package com.sgcc.repository;

import com.sgcc.dao.SuggestionRedisDao;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface SuggestionRedisRepository extends CrudRepository<SuggestionRedisDao,String> {
    List<SuggestionRedisDao> findAllByUserId( String userId );

    SuggestionRedisDao findBySuggestionId( String suggestionId);
}
