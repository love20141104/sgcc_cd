package com.sgcc.amqp.consumer;

import com.sgcc.service.BusinessGuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class BusinessGuideConsumer {
    @Autowired
    private BusinessGuideService businessGuideService;

    @JmsListener(destination = "BusinessGuide_mq_p")
    public void initRedisBusinessGuide(String str){
        System.out.println(str);
        try{
            businessGuideService.initRedisBusinessGuide();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
