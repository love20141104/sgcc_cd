package com.sgcc.entity.event;

import com.sgcc.dao.ArticleDao;
import com.sgcc.dao.ArticleRedisDao;
import com.sgcc.repository.ArticleRedisRepository;
import com.sgcc.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ArticleEventEntity {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleRedisRepository articleRedisRepository;

    public void Cache( ArticleRedisDao dao )
    {
        articleRedisRepository.save(dao);
    }
    public void CacheAll( List<ArticleRedisDao> daos )
    {
        articleRedisRepository.saveAll(daos);
    }

    public void deleteCache( List<String> ids)
    {
        List<ArticleRedisDao> rdaos = articleRedisRepository.findAllByIdExists(ids);
        articleRedisRepository.deleteAll( rdaos );
    }
    public void update( ArticleDao dao)
    {
        articleRepository.update(dao);
    }

    public void save( ArticleDao dao)
    {
        articleRepository.save(dao);
    }

    public void deletes( List<String> articleIds)
    {
        articleRepository.deleteAll( articleIds );
    }

    @Transactional
    public void updateOrderById(String upId,String downId) {
        Integer orderByupId = articleRepository.getOrderById(upId);
        Integer orderBydownId = articleRepository.getOrderById(downId);
        articleRepository.updateOrderById(upId, orderBydownId);
        articleRepository.updateOrderById(downId, orderByupId);
    }
}
