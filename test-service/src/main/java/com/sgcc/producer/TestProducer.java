package com.sgcc.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    //测试用消息队列
    private static final String TEST_MQ = "test_mq";

    public void testMQ(String id){
        jmsMessagingTemplate.convertAndSend(TEST_MQ,id);
    }
}
