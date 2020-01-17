package com.sgcc.producer;

import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PreBookDao;
import com.sgcc.dao.PrebookInfoDao;
import com.sgcc.dto.PrebookInfoSaveDTO;
import com.sgcc.dto.SendMsgMQDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 在线预约生产者
 */
@Service
public class PrebookProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    //在线预约消息队列
    private static final String prebook_MQ = "prebook_mq";
    private static final String taxticket_MQ = "taxticket_MQ";

    public void prebookMQ(PreBookDao preBookDao) {
        jmsMessagingTemplate.convertAndSend(prebook_MQ, preBookDao);
    }

    public void sendMessage(SendMsgMQDTO sendMsgMQDTO) {
        jmsMessagingTemplate.convertAndSend(taxticket_MQ, sendMsgMQDTO);
    }



}
