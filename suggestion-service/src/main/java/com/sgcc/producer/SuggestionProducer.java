package com.sgcc.producer;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDao;
import com.sgcc.dao.SuggestionRedisDaos;
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
    private static final String Suggestion_CacheMQ = "Suggestion_mq_c";

    public void SaveSuggestionMQ( SuggestionDao dao )
    {
        jmsMessagingTemplate.convertAndSend(Suggestion_PersistMQ,dao);
    }

    public void CacheSuggestionMQ( SuggestionRedisDaos daos )
    {
        jmsMessagingTemplate.convertAndSend(Suggestion_CacheMQ,daos);
    }
    // TODO 到时候再说消息体
    public void SendMsgMQ( Object dao )
    {
        jmsMessagingTemplate.convertAndSend(Suggestion_SendMsgMQ,dao);
    }
}
