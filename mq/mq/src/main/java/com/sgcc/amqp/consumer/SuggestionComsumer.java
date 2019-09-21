package com.sgcc.amqp.consumer;

import com.google.common.base.Strings;
import com.sgcc.FastDFSClient.FastDFSClient;
import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDaos;
import com.sgcc.entity.WeChatEntity;
import com.sgcc.entity.query.SuggestionEventEntity;
import com.sgcc.model.SuggestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SuggestionComsumer {
    @Autowired
    private WeChatEntity weChatEntity;
    @Autowired
    private SuggestionEventEntity suggestionEventEntity;

    @JmsListener(destination = "Suggestion_mq_p")
    public void Save( SuggestionDao dao ){
        try{
            // Todo 下载
            dao.setImg_1(uploadFile(dao.getImg_1())); ;
            dao.setImg_2(uploadFile(dao.getImg_2())); ;
            dao.setImg_3(uploadFile(dao.getImg_3())); ;
            // Todo 持久化
            suggestionEventEntity.Save(dao);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private String uploadFile( String mediaId )
    {
        if( Strings.isNullOrEmpty(mediaId) )
        {
            return "";
        }
        byte[] ctnt = weChatEntity.downloadMedia(mediaId);
        if( ctnt == null ||ctnt.length < 1 )
        {
            return "";
        }
        return new FastDFSClient().uploadFile( new String(ctnt) ,".jpg");
    }

    @JmsListener(destination = "Suggestion_mq_s")
    public void sendMsg( SuggestionDao dao ){
        // Todo
    }

    @JmsListener(destination = "Suggestion_mq_c")
    public void Cache( SuggestionRedisDaos sugst ){
        // Todo
        suggestionEventEntity.SaveAll(sugst.getSuggestionRedisDaoList());
    }
}
