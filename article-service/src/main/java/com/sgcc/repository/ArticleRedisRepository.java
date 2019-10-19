package com.sgcc.repository;
import com.sgcc.dao.ArticleRedisDao;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRedisRepository extends CrudRepository<ArticleRedisDao,String> {
    Optional<ArticleRedisDao> findById(String id);
    List<ArticleRedisDao> findAllByArticleRecommended( boolean userId );
    List<ArticleRedisDao> findAllByArticleType( String userId );
    List<ArticleRedisDao> findAllByIdExists(List<String> ids);
}