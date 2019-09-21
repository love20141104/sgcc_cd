package com.sgcc.amqp.consumer;

import com.sgcc.dao.PreBookDao;
import com.sgcc.dto.PrebookInfoSaveDTO;
import com.sgcc.entity.PrebookEntity;
import com.sgcc.entity.event.PrebookEventEntity;
import com.sgcc.entity.query.PrebookQueryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 在线预约消费者
 */
@Component
public class PrebookConsumer {

    @Autowired
    private PrebookQueryEntity prebookQueryEntity;

    @Autowired
    private PrebookEventEntity prebookEventEntity;

    @JmsListener(destination = "prebook_mq")
    public void savePrebook(PreBookDao preBookDao){
        try{
            prebookEventEntity.savePrebooks(
                    preBookDao
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
