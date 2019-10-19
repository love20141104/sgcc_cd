package com.sgcc.amqp.consumer;

import com.sgcc.dao.ArticleDao;
import com.sgcc.dao.ArticleDaos;
import com.sgcc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class ArticleConsumer {
    @Autowired
    private ArticleService articleService;

    @JmsListener(destination = "Article_mq_cacheAll")
    public void Save( ArticleDaos dao ){
        try{
            articleService.Initialize( dao );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @JmsListener(destination = "Article_mq_update")
    public void Update( ArticleDao dao ){
        try{
            articleService.UpdateCache( dao );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
