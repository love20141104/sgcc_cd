package com.sgcc.amqp.consumer;

import com.sgcc.dao.ApiStatisticsDao;
import com.sgcc.service.ApiStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ApiStatisticsConsumer {
    @Autowired
    private ApiStatisticsService apiStatisticsService;

    @JmsListener(destination = "apiStatistics_mq")
    public void saveApiStatistics(ApiStatisticsDao apiStatisticsDao){
        apiStatisticsService.saveApiStatistics(apiStatisticsDao);
    }
}
