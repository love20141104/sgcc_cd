package com.sgcc.entity.query;

import com.sgcc.dao.ArticleDao;
import com.sgcc.dao.ArticleRedisDao;
import com.sgcc.repository.ArticleRedisRepository;
import com.sgcc.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.collections.IteratorUtils;

import java.util.Iterator;
import java.util.List;

@Component
public class ArticleQueryEntity {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleRedisRepository articleRedisRepository;

    public List<ArticleRedisDao> getRedisArticles( boolean isRecommended )
    {
        return articleRedisRepository.findAllByArticleRecommended(isRecommended);
    }
    public List<ArticleRedisDao> getRedisArticles()
    {
        Iterable<ArticleRedisDao> it = articleRedisRepository.findAll();
        return IteratorUtils.toList((Iterator) it);
    }

    public List<ArticleDao> getArticles( boolean isRecommended )
    {
        return articleRepository.findAllByRecommended(isRecommended);
    }

    public List<ArticleRedisDao> getRedisArticles(String articleType )
    {
        return articleRedisRepository.findAllByArticleType(articleType);
    }

    public List<ArticleDao> getArticles( String articleType )
    {
        return articleRepository.findAllByArticleType(articleType);
    }

    public ArticleRedisDao getRedisArticle( String id )
    {
        return articleRedisRepository.findById(id).get();
    }

    public List<ArticleDao> getArticles( )
    {
        return articleRepository.findAll();
    }

    public List<ArticleDao> getArticlesByTitle(String articleTitle) {
        return articleRepository.findAllByArticleTitle(articleTitle);
    }

    public Integer getOrderById(String id) {
        return articleRepository.getOrderById(id);
    }

    public Integer getMaxOrder() {
        return articleRepository.getMaxOrder();
    }
}
