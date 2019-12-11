package com.sgcc.service;

import com.example.result.Result;
import com.google.common.base.Strings;
import com.sgcc.dao.*;
import com.sgcc.dto.*;
import com.sgcc.entity.query.SuggestionQueryEntity;
import com.sgcc.entity.event.SuggestionEventEntity;
import com.sgcc.exception.TopErrorCode;
import com.sgcc.model.SuggestionModel;
import com.sgcc.producer.SuggestionProducer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

        // 重新加载数据到 Redis
        // 根据 userId 装载所有的意见列表
        suggestionProducer.ReloadSuggestionsMQ( dao.getUserId() );

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

        // 缓存到Redis
        suggestionProducer.CacheAllSuggestionsMQ( model.ListDao2RedisDaos(daos) );

        return model.DAOS2DTOS(daos);
    }

    public Result submit(SuggestionSubmitDTO submitDTO, String openId) {
        SuggestionModel model = new SuggestionModel( submitDTO );
        SuggestionDao dao = model.DTO2DAO();
        // 缓存到redis
        suggestionEventEntity.Cache( model.Dao2RedisDao(dao) );
        // 异步消息写MySQL，然后刷新 Redis 缓存
        suggestionProducer.SaveSuggestionMQ( dao );
        // 创建回复单据
        CreateSuggestionReply( new SuggestionMidDTO(dao.getSuggestionId() , submitDTO.getUserLocation()) );
        Result ret = Result.success( getSuggestions(openId) );
        ret.setMsg( dao.getSuggestionId() );
        return ret;
    }

    public SuggestionReplyInstDao GetBySuggestionID(String sugstID )
    {
        return suggestionQueryEntity.getInstReply(sugstID);
    }

    public Result reply(SuggestionReplyDTO updateDTO ) {
        SuggestionModel model = new SuggestionModel( updateDTO );
        // 写MySQL
        SuggestionDao dao = suggestionEventEntity.Update(updateDTO.getReplyUserId()
                ,updateDTO.getReplyContent(),new Date(),updateDTO.getSuggestionId());
        if( dao == null )
            return null;
        // 更新 Redis
        suggestionProducer.CacheSuggestionMQ( model.Dao2RedisDao(dao) );

        return Result.success( model.DAO2DTO(dao) );
    }

    public Result update(SuggestionMappingDTO mappingDTO ) {
        SuggestionModel model = new SuggestionModel();
        SuggestionDao dao = model.MapDTO2DAO(mappingDTO);
        // 写MySQL
        dao = suggestionEventEntity.Update(dao);
        if( dao == null )
            return null;
        // 更新 Redis
        suggestionProducer.CacheSuggestionMQ( model.Dao2RedisDao(dao) );

        return Result.success( model.DAO2DTO(dao) );
    }

    public Result delete( SuggestionDeleteDTO dto ) {
        suggestionProducer.DeleteSuggestionMQ(dto);
        suggestionEventEntity.FlushSuggestions( dto.getSuggestionIds() );
        // TODO 返回数据
        return Result.success();
    }

    public void ReloadSuggestions( String userId )
    {
        SuggestionModel model = new SuggestionModel( );
        List<SuggestionDao> daos = suggestionQueryEntity.GetAllSuggestions(userId);
        List<SuggestionRedisDao> redisdaos = model.ListDao2ListRedisDao(daos);
        suggestionEventEntity.CacheAll( redisdaos );
    }
    public void CacheSuggestions( SuggestionRedisDaos daos )
    {
        suggestionEventEntity.CacheAll( daos.getSuggestionRedisDaoList() );
    }
    public void CacheSuggestion( SuggestionRedisDao dao )
    {
        suggestionEventEntity.Cache( dao );
    }
    public void SaveSuggestion( SuggestionDao dao)
    {
        suggestionEventEntity.Save( dao );
    }
    public void DeleteSuggestions(List<String> suggestionIds )
    {
        suggestionEventEntity.DeleteSuggestions(suggestionIds);
    }
    public Result AddSuggestion(SuggestionMappingDTO dto )
    {
        SuggestionModel model = new SuggestionModel();
        // 持久化
        SaveSuggestion( model.MapDTO2DAO(dto) );
        // 同步 Redis
        ReloadSuggestions( dto.getUserId() );
        return Result.success();
    }
    public Result getSuggestions(  )
    {
        try {
            List<SuggestionDao> daos = suggestionQueryEntity.GetSuggestions();
            SuggestionModel model = new SuggestionModel();
            return Result.success(model.DAOs2MapDTOs(daos));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(TopErrorCode.PARAMETER_ERR);
        }
    }
    public void CreateSuggestionReply( SuggestionMidDTO dto )
    {
        try {
            String location = dto.getUser_location();
            List<ReplierAndCheckerDao> daos = suggestionQueryEntity.GetAll();
            SuggestionModel model = new SuggestionModel();
            ReplierAndCheckerDao dao = model.GetFirstMatch(daos,location);
            if( dao == null )
                return;
            suggestionEventEntity.InitReplyID( new SuggestionReplyInitDao(
                    UUID.randomUUID().toString(),
                    dto.getSuggestion_id(),
                    dao.getReplier_openid(),
                    dao.getChecker_openid()
            ) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ReplyContent( SuggestionReplyContentDTO dto )
    {
        SuggestionModel model = new SuggestionModel();
        suggestionEventEntity.ContentReply( model.GetSuggestionReplyDao(dto));
    }
    public void ReplyCheck( SuggestionReplyCheckDTO dto )
    {
        SuggestionModel model = new SuggestionModel();
        suggestionEventEntity.CheckReply( model.GetSuggestionCheckDao(dto));
    }
    public List<ReplierAndCheckerDao> GetReplierAndChecker( String region )
    {
        if( !Strings.isNullOrEmpty( region) )
        {
            return suggestionQueryEntity.findAllByRegion(region) ;
        }
        else
        {
            return suggestionQueryEntity.GetAll() ;
        }
    }
    public Result SaveReplierAndChecker( ReplierAndCheckerSubmitDTO dto )
    {
        ReplierAndCheckerDao dao = new ReplierAndCheckerDao();
        dao.setId(UUID.randomUUID().toString());
        BeanUtils.copyProperties(dto,dao);
        suggestionEventEntity.save(dao);
        return Result.success();
    }
    public Result UpdateReplierAndChecker( ReplierAndCheckerUpdateDTO dto )
    {
        ReplierAndCheckerDao dao = new ReplierAndCheckerDao();
        BeanUtils.copyProperties(dto,dao);
        suggestionEventEntity.update(dao);
        return Result.success();
    }
}

