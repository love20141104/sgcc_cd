package com.sgcc.producer;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDao;
import com.sgcc.dao.SuggestionRedisDaos;
import com.sgcc.dto.SuggestionDeleteDTO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SuggestionProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    //在线预约消息队列
    private static final String Suggestion_PersistMQ = "Suggestion_mq_p";
    private static final String Suggestion_SendMsgMQ = "Suggestion_mq_s";
    private static final String Suggestion_CacheAllMQ = "Suggestion_mq_cacheAll";
    private static final String Suggestion_CacheMQ = "Suggestion_mq_cache";
    private static final String Suggestion_ReloadMQ = "Suggestion_mq_r";
    private static final String Suggestion_DeleteMQ = "Suggestion_mq_d";

    public void SaveSuggestionMQ( SuggestionDao dao )
    {
        jmsMessagingTemplate.convertAndSend(Suggestion_PersistMQ,dao);
    }

    public void CacheAllSuggestionsMQ( SuggestionRedisDaos daos )
    {
        jmsMessagingTemplate.convertAndSend(Suggestion_CacheAllMQ,daos);
    }

    public void CacheSuggestionMQ( SuggestionRedisDao dao )
    {
        jmsMessagingTemplate.convertAndSend(Suggestion_CacheMQ,dao);
    }

    public void ReloadSuggestionsMQ( String userId )
    {
        jmsMessagingTemplate.convertAndSend(Suggestion_ReloadMQ,userId);
    }

    public void DeleteSuggestionMQ( SuggestionDeleteDTO dto )
    {
        jmsMessagingTemplate.convertAndSend(Suggestion_DeleteMQ,dto );
    }
    // TODO 到时候再说消息体
    public void SendMsgMQ( Object dao )
    {
        jmsMessagingTemplate.convertAndSend(Suggestion_SendMsgMQ,dao);
    }
}
