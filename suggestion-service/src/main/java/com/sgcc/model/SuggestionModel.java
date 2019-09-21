package com.sgcc.model;

import com.sgcc.dao.SuggestionDao;
import com.sgcc.dao.SuggestionRedisDao;
import com.sgcc.dao.SuggestionRedisDaos;
import com.sgcc.dto.SuggestionDetailDTO;
import com.sgcc.dto.SuggestionUpdateDTO;
import com.sgcc.dto.SuggestionViewDTO;
import com.sgcc.dto.SuggestionSubmitDTO;
import com.sgcc.entity.event.SuggestionQueryEntity;
import com.sgcc.entity.query.SuggestionEventEntity;
import com.sgcc.producer.SuggestionProducer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class SuggestionModel {
    private List<SuggestionDao> m_suggestionDaos = null;
    private List<SuggestionViewDTO> m_suggestionViewDTO = null;
    private SuggestionSubmitDTO m_suggestionSubmitDTO = null;
    private SuggestionUpdateDTO m_suggestionUpdatetDTO = null;
    private String m_userId = null;
    private String m_errMsg = "";
    @Autowired
    private SuggestionQueryEntity suggestionQueryEntity;

    @Autowired
    private SuggestionEventEntity suggestionEventEntity;

    @Autowired
    private SuggestionProducer suggestionProducer;

    public SuggestionModel( SuggestionSubmitDTO dto  ){
        m_suggestionSubmitDTO = dto;
    }
    public SuggestionModel( SuggestionUpdateDTO updateDto  ){
        m_suggestionUpdatetDTO = updateDto;
    }
    public SuggestionModel(SuggestionSubmitDTO dto , List<SuggestionDao> daos){
        m_suggestionSubmitDTO = dto;
        m_suggestionDaos = daos;
    }
    public SuggestionModel( List<SuggestionDao> daos ){
        m_suggestionDaos = daos;
    }
    public SuggestionModel( String userId ){
        m_userId = userId;
    }

    public List<SuggestionViewDTO> DAOS2DTOS( List<SuggestionDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;

        List<SuggestionViewDTO> dtos = new ArrayList<>();
        for( SuggestionDao dao : daos ){
            dtos.add(DAO2DTO(dao));
        }
        return dtos;
    }
    public SuggestionViewDTO DAO2DTO( SuggestionDao dao )
    {
        if( dao == null )
            return null;

        SuggestionViewDTO dto = new SuggestionViewDTO();
        dto.setSuggestionId(dao.getSuggestionId());
        dto.setSuggestionContent( dao.getSuggestionContent());
        dto.setImg_1( dao.getImg_1());
        dto.setImg_2( dao.getImg_2());
        dto.setImg_3( dao.getImg_3());
        dto.setSuggestionReply(dao.getReplyContent());
        return dto;
    }
    public List<SuggestionViewDTO> RedisDAOS2DTOS( List<SuggestionRedisDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;

        List<SuggestionViewDTO> dtos = new ArrayList<>();
        for( SuggestionRedisDao dao : daos ){
            dtos.add(RedisDAO2DTO(dao));
        }
        return dtos;
    }
    public SuggestionViewDTO RedisDAO2DTO( SuggestionRedisDao dao )
    {
        if( dao == null )
            return null;

        SuggestionViewDTO dto = new SuggestionViewDTO();
        dto.setSuggestionId(dao.getSuggestionId());
        dto.setSuggestionContent( dao.getSuggestionContent());
        dto.setImg_1( dao.getImg_1());
        dto.setMedia_1( dao.getMediaId_1());
        dto.setImg_2( dao.getImg_2());
        dto.setMedia_2( dao.getMediaId_2());
        dto.setImg_3( dao.getImg_3());
        dto.setMedia_3( dao.getMediaId_3());
        dto.setSuggestionReply(dao.getReplyContent());
        return dto;
    }
    public SuggestionRedisDao Dao2RedisDao( SuggestionDao dao )
    {
        if( dao == null )
            return null;
        SuggestionRedisDao rdao = new SuggestionRedisDao();
        rdao.setId(dao.getId());
        rdao.setImg_1(dao.getImg_1());
        rdao.setImg_2(dao.getImg_2());
        rdao.setImg_3(dao.getImg_3());
        rdao.setSubmitDate(dao.getSubmitDate());
        rdao.setSuggestionContact(dao.getSuggestionContact());
        rdao.setSuggestionContent(dao.getSuggestionContent());
        rdao.setSuggestionId(dao.getSuggestionId());
        rdao.setSuggestionTel(dao.getSuggestionTel());
        rdao.setUserId(dao.getUserId());
        rdao.setReplyUserId(dao.getReplyUserId());
        rdao.setReplyDate(dao.getReplyDate());
        rdao.setReplyContent(dao.getReplyContent());
        return rdao;
    }
    public List<SuggestionRedisDao> ListDao2ListRedisDao(List<SuggestionDao> daos )
    {
        if( daos == null || daos.size() < 1 )
            return null;
        List<SuggestionRedisDao> list = new ArrayList<>();
        for( SuggestionDao dao:daos)
            list.add(Dao2RedisDao(dao));

        return list;
    }
    public SuggestionRedisDaos ListDao2RedisDaos(List<SuggestionDao> daos )
    {
        SuggestionRedisDaos ret = new SuggestionRedisDaos();
        ret.setSuggestionRedisDaoList(  ListDao2ListRedisDao(daos) );
        return ret;
    }
    public SuggestionDao DTO2DAO( SuggestionSubmitDTO dto )
    {
        if( dto == null )
            return null;

        SuggestionDao dao = new SuggestionDao();
        dao.setId(UUID.randomUUID().toString());
        dao.setSuggestionId(dao.getId());
        dao.setSuggestionTel(dto.getSuggestionTel());
        dao.setSuggestionContact(dto.getSuggestionContact());
        dao.setSuggestionContent(dto.getSuggestionContent());
        dao.setUserId(dto.getUserId());
        dao.setSubmitDate(new Date());

        if( !dto.getMedia_1().equals("") )
            dao.setImg_1(dto.getMedia_1());
        else
            dao.setImg_1("");

        if( !dto.getMedia_2().equals("") )
            dao.setImg_2(dto.getMedia_2());
        else
            dao.setImg_2("");

        if( !dto.getMedia_3().equals("") )
            dao.setImg_3(dto.getMedia_3());
        else
            dao.setImg_3("");

        return dao;
    }
    public SuggestionDetailDTO RedisDAO2DetailDTO(SuggestionRedisDao dao)
    {
        if( dao == null )
            return null;
        SuggestionDetailDTO dto = new SuggestionDetailDTO();
        dto.setId(dao.getId());
        dto.setSuggestionId( dao.getSuggestionId());
        dto.setSuggestionContent( dao.getSuggestionContent());
        dto.setSuggestionContact( dao.getSuggestionContact());
        dto.setSuggestionTel( dao.getSuggestionTel());
        dto.setSubmitDate( dao.getSubmitDate());
        dto.setImg_1( dao.getImg_1());
        dto.setMedia_1( dao.getMediaId_1());
        dto.setImg_2( dao.getImg_2());
        dto.setMedia_2( dao.getMediaId_2());
        dto.setImg_3( dao.getImg_3());
        dto.setMedia_3( dao.getMediaId_3());
        dto.setReplyContent(dao.getReplyContent());
        dto.setReplyDate(dao.getReplyDate());
        return dto;
    }

    public List<SuggestionViewDTO> submit()
    {
        SuggestionDao dao = DTO2DAO(m_suggestionSubmitDTO);
        if( dao == null ){
            return null;
        }
        m_userId = dao.getUserId();
        SuggestionRedisDao redisDao = Dao2RedisDao(dao);
        suggestionEventEntity.Save(redisDao);
        suggestionProducer.SaveSuggestionMQ(dao);
        return GetAllSuggestions();
    }

    public List<SuggestionViewDTO> GetAllSuggestions( )
    {
        // 读Redis
        List<SuggestionRedisDao>  redisDaos = suggestionQueryEntity.GetAllsuggestions(m_userId);
        if( redisDaos.size() > 0 ){
            return RedisDAOS2DTOS(redisDaos);
        }

        // 读MySQL
        List<SuggestionDao> daos = suggestionQueryEntity.GetAllSuggestions(m_userId);
        if( daos == null || daos.size() < 1 )
            return null;

        // 存Redis
        suggestionProducer.CacheSuggestionMQ( ListDao2RedisDaos(daos) );

        return DAOS2DTOS(daos);
    }

    public SuggestionDetailDTO GetSuggestion(String suggestionId)
    {
        // 读Redis
        SuggestionRedisDao redisDao = suggestionQueryEntity.GetRedisSuggestion(suggestionId);
        if( redisDao != null ){
            return RedisDAO2DetailDTO(redisDao);
        }

        // 读MySQL
        SuggestionDao dao = suggestionQueryEntity.GetSuggestion(suggestionId);
        if( dao == null )
            return null;

        List<SuggestionDao> daos = new ArrayList<>();
        daos.add(dao);
        // 存Redis
        suggestionProducer.CacheSuggestionMQ( ListDao2RedisDaos(daos) );

        return RedisDAO2DetailDTO(Dao2RedisDao(dao));
    }

    public SuggestionViewDTO reply()
    {
        String suggestionId = m_suggestionUpdatetDTO.getSuggestionId();

        // 写MySQL
        SuggestionDao dao = suggestionEventEntity.Update(m_suggestionUpdatetDTO.getUserId()
                ,m_suggestionUpdatetDTO.getReplyContent(),new Date(),suggestionId);
        if( dao == null )
            return null;

        List<SuggestionDao> daos = new ArrayList<>();
        daos.add(dao);
        // 存Redis
        suggestionProducer.CacheSuggestionMQ( ListDao2RedisDaos(daos) );

        return DAO2DTO(dao);
    }
}
