package com.sgcc.amqp.consumer;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDaos;
import com.sgcc.entity.query.SuggestionEventEntity;
import com.sgcc.model.SuggestionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class SuggestionComsumer {

    @Autowired
    private SuggestionEventEntity suggestionEventEntity;

    @JmsListener(destination = "Suggestion_mq_p")
    public void Save( SuggestionDao dao ){
        try{
            // Todo 下载

            // Todo 更新Dao

            // Todo 持久化
            suggestionEventEntity.Save(dao);
        }catch (Exception e){
            e.printStackTrace();
        }
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
