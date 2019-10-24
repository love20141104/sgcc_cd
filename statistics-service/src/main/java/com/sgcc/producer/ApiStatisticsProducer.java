package com.sgcc.producer;

import com.sgcc.dao.ApiStatisticsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ApiStatisticsProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    //在线预约消息队列
    private static final String apiStatistics_MQ = "apiStatistics_mq";

    public void apiStatisticsMQ(ApiStatisticsDao apiStatistics) {
        jmsMessagingTemplate.convertAndSend(apiStatistics_MQ, apiStatistics);
    }


}
