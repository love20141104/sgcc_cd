package com.sgcc.model;

import com.example.Utils;
import com.sgcc.dao.ArticleDao;
import com.sgcc.dao.ArticleDaos;
import com.sgcc.dao.ArticleRedisDao;
import com.sgcc.dao.ArticleRedisDaos;
import com.sgcc.dto.ArticleMappingDTO;
import com.sgcc.dto.ArticleSubmitDTO;
import com.sgcc.dto.ArticleUpdateDTO;
import com.sgcc.dto.ArticleViewDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArticleModel {
    public ArticleRedisDao dao2redisdao( ArticleDao dao )
    {
        if( dao == null )
            return null;
        ArticleRedisDao redisDao = new ArticleRedisDao();
        redisDao.setId( dao.getId() );
        redisDao.setArticle_title( dao.getArticle_title() );
        redisDao.setArticle_desc( dao.getArticle_desc() );
        redisDao.setArticle_img( dao.getArticle_img() );
        redisDao.setArticle_recommended( dao.isArticle_recommended() );
        redisDao.setArticle_url( dao.getArticle_url() );
        redisDao.setArticle_type( dao.getArticle_type() );
        return redisDao;
    }
    public List<ArticleRedisDao> daos2listredisdao( List<ArticleDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<ArticleRedisDao> redisDaos = new ArrayList<>();
        for( ArticleDao dao : daos )
        {
            redisDaos.add(dao2redisdao(dao));
        }
        return redisDaos;
    }
    public ArticleRedisDaos daos2redisdaos(List<ArticleDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;
        ArticleRedisDaos redisDaos = new ArticleRedisDaos();
        redisDaos.setArticleRedisDaoList(daos2listredisdao(daos));
        return redisDaos;
    }
    public ArticleViewDTO dao2viewdto( ArticleDao dao )
    {
        if( dao == null )
            return null;
        ArticleViewDTO dto = new ArticleViewDTO();
        dto.setId( dao.getId() );
        dto.setArticle_title( dao.getArticle_title() );
        dto.setArticle_desc( dao.getArticle_desc() );
        dto.setArticle_img( dao.getArticle_img() );
        dto.setArticle_recommended( dao.isArticle_recommended() );
        dto.setArticle_url( dao.getArticle_url() );
        dto.setArticle_type( dao.getArticle_type() );
        return dto;
    }
    public ArticleViewDTO redisdao2viewdto( ArticleRedisDao redisDao )
    {
        if( redisDao == null )
            return null;
        ArticleViewDTO dto = new ArticleViewDTO();
        dto.setId( redisDao.getId() );
        dto.setArticle_title( redisDao.getArticle_title() );
        dto.setArticle_desc( redisDao.getArticle_desc() );
        dto.setArticle_img( redisDao.getArticle_img() );
        dto.setArticle_recommended( redisDao.isArticle_recommended() );
        dto.setArticle_url( redisDao.getArticle_url() );
        dto.setArticle_type( redisDao.getArticle_type() );
        return dto;
    }
    public List<ArticleViewDTO> daos2listviewdto( List<ArticleDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<ArticleViewDTO> viewDTOS = new ArrayList<>();
        for( ArticleDao dao : daos )
        {
            viewDTOS.add(dao2viewdto(dao));
        }
        return viewDTOS;
    }
    public List<ArticleViewDTO> redisdaos2listviewdto( List<ArticleRedisDao> redisDaos )
    {
        if( redisDaos == null || redisDaos.size() < 1 )
            return null;
        List<ArticleViewDTO> viewDTOS = new ArrayList<>();
        for( ArticleRedisDao redisdao : redisDaos )
        {
            viewDTOS.add(redisdao2viewdto(redisdao));
        }
        return viewDTOS;
    }
    public ArticleDaos listdao2daos(List<ArticleDao> listdao )
    {
        if( listdao == null || listdao.size() < 1 )
            return null;
        ArticleDaos daos = new ArticleDaos();
        daos.setArticleRedisDaoList(listdao);
        return daos;
    }
    public ArticleDao submitdto2dao(ArticleSubmitDTO dto )
    {
        if( dto == null )
            return null;
        ArticleDao dao = new ArticleDao();
        dao.setId( UUID.randomUUID().toString() );
        dao.setArticle_title( dto.getArticle_title() );
        dao.setArticle_desc( dto.getArticle_desc() );
        dao.setArticle_img( dto.getArticle_img() );
        dao.setArticle_recommended( dto.isArticle_recommended() );
        dao.setArticle_url( dto.getArticle_url() );
        dao.setArticle_type( dto.getArticle_type() );
        dao.setSubmitDate( Utils.GetCurTime() );
        return dao;
    }
    public ArticleDao updatedto2dao( ArticleUpdateDTO dto )
    {
        if( dto == null )
            return null;
        ArticleDao dao = new ArticleDao();
        dao.setId( dto.getId() );
        dao.setArticle_title( dto.getArticle_title() );
        dao.setArticle_desc( dto.getArticle_desc() );
        dao.setArticle_img( dto.getArticle_img() );
        dao.setArticle_recommended( dto.isArticle_recommended() );
        dao.setArticle_url( dto.getArticle_url() );
        dao.setArticle_type( dto.getArticle_type() );
        dao.setSubmitDate( Utils.GetCurTime() );
        return dao;
    }
    public ArticleMappingDTO dao2listmappingdto(ArticleDao dao ){
        if( dao == null )
            return null;
        ArticleMappingDTO dto = new ArticleMappingDTO();
        dto.setId( dao.getId() );
        dto.setArticle_title( dao.getArticle_title() );
        dto.setArticle_desc( dao.getArticle_desc() );
        dto.setArticle_img( dao.getArticle_img() );
        dto.setArticle_recommended( dao.isArticle_recommended() );
        dto.setArticle_url( dao.getArticle_url() );
        dto.setArticle_type( dao.getArticle_type() );
        dto.setSubmitDate( dao.getSubmitDate() );
        return  dto;
    }

    public List<ArticleMappingDTO> daos2listmappingdtos(List<ArticleDao> daos ){
        if( daos == null || daos.size() < 1 )
            return null;
        List<ArticleMappingDTO> mappingDTOS = new ArrayList<>();
        for( ArticleDao dao : daos )
        {
            mappingDTOS.add(dao2listmappingdto(dao));
        }
        return mappingDTOS;
    }
}
