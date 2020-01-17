package com.sgcc.amqp.consumer;

import com.example.Utils;
import com.sgcc.dao.CheckerInfoDao;
import com.sgcc.dao.PreBookDao;
import com.sgcc.dto.PrebookInfoSaveDTO;
import com.sgcc.dto.SendMsgMQDTO;
import com.sgcc.dtomodel.wechat.template.TemplateData;
import com.sgcc.dtomodel.wechat.template.TemplateMessage;
import com.sgcc.entity.PrebookEntity;
import com.sgcc.entity.event.PrebookEventEntity;
import com.sgcc.entity.query.PrebookQueryEntity;
import com.sgcc.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 在线预约消费者
 */
@Component
public class PrebookConsumer {

    @Autowired
    private PrebookQueryEntity prebookQueryEntity;

    @Autowired
    private PrebookEventEntity prebookEventEntity;

    @Autowired
    private WeChatService weChatService;

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

    @JmsListener(destination = "taxticket_MQ")
    public void sendMessage(SendMsgMQDTO sendMsgMQDTO){
        try{
            // 推送消息给审核人
            TemplateMessage temp = new TemplateMessage();
            temp.setTemplate_id("Ng58N4cns1ekU9KVMh_MxThDJHQHVLGhSIsUWHnPoV4");
            temp.setTouser( sendMsgMQDTO.getCheckerOpenId() );
            temp.setUrl("https://sgcc.link/appointmentList");
            Map<String, TemplateData> map = new LinkedHashMap<>();
            map.put("first",new TemplateData("您的客户预约已取消!","#000000"));
            map.put("keyword1",new TemplateData(sendMsgMQDTO.getUserName(),"#000000"));
            map.put("keyword2",new TemplateData(sendMsgMQDTO.getCancelDate(),"#000000"));
            map.put("remark",new TemplateData("谢谢使用!","#000000"));
            temp.setData( map );
            weChatService.SimpleSendMsg( temp );
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
