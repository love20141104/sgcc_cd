package com.sgcc.service;

import com.example.result.Result;
import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDao;
import com.sgcc.dto.SuggestionDetailDTO;
import com.sgcc.dto.SuggestionUpdateDTO;
import com.sgcc.dto.SuggestionViewDTO;
import com.sgcc.dto.SuggestionSubmitDTO;
import com.sgcc.entity.event.SuggestionQueryEntity;
import com.sgcc.entity.query.SuggestionEventEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.SuggestionModel;
import com.sgcc.producer.SuggestionProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SuggestionService {
    @Autowired
    private SuggestionQueryEntity suggestionQueryEntity;

    @Autowired
    private SuggestionEventEntity suggestionEventEntity;
    @Autowired
    private SuggestionProducer suggestionProducer;

    public Result getSuggestion( String suggestionId ) {
        SuggestionModel model = new SuggestionModel( );
        // 读Redis
        SuggestionRedisDao redisDao = suggestionQueryEntity.GetRedisSuggestion(suggestionId);
        if( redisDao != null ){
            return Result.success( model.RedisDAO2DetailDTO(redisDao) );
        }

        // 读MySQL
        SuggestionDao dao = suggestionQueryEntity.GetSuggestion(suggestionId);
        if( dao == null )
            return Result.failure(TopErrorCode.NO_DATAS);

        List<SuggestionDao> daos = new ArrayList<>();
        daos.add(dao);
        // 存Redis
        suggestionProducer.CacheSuggestionMQ( model.ListDao2RedisDaos(daos) );

        return Result.success( model.RedisDAO2DetailDTO(model.Dao2RedisDao(dao)) );
    }

    public List<SuggestionViewDTO> getSuggestions( String openId ) {
        SuggestionModel model = new SuggestionModel( );
        // 读Redis
        List<SuggestionRedisDao>  redisDaos = suggestionQueryEntity.GetAllsuggestions(openId);
        if( redisDaos.size() > 0 ){
            return model.RedisDAOS2DTOS(redisDaos);
        }

        // 读MySQL
        List<SuggestionDao> daos = suggestionQueryEntity.GetAllSuggestions(openId);
        if( daos == null || daos.size() < 1 )
            return null;

        // 存Redis
        suggestionProducer.CacheSuggestionMQ( model.ListDao2RedisDaos(daos) );

        return model.DAOS2DTOS(daos);
    }

    public Result submit(SuggestionSubmitDTO submitDTO, String openId) {
        SuggestionModel model = new SuggestionModel( submitDTO );
        SuggestionDao dao = model.DTO2DAO();
        List<SuggestionDao> daos = new ArrayList<>();
        daos.add(dao);
        suggestionProducer.CacheSuggestionMQ( model.ListDao2RedisDaos(daos) );
        suggestionProducer.SaveSuggestionMQ( dao );
        return Result.success(getSuggestions(openId));
    }

    public Result update(SuggestionUpdateDTO updateDTO ) {
        SuggestionModel model = new SuggestionModel( updateDTO );
        // 写MySQL
        SuggestionDao dao = suggestionEventEntity.Update(updateDTO.getReplyUserId()
                ,updateDTO.getReplyContent(),new Date(),updateDTO.getSuggestionId());
        if( dao == null )
            return null;

        List<SuggestionDao> daos = new ArrayList<>();
        daos.add(dao);
        // 存Redis
        suggestionProducer.CacheSuggestionMQ( model.ListDao2RedisDaos(daos) );

        return Result.success( model.DAO2DTO(dao) );
    }
}

