package com.sgcc.service;

import com.sgcc.dao.ArticleDao;
import com.sgcc.dao.ArticleDaos;
import com.sgcc.dao.ArticleRedisDao;
import com.sgcc.dao.ArticleRedisDaos;
import com.sgcc.dto.ArticleMappingDTO;
import com.sgcc.dto.ArticleSubmitDTO;
import com.sgcc.dto.ArticleUpdateDTO;
import com.sgcc.dto.ArticleViewDTO;
import com.sgcc.entity.event.ArticleEventEntity;
import com.sgcc.entity.query.ArticleQueryEntity;
import com.sgcc.model.ArticleModel;
import com.sgcc.producer.ArticleProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleProducer articleProducer;
    @Autowired
    private ArticleQueryEntity articleQueryEntity;
    @Autowired
    private ArticleEventEntity articleEventEntity;

    public void Initialize( ArticleDaos daos ){
        List<ArticleDao> listdao = new ArrayList<>();
        if( daos != null && daos.getArticleRedisDaoList() != null && daos.getArticleRedisDaoList().size() > 0 )
            listdao = daos.getArticleRedisDaoList();
        else
            listdao = articleQueryEntity.getArticles();

        if( listdao == null || listdao.size() < 1 )
            return;
        ArticleModel model = new ArticleModel();
        List<ArticleRedisDao> redisdaos = model.daos2listredisdao(listdao);
        articleEventEntity.CacheAll(redisdaos);
    }

    public void UpdateCache( ArticleDao dao ){
        if( dao == null )
            return;
        ArticleModel model = new ArticleModel();
        ArticleRedisDao redisdao = model.dao2redisdao(dao);
        articleEventEntity.Cache(redisdao);
    }
    // 后台管理使用
    public List<ArticleMappingDTO> GetAllArticles()
    {
        ArticleModel model = new ArticleModel();
        List<ArticleDao> daos = articleQueryEntity.getArticles();
        if( daos == null || daos.size() < 1 )
        {
            return null;
        }
        return model.daos2listmappingdtos(daos);
    }
    // 后台管理使用
    public List<ArticleMappingDTO> GetArticlesByRecommended(boolean isRecommended)
    {
        ArticleModel model = new ArticleModel();
        List<ArticleDao> daos = articleQueryEntity.getArticles(isRecommended);
        if( daos == null || daos.size() < 1 )
        {
            return null;
        }
        return model.daos2listmappingdtos(daos);
    }
    // 后台管理使用
    public List<ArticleMappingDTO> GetArticlesByArticleType(String article_type)
    {
        ArticleModel model = new ArticleModel();
        List<ArticleDao> daos = articleQueryEntity.getArticles(article_type);
        if( daos == null || daos.size() < 1 )
        {
            return null;
        }
        return model.daos2listmappingdtos(daos);
    }
    // 微信前端使用
    public List<ArticleViewDTO> GetArticles(String article_type)
    {
        ArticleModel model = new ArticleModel();
        List<ArticleRedisDao> redisdaos = articleQueryEntity.getRedisArticles(article_type);
        if( redisdaos != null && redisdaos.size() > 0 )
        {
            return model.redisdaos2listviewdto(redisdaos);
        }
        List<ArticleDao> daos = articleQueryEntity.getArticles(article_type);
        if( daos == null || daos.size() < 1 )
        {
            return null;
        }
        articleProducer.CacheAllArticlesMQ( model.listdao2daos(daos) );
        return model.daos2listviewdto(daos);
    }
    // 微信前端使用
    public List<ArticleViewDTO> GetArticles(boolean isRecommended)
    {
        ArticleModel model = new ArticleModel();
        List<ArticleRedisDao> redisdaos = articleQueryEntity.getRedisArticles(isRecommended);
        if( redisdaos != null && redisdaos.size() > 0 )
        {
            return model.redisdaos2listviewdto(redisdaos);
        }
        List<ArticleDao> daos = articleQueryEntity.getArticles(isRecommended);
        if( daos == null || daos.size() < 1 )
        {
            return null;
        }
        articleProducer.CacheAllArticlesMQ( model.listdao2daos(daos) );
        return model.daos2listviewdto(daos);
    }

    public void submit( ArticleSubmitDTO dto )
    {
        ArticleModel model = new ArticleModel();
        ArticleDao dao = model.submitdto2dao(dto);
        if( dao == null )
            return;

        articleEventEntity.save(dao);
        articleEventEntity.Cache(model.dao2redisdao(dao));
    }

    public void update( ArticleUpdateDTO dto )
    {
        ArticleModel model = new ArticleModel();
        ArticleDao dao = model.updatedto2dao(dto);
        if( dao == null )
            return;

        articleEventEntity.update(dao);
        articleProducer.CacheAllArticlesMQ( new ArticleDaos() );
    }

    public void deletes( List<String> articleIds )
    {
        if( articleIds == null || articleIds.size() < 1 )
            return;
        articleEventEntity.deletes(articleIds);
        Initialize(null);
    }
}
