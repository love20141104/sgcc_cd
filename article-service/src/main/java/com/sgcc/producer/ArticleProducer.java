package com.sgcc.producer;

import com.sgcc.dao.ArticleDao;
import com.sgcc.dao.ArticleDaos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ArticleProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    private static final String Article_CacheAllMQ = "Article_mq_cacheAll";
    private static final String Article_UpdateMQ = "Article_mq_update";

    public void CacheAllArticlesMQ( ArticleDaos dao )
    {
        jmsMessagingTemplate.convertAndSend( Article_CacheAllMQ,dao );
    }

    public void UpdateArticlesMQ( ArticleDao dao )
    {
        jmsMessagingTemplate.convertAndSend( Article_UpdateMQ,dao );
    }
}
