package com.sgcc.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class BusinessGuideProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    //在线预约消息队列
    private static final String BusinessGuide_PersistMQ = "BusinessGuide_mq_p";

    public void CacheAllSuggestionsMQ()
    {
        jmsMessagingTemplate.convertAndSend(BusinessGuide_PersistMQ,"initRedisBusinessGuide");
    }
}
