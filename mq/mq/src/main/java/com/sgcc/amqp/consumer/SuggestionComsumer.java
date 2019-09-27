package com.sgcc.amqp.consumer;

import com.google.common.base.Strings;
import com.sgcc.FastDFSClient.FastDFSClient;
import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDaos;
import com.sgcc.dao.SuggestionRedisDao;
import com.sgcc.dto.SuggestionDeleteDTO;
import com.sgcc.entity.WeChatEntity;
import com.sgcc.service.SuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class SuggestionComsumer {
    @Autowired
    private WeChatEntity weChatEntity;
    @Autowired
    private SuggestionService suggestionService;
    @Autowired
    private FastDFSClient fastDFSClient;

    @JmsListener(destination = "Suggestion_mq_p")
    public void Save( SuggestionDao dao ){
        try{
            // 下载
            dao.setImg_1(uploadFile(dao.getImg_1()));
            dao.setImg_2(uploadFile(dao.getImg_2()));
            dao.setImg_3(uploadFile(dao.getImg_3()));
            // 持久化
            suggestionService.SaveSuggestion(dao);
            // 同步 Redis
            suggestionService.ReloadSuggestions( dao.getUserId() );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //TODO 上传文件修改
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
        return fastDFSClient.uploadFile( new String(ctnt) ,".jpg");
    }

    @JmsListener(destination = "Suggestion_mq_s")
    public void sendMsg( SuggestionDao dao ){
        // Todo
    }

    @JmsListener(destination = "Suggestion_mq_cacheAll")
    public void CacheAll( SuggestionRedisDaos daos ){
        suggestionService.CacheSuggestions( daos );
    }
    @JmsListener(destination = "Suggestion_mq_cache")
    public void Cache( SuggestionRedisDao dao ){
        suggestionService.CacheSuggestion( dao );
    }
    @JmsListener(destination = "Suggestion_mq_r")
    public void Reload( String userId ){
        suggestionService.ReloadSuggestions( userId );
    }
    @JmsListener(destination = "Suggestion_mq_d")
    public void Delete( SuggestionDeleteDTO dto ){
        // Todo
        suggestionService.DeleteSuggestions( dto.getSuggestionIds() );
    }

}
